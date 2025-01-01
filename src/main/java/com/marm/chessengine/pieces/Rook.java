/******************************************
 *CSCI 205 - Software Engineering and Design
 *Spring 2024
 *Instructor: Prof. Lily Romano / Prof. Joshua Stough
 *
 *Name: Miguel Romero
 *Section: 01
 *Date: 12/31/24
 *Time: 12:34 AM
 *
 *Project: chessEngine
 *Package: com.marm.chessengine.pieces
 *Class: Rook
 *Description:
 * **************************************** */
package com.marm.chessengine.pieces;

import com.marm.chessengine.Alliance;
import com.marm.chessengine.board.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Rook extends Piece{
    private final static int[][] CANDIDATE_MOVE_VECTOR_COORDINATES = {{1,0}, {-1,0}, {0,1}, {0,-1}};
    public Rook(int pieceXCord, int pieceYCord, Alliance alliance) {
        super(pieceXCord, pieceYCord, alliance);
    }

    @Override
    public List<Move> calculateLegalMoves(final Board board) {
        return this.legalMovesQRB(board, CANDIDATE_MOVE_VECTOR_COORDINATES);
    }
}