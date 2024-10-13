package com.assignment.tictactoe.controller;

import com.assignment.tictactoe.service.AiPlayer;
import com.assignment.tictactoe.service.BoardImpl;
import com.assignment.tictactoe.service.HumanPlayer;
import com.assignment.tictactoe.service.Piece;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class BoardController {

    @FXML
    private GridPane boardGrid; // The grid layout for the Tic-Tac-Toe board

    @FXML
    private Label msgLabel; // Label to display messages to the user

    @FXML
    private Button cell00, cell01, cell02, cell10, cell11, cell12, cell20, cell21, cell22; // Buttons representing each cell on the board

    private BoardImpl board; // The game board
    private HumanPlayer playerX; // Human player using piece X
    private AiPlayer playerO; // AI player using piece O
    private boolean isXTurn; // Flag to track whose turn it is

    @FXML
    public void initialize() {
        board = new BoardImpl(); // Initialize the game board
        playerX = new HumanPlayer(Piece.X); // Initialize the human player with piece X
        playerO = new AiPlayer(Piece.O); // Initialize the AI player with piece O
        isXTurn = true; // X always starts first
        initializeBoard(); // Set up the board UI
    }

    private void initializeBoard() {
        // Array of buttons representing the board cells
        Button[][] buttons = {
                {cell00, cell01, cell02},
                {cell10, cell11, cell12},
                {cell20, cell21, cell22}
        };

        // Set up event handlers for each button
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                final int row = i;
                final int col = j;
                buttons[i][j].setOnAction(event -> handleCellClick(row, col));
            }
        }
    }

    private void handleCellClick(int row, int col) {
        // Handle a cell click event
        if (board.isLegalMove(row, col)) { // Check if the move is legal
            if (isXTurn) {
                board.updateMove(row, col, Piece.X); // Update the board with piece X
                updateCell(row, col, "X"); // Update the UI
            } else {
                board.updateMove(row, col, Piece.O); // Update the board with piece O
                updateCell(row, col, "O"); // Update the UI
            }
            isXTurn = !isXTurn; // Switch turns

            Piece winner = board.checkWinner(); // Check for a winner
            if (winner != Piece.EMPTY) {
                System.out.println("Winner: " + winner); // Announce the winner
                resetBoard(); // Reset the board for a new game
            } else if (isBoardFull()) {
                System.out.println("It's a draw!"); // Announce a draw
                resetBoard(); // Reset the board for a new game
            }
        }
    }

    private void updateCell(int row, int col, String symbol) {
        // Update the UI for a specific cell
        Button[][] buttons = {
                {cell00, cell01, cell02},
                {cell10, cell11, cell12},
                {cell20, cell21, cell22}
        };
        buttons[row][col].setText(symbol); // Set the text of the button to the player's symbol
    }

    private void resetBoard() {
        // Reset the game board and UI for a new game
        board.initializeBoard(); // Reinitialize the game board
        Button[][] buttons = {
                {cell00, cell01, cell02},
                {cell10, cell11, cell12},
                {cell20, cell21, cell22}
        };
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText(""); // Clear the text of each button
            }
        }
        isXTurn = true; // X always starts first
    }

    private boolean isBoardFull() {
        // Check if the board is full (no more legal moves)
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.isLegalMove(i, j)) {
                    return false; // If there is at least one legal move, the board is not full
                }
            }
        }
        return true; // No legal moves left, the board is full
    }
}