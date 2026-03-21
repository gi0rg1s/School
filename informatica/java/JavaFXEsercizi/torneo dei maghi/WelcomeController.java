import java.io.IOException;

import Characters.TrixChars;
import Characters.WinxChars;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class WelcomeController {

    @FXML
    private void onStartClicked(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("TorneoWinxFXML.fxml"));
        Parent root = loader.load();

        TorneoWinxController controller = loader.getController();
        controller.initialize(new WinxChars());

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root, 620, 420));
        stage.show();
    }

    @FXML
    private void onAllVsAllClicked(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("gameFXML.fxml"));
        Parent root = loader.load();

        GameController controller = loader.getController();
        controller.initializeAllVsAll(new WinxChars(), new TrixChars());

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root, 620, 420));
        stage.show();
    }
}
