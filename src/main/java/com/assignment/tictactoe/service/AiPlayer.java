package com.assignment.tictactoe.service;

public class AiPlayer extends Player {
    private Piece piece;  // AI's piece (X or O)
    private Piece opponentPiece;  // Opponent's piece
    private BoardImpl board;  // The game board

    // Constructor to initialize the AiPlayer with a board and piece
    public AiPlayer(BoardImpl board, Piece piece) {
        super(board); // Pass the board to the Player constructor
        this.board = board;
        this.piece = piece; // Store the AI's piece (X or O)
        this.opponentPiece = (piece == Piece.X) ? Piece.O : Piece.X; // Determine the opponent's piece
    }

    @Override
    public void move(int row, int col) {
        // Use minimax to determine the best move for the AI
        int[] bestMove = getBestMove();  // Call getBestMove to find the best move
        int bestRow = bestMove[0];
        int bestCol = bestMove[1];

        // Perform the move on the board if it's valid
        if (bestRow != -1 && bestCol != -1 && board.isLegalMove(bestRow, bestCol)) {
            board.updateMove(bestRow, bestCol, piece);
        }
    }

    // Method to find the best move for the AI using the minimax algorithm
    public int[] getBestMove() {
        int bestVal = Integer.MIN_VALUE;
        int[] bestMove = {-1, -1};

        // Traverse all cells, evaluate minimax function for all empty cells
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                // Check if cell is empty
                if (board.isLegalMove(i, j)) {
                    // Make the move
                    board.updateMove(i, j, piece);

                    // Compute evaluation function for this move
                    int moveVal = minimax(board, 0, false);

                    // Undo the move
                    board.updateMove(i, j, Piece.EMPTY);

                    // If the value of the current move is more than the best value, update best
                    if (moveVal > bestVal) {
                        bestMove[0] = i;
                        bestMove[1] = j;
                        bestVal = moveVal;
                    }
                }
            }
        }
        return bestMove;
    }

    // Minimax algorithm to calculate the best move for the AI
    private int minimax(BoardImpl board, int depth, boolean isMaximizing) {
        Winner winner = board.checkWinner();
        if (winner.winningPiece == piece) return 10 - depth;
        if (winner.winningPiece == opponentPiece) return depth - 10;
        if (board.isBoardFull()) return 0;

        if (isMaximizing) {
            int best = Integer.MIN_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board.isLegalMove(i, j)) {
                        board.updateMove(i, j, piece);
                        best = Math.max(best, minimax(board, depth + 1, false));
                        board.updateMove(i, j, Piece.EMPTY);
                    }
                }
            }
            return best;
        } else {
            int best = Integer.MAX_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board.isLegalMove(i, j)) {
                        board.updateMove(i, j, opponentPiece);
                        best = Math.min(best, minimax(board, depth + 1, true));
                        board.updateMove(i, j, Piece.EMPTY);
                    }
                }
            }
            return best;
        }
    }

    public Piece getPiece() {
        return piece;
    }
}