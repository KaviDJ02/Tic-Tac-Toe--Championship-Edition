package com.assignment.tictactoe.service;

public class BoardImpl implements Board{
    private Piece[][] pieces; // 2D array to represent the board

    public BoardImpl() {
        initializeBoard(); // Initialize the board when an instance is created
    }

    @Override
    public void getBoardUI() {

    }

    @Override
    public void initializeBoard() {
        pieces = new Piece[3][3]; // Create a 3x3 board
        // Set all cells to EMPTY
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                pieces[i][j] = Piece.EMPTY;
            }
        }
    }

    @Override
    public boolean isLegalMove(int row, int col) {
        // A move is legal if the cell is EMPTY
        return pieces[row][col] == Piece.EMPTY;
    }

    @Override
    public void updateMove(int row, int col, Piece piece) {
        // Update the cell with the given piece if the move is legal
        if (isLegalMove(row, col)) {
            pieces[row][col] = piece;
            printBoard();
        }
    }

    @Override
    public Piece checkWinner() {
        // Check rows for a winner
        for (int i = 0; i < 3; i++) {
            if (pieces[i][0] == pieces[i][1] && pieces[i][1] == pieces[i][2] && pieces[i][0] != Piece.EMPTY) {
                return pieces[i][0];
            }
        }
        // Check columns for a winner
        for (int i = 0; i < 3; i++) {
            if (pieces[0][i] == pieces[1][i] && pieces[1][i] == pieces[2][i] && pieces[0][i] != Piece.EMPTY) {
                return pieces[0][i];
            }
        }
        // Check diagonals for a winner
        if (pieces[0][0] == pieces[1][1] && pieces[1][1] == pieces[2][2] && pieces[0][0] != Piece.EMPTY) {
            return pieces[0][0];
        }
        if (pieces[0][2] == pieces[1][1] && pieces[1][1] == pieces[2][0] && pieces[0][2] != Piece.EMPTY) {
            return pieces[0][2];
        }
        // No winner found, return EMPTY
        return Piece.EMPTY;
    }

    @Override
    public void printBoard() {
        // Print the current state of the board
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(pieces[i][j] + " ");
            }
            System.out.println();
        }
    }
}