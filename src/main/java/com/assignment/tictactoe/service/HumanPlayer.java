package com.assignment.tictactoe.service;

public class HumanPlayer extends Player {
    public HumanPlayer(Piece piece) {
        super(piece);
    }

    @Override
    public int[] makeMove(Board board) {
        // Add logic to capture the human player's move, possibly via button click on the UI
        int x=2 , y=2;
        return new int[]{x, y};
    }
}

