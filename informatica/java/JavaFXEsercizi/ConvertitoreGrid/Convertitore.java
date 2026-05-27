import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Convertitore extends Application {
    public void start(Stage stage){
        TextField euroField = new TextField();
        TextField dollaroField = new TextField();

        Button eurobtn = new Button("Converti in Euro");
        Button dollarbtn = new Button("Converti in Dollari");
        Button chiudi = new Button("Chiudi");

        GridPane grid = new GridPane();

        grid.add(chiudi, 0, 0);

        Label etxt = new Label("euro: ");
        Label dtxt = new Label("dollaro: ");

        grid.add(etxt,0,1);
        grid.add(euroField, 1, 1);
        grid.add(dollarbtn, 2, 1);

        grid.add(dtxt,0,2);
        grid.add(dollaroField, 1, 2);
        grid.add(eurobtn, 2, 2);

        grid.setHgap(10);
        grid.setVgap(10);

        dollarbtn.setOnAction(e->{
            try{
                double euro = Double.parseDouble(euroField.getText());
                if(euro < 0) throw new NumberFormatException();
                euro *= 1.18;
                dollaroField.setText(String.format("%.2f", euro));
            } catch (NumberFormatException ex) {
                dollaroField.setText("Input non valido");
                return;
            }
        });
        eurobtn.setOnAction(e->{
            try {
                double dollar = Double.parseDouble(dollaroField.getText());
                if (dollar < 0) throw new NumberFormatException();
                dollar *= 0.85;
                euroField.setText(String.format("%.2f", dollar));
            } catch (NumberFormatException ex) {
                euroField.setText("Input non valido");
            }
        });

        chiudi.setOnAction(e-> stage.close());

        Scene scene = new Scene(grid, 400, 200);
        scene.getStylesheets().add("file:style.css");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) { launch(); }
}