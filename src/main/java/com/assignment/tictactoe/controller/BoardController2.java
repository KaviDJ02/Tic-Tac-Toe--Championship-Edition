// src/main/java/com/assignment/tictactoe/controller/BoardController2.java
package com.assignment.tictactoe.controller;

import com.assignment.tictactoe.service.AiPlayer;
import com.assignment.tictactoe.service.BoardImpl;
import com.assignment.tictactoe.service.HumanPlayer;
import com.assignment.tictactoe.service.Piece;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class BoardController2 {

    @FXML
    private ImageView oImage;

    @FXML
    private Button cell1;

    @FXML
    private Button cell2;

    @FXML
    private Button cell3;

    @FXML
    private Button cell4;

    @FXML
    private Button cell5;

    @FXML
    private Button cell6;

    @FXML
    private Button cell7;

    @FXML
    private Button cell8;

    @FXML
    private Button cell9;

    @FXML
    private Label msgLabel;

    @FXML
    private Label oScore;

    @FXML
    private AnchorPane welcomePane;

    @FXML
    private AnchorPane gamePane;

    @FXML
    private ImageView xImage;

    @FXML
    private Label xScore;

    private BoardImpl board;
    private HumanPlayer humanPlayer;
    private AiPlayer aiPlayer;
    private boolean isPlayerTurn;

    @FXML
    public void initialize() {
        welcomePane.setVisible(true);
        gamePane.setVisible(false);

        // Add event handlers for image clicks
        oImage.setOnMouseClicked(event -> handleImageClick("oImage"));
        xImage.setOnMouseClicked(event -> handleImageClick("xImage"));
    }

    private void handleImageClick(String imageName) {
        System.out.println("User clicked on: " + imageName);

        if (imageName.equals("xImage")) {
            humanPlayer = new HumanPlayer(Piece.X);
            aiPlayer = new AiPlayer(Piece.O);
            isPlayerTurn = true;
        } else if (imageName.equals("oImage")) {
            humanPlayer = new HumanPlayer(Piece.O);
            aiPlayer = new AiPlayer(Piece.X);
            isPlayerTurn = false;
        }

        board = new BoardImpl();

        welcomePane.setVisible(false);
        gamePane.setVisible(true);

        initializeBoard();

        if (!isPlayerTurn) {
            aiMove();
        }
    }

    private void initializeBoard() {
        // Array of buttons representing the board cells
        Button[][] buttons = {
                {cell1, cell2, cell3},
                {cell4, cell5, cell6},
                {cell7, cell8, cell9}
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
        if (board.isLegalMove(row, col) && isPlayerTurn) {  // Only allow human moves during player's turn
            board.updateMove(row, col, humanPlayer.getPiece());
            updateCell(row, col, humanPlayer.getPiece().toString());
            board.printBoard();
            isPlayerTurn = false;
            checkGameState();  // Check if the game ends before allowing AI to move

            if (!isPlayerTurn) {
                aiMove();  // Trigger AI move only if it's not the player's turn
            }
        }
    }

    private void handleCellClick2(int row, int col){
        if (board.isLegalMove(row, col)){
            if (isPlayerTurn){
                humanPlayer.makeMove(board);
            }else aiPlayer.makeMove(board);

            board.checkWinner();
        }
    }

    private void aiMove() {
        int[] move = aiPlayer.makeMove(board); // Get the AI's move
        board.updateMove(move[0], move[1], aiPlayer.getPiece()); // Update the board with the AI's piece
        board.printBoard();
        updateCell(move[0], move[1], aiPlayer.getPiece().toString()); // Update the UI
        isPlayerTurn = true; // Switch to human player's turn

        checkGameState();
    }

    private void updateCell(int row, int col, String symbol) {
        // Update the UI for a specific cell
        Button[][] buttons = {
                {cell1, cell2, cell3},
                {cell4, cell5, cell6},
                {cell7, cell8, cell9}
        };
        buttons[row][col].setText(symbol); // Set the text of the button to the player's symbol
    }

    private void resetBoard() {
        // Reset the game board and UI for a new game
        board.initializeBoard(); // Reinitialize the game board
        Button[][] buttons = {
                {cell1, cell2, cell3},
                {cell4, cell5, cell6},
                {cell7, cell8, cell9}
        };
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText(""); // Clear the text of each button
            }
        }
        isPlayerTurn = true; // X always starts first
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

    private void checkGameState() {
        Piece winner = board.checkWinner(); // Check for a winner
        if (winner != Piece.EMPTY) {
            System.out.println("Winner: " + winner); // Announce the winner
            board.printBoard();
            resetBoard(); // Reset the board for a new game
        } else if (isBoardFull()) {
            System.out.println("It's a draw!"); // Announce a draw
            board.printBoard();
            resetBoard(); // Reset the board for a new game
        }
    }

    @FXML
    void BackButtonOnAction(ActionEvent event) {
        // Handle back button action
    }

    @FXML
    void resetButtonOnAction(ActionEvent event) {
        // Handle reset button action
        resetBoard();
    }
}