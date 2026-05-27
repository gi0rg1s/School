import java.util.ArrayList;

import Characters.Fata;
import Characters.Trix;
import Characters.TrixChars;
import Characters.Winx;
import Characters.WinxChars;

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
    private ArrayList<Winx> allWinx = new ArrayList<>();
    private ArrayList<Trix> allTrix = new ArrayList<>();
    private boolean allVsAllMode = false;
    private boolean winxTurn = true;

    // Initializes the 1v1 mode: sets selected characters, button styles, handlers, and starts with Winx action
    public void initialize(Winx selectedWinx, Trix selectedTrix) {
        allVsAllMode = false;
        this.selectedWinx = selectedWinx;
        this.selectedTrix = selectedTrix;
    
        setCharacterImages(selectedWinx, selectedTrix);

        attack_btn.setStyle("-fx-background-color: #ff72b4; -fx-text-fill: #2e2e2e; -fx-font-weight: bold; -fx-background-radius: 14; -fx-padding: 8 16;");
        cura_btn.setStyle("-fx-background-color: #ff94c7; -fx-text-fill: #2e2e2e; -fx-font-weight: bold; -fx-background-radius: 14; -fx-padding: 8 16;");
        rest_btn.setStyle("-fx-background-color: #ffc4df; -fx-text-fill: #2e2e2e; -fx-font-weight: bold; -fx-background-radius: 14; -fx-padding: 8 16;");
        txt_label.setStyle("-fx-font-size: 15px; -fx-font-weight: bold; -fx-text-fill: #2e2e2e; -fx-padding: 12;");
        setupButtonsHandlers();

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

    // Initializes the all vs all mode: sets up the lists of characters, buttons, and starts with the first Winx action
    public void initializeAllVsAll(WinxChars winxChars, TrixChars trixChars) {
        allVsAllMode = true;
        winxTurn = true;

        allWinx.clear();
        allTrix.clear();
        allWinx.addAll(winxChars.getWinxChars());
        allTrix.addAll(trixChars.getTrixChars());

        attack_btn.setDisable(false);
        cura_btn.setDisable(false);
        rest_btn.setDisable(false);

        // Set styles for all vs all mode
        attack_btn.setStyle("-fx-background-color: #ff72b4; -fx-text-fill: #2e2e2e; -fx-font-weight: bold; -fx-background-radius: 14; -fx-padding: 8 16;");
        cura_btn.setStyle("-fx-background-color: #ff94c7; -fx-text-fill: #2e2e2e; -fx-font-weight: bold; -fx-background-radius: 14; -fx-padding: 8 16;");
        rest_btn.setStyle("-fx-background-color: #ffc4df; -fx-text-fill: #2e2e2e; -fx-font-weight: bold; -fx-background-radius: 14; -fx-padding: 8 16;");
        txt_label.setStyle("-fx-font-size: 15px; -fx-font-weight: bold; -fx-text-fill: #2e2e2e; -fx-padding: 12;");
        setupButtonsHandlers();

        selectedWinx = chooseWinxWithHighestMana();
        selectedTrix = chooseTrixWithLowestHp();
        setCharacterImages(selectedWinx, selectedTrix);
        updateStatsLabel();

        txt_label.setText("Modalita tutti contro tutti: turno Winx, scegli un'azione");
    }

    //Button handlers for all vs all mode
    private void setupButtonsHandlers() {
        attack_btn.setOnAction(e -> {
            if (allVsAllMode) {
                handleAllVsAllPlayerAction("ATTACK");
            } else {
                attack(selectedWinx, selectedTrix);
                afterPlayerAction();
            }
        });

        cura_btn.setOnAction(e -> {
            if (allVsAllMode) {
                handleAllVsAllPlayerAction("HEAL");
            } else {
                cura(selectedWinx);
                afterPlayerAction();
            }
        });

        rest_btn.setOnAction(e -> {
            if (allVsAllMode) {
                handleAllVsAllPlayerAction("REST");
            } else {
                rest(selectedWinx);
                afterPlayerAction();
            }
        });
    }

    private void handleAllVsAllPlayerAction(String actionType) {
        if (!allVsAllMode) {
            return;
        }

        if (!winxTurn) {
            txt_label.setText("Attendi: sta agendo la Trix...");
            return;
        }

        if (getAliveWinx().isEmpty() || getAliveTrix().isEmpty()) {
            endAllVsAllFight();
            return;
        }

        selectedWinx = chooseWinxWithHighestMana();
        selectedTrix = chooseTrixWithLowestHp();
        if (selectedWinx == null || selectedTrix == null) {
            endAllVsAllFight();
            return;
        }

        setCharacterImages(selectedWinx, selectedTrix);

        switch (actionType) {
            case "HEAL":
                txt_label.setText("Turno Winx: " + selectedWinx.getNome() + " si sta curando...");
                cura(selectedWinx);
                break;
            case "REST":
                txt_label.setText("Turno Winx: " + selectedWinx.getNome() + " si sta riposando...");
                rest(selectedWinx);
                break;
            default:
                txt_label.setText("Turno Winx: " + selectedWinx.getNome() + " attacca " + selectedTrix.getNome());
                attack(selectedWinx, selectedTrix);
                break;
        }

        updateStatsLabel();

        // After player action, check if fight is over before letting Trix play
        if (getAliveWinx().isEmpty() || getAliveTrix().isEmpty()) {
            endAllVsAllFight();
            return;
        }

        winxTurn = false;
        runAllVsAllTrixTurn();
    }

    // Trix AI turn in all vs all mode: chooses action based on HP and mana, then lets player choose next Winx action
    private void runAllVsAllTrixTurn() {
        // Check if fight is already over before letting Trix play
        if (getAliveWinx().isEmpty() || getAliveTrix().isEmpty()) {
            endAllVsAllFight();
            return;
        }

        selectedTrix = chooseTrixWithLowestHp();
        selectedWinx = chooseWinxWithHighestMana();
        if (selectedWinx == null || selectedTrix == null) {
            endAllVsAllFight();
            return;
        }

        setCharacterImages(selectedWinx, selectedTrix);
        applyAiAction(selectedTrix, selectedWinx);
        updateStatsLabel();

        if (getAliveWinx().isEmpty() || getAliveTrix().isEmpty()) {
            endAllVsAllFight();
            return;
        }

        // After Trix plays, switch back to Winx turn and let player choose next action
        winxTurn = true;
        selectedWinx = chooseWinxWithHighestMana();
        selectedTrix = chooseTrixWithLowestHp();
        setCharacterImages(selectedWinx, selectedTrix);
        txt_label.setText("Turno Winx: " + selectedWinx.getNome() + " contro " + selectedTrix.getNome() + ". Scegli un'azione");
        updateStatsLabel();
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

    // AI logic for all vs all mode: heal if low HP, rest if low mana, otherwise attack
    private void applyAiAction(Fata caster, Fata target) {
        if (caster.getHp() < (caster.getHpMax() * 0.3)) {
            txt_label.setText("Turno Trix: " + caster.getNome() + " si sta curando...");
            cura(caster);
        } else if (caster.getMana() < (caster.getManaMax() * 0.2)) {
            txt_label.setText("Turno Trix: " + caster.getNome() + " si sta riposando...");
            rest(caster);
        } else {
            txt_label.setText("Turno Trix: " + caster.getNome() + " attacca " + target.getNome());
            attack(caster, target);
        }
    }

    //helper methods for all vs all mode
    private ArrayList<Winx> getAliveWinx() {
        ArrayList<Winx> aliveWinx = new ArrayList<>();
        for (Winx w : allWinx) {
            if (w.getHp() > 0) aliveWinx.add(w);
        }
        return aliveWinx;
    }

    //helper method to get alive trix
    private ArrayList<Trix> getAliveTrix() {
        ArrayList<Trix> aliveTrix = new ArrayList<>();
        for (Trix t : allTrix) {
            if (t.getHp() > 0) aliveTrix.add(t);
        }
        return aliveTrix;
    }

    private Winx chooseWinxWithHighestMana() {
        Winx bestWinx = null;
        for (Winx w : getAliveWinx()) {
            if (bestWinx == null || w.getMana() > bestWinx.getMana() || (w.getMana() == bestWinx.getMana() && w.getHp() > bestWinx.getHp())) 
                bestWinx = w;
        }
        return bestWinx;
    }

    private Trix chooseTrixWithLowestHp() {
        Trix bestTrix = null;
        for (Trix t : getAliveTrix()) {
            if (bestTrix == null || t.getHp() < bestTrix.getHp() || (t.getHp() == bestTrix.getHp() && t.getMana() < bestTrix.getMana())) {
                bestTrix = t;
            }
        }
        return bestTrix;
    }

    // end all vs all fight
    private void endAllVsAllFight() {

        updateStatsLabel();

        if (getAliveTrix().isEmpty() && getAliveWinx().isEmpty()) {
            txt_label.setText("Pareggio tra team");
        } else if (getAliveTrix().isEmpty()) {
            txt_label.setText("Vittoria team Winx");
        } else {
            txt_label.setText("Vittoria team Trix");
        }

        attack_btn.setDisable(true);
        cura_btn.setDisable(true);
        rest_btn.setDisable(true);
    }

    // Set character images based on their names
    private void setCharacterImages(Winx winx, Trix trix) {
        if (winx != null) {
            winx_img.setImage(new Image(getClass().getResourceAsStream("/sprites/" + winx.getNome() + ".png")));
        }
        if (trix != null) {
            trix_img.setImage(new Image(getClass().getResourceAsStream("/sprites/" + trix.getNome() + ".png")));
        }
    }

    // Ends the fight in 1v1 mode
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
        if (selectedWinx == null || selectedTrix == null) {
            return;
        }

        int aliveWinx = allVsAllMode ? getAliveWinx().size() : (selectedWinx.getHp() > 0 ? 1 : 0);
        int aliveTrix = allVsAllMode ? getAliveTrix().size() : (selectedTrix.getHp() > 0 ? 1 : 0);

        // Winx stats
        VBox winxStats = new VBox(5);
        winxStats.setAlignment(Pos.CENTER);
        winxStats.setStyle("-fx-background-color: #fff1f8; -fx-background-radius: 8; -fx-padding: 8;");
        Label winxTitle = new Label("Winx: " + selectedWinx.getNome() + " (vive: " + aliveWinx + ")");
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
        Label trixTitle = new Label("Trix: " + selectedTrix.getNome() + " (vive: " + aliveTrix + ")");
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

        // Put everything into the dedicated statsContainer
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
