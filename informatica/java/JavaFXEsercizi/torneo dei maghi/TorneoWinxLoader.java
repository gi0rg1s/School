import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import java.util.ArrayList;

import Characters.Winx;
import javafx.application.Application;

public class TorneoWinxLoader extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("TorneoWinxFXML.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void initialize(ArrayList<Winx> winxs) {

        // scroll the winx arraylist and create a block for each winx
        for (Winx w : winxs) {
            // Create an hbox for each winx
            HBox blocchetto = new HBox(15);
            blocchetto.setPadding(new Insets(10));
            blocchetto.setStyle("-fx-border-color: #333; -fx-border-width: 1; -fx-background-color: #f0f0f0;");
            
            // ImageView per for the winx img (placeholder)
            ImageView image = new ImageView();
            image.setFitWidth(80);
            image.setFitHeight(80);
            image.setStyle("-fx-background-color: #cccccc;");
            
            // VBox for the winx attributes
            VBox attributi = new VBox(5);
            attributi.getChildren().addAll(
                new Label("Nome: " + w.getNome()),
                new Label("HP: " + w.getHp() + "/" + w.getHp()),
                new Label("Mana: " + w.getMana() + "/" + w.getMana()),
                new Label("Potenza Magica: " + w.getPotenzaMagica()),
                new Label("Difesa: " + w.getDifesaMagica()),
                new Label("Velocità: " + w.getSpeed())
            );
            
            // add the image and the attributes to the hbox
            blocchetto.getChildren().addAll(image, attributi);
        
            // add the hbox to the main container
            winxContainer.getChildren().add(blocchetto);
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
