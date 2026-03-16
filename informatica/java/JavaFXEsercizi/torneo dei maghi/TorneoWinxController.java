import java.io.IOException;

import Characters.Winx;
import Characters.WinxChars;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;

public class TorneoWinxController {

    private static final String CARD_DEFAULT_STYLE = "-fx-border-color: #333; -fx-border-width: 1; -fx-background-color: #f0f0f0;";
    private static final String CARD_SELECTED_STYLE = "-fx-border-color: #ff0090; -fx-border-width: 2; -fx-background-color: #fff2fa;";

    @FXML
    private Button close_btn;

    @FXML
    private Button selectedButton;

    @FXML
    void onSelectedButton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("TorneoTrixFXML.fxml"));
        Parent root = loader.load();

        TrixController controller = loader.getController();
        controller.initialize(selectedWinx, new Characters.TrixChars());

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    private TilePane winxContainer;

    private VBox selectedCard;
    private Winx selectedWinx;

    @FXML
    void close_stage(ActionEvent event) {
        System.exit(0);
    }

    public void initialize(WinxChars winxChars) {
        selectedButton.setDisable(true);

        // scroll the winx arraylist and create a block for each winx
        for (Winx w : winxChars.getWinxChars()) {

            // create a header for the page
            VBox header = new VBox(2);
            Label title = new Label("Winx");
            title.setStyle("-fx-font-weight: bold; -fx-font-size: 16px; -fx-text-color: #ff0090;");
            Label subtitle = new Label("Scegli la tua Winx!");
            subtitle.setStyle("-fx-font-size: 12px; -fx-text-color: #555;");
            
            // add title and subtitle to the header
            header.getChildren().addAll(title, subtitle);

            // Create a compact vertical card for each Winx.
            VBox block = new VBox(8);
            block.setPadding(new Insets(10));
            block.setStyle(CARD_DEFAULT_STYLE);
            block.setPrefWidth(180);
            block.setOnMouseClicked(event -> onWinxSelected(w, block));
            
            // ImageView per for the winx img 
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
                new Label(w.getType()),
                new Label("HP: " + w.getHp() + "/" + w.getHp()),
                new Label("Mana: " + w.getMana() + "/" + w.getMana()),
                new Label("Potenza Magica: " + w.getPotenzaMagica()),
                new Label("Difesa: " + w.getDifesaMagica()),
                new Label("Velocità: " + w.getSpeed())
            );
            
            // add image and attributes to the card
            block.getChildren().addAll(image, attributes);
            image.setImage(new Image(getClass().getResourceAsStream("/sprites/" + w.getNome() + ".png")));

            // add the hbox to the main container
            winxContainer.getChildren().add(block);
        }
    }


    //Handle the winx selected
    public void onWinxSelected(Winx chosenWinx, VBox card) {

        if (selectedCard != null) selectedCard.setStyle(CARD_DEFAULT_STYLE);

        selectedCard = card;
        selectedWinx = chosenWinx;

        selectedCard.setStyle(CARD_SELECTED_STYLE);
        selectedButton.setDisable(false);
        System.out.println("Selected Winx: " + selectedWinx.getNome());

    }
}

