package com.assignment.tictactoe.service;

public abstract class Player {
    protected Piece piece; // The piece (X or O) that the player will use

    public Player(Piece piece) {
        this.piece = piece; // Initialize the player's piece
    }

    public Piece getPiece() {
        return piece;
    }

    // Abstract method to make a move on the board
    // This method must be implemented by subclasses (e.g., HumanPlayer, AiPlayer)
    public abstract int[] makeMove(Board board);
}