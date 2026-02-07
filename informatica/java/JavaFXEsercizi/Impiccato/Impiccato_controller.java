import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.control.ButtonType;
import java.util.Optional;

public class Impiccato_controller {

    @FXML
    private Button btn_chiudi;
    @FXML
    private ImageView img;
    @FXML
    private Label lbl_unknown_word;

    private StringBuilder unknown_word;
    private int wrong_guesses = 0;

    //getter
    public StringBuilder getUnknown_word() {
        return unknown_word;
    }

    //setter
    public void setUnknown_word(StringBuilder unknown_word) {
        this.unknown_word = unknown_word;
    }

    @FXML
    void btn_chiudi_event(ActionEvent event) {

    }

    @FXML
    void key_event(KeyEvent event) {
        if (event.getText().matches("[a-zA-Z]")) {
            char guessedLetter = event.getText().toLowerCase().charAt(0);

            for(int i = 0; i < unknown_word.length(); i += 2){
                if(getUnknown_word().charAt(i / 2) == guessedLetter){
                    unknown_word.setCharAt(i, guessedLetter);
                    lbl_unknown_word.setText(unknown_word.toString());

                    if(!unknown_word.toString().contains("_")){
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Congratulations");
                        alert.setHeaderText("You guessed the word!");
                        alert.setContentText("You win! The word was: " + unknown_word.toString() + 
                        "\nDo you want to play again? Press Y/N");

                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.isPresent() && result.get() == ButtonType.OK) resetGame();
                        else {
                            System.exit(0);
                        }
                    }
                }else{
                    if(wrong_guesses >= 10){
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Game Over");
                        alert.setHeaderText("You've been hanged!");
                        alert.setContentText("You lose. The word was: " + unknown_word.toString() + 
                        "\nDo you want to play again? Press Y/N");
                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.isPresent() && result.get() == ButtonType.OK) resetGame();
                        else {
                            System.exit(0);
                        }
                        
                    }
                    wrong_guesses++;
                    img.setImage(new Image("immagniImpiccato/" + wrong_guesses + ".png"));
                }
            }
        }
    }

    /**
     * Initializes the game by selecting a random word and setting up the unknown word display
     */
    @FXML
    public void initialize() {
        initializeUnknownWord(getRandomWord());
        lbl_unknown_word.setText(unknown_word.toString());
    }

    /**
     * returns a random word from the Words class
     * @return
     */
    public StringBuilder getRandomWord(){
        String[] words = Words.getWORDS();
        return new StringBuilder(words[(int) (Math.random() * words.length)]);
    }

    /**
     * Initializes the unknown word with underscores
     * @param word
     */
    public void initializeUnknownWord(StringBuilder word){
        for(int i = 0; i < word.length(); i++)
            setUnknown_word(getUnknown_word().append("_ "));
    }

    public void resetGame() {
        wrong_guesses = 0;
        img.setImage(new Image("immagniImpiccato/0.png"));
        unknown_word = new StringBuilder();
        initializeUnknownWord(getRandomWord());
        lbl_unknown_word.setText(unknown_word.toString());
    }

    public static void main(String[] args) {
        
    }

}
