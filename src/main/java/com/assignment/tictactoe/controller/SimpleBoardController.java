package com.assignment.tictactoe.controller;

import com.assignment.tictactoe.service.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class SimpleBoardController implements BoardUi {

    @FXML
    private Button cell1, cell2, cell3, cell4, cell5, cell6, cell7, cell8, cell9;

    @FXML
    private Label msgLabel;


    private BoardImpl board = new BoardImpl();
    private HumanPlayer humanPlayer = new HumanPlayer(board, Piece.X);
    private AiPlayer aiPlayer = new AiPlayer(board, Piece.O);
    private boolean isPlayerTurn = true;
    private Button[][] buttons;
    private Winner winner;

    @FXML
    public void initialize() {

        // Initialize the buttons array here
        buttons = new Button[][]{
                {cell1, cell2, cell3},
                {cell4, cell5, cell6},
                {cell7, cell8, cell9}
        };

        board.initializeBoard();
    }


    @FXML
    void resetButtonOnAction(ActionEvent event) {
        board.initializeBoard();
        resetBoardUI();
        msgLabel.setText("Board Reset");
        isPlayerTurn = true;
    }

    private void resetBoardUI() {
        for (Button[] row : buttons) {
            for (Button button : row) {
                button.setText("");  // Clear the text of each button
                button.setDisable(false);  // Enable the button
                button.setStyle("");  // Reset any custom styles
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
        winner = board.checkWinner();
        if (winner.winningPiece != Piece.EMPTY) {
            notifyWinner();
        }
    }

    @Override
    public void notifyWinner() {
        if (winner != null && winner.winningPiece != Piece.EMPTY) {
            System.out.println("Winner: " + winner.winningPiece);
            msgLabel.setText("Winner: " + winner.winningPiece);
            markWinningMove();
            System.out.println("Winner Board\n" +
                    winner.col1 + " " + winner.col2 + " " + winner.col3 + "\n" +
                    winner.row1 + " " + winner.row2 + " " + winner.row3);
            disableAllCells();
            isPlayerTurn = true;
        } else if (board.isBoardFull()) {
            System.out.println("The game is a draw.");
            msgLabel.setText("The game is a draw.");
            isPlayerTurn = true;
        }
    }

    public void markWinningMove(){
        if (winner != null && winner.winningPiece != Piece.EMPTY) {
            Button button1 = getButtonByPosition(winner.row1, winner.col1);
            Button button2 = getButtonByPosition(winner.row2, winner.col2);
            Button button3 = getButtonByPosition(winner.row3, winner.col3);
            button1.setStyle("-fx-background-color: #00FF00");
            button2.setStyle("-fx-background-color: #00FF00");
            button3.setStyle("-fx-background-color: #00FF00");
        }
    }

    public void handleCellClick(ActionEvent actionEvent) {
        Button clickedButton = (Button) actionEvent.getSource();
        int row = getButtonRow(clickedButton);
        int col = getButtonCol(clickedButton);

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

    private Button getButtonByPosition(int row, int col) {
        return buttons[row][col];
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
        for (Button[] row : buttons) {
            for (Button button : row) {
                button.setDisable(true);
            }
        }
    }
}