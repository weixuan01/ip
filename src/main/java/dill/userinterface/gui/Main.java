package dill.userinterface.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.Scene;
import javafx.stage.Stage;

import dill.Dill;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {
    private Dill dill = new Dill();

    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("Dill");
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDill(dill);  // inject the Dill instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

