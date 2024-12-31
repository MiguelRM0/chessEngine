/******************************************
 *CSCI 205 - Software Engineering and Design
 *Spring 2024
 *Instructor: Prof. Lily Romano / Prof. Joshua Stough
 *
 *Name: Miguel Romero
 *Section: 01
 *Date: 12/30/24
 *Time: 9:20 PM
 *
 *Project: chessEngine
 *Package: com.marm.chessengine.pieces
 *Class: Knight
 *Description:
 * **************************************** */
package com.marm.chessengine.pieces;

import com.marm.chessengine.Alliance;
import com.marm.chessengine.board.Board;
import com.marm.chessengine.board.BoardUtils;
import com.marm.chessengine.board.Move;
import com.marm.chessengine.board.Tile;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Knight extends Piece{

    private final static int[][] CANDIDATE_MOVE_COORDINATES = {{2, 1}, {2, -1}, {-2, 1}, {-2, -1}, {1, 2}, {1, -2}, {-1, 2}, {-1, -2}};

    Knight(final int pieceXCord, final int pieceYCord, final Alliance alliance) {
        super(pieceXCord, pieceYCord, alliance);
    }

    @Override
    public List<Move> calculateLegalMoves(Board board) {
        Pair<Integer, Integer> candidateDestinationCoordinate;
        final List<Move> legalMoves = new ArrayList<>();
        for(int[] candidate: CANDIDATE_MOVE_COORDINATES){
            candidateDestinationCoordinate = new Pair<>(this.pieceXCord + candidate[0] , this.pieceYCord + candidate[1]);

            if ( BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)){
                final Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinate);
                /* If it's not occupied, possible move */
                if (!candidateDestinationTile.isTileOccupied()) {
                    /* non-attack Move*/
                    legalMoves.add(new Move());

                }else /* It is occupied */{
                    final Piece pieceAtDestination = candidateDestinationTile.getPiece();
                    final Alliance pieceAlliance = pieceAtDestination.getPieceAlliance();

                    /* Opposing piece, can attack*/
                    if (this.pieceAlliance != pieceAlliance){
                        /* Attacking Move*/
                        legalMoves.add(new Move());
                    }
                }


            }
        }
        return Collections.unmodifiableList(legalMoves);
    }

    public static void main(String[] args) {
        Piece knight = new Knight(5,3, Alliance.WHITE);

    }
}