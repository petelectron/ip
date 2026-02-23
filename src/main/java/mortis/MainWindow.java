package mortis;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Mortis mortis;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image mortisImage = new Image(this.getClass().getResourceAsStream("/images/Mortis.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Mortis instance */
    public void setMortis(Mortis m) {
        mortis = m;
        String welcome = mortis.getWelcomeMessage() + "\n" + mortis.getInitMessage();
        dialogContainer.getChildren().add(
            DialogBox.getMortisDialog(welcome, mortisImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = mortis.getResponse(input);
        dialogContainer.getChildren().addAll(
            DialogBox.getUserDialog(input, userImage),
            DialogBox.getMortisDialog(response, mortisImage)
        );
        userInput.clear();

        if (mortis.shouldExit()) {
            // Close the window after a short delay to allow the user to see the goodbye message
            javafx.animation.PauseTransition delay = new javafx.animation.PauseTransition(javafx.util.Duration.seconds(1.5));
            delay.setOnFinished(event -> {
                // Get the stage (window) and close it
                javafx.stage.Stage stage = (javafx.stage.Stage) dialogContainer.getScene().getWindow();
                stage.close();
            });
            delay.play();
        }
    }
}
