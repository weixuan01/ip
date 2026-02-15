package dill.userinterface.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import dill.Dill;

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

    private Dill dill;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image dillImage = new Image(this.getClass().getResourceAsStream("/images/dill.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Dill instance */
    public void setDill(Dill d) {
        dill = d;
        String bootMessage = dill.getGuiBootMessage();
        dialogContainer.getChildren().add(DialogBox.getDillDialog(bootMessage, dillImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = dill.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDillDialog(response, dillImage)
        );
        if (input.equals("bye")) {
            javafx.animation.PauseTransition delay = new javafx.animation.PauseTransition(javafx.util.Duration.seconds(3));
            delay.setOnFinished(event -> javafx.application.Platform.exit());
            delay.play();
        }
        userInput.clear();
    }
}
