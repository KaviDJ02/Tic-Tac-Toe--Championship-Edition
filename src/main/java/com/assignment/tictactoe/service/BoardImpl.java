// BoardImpl.java
package com.assignment.tictactoe.service;

public class BoardImpl implements Board {
    private Piece[][] board;

    public BoardImpl() {
        initializeBoard();
    }

    @Override
    public void initializeBoard() {
        board = new Piece[3][3];
        // Later: Initialize each cell as Piece.EMPTY
    }

    @Override
    public boolean isLegalMove(int row, int col) {
        // Later: Add logic to check if the move is valid
        return false;
    }

    @Override
    public void updateMove(int row, int col, Piece piece) {
        // Later: Add logic to update the board with the current move
    }

    @Override
    public Piece checkWinner() {
        // Later: Add logic to check if there's a winner
        return Piece.EMPTY;
    }

    @Override
    public void printBoard() {
        // Later: Add logic to print the current board state
    }
}
