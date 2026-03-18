import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.Application;

public class TorneoWinxLoader extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("WelcomeFXML.fxml"));
        Parent root = loader.load();

        stage.setTitle("Alfea");
        stage.setScene(new Scene(root, 620, 420));
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
    
}
