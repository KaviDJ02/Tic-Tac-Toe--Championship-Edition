package com.assignment.tictactoe.service;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class BoardUi {

    private GridPane boardGrid; // The grid layout for the Tic-Tac-Toe board
    private Button[][] cells; // 2D array of buttons representing each cell on the board

    public BoardUi(GridPane boardGrid) {
        this.boardGrid = boardGrid; // Initialize the board grid
        initializeBoard(); // Set up the board UI
    }

    private void initializeBoard() {
        // Initialize the 2D array of buttons
        cells = new Button[3][3];
        // Iterate through each cell in the grid
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cells[i][j] = new Button(); // Create a new button for each cell
                cells[i][j].setMinSize(100, 100); // Set the size of the button
                final int row = i;
                final int col = j;
                // Set up event handler for each button
                cells[i][j].setOnAction(event -> handleCellClick(row, col));
                boardGrid.add(cells[i][j], j, i); // Add the button to the grid
            }
        }
    }

    private void handleCellClick(int row, int col) {
        // Triggered when a button (cell) is clicked
        // Call controller to update game state here (communicate with BoardController)
        // Example: boardController.handleCellClick(row, col);
    }

    public void updateCell(int row, int col, String symbol) {
        // Update the UI when a player makes a move
        cells[row][col].setText(symbol); // Set the text of the button to the player's symbol
    }

    public void resetBoard() {
        // Clear all cells for a new game
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cells[i][j].setText(""); // Clear the text of each button
            }
        }
    }
}