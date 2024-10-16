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
    private Button cell1, cell2, cell3, cell4, cell5, cell6, cell7, cell8, cell9;

    @FXML
    private AnchorPane gamePane, welcomePane;

    @FXML
    private Label msgLabel, roundLabel, oScore, xScore;

    @FXML
    private ImageView oImage, xImage;

    private BoardImpl board = new BoardImpl();
    private HumanPlayer humanPlayer;
    private AiPlayer aiPlayer;
    private boolean isPlayerTurn;
    private Button[][] buttons = {{cell1, cell2, cell3}, {cell4, cell5, cell6}, {cell7, cell8, cell9}};

    @FXML
    public void initialize() {
        welcomePane.setVisible(true);
        gamePane.setVisible(false);

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
    void nextButtonOnAction(ActionEvent event) {
        // Handle next button action
    }

    @FXML
    void resetButtonOnAction(ActionEvent event) {
        board.initializeBoard();
        resetBoardUI();
        msgLabel.setText("Welcome");
    }

    private void resetBoardUI() {
        for (Button[] row : buttons) {
            for (Button button : row) {
                button.setText("");  // Clear the text of each button
                button.setDisable(false);  // Enable the button
            }
        }
    }

    @Override
    public void update(int row, int col, boolean isHuman) {
        Button buttonToUpdate = getButtonByPosition(row, col);
        if (buttonToUpdate != null) {
            buttonToUpdate.setText(isHuman ? humanPlayer.getPiece().toString() : aiPlayer.getPiece().toString());
            buttonToUpdate.setDisable(true);  // Disable the button after it has been used
        }

        // Update the board and check for a winner
        board.updateMove(row, col, isHuman ? humanPlayer.getPiece() : aiPlayer.getPiece());
        Winner winner = board.checkWinner();
        if (winner.winningPiece != Piece.EMPTY) {
            notifyWinner();
        }
    }

    @Override
    public void notifyWinner() {
        msgLabel.setText("Winner: " + (isPlayerTurn ? "You" : "AI"));
        disableAllCells();
    }

    public void handleCellClick(ActionEvent actionEvent) {
        Button clickedButton = (Button) actionEvent.getSource();
        int row = getButtonRow(clickedButton);
        int col = getButtonCol(clickedButton);

        if (isPlayerTurn && board.isLegalMove(row, col)) {
            humanPlayer.move(row, col);
            update(row, col, true);
            isPlayerTurn = false;

            if (board.checkWinner().winningPiece == Piece.EMPTY && !board.isBoardFull()) {
                int[] aiMove = aiPlayer.getBestMove();
                aiPlayer.move(aiMove[0], aiMove[1]);
                update(aiMove[0], aiMove[1], false);
                isPlayerTurn = true;
            }
        }

        msgLabelTurn();
    }

    public void msgLabelTurn() {
        msgLabel.setText(isPlayerTurn ? "Your turn" : "AI's turn");
    }

    private Button getButtonByPosition(int row, int col) {
        switch (row * 3 + col) {
            case 0: return cell1;
            case 1: return cell2;
            case 2: return cell3;
            case 3: return cell4;
            case 4: return cell5;
            case 5: return cell6;
            case 6: return cell7;
            case 7: return cell8;
            case 8: return cell9;
            default: return null;
        }
    }

    private int getButtonRow(Button button) {
        if (button == cell1 || button == cell2 || button == cell3) return 0;
        if (button == cell4 || button == cell5 || button == cell6) return 1;
        if (button == cell7 || button == cell8 || button == cell9) return 2;
        return -1;
    }

    private int getButtonCol(Button button) {
        if (button == cell1 || button == cell4 || button == cell7) return 0;
        if (button == cell2 || button == cell5 || button == cell8) return 1;
        if (button == cell3 || button == cell6 || button == cell9) return 2;
        return -1;
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

    private void disableCell(int row, int col) {
        buttons[row][col].setDisable(true);
    }
}
