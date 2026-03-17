import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import Characters.Trix;
import Characters.Winx;

public class GameController {

    @FXML
    private Button attack_btn;

    @FXML
    private Button cura_btn;

    @FXML
    private Label txt_label;

    @FXML
    private ImageView trix_img;

    @FXML
    private ImageView winx_img;

    private Winx selectedWinx;
    private Trix selectedTrix;

    public void initialize(Winx selectedWinx, Trix selectedTrix) {
        this.selectedWinx = selectedWinx;
        this.selectedTrix = selectedTrix;

    }

}
