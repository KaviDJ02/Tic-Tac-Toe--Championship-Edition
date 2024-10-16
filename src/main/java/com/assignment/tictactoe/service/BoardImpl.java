package com.assignment.tictactoe.service;

public class BoardImpl implements Board{
    public Piece[][] pieces; // 2D array to represent the board
    public BoardUi boardUi; // Reference to the board UI


    public BoardImpl() {
        initializeBoard(); // Initialize the board when an instance is created
    }

    @Override
    public BoardUi getBoardUI() {
        return boardUi;
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
        } else if (piece == Piece.EMPTY) {
            pieces[row][col] = piece;
        }

        //System.out.println("Move made by: " + piece + " at row: " + row + " col: " + col);

    }

    @Override
    public Winner checkWinner() {
        // Check rows for a winner
        for (int row = 0; row < 3; row++) {
            if (pieces[row][0] != Piece.EMPTY && pieces[row][0] == pieces[row][1] && pieces[row][1] == pieces[row][2]) {
                return new Winner(pieces[row][0], 0, 1, 2, row, row, row);
            }
        }

        // Check columns for a winner
        for (int col = 0; col < 3; col++) {
            if (pieces[0][col] != Piece.EMPTY && pieces[0][col] == pieces[1][col] && pieces[1][col] == pieces[2][col]) {
                return new Winner(pieces[0][col], col, col, col, 0, 1, 2);
            }
        }

        // Check diagonals for a winner
        if (pieces[0][0] != Piece.EMPTY && pieces[0][0] == pieces[1][1] && pieces[1][1] == pieces[2][2]) {
            return new Winner(pieces[0][0], 0, 1, 2, 0, 1, 2);
        }
        if (pieces[0][2] != Piece.EMPTY && pieces[0][2] == pieces[1][1] && pieces[1][1] == pieces[2][0]) {
            return new Winner(pieces[0][2], 2, 1, 0, 0, 1, 2);
        }

        // No winner found
        return new Winner(Piece.EMPTY);
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

    public boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (isLegalMove(i, j)) {
                    return false; // Found an empty cell, board is not full
                }
            }
        }
        return true; // No empty cells found, board is full
    }
}