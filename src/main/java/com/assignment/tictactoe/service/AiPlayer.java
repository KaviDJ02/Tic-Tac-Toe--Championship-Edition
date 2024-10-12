package com.assignment.tictactoe.service;

import java.util.Random;

public class AiPlayer extends Player {
    public AiPlayer(Piece piece) {
        super(piece);
    }

    @Override
    public int[] makeMove(Board board) {
        // Add AI logic for choosing a move
        int x=2 , y=2;
        return new int[]{x, y}; // AI's move coordinates
    }
}

