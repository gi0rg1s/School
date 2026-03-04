import java.util.ArrayList;

import Characters.Winx;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.image.ImageView;

public class TorneoWinxFXML {

    @FXML
    private Button close_btn;
    
    @FXML
    private TilePane winxContainer;

    @FXML
    void close_stage(ActionEvent event) {
        System.exit(0);
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

    
}
