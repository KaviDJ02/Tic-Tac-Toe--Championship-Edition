package com.assignment.tictactoe.service;

public class AiPlayer extends Player {
    private Piece piece;  // AI's piece (X or O)

    // Constructor to initialize the AiPlayer with a board and piece
    public AiPlayer(BoardImpl board, Piece piece) {
        super(board); // Pass the board to the Player constructor
        this.piece = piece; // Store the AI's piece (X or O)
    }

    @Override
    public void move(int row, int col) {
        // Use minimax to determine the best move for the AI
        int[] bestMove = minimax(getBoard(), piece, true);  // Call minimax with the board and AI's piece
        int bestRow = bestMove[1];
        int bestCol = bestMove[2];

        // Perform the move on the board if it's valid
        if (bestRow != -1 && bestCol != -1 && getBoard().isLegalMove(bestRow, bestCol)) {
            getBoard().updateMove(bestRow, bestCol, piece);  // Update the board with AI's move
            System.out.println("AI move: [" + bestRow + ", " + bestCol + "]");
            //updateCell(bestRow, bestCol, piece.toString()); // Update the UI for AI move
        }
    }




    // Minimax algorithm to calculate the best move for the AI
    private int[] minimax(BoardImpl board, Piece currentPiece, boolean isMaximizing) {
        // Check if there is a winner
        Piece winner = board.checkWinner();
        if (winner == Piece.X) return new int[]{-1, -1, -1}; // X wins, return score -1 and dummy row/col
        if (winner == Piece.O) return new int[]{1, -1, -1};  // O wins, return score 1 and dummy row/col
        if (isBoardFull(board)) return new int[]{0, -1, -1}; // Draw, return score 0 and dummy row/col

        // Initialize the best score and move
        int bestScore = isMaximizing ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        int[] bestMove = {-1, -1};  // Store row and col of the best move

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
                            bestMove = new int[]{i, j};  // Store the row and column of the best move
                        }
                    } else {
                        if (score < bestScore) {
                            bestScore = score;
                            bestMove = new int[]{i, j};  // Store the row and column of the best move
                        }
                    }
                }
            }
        }

        // Return the best score along with the best move found
        return new int[]{bestScore, bestMove[0], bestMove[1]};
    }


    // Method to check if the board is full (no more legal moves)
    private boolean isBoardFull(BoardImpl board) {
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
