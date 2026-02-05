import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;

public class Impiccato_controller {

    @FXML
    private Button btn_chiudi;

    @FXML
    private ImageView img;

    @FXML
    private Label lbl_unknown_word;

    @FXML
    void btn_chiudi_event(ActionEvent event) {

    }

    @FXML
    void key_event(KeyEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Key Pressed");
        alert.setHeaderText(null);
        alert.setContentText("You pressed: " + event.getText());
        alert.showAndWait();
    }

}
