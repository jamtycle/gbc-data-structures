package gbc.ds.assignment2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class TicTacToeApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        TicTacToeController controller = new TicTacToeController();
        controller.startMenu();
    }

    public static void main(String[] args) {
        launch();
    }
}