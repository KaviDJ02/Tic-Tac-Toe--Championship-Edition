package com.assignment.tictactoe.service;

public interface BoardUi {

    void update(int row, int col, boolean isHuman);

    void notifyWinner();

}