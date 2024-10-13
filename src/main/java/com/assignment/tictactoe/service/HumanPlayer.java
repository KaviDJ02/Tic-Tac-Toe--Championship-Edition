package com.assignment.tictactoe.service;

public class HumanPlayer extends Player {
    public HumanPlayer(Piece piece) {
        super(piece); // Call the superclass constructor to set the piece (X or O) for the human player
    }

    @Override
    public int[] makeMove(Board board) {
        // Add logic to capture the human player's move, possibly via button click on the UI
        // For now, we will return a dummy move (row 0, column 0)
        return new int[]{0, 0};
    }
}