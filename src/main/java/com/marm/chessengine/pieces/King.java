/******************************************
 *CSCI 205 - Software Engineering and Design
 *Spring 2024
 *Instructor: Prof. Lily Romano / Prof. Joshua Stough
 *
 *Name: Miguel Romero
 *Section: 01
 *Date: 12/31/24
 *Time: 4:28 PM
 *
 *Project: chessEngine
 *Package: com.marm.chessengine.pieces
 *Class: King
 *Description:
 * **************************************** */
package com.marm.chessengine.pieces;

import com.marm.chessengine.Alliance;
import com.marm.chessengine.board.*;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class King extends Piece{
    private final static int[][] CANDIDATE_MOVE_COORDINATES = {{1,1}, {1,-1}, {-1,1}, {-1,-1}, {1,0}, {-1,0}, {0,1}, {0,-1}};

    private final Image kingImgWhite = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/marm/gui/images/King/chess_king_white.png")));
    private final Image kingImgBlack = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/marm/gui/images/King/chess_king_black.png")));
    public King(final int pieceXCord, final int pieceYCord, final Alliance alliance) {
        super(pieceXCord, pieceYCord, alliance, PieceType.KING);
    }

    @Override
    public King copyAt(MutableCoordinate newMutableCoordinates) {
        return new King(newMutableCoordinates.getX(), newMutableCoordinates.getY(), this.pieceAlliance);
    }

    @Override
    public List<Move> calculateLegalMoves(Board board) {
        final List<Move> legalMoves = new ArrayList<>();
        for (int[] currentCandidateVector: CANDIDATE_MOVE_COORDINATES){
            final MutableCoordinate candidateDestinationCoordinate = new MutableCoordinate(this.pieceXCord + currentCandidateVector[0], this.pieceYCord + currentCandidateVector[1]);
            if (BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)) {
                final Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinate);
                if (!candidateDestinationTile.isTileOccupied()) {
                    /* non-attack Move*/
                    legalMoves.add(new Move.MajorMove(board, this, candidateDestinationCoordinate));

                } else /* It is occupied */ {
                    final Piece pieceAtDestination = candidateDestinationTile.getPiece();
                    final Alliance pieceAlliance = pieceAtDestination.getPieceAlliance();

                    /* Opposing piece, can attack*/
                    if (this.pieceAlliance != pieceAlliance) {
                        /* Attacking Move*/
                        legalMoves.add(new Move.AttackMove(board, this, candidateDestinationCoordinate, pieceAtDestination));
                    }
                }
            }


        }
        return Collections.unmodifiableList(legalMoves);
    }


    public King movePiece(final Move move) {
        return new King(move.getDestinationCoordinate().getX(),move.getDestinationCoordinate().getY(),move.getMovedPiece().getPieceAlliance());
    }

    @Override
    public Image getImage() {
        if (this.pieceAlliance == Alliance.WHITE){
            return kingImgWhite;
        }
        return kingImgBlack;
    }

    @Override
    public String toString(){
        return PieceType.KING.toString();
    }
}