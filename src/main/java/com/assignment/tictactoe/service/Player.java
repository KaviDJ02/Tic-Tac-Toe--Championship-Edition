package com.assignment.tictactoe.service;

public abstract class Player {
    protected Piece piece;

    public Player(Piece piece) {
        this.piece = piece;
    }

    public Piece getPiece() {
        return piece;
    }

    public abstract int[] makeMove(Board board);
}

