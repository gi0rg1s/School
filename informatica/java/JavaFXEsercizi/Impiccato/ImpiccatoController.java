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

        //check if the player guessed a letter
        String lowerKey = event.getText().toLowerCase();
        if (lowerKey.matches("[a-z]")) {

            //if the letter is in the word, it will be revealed
            char letter = lowerKey.charAt(0);

            //check if the player guessed a letter in the word
            for (int i = 0; i < targetWord.length(); i += 2) {
                if (letter == targetWord.charAt(i)) {
                    guess = true;
                    unknownWordBuilder.setCharAt(i / 2, letter);
                    unknown_word.setText(unknownWordBuilder.toString());
                    
                    if(!(unknownWordBuilder.toString().contains("_"))){
                        Alert msg = new Alert(Alert.AlertType.INFORMATION);
                        msg.setTitle("Congratulations!");
                        msg.setHeaderText(null);
                        msg.setContentText("Congratulations! You guessed the word: " + targetWord);
                        msg.showAndWait();
                        return;
                    }
                }
            }

            //if not it made an error
            if(!guess){
                wrongGuesses++;

                //update the image
                updateImage();
                //game over
                if(wrongGuesses >= 10){
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
    }

    @FXML
    public void initialize() {
        targetWord = chooseRandomWord().toLowerCase();
        initializeUnknownWord(targetWord);
        updateImage();
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
