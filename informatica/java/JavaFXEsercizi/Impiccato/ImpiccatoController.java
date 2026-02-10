import java.util.List;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;

public class ImpiccatoController {

    @FXML
    private Button exit_btn;

    @FXML
    private ImageView img;

    @FXML
    private BorderPane root;

    @FXML
    private Label unknown_word;
    private List<Character> trys = new ArrayList<>();

    private String targetWord;
    private StringBuilder unknownWordBuilder = new StringBuilder();
    private int wrongGuesses = 0;

    @FXML
    void exit_btn_event(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void key_event(KeyEvent event) {
        boolean guess = false;

        if (targetWord == null || targetWord.isEmpty()) return;

        String keyText = event.getCharacter();
        if (keyText == null) keyText = "";
        
        keyText = keyText.toLowerCase();
        if (keyText.isEmpty() || !keyText.matches("[a-z]")) return;

        char letter = keyText.charAt(0);

        //debug
        System.out.println("pressed: " + letter);


        if (!(trys.contains(letter))) {

            trys.add(letter);

            // check if the player guessed a letter in the word
            for (int i = 0; i < targetWord.length(); i++) {
                if (letter == targetWord.charAt(i)) {
                    guess = true;
                    // unknownWordBuilder stores "_ " for each letter, so underscore positions are at index i*2
                    unknownWordBuilder.setCharAt(i * 2, letter);
                }
            }
        }

        unknown_word.setText(unknownWordBuilder.toString());

        // winning check
        if (!unknownWordBuilder.toString().contains("_")) {
            Alert msg = new Alert(Alert.AlertType.INFORMATION);
            msg.setTitle("Congratulations!");
            msg.setHeaderText(null);
            msg.setContentText("Congratulations! You guessed the word: " + targetWord);
            msg.showAndWait();
            return;
        }

        // if not guessed, count as wrong
        if (!guess ) {
            wrongGuesses++;
            updateImage();

            if (wrongGuesses >= 10) {
                Alert msg = new Alert(Alert.AlertType.INFORMATION);
                msg.setTitle("Game Over");
                msg.setHeaderText(null);
                msg.setContentText("Game Over! The word was: " + targetWord);
                msg.showAndWait();
                updateImage();
                return;
            }
        }
    }

    @FXML
    public void initialize() {
        targetWord = chooseRandomWord().toLowerCase();
        initializeUnknownWord(targetWord);
        updateImage();
        // initialize tried letters list
        if (trys == null) trys = new ArrayList<>();
        if (root != null) {
            root.requestFocus();
            root.setOnMouseClicked(e -> root.requestFocus());
        }
    }

    //choose a random word from the list
    String chooseRandomWord(){
        String[] words = Words.getWORDS();
        int randomIndex = (int) (Math.random() * words.length);
        return words[randomIndex];
    }

    //initialize the unknown word with underscores
    void initializeUnknownWord(String word) {
        unknownWordBuilder = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            unknownWordBuilder.append("_ ");
        }
        unknown_word.setText(unknownWordBuilder.toString());
    }

    private void updateImage() {
        String resourcePath = "/immaginiImpiccato/" + wrongGuesses + ".png";
        if (getClass().getResource(resourcePath) != null) {
            img.setImage(new Image(getClass().getResourceAsStream(resourcePath)));
        } else {
            img.setImage(new Image("file:immaginiImpiccato/" + wrongGuesses + ".png"));
        }
    }

}
