package com.assignment.tictactoe;

import com.assignment.tictactoe.service.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/com/assignment/tictactoe/Board.fxml"));
        primaryStage.setTitle("Tic-Tac-Toe: Championship Edition");
        primaryStage.setScene(new Scene(root));
        primaryStage.resizableProperty().setValue(Boolean.FALSE);
        primaryStage.show();
    }
}