package com.assignment.tictactoe.service;

public class Winner {
    Piece winningPiece;
    int col1, col2, col3, row1, row2, row3;

    public Winner(Piece winningPiece) {
        this.winningPiece = winningPiece;
    }

    public Winner(Piece winningPiece, int col1, int col2, int col3, int row1, int row2, int row3) {
        this.winningPiece = winningPiece;
        this.col1 = col1;
        this.col2 = col2;
        this.col3 = col3;
        this.row1 = row1;
        this.row2 = row2;
        this.row3 = row3;
    }
}
