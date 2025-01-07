/******************************************
 *CSCI 205 - Software Engineering and Design
 *Spring 2024
 *Instructor: Prof. Lily Romano / Prof. Joshua Stough
 *
 *Name: Miguel Romero
 *Section: 01
 *Date: 12/30/24
 *Time: 11:22 PM
 *
 *Project: chessEngine
 *Package: com.marm.chessengine.pieces
 *Class: Bishop
 *Description:
 * **************************************** */
package com.marm.chessengine.pieces;

import com.marm.chessengine.Alliance;
import com.marm.chessengine.board.*;
import javafx.scene.image.Image;
import javafx.util.Pair;

import java.security.Permission;
import java.util.*;

public class Bishop extends Piece {
    private final static int[][] CANDIDATE_MOVE_VECTOR_COORDINATES = {{1,1}, {1,-1}, {-1,1}, {-1,-1}};

    private final Image bishopImgWhite = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/marm/gui/images/Bishop/chess_bishop_white.png")));
    private final Image bishopImgBlack = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/marm/gui/images/Bishop/chess_bishop_black.png")));
    public Bishop(int pieceXCord, int pieceYCord, Alliance alliance) {
        super(pieceXCord, pieceYCord, alliance, PieceType.BISHOP);
    }

    @Override
    public List<Move> calculateLegalMoves(final Board board) {
        return this.legalMovesQRB(board, CANDIDATE_MOVE_VECTOR_COORDINATES);
    }



    @Override
    public Bishop movePiece(final Move move) {
        return new Bishop(move.getDestinationCoordinate().getX(),move.getDestinationCoordinate().getY(),move.getMovedPiece().getPieceAlliance());
    }

    @Override
    public Image getBlackImg() {
        return this.bishopImgBlack;
    }

    @Override
    public Image getWhiteImg() {
        return this.bishopImgWhite;
    }

    @Override
    public String toString(){
        return PieceType.BISHOP.toString();
    }
}