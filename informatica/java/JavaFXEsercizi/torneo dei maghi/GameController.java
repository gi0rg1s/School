import Characters.Fata;
import Characters.Trix;
import Characters.Winx;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class GameController {

    @FXML
    private Button attack_btn;

    @FXML
    private Button cura_btn;

    @FXML
    private Button rest_btn;

    @FXML
    private Label txt_label;

    @FXML
    private VBox statsContainer;

    @FXML
    private ImageView trix_img;

    @FXML
    private ImageView winx_img;

    private Winx selectedWinx;
    private Trix selectedTrix;

    public void initialize(Winx selectedWinx, Trix selectedTrix) {
        this.selectedWinx = selectedWinx;
        this.selectedTrix = selectedTrix;
    
        // Set images
        winx_img.setImage(new Image(getClass().getResourceAsStream("/sprites/" + selectedWinx.getNome() + ".png")));
        trix_img.setImage(new Image(getClass().getResourceAsStream("/sprites/" + selectedTrix.getNome() + ".png")));

        attack_btn.setStyle("-fx-background-color: #ff72b4; -fx-text-fill: #2e2e2e; -fx-font-weight: bold; -fx-background-radius: 14; -fx-padding: 8 16;");
        cura_btn.setStyle("-fx-background-color: #ff94c7; -fx-text-fill: #2e2e2e; -fx-font-weight: bold; -fx-background-radius: 14; -fx-padding: 8 16;");
        rest_btn.setStyle("-fx-background-color: #ffc4df; -fx-text-fill: #2e2e2e; -fx-font-weight: bold; -fx-background-radius: 14; -fx-padding: 8 16;");
        txt_label.setStyle("-fx-font-size: 15px; -fx-font-weight: bold; -fx-text-fill: #2e2e2e; -fx-padding: 12;");

        updateStatsLabel();
        txt_label.setText("Turno Winx: scegli un'azione");

        // match buttons actions
        //attack button: Winx attacks and calls afterPlayerAction to let Trix play
        attack_btn.setOnAction(e -> {
            attack(selectedWinx, selectedTrix);
            afterPlayerAction();
        });

        //cura button: Winx heals and calls afterPlayerAction to let Trix play
        cura_btn.setOnAction(e -> {
            cura(selectedWinx);
            afterPlayerAction();
        });

        //rest button: Winx rests and calls afterPlayerAction to let Trix play
        rest_btn.setOnAction(e -> {
            rest(selectedWinx);
            afterPlayerAction();
        });
    }

    private void afterPlayerAction() {
        if (selectedWinx.getHp() <= 0 || selectedTrix.getHp() <= 0) {
            endFight();
            return;
        }

        txt_label.setText("Turno Trix: " + selectedTrix.getNome() + " sta pensando...");

        // Trix AI: heal at low HP, otherwise attack
        if (selectedTrix.getHp() < (selectedTrix.getHpMax() * 0.3)) {
            txt_label.setText("Turno Trix: " + selectedTrix.getNome() + " si sta curando...");
            cura(selectedTrix);
        } else if(selectedTrix.getMana() < (selectedTrix.getManaMax() * 0.2)) {
            txt_label.setText("Turno Trix: " + selectedTrix.getNome() + " si sta riposando...");
            rest(selectedTrix);
        }else {
            txt_label.setText("Turno Trix: " + selectedTrix.getNome() + " ti sta attaccando...");
            attack(selectedTrix, selectedWinx);
        }

        updateStatsLabel();

        if (selectedWinx.getHp() <= 0 || selectedTrix.getHp() <= 0) endFight();
    }

    private void endFight() {
        updateStatsLabel();
        if (selectedWinx.getHp() <= 0 && selectedTrix.getHp() <= 0) {
            txt_label.setText("Pareggio");
        } else if (selectedWinx.getHp() <= 0) {
            txt_label.setText("Hai perso");
        } else {
            txt_label.setText("Hai vinto");
        }

        attack_btn.setDisable(true);
        cura_btn.setDisable(true);
        rest_btn.setDisable(true);
    }
    
    //set status labels
    private void updateStatsLabel() {
        // Winx stats
        VBox winxStats = new VBox(5);
        winxStats.setAlignment(Pos.CENTER);
        winxStats.setStyle("-fx-background-color: #fff1f8; -fx-background-radius: 8; -fx-padding: 8;");
        Label winxTitle = new Label("Winx: " + selectedWinx.getNome());
        winxTitle.setStyle("-fx-font-weight: bold; -fx-font-size: 14px; -fx-text-fill: #d10078;");
        winxStats.getChildren().addAll(
            winxTitle,
            new Label("HP: " + selectedWinx.getHp() + "/" + selectedWinx.getHpMax()),
            new Label("Potenza Magica: " + selectedWinx.getPotenzaMagica()),
            new Label("Mana: " + selectedWinx.getMana() + "/" + selectedWinx.getManaMax())
        );

        // Trix stats
        VBox trixStats = new VBox(5);
        trixStats.setAlignment(Pos.CENTER);
        trixStats.setStyle("-fx-background-color: #eaf1ff; -fx-background-radius: 8; -fx-padding: 8;");
        Label trixTitle = new Label("Trix: " + selectedTrix.getNome());
        trixTitle.setStyle("-fx-font-weight: bold; -fx-font-size: 14px; -fx-text-fill: #3c52d9;");
        trixStats.getChildren().addAll(
            trixTitle,
            new Label("HP: " + selectedTrix.getHp() + "/" + selectedTrix.getHpMax()),
            new Label("Potenza Magica: " + selectedTrix.getPotenzaMagica()),
            new Label("Mana: " + selectedTrix.getMana() + "/" + selectedTrix.getManaMax())
        );

        // Stats row
        HBox statsRow = new HBox(20);
        statsRow.setAlignment(Pos.CENTER);
        statsRow.setPadding(new Insets(10));
        statsRow.setStyle("-fx-border-color: #ffc2dd; -fx-border-width: 1; -fx-background-color: #fff1f8; -fx-background-radius: 10; -fx-border-radius: 10;");
        statsRow.getChildren().addAll(winxStats, trixStats);

        // Images row
        HBox imagesRow = new HBox(20);
        imagesRow.setAlignment(Pos.CENTER);
        imagesRow.setPadding(new Insets(10));
        imagesRow.getChildren().addAll(winx_img, trix_img);

        // Put everything into the dedicated statsContainer (NOT txt_label)
        statsContainer.getChildren().setAll(statsRow, imagesRow);
    }

    //attack methods
    public void attack(Fata caster, Fata target) {
        // Fata attacks the other fata with first incantesimo
        caster.castIncantesimo(caster.getIncantesimi().get(0), target);
    }

    //heal method
    public void cura(Fata caster) {
        // Fata heals herself with second incantesimo
        caster.castIncantesimo(caster.getIncantesimi().get(1), null);
    }

    //rest method
    public void rest(Fata caster) {
        // Fata rests and recovers some HP
        caster.rest();
    }
}
