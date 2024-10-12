package com.assignment.tictactoe.controller;

import com.assignment.tictactoe.service.*;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;

public class BoardController {

    @FXML
    private GridPane boardGrid;

    private BoardImpl board;
    private BoardUi boardUi;
    private HumanPlayer playerX;
    private AiPlayer playerO;
    private boolean isXTurn;

    public void initialize() {

    }

    @FXML
    public void handleClick(int row, int col) {
        // Handle the logic for player move
    }

    private void checkWin() {
        // Add logic to check for winner after every move
    }

    @FXML
    public void resetGame() {

    }
}
