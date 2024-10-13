package com.assignment.tictactoe.service;

public class AiPlayer extends Player {
    public AiPlayer(Piece piece) {
        super(piece); // Call the superclass constructor to set the piece (X or O) for the AI player
    }

    @Override
    public int[] makeMove(Board board) {
        // Use the minimax algorithm to determine the best move for the AI player
        int[] bestMove = minimax(board, piece, true);
        // Return the row and column of the best move
        return new int[]{bestMove[1], bestMove[2]};
    }

    private int[] minimax(Board board, Piece currentPiece, boolean isMaximizing) {
        // Check if there is a winner
        Piece winner = board.checkWinner();
        if (winner == Piece.X) return new int[]{-1}; // X wins, return score -1
        if (winner == Piece.O) return new int[]{1}; // O wins, return score 1
        if (isBoardFull(board)) return new int[]{0}; // Draw, return score 0

        // Initialize the best score and move
        int bestScore = isMaximizing ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        int[] bestMove = new int[3];

        // Iterate through all cells on the board
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                // Check if the cell is empty
                if (board.isLegalMove(i, j)) {
                    // Make the move
                    board.updateMove(i, j, currentPiece);
                    // Recursively call minimax to evaluate the move
                    int score = minimax(board, currentPiece == Piece.X ? Piece.O : Piece.X, !isMaximizing)[0];
                    // Undo the move
                    board.updateMove(i, j, Piece.EMPTY);

                    // Update the best score and move based on whether we are maximizing or minimizing
                    if (isMaximizing) {
                        if (score > bestScore) {
                            bestScore = score;
                            bestMove = new int[]{score, i, j};
                        }
                    } else {
                        if (score < bestScore) {
                            bestScore = score;
                            bestMove = new int[]{score, i, j};
                        }
                    }
                }
            }
        }
        // Return the best move found
        return bestMove;
    }

    private boolean isBoardFull(Board board) {
        // Check if there are any legal moves left on the board
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.isLegalMove(i, j)) {
                    return false; // Found an empty cell, board is not full
                }
            }
        }
        return true; // No empty cells found, board is full
    }
}