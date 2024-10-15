package com.assignment.tictactoe.service;

public class HumanPlayer extends Player {
    private Piece piece;  // The piece the human player is using (X or O)

    // Constructor that accepts the player's piece (X or O)
    public HumanPlayer(BoardImpl board, Piece piece) {
        super(board);  // Call the superclass constructor to initialize the board
        this.piece = piece;  // Store the player's piece (X or O)
    }

    @Override
    public void move(int row, int col) {
        // Access the board through the getBoard() method from the Player class

    }

    public Piece getPiece() {
        return piece;
    }
}
