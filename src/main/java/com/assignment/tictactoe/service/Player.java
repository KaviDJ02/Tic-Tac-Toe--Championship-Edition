package com.assignment.tictactoe.service;

public abstract class Player {
    private BoardImpl board;

    Player(BoardImpl board) {
        this.board = board;
    }

    // Protected method to expose the board to subclasses like AiPlayer
    protected BoardImpl getBoard() {
        return board;
    }

    public abstract void move(int row, int col);
}
