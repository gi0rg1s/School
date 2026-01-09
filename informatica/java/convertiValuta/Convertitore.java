import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

// 3) TextField + Button + Label (Hello + Nome)
public class Convertitore extends Application{
    public void start(Stage stage) {
        TextField txt = new TextField();
        txt.setPromptText("Inserisci la cifra in euro");    //prompt text for euro input
        Label lbl = new Label();
        Button btn = new Button("Converti in dollari");     //button for conversion from euro to dollars
        btn.setOnAction(e -> {
            try {
                if(Double.parseDouble(txt.getText()) < 0) throw new NumberFormatException();     //check for negative values
                lbl.setText(String.format("%.2f euro = %.2f dollari", Double.parseDouble(txt.getText()), 
                Double.parseDouble(txt.getText()) * 1.17)); //conversion rate 1 euro = 1.17 dollars
            } catch (NumberFormatException ex) {
                lbl.setText("Inserisci un numero valido");  //error message for invalid input
            }
        });
        Button btnInverti = new Button("Converti in euro");   //button for conversion from dollars to euro
        TextField txt2 = new TextField();                     //input field for dollars
        txt2.setPromptText("Inserisci la cifra in dollari");  //prompt text for dollars input
        btnInverti.setOnAction(e -> {
            try {
                if(Double.parseDouble(txt2.getText()) < 0) throw new NumberFormatException(); //check for negative values
                lbl.setText(String.format("%.2f dollari = %.2f euro", Double.parseDouble(txt2.getText()), 
                Double.parseDouble(txt2.getText()) / 1.17));    //conversion rate 1 dollar = 0.85 euro
            } catch (NumberFormatException ex) {
                lbl.setText("Inserisci un numero valido");      //error message for invalid input
            }
        });

        // VBox con spaziatura di 10 pixel tra i nodi
        VBox root = new VBox(10, txt, btn, txt2, btnInverti, lbl); //vertical box layout with 10 pixel spacing between nodes
        stage.setScene(new Scene(root, 300, 200));                 //set scene with specified width and height
        stage.show();                                              //display the stage   
    }
    public static void main(String[] args) { launch(); }
}
