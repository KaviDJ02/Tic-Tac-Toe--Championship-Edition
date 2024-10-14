package com.assignment.tictactoe.service;

public interface Board {
    //void getBoardUI();
    // Initialize the game board to its starting state
    void initializeBoard();

    // Check if a move is legal (i.e., the cell is empty)
    boolean isLegalMove(int row, int col);

    // Update the board with a move (place a piece on the board)
    void updateMove(int row, int col, Piece piece);

    // Check if there is a winner on the board
    Piece checkWinner();

    // Print the current state of the board (for debugging purposes)
    void printBoard();
}