/******************************************
 *CSCI 205 - Software Engineering and Design
 *Spring 2024
 *Instructor: Prof. Lily Romano / Prof. Joshua Stough
 *
 *Name: Miguel Romero
 *Section: 01
 *Date: 12/31/24
 *Time: 12:42 AM
 *
 *Project: chessEngine
 *Package: com.marm.chessengine.pieces
 *Class: Queen
 *Description:
 * **************************************** */
package com.marm.chessengine.pieces;

import com.marm.chessengine.Alliance;
import com.marm.chessengine.board.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Queen extends Piece{
    private final static int[][] CANDIDATE_MOVE_VECTOR_COORDINATES = {{1,1}, {1,-1}, {-1,1}, {-1,-1}, {1,0}, {-1,0}, {0,1}, {0,-1}};
    public Queen(int pieceXCord, int pieceYCord, Alliance alliance) {
        super(pieceXCord, pieceYCord, alliance,PieceType.QUEEN);
    }

    @Override
    public List<Move> calculateLegalMoves(final Board board) {
        return this.legalMovesQRB(board, CANDIDATE_MOVE_VECTOR_COORDINATES);
    }

    @Override
    public String toString(){
        return PieceType.QUEEN.toString();
    }
}