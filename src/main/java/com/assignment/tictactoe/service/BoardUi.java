package com.assignment.tictactoe.service;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class BoardUi {

    private GridPane boardGrid;
    private Button[][] cells;

    public BoardUi(GridPane boardGrid) {

    }

    private void initializeBoard() {

    }

    private void handleCellClick(int row, int col) {
        // Triggered when a button (cell) is clicked
        // Call controller to update game state here (communicate with BoardController)
    }

    public void updateCell(int row, int col, String symbol) {
        // Update the UI when a player makes a move
    }

    public void resetBoard() {
        // Clear all cells for a new game
    }
}
