import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.TilePane;

public class TorneoWinxFXML {

    @FXML
    private Button close_btn;
    
    @FXML
    private TilePane winxContainer;

    @FXML
    void close_stage(ActionEvent event) {
        System.exit(0);
    }
}
