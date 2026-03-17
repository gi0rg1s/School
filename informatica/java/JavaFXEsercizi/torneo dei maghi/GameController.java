import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import Characters.Fata;
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
        if (this.selectedWinx != null) System.out.println("Winx ricevuta: " + this.selectedWinx.getNome());
        if (this.selectedTrix != null) System.out.println("Trix ricevuta: " + this.selectedTrix.getNome());

        // set imgs
         winx_img.setImage(new Image(getClass().getResourceAsStream("/sprites/" + selectedWinx.getNome() + ".png")));
        trix_img.setImage(new Image(getClass().getResourceAsStream("/sprites/" + selectedTrix.getNome() + ".png")));
        
        updateStatsLabel();
        txt_label.setText("Turno Winx: scegli un'azione");

        // match buttons actions
        //attack button: Winx attacks and calls afterPlayerAction to let Trix play
        attack_btn.setOnAction(e -> {
            attack(e, selectedWinx, selectedTrix);
            afterPlayerAction();
        });

        //cura button: Winx heals and calls afterPlayerAction to let Trix play
        cura_btn.setOnAction(e -> {
            cura(e, selectedWinx);
            afterPlayerAction();
        });
    }

    private void afterPlayerAction() {
        if (selectedWinx.getHp() <= 0 || selectedTrix.getHp() <= 0) {
            endFight();
            return;
        }

        txt_label.setText("Turno Trix: " + selectedTrix.getNome() + " sta pensando...");
        // Trix AI: heal at low HP, otherwise attack.
        if (selectedTrix.getHp() < (selectedTrix.getHpMax() * 0.3)){
            txt_label.setText("Turno Trix: " + selectedTrix.getNome() + " si sta curando...");
            cura(null, selectedTrix);
        }
        else{
            txt_label.setText("Turno Trix: " + selectedTrix.getNome() + " ti sta attaccando...");
            attack(null, selectedTrix, selectedWinx);
        }

        updateStatsLabel();

        if (selectedWinx.getHp() <= 0 || selectedTrix.getHp() <= 0) endFight();
    }

    private void endFight() {
        if (selectedWinx.getHp() <= 0 && selectedTrix.getHp() <= 0) txt_label.setText("Pareggio");
        else if (selectedWinx.getHp() <= 0) txt_label.setText("Hai perso");
        else txt_label.setText("Hai vinto");

        attack_btn.setDisable(true);
        cura_btn.setDisable(true);
    }
    
    //set status labels
    private void updateStatsLabel() {
        Label winx_lbl = new Label(selectedWinx.getNome() + " - HP: " + selectedWinx.getHp() + "/" + selectedWinx.getHpMax() + " - MAG: " + selectedWinx.getPotenzaMagica());
        Label trix_lbl = new Label(selectedTrix.getNome() + " - HP: " + selectedTrix.getHp() + "/" + selectedTrix.getHpMax() + " - MAG: " + selectedTrix.getPotenzaMagica());
        txt_label.setGraphic(new VBox(winx_lbl, trix_lbl));
    }

    //attack methods
    public void attack(ActionEvent event, Fata caster, Fata target) {
        // Fata attacks the other fata with first incantesimo
        caster.castIncantesimo(caster.getIncantesimi().get(0), target);
    }

    //heal method
    public void cura(ActionEvent event, Fata caster) {
        // Fata heals herself with second incantesimo
        caster.castIncantesimo(caster.getIncantesimi().get(1), caster);
    }
}
