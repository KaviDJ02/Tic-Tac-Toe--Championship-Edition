package com.assignment.tictactoe.controller;

import com.assignment.tictactoe.service.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class BoardController implements BoardUi {

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
    private AnchorPane gamePane;

    @FXML
    private Label msgLabel;

    @FXML
    private Label roundLabel;

    @FXML
    private ImageView oImage;

    @FXML
    private Label oScore;

    @FXML
    private AnchorPane welcomePane;

    @FXML
    private ImageView xImage;

    @FXML
    private Label xScore;

    private BoardImpl board = new BoardImpl();
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
            humanPlayer = new HumanPlayer(board, Piece.X);
            aiPlayer = new AiPlayer(board, Piece.O);
            isPlayerTurn = true;
        } else if (imageName.equals("oImage")) {
            humanPlayer = new HumanPlayer(board, Piece.O);
            aiPlayer = new AiPlayer(board, Piece.X);
            isPlayerTurn = false;
        }

        welcomePane.setVisible(false);
        gamePane.setVisible(true);

        board.initializeBoard();
        board.printBoard();
    }

    @FXML
    void backButtonOnAction(ActionEvent event) {
        // Handle back button action
    }

    @FXML
    void resetButtonOnAction(ActionEvent event) {
        board.initializeBoard();

        Button[][] buttons = {
                {cell1, cell2, cell3},
                {cell4, cell5, cell6},
                {cell7, cell8, cell9}
        };
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText(""); // Clear the text of each button
                buttons[i][j].setDisable(false); // Enable the button
            }
        }
        msgLabel.setText("Welcome");
    }

    @FXML
    void nextButtonOnAction(ActionEvent event) {
        // Handle next button action
    }

    @Override
    public void update(int row, int col, boolean isHuman) {
        Button buttonToUpdate = getButtonByPosition(row, col);

        // Update the button text based on the player
        if (buttonToUpdate != null) {
            buttonToUpdate.setText(isHuman ? humanPlayer.getPiece().toString() : aiPlayer.getPiece().toString());
        }

        // Update the board state
        board.updateMove(row, col, isHuman ? humanPlayer.getPiece() : aiPlayer.getPiece());

        // Check for a winner
        Winner winner = board.checkWinner();
        if (winner.winningPiece != Piece.EMPTY) {
            notifyWinner();
        }
    }

    @Override
    public void notifyWinner() {
        msgLabel.setText("Winner: " + humanPlayer.getPiece());
        disableAllCells();
    }

//    @Override
//    public void notifyWinner(Winner winner) {
//        msgLabel.setText("Winner: " + winner.winningPiece);
//        disableAllCells();
//    }

    public void handleCellClick(ActionEvent actionEvent) {
        Button clickedButton = (Button) actionEvent.getSource();
        int row = -1, col = -1;

        // Determine which button was clicked and set the row and column accordingly
        if (clickedButton == cell1) { row = 0; col = 0; }
        else if (clickedButton == cell2) { row = 0; col = 1; }
        else if (clickedButton == cell3) { row = 0; col = 2; }
        else if (clickedButton == cell4) { row = 1; col = 0; }
        else if (clickedButton == cell5) { row = 1; col = 1; }
        else if (clickedButton == cell6) { row = 1; col = 2; }
        else if (clickedButton == cell7) { row = 2; col = 0; }
        else if (clickedButton == cell8) { row = 2; col = 1; }
        else if (clickedButton == cell9) { row = 2; col = 2; }

        msgLabelTurn();

        // Perform the move if it's the player's turn
        if (isPlayerTurn && board.isLegalMove(row, col)) {
            humanPlayer.move(row, col);
            update(row, col, true);
            isPlayerTurn = false;

            // Let the AI make a move if the game is not over
            if (board.checkWinner().winningPiece == Piece.EMPTY && !board.isBoardFull()) {
                int[] aiMove = aiPlayer.getBestMove();
                aiPlayer.move(aiMove[0], aiMove[1]);
                update(aiMove[0], aiMove[1], false);
                isPlayerTurn = true;
            }
        }
    }

    public void msgLabelTurn() {
        if (isPlayerTurn) {
            msgLabel.setText("Your turn");
        } else {
            msgLabel.setText("AI's turn");
        }
    }

    private Button getButtonByPosition(int row, int col) {
        if (row == 0 && col == 0) return cell1;
        if (row == 0 && col == 1) return cell2;
        if (row == 0 && col == 2) return cell3;
        if (row == 1 && col == 0) return cell4;
        if (row == 1 && col == 1) return cell5;
        if (row == 1 && col == 2) return cell6;
        if (row == 2 && col == 0) return cell7;
        if (row == 2 && col == 1) return cell8;
        if (row == 2 && col == 2) return cell9;
        return null;
    }

    private void disableAllCells() {
        cell1.setDisable(true);
        cell2.setDisable(true);
        cell3.setDisable(true);
        cell4.setDisable(true);
        cell5.setDisable(true);
        cell6.setDisable(true);
        cell7.setDisable(true);
        cell8.setDisable(true);
        cell9.setDisable(true);
    }
}