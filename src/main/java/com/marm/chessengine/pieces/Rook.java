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
import javafx.scene.image.Image;

import java.util.List;
import java.util.Objects;

public class Rook extends Piece{
    private final static int[][] CANDIDATE_MOVE_VECTOR_COORDINATES = {{1,0}, {-1,0}, {0,1}, {0,-1}};

    private final Image rookImgWhite = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/marm/gui/images/Rook/chess_rook_white.png")));
    private final Image rookImgBlack = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/marm/gui/images/Rook/chess_rook_black.png")));
    public Rook(int pieceXCord, int pieceYCord, Alliance alliance) {
        super(pieceXCord, pieceYCord, alliance,PieceType.ROOK);
    }


    @Override
    public List<Move> calculateLegalMoves(final Board board) {
        return this.legalMovesQRB(board, CANDIDATE_MOVE_VECTOR_COORDINATES);
    }

    @Override
    public Rook movePiece(final Move move) {
        return new Rook(move.getDestinationCoordinate().getX(),move.getDestinationCoordinate().getY(),move.getMovedPiece().getPieceAlliance());
    }
    @Override
    public Image getImage() {
        if (this.pieceAlliance == Alliance.WHITE){
            return rookImgWhite;
        }
        return rookImgBlack;
    }

    @Override
    public String toString(){
        return PieceType.ROOK.toString();
    }


}