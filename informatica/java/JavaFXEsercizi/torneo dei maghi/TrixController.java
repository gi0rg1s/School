import java.io.IOException;

import Characters.Trix;
import Characters.Winx;
import Characters.TrixChars;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TrixController {
    private static final String CARD_DEFAULT_STYLE = "-fx-border-color: #333; -fx-border-width: 1; -fx-background-color: #f0f0f0;";
    private static final String CARD_SELECTED_STYLE = "-fx-border-color: #270075; -fx-border-width: 2; -fx-background-color: #f1ebfd;";

    @FXML
    private Button close_btn;

    @FXML
    private Button selectedButton;

    @FXML
    public void onSelectedButtonClicked(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("gameFXML.fxml"));
        Parent root = loader.load();

        GameController gameController = new GameController();
        gameController = loader.getController();

        gameController.initialize(selectedWinx, selectedTrix);

        Stage stage = (Stage) selectedButton.getScene().getWindow();
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private TilePane trixContainer;

    private VBox selectedCard;
    private Trix selectedTrix;
    private Winx selectedWinx;

    @FXML
    void close_stage(ActionEvent event) {
        System.exit(0);
    }

	public void initialize(Winx selectedWinx, TrixChars trixChars) {
        this.selectedWinx = selectedWinx;
        if (this.selectedWinx != null) System.out.println("Winx ricevuta: " + this.selectedWinx.getNome());
        
        selectedButton.setDisable(true);

        for(Trix t : trixChars.getTrixChars()){
            
            // create a header for the page
            VBox header = new VBox(2);
            Label title = new Label("Trix");
            title.setStyle("-fx-font-weight: bold; -fx-font-size: 16px; -fx-text-color: #270075;");
            Label subtitle = new Label("Scegli la Trix da sfidare!");
            subtitle.setStyle("-fx-font-size: 12px; -fx-text-color: #555;");
            
            // add title and subtitle to the header
            header.getChildren().addAll(title, subtitle);

            // Create a compact vertical card for each Trix
            VBox block = new VBox(8);
            block.setPadding(new Insets(10));
            block.setStyle(CARD_DEFAULT_STYLE);
            block.setPrefWidth(180);
            block.setOnMouseClicked(event -> onTrixSelected(t, block));
            

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
                new Label("Nome: " + t.getNome()),
                new Label(t.getType()),
                new Label("HP: " + t.getHp() + "/" + t.getHp()),
                new Label("Mana: " + t.getMana() + "/" + t.getMana()),
                new Label("Potenza Magica: " + t.getPotenzaMagica()),
                new Label("Difesa: " + t.getDifesaMagica()),
                new Label("Velocità: " + t.getSpeed())
            );
            
            // add image and attributes to the card
            block.getChildren().addAll(image, attributes);
            image.setImage(new Image(getClass().getResourceAsStream("/sprites/" + t.getNome() + ".png")));

            // add the hbox to the main container
            trixContainer.getChildren().add(block);

            //on clock select the card and enable the button
            block.setOnMouseClicked(event -> onTrixSelected(t, block));
        }
    }

    public void onTrixSelected(Trix t, VBox card) {
            if (selectedCard != null) selectedCard.setStyle(CARD_DEFAULT_STYLE);
            selectedCard = card;
            selectedCard.setStyle(CARD_SELECTED_STYLE);
            selectedTrix = t;
            System.out.println("Selected Trix: " + selectedTrix.getNome());
            selectedButton.setDisable(false);
    }
}