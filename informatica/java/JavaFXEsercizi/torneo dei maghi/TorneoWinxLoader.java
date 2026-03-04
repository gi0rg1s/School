import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import java.util.ArrayList;

import Characters.Winx;
import javafx.application.Application;

public class TorneoWinxLoader extends Application {

    public void start(Stage stage) throws Exception {
        // Creo i maghi
        ArrayList<Winx> maghi = new ArrayList<>();
        maghi.add(new Winx("Stella", "Fata del Sole", 100, 100, 80, 70, 60, "Magic Winx"));
        maghi.add(new Winx("Bloom", "Fata del fuoco", 80, 80, 90, 60, 70, "Enchantix"));
        maghi.add(new Winx("Flora", "Fata dei fiori", 90, 90, 70, 80, 85, "Magic Winx"));
        maghi.add(new Winx("Musa", "Fata della Musica", 70, 70, 95, 50, 60, "Sirenix"));
        maghi.add(new Winx  ("Aisha", "Fata dell'Acqua", 85, 85, 85, 75, 75, "Magic Winx"));
        
        // Spezzo il loader, così posso passare i maghi al controller
        FXMLLoader loader = new FXMLLoader(getClass().getResource("TorneoWinxFXML.fxml"));
        Parent root = loader.load();
        
        // Estraggo il controller e passo i maghi
        TorneoWinxFXML controller = loader.getController();
        controller.initialize(maghi);
        
        stage.setTitle("Arena dei Maghi");
        stage.setScene(new Scene(root, 620, 420));
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
    
}
