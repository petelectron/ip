package mortis;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.util.Collections;

/**
 * Represents a dialog box consisting of an ImageView to represent the speaker's face
 * and a label containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
            this.setMargin(this, new javafx.geometry.Insets(5));
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(img);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.setStyle("-fx-background-color: rgba(240, 221, 200, 0.95); -fx-background-radius: 12; -fx-padding: 10;");
        db.dialog.setStyle("-fx-text-fill: #212B50;");
        return db;
    }

    public static DialogBox getMortisDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        db.setStyle("-fx-background-color: rgba(233, 240, 225, 0.95); -fx-background-radius: 12; -fx-padding: 10;");
        db.dialog.setStyle("-fx-text-fill: #212121;");
        return db;
    }
}
