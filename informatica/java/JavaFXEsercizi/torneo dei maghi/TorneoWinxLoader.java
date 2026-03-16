import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.Application;

// Loader for the winx tournament
import Characters.WinxChars;

public class TorneoWinxLoader extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        // Creo i maghi
        WinxChars winxChars = new WinxChars();
        // Spezzo il loader, così posso passare i maghi al controller
        FXMLLoader loader = new FXMLLoader(getClass().getResource("TorneoWinxFXML.fxml"));
        Parent root = loader.load();
        
        // Estraggo il controller e passo i maghi
        TorneoWinxController controller = loader.getController();
        controller.initialize(winxChars);

        stage.setTitle("Alfea");
        stage.setScene(new Scene(root, 620, 420));
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
    
}
