package com.assignment.tictactoe.service;

public abstract class Player {
    protected Board board;

    Player(Board board) {
        this.board = board;
    }

    public abstract void move(int row, int col);
}
