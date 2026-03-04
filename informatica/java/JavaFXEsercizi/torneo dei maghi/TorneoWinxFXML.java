import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class TorneoWinxFXML {

    @FXML
    private Button close_btn;

    @FXML
    void close_stage(ActionEvent event) {
        System.exit(0);
    }

    
}
