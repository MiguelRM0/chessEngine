/******************************************
 *CSCI 205 - Software Engineering and Design
 *Spring 2024
 *Instructor: Prof. Lily Romano / Prof. Joshua Stough
 *
 *Name: Miguel Romero
 *Section: 01
 *Date: 12/30/24
 *Time: 7:42 PM
 *
 *Project: chessEngine
 *Package: com.marm.chessengine.board
 *Class: Move
 *Description:
 * **************************************** */
package com.marm.chessengine.board;

import com.marm.chessengine.pieces.Piece;
import javafx.util.Pair;

public abstract class  Move {
    final Board board;
    final Piece movedPiece;
    final MutableCoordinate destinationCoordinate;

    private Move(final Board board , final Piece movedPiece, final MutableCoordinate destinationCoordinate){
        this.board = board;
        this.movedPiece = movedPiece;
        this.destinationCoordinate = destinationCoordinate;
    }

    public static final class  MajorMove extends Move{

        public MajorMove(final Board board, final Piece movedPiece, final MutableCoordinate destinationCoordinate) {
            super(board, movedPiece, destinationCoordinate);
        }
    }

    public static final class AttackMove extends Move{
        final Piece attackedPiece;

        public AttackMove(final Board board, final Piece movedPiece,final  MutableCoordinate destinationCoordinate, final Piece attackedPiece ) {
            super(board, movedPiece, destinationCoordinate);
            this.attackedPiece = attackedPiece;

        }
    }



}