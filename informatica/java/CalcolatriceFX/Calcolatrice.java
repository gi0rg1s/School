import javafx.application.Application;
import javafx.event.Event;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Calcolatrice extends Application{
	
    private Button[] btns;
    private String[] strs = {"1", "2", "3", "*", 
                             "4", "5", "6", "+", 
                             "7", "8", "9", ",", 
                             "/", "0", "-", "="};
    private TextField result;
    
    // State variables for calculator logic
    private double firstNum = 0;
    private double secondNum = 0;
    private String operation = "";
    private boolean newNumber = true;

	public void start(Stage stage) {

        // Initialize buttons and text field
		btns = new Button[16];
        result = new TextField();
        result.getStyleClass().add("textspace");

        // Set up layout
        BorderPane root = new BorderPane();
        root.getStyleClass().add("backgournd");
        BorderPane txt = new BorderPane();

        // Close button
        Button close_btn = new Button("close");
        close_btn.setOnAction(e -> stage.close());

        // Arrange components
        root.setTop(txt);
        txt.setTop(close_btn);
        txt.setCenter(result);

        // Create grid for buttons
        GridPane grid = new GridPane();
        grid.setHgap(4);
        grid.setVgap(4);
        grid.getStyleClass().add("background");
        
        // Create buttons and add to grid
        for (int i = 0; i < strs.length; i++) {
            btns[i] = new Button(strs[i]);
            btns[i].getStyleClass().add("btns");
            btns[i].setOnAction(this::handleButtonClick);
            grid.add(btns[i], i % 4, i / 4);
        }
        
        // Set grid in center of root
        root.setCenter(grid);
        
        // put the stage SO THAT WILL WORK RIGHTLY
        Scene scene = new Scene(root, 300, 400);
        stage.setScene(scene);
        scene.getStylesheets().add("file:style.css");
        stage.setTitle("Calculator");
        stage.show();
	}

    void handleButtonClick(Event e){

        // Get the button that was clicked
        Button btn = (Button) e.getSource();
        String currentChar = btn.getText();
        
        // Handle number buttons (0-9)
        if(currentChar.matches("[0-9]")) {
            if(newNumber) {
                result.setText(currentChar);
                newNumber = false;
            } else  result.appendText(currentChar);
        }
        // Handle comma (decimal point)
        else if(currentChar.equals(",")) {
            if(!result.getText().contains(",")) result.appendText(",");
        }

        // Handle operations (+, -, *, /)
        else if(currentChar.equals("+") || currentChar.equals("-") || 
                currentChar.equals("*") || currentChar.equals("/")) {
            if(!operation.isEmpty()) calculate();

            // Store the first number and operation
            firstNum = Double.parseDouble(result.getText().replace(",", "."));
            operation = currentChar;
            newNumber = true;   // Prepare for new number input
        }
        // Handle equals
        else if(currentChar.equals("=")) {
            calculate();
        }
    }
    
    void calculate() {
        secondNum = Double.parseDouble(result.getText().replace(",", "."));
        double res = 0;
        
        switch(operation) {
            case "+":
                res = firstNum + secondNum;
                break;
            case "-":
                res = firstNum - secondNum;
                break;
            case "*":
                res = firstNum * secondNum;
                break;
            case "/":
                try {
                    if(secondNum == 0) throw new ArithmeticException("You cannot divide by zero!"); 
                } catch (Exception e) {
                    result.setText("Error: " + e.getMessage());
                    newNumber = true;
                    return;
                }

                if(secondNum != 0) res = firstNum / secondNum;
                break;
        }
        
        // Display result
        result.setText(String.valueOf(res).replace(".", ","));
        newNumber = true;
    }
    public static void main(String[] args) {
        launch();
    }
}
