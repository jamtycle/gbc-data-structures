package gbc.ds.assignment2;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.util.Pair;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class TicTacToeController implements Initializable {

    private final Integer[][] board = new Integer[3][3];

    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    // Task 1
    private Pair<Integer, Integer> getRandomPosition() throws Exception /*TODO: Remove the exception thrown*/ {
        // TODO: Get two numbers from 0 - 2 considerations:
        //          - Only randomize the numbers that are not 0 in the board.
        //          - You can randomize a 1D array and then transform to 2D position.
        throw new Exception("Not implemented yet");
    }

    // Task 2
    // TODO: Add below all the functions for detecting a click in a Pane inside the GridPane.
    //          - The function must return the position of the Pane, for instance
    //                  if I click Pane in position (0, 1) the function must return that coordinate.
    //                  hint: for returning the coordinate, use a Pair<Integer, Integer>
    //          - In case there is not a way to detect which Pane is begin hit and return the coordinate,
    //                  please, make a field in this class and set the coordinate to the field.

    public void startMenu() {
        Dialog<ButtonType> box = new Dialog<>();
        box.setTitle("Tic Tac Toe");
        box.setHeaderText("Please fill in your information before you begin!");

        Label nl = new Label("Name:");
        TextField name = new TextField();
        Label dl = new Label("Difficulty:");
        ToggleGroup difficulty = new ToggleGroup();
        RadioButton weak = new RadioButton("Weak");
        weak.setToggleGroup(difficulty);
        weak.setSelected(true);
        RadioButton intelligent = new RadioButton("Intelligent");
        intelligent.setToggleGroup(difficulty);

        Label nl2 = new Label("Choose your symbol:");
        ToggleGroup symbol = new ToggleGroup();
        RadioButton x = new RadioButton("X's");
        x.setToggleGroup(symbol);
        x.setSelected(true);
        RadioButton o = new RadioButton("O's");
        o.setToggleGroup(symbol);

        VBox inputBox = new VBox(15, nl, name, dl, weak, intelligent, nl2, x, o);

        box.getDialogPane().setContent(inputBox);

        ButtonType go = new ButtonType("Go", ButtonBar.ButtonData.OK_DONE);
        box.getDialogPane().getButtonTypes().addAll(go, ButtonType.CANCEL);

        Node goButton = box.getDialogPane().lookupButton(go);
        goButton.setDisable(true);
        name.textProperty().addListener((observable, oldValue, newValue) -> goButton.setDisable(newValue.trim().isEmpty()));

        Optional<ButtonType> result = box.showAndWait();

        if (result.isPresent() && result.get() == go) {
            String user = name.getText();
            String userDiff = "";
            String userSymbol = "";
            if (intelligent.isSelected()){
                userDiff = "Intelligent";
            }
            else{
                userDiff = "Weak";
            }

            if (x.isSelected()){
                userSymbol = "X";
            }
            else{
                userSymbol = "O";
            }

            startGame(user, userDiff, userSymbol);
        }
    }

    public void startGame(String user, String userDiff, String userSymbol) {
        final int SIZE = 3;
        final int CELL_SIZE = 100;
        final Color HOVER_COLOR = Color.LIGHTGRAY;
        final Color CLICKED_COLOR = Color.LIGHTBLUE;
        GridPane game = new GridPane();
        game.setAlignment(Pos.CENTER);
        game.setHgap(5);
        game.setVgap(5);
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                Rectangle cell = new Rectangle(CELL_SIZE, CELL_SIZE, Color.WHITE);
                cell.setStroke(Color.BLACK);
                int row = i;
                int col = j;
                cell.setOnMouseEntered(event -> cell.setFill(HOVER_COLOR));
                cell.setOnMouseExited(event -> cell.setFill(Color.WHITE));
                cell.setOnMouseClicked(event -> {
                    cell.setFill(CLICKED_COLOR);
                    Label symbol = new Label(userSymbol);
                    symbol.setStyle("-fx-font-size: 72px;");
                    StackPane pane_with_symbol = new StackPane(symbol);
                    pane_with_symbol.setBackground(new Background(new BackgroundFill(CLICKED_COLOR, CornerRadii.EMPTY, Insets.EMPTY)));
                    game.add(pane_with_symbol, col, row);
                });
                game.add(cell, j, i);
            }
        }
        Scene scene = new Scene(game);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Welcome! " + user);
        stage.show();
    }
}
