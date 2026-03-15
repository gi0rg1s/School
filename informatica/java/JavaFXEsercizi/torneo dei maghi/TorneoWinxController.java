import Characters.Winx;
import Characters.WinxChars;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
//import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.image.ImageView;

public class TorneoWinxController {

    @FXML
    private Button close_btn;
    
    @FXML
    private TilePane winxContainer;

    @FXML
    void close_stage(ActionEvent event) {
        System.exit(0);
    }

    public void initialize(WinxChars winxChars) {

        // Show multiple cards on the same row.
        winxContainer.setPadding(new Insets(12));
        winxContainer.setHgap(12);
        winxContainer.setVgap(12);
        winxContainer.setPrefColumns(3);

        // scroll the winx arraylist and create a block for each winx
        for (Winx w : winxChars.getWinxChars()) {

            // Create a compact vertical card for each Winx.
            VBox block = new VBox(8);
            block.setPadding(new Insets(10));
            block.setStyle("-fx-border-color: #333; -fx-border-width: 1; -fx-background-color: #f0f0f0;");
            block.setPrefWidth(180);
            
            // ImageView per for the winx img (placeholder)
            ImageView image = new ImageView();
            image.setFitWidth(120);
            image.setFitHeight(120);
            image.setPreserveRatio(true);
            image.setSmooth(true);
            image.setStyle("-fx-background-color: #cccccc;");
            
            // VBox for the winx attributes
            VBox attributes = new VBox(5);
            attributes.getChildren().addAll(
                new Label("Nome: " + w.getNome()),
                new Label("HP: " + w.getHp() + "/" + w.getHp()),
                new Label("Mana: " + w.getMana() + "/" + w.getMana()),
                new Label("Potenza Magica: " + w.getPotenzaMagica()),
                new Label("Difesa: " + w.getDifesaMagica()),
                new Label("Velocità: " + w.getSpeed())
            );
            
            // add the image and the attributes to the hbox
            block.getChildren().addAll(image, attributes);
            image.setImage(new Image(getClass().getResourceAsStream("/images/" + w.getNome().toLowerCase() + ".png")));

            // add the hbox to the main container
            winxContainer.getChildren().add(block);
        }
    }

    
}
