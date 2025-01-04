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

public abstract class  Move {
    final Board board;
    final Piece movedPiece;
    final MutableCoordinate destinationCoordinate;

    public static final Move NUll_MOVE = new NullMove();

    private Move(final Board board , final Piece movedPiece, final MutableCoordinate destinationCoordinate){
        this.board = board;
        this.movedPiece = movedPiece;
        this.destinationCoordinate = destinationCoordinate;
    }

    public MutableCoordinate getCurrentCoordinate(){
        return this.movedPiece.getPieceCoordinatePair();
    }

    public Piece getMovedPiece(){
        return this.movedPiece;
    }

    public MutableCoordinate getDestinationCoordinate() {
        return this.destinationCoordinate;
    }

    public Board execute() {
        final Board.Builder builder = new Board.Builder();
        System.out.println(builder);
        for (final Piece piece : this.board.currentPlayer().getActivePieces()){
            if(!this.movedPiece.equals(piece)){
                builder.setPiece(piece);
            }

        }

        for (final Piece piece : this.board.currentPlayer().getOpponent().getActivePieces()){
            builder.setPiece(piece);
        }
        // move the moved piece
        builder.setPiece(this.movedPiece.movePiece(this));
        builder.setMoveMaker(this.board.currentPlayer().getOpponent().getAlliance());
        return builder.build();
    }

    public static final class  MajorMove extends Move{

        public MajorMove(final Board board, final Piece movedPiece, final MutableCoordinate destinationCoordinate) {
            super(board, movedPiece, destinationCoordinate);
        }

    }

    public static class AttackMove extends Move{
        final Piece attackedPiece;

        public AttackMove(final Board board, final Piece movedPiece,final  MutableCoordinate destinationCoordinate, final Piece attackedPiece ) {
            super(board, movedPiece, destinationCoordinate);
            this.attackedPiece = attackedPiece;

        }

        @Override
        public Board execute() {
            return null;
        }
    }

    public static final class PawnMove extends Move{

        public PawnMove(final Board board, final Piece movedPiece, final MutableCoordinate destinationCoordinate) {
            super(board, movedPiece, destinationCoordinate);
        }

    }

    public static class PawnAttackMove extends AttackMove{

        public PawnAttackMove(final Board board, final Piece movedPiece, final MutableCoordinate destinationCoordinate, final Piece attackedPiece) {
            super(board, movedPiece, destinationCoordinate,attackedPiece);
        }

    }

    public static final class PawnEnPassantAttackMove extends PawnAttackMove{

        public PawnEnPassantAttackMove(final Board board, final Piece movedPiece, final MutableCoordinate destinationCoordinate, final Piece attackedPiece) {
            super(board, movedPiece, destinationCoordinate,attackedPiece);
        }

    }

    public static final class PawnJump extends Move{

        private PawnJump(Board board, Piece movedPiece, MutableCoordinate destinationCoordinate) {
            super(board, movedPiece, destinationCoordinate);
        }
    }

    static abstract class CastleMove extends Move{


        private CastleMove(Board board, Piece movedPiece, MutableCoordinate destinationCoordinate) {
            super(board, movedPiece, destinationCoordinate);
        }

        public static final class KingSideCastleMove extends CastleMove{

            private KingSideCastleMove(Board board, Piece movedPiece, MutableCoordinate destinationCoordinate) {
                super(board, movedPiece, destinationCoordinate);
            }
        }


        public static final class QueenSideCastleMove extends CastleMove{

            private QueenSideCastleMove(Board board, Piece movedPiece, MutableCoordinate destinationCoordinate) {
                super(board, movedPiece, destinationCoordinate);
            }
        }




    }

    public static final class NullMove extends Move{

        private NullMove() {
            super(null, null, null);
        }

        @Override
        public Board execute(){
            throw new RuntimeException("cannot execute null move!");
        }
    }


    public static class MoveFactory{
        private MoveFactory(){
            throw new RuntimeException("Not Instantiable");
        }

        public static Move createMove(final Board board,final int currentCoordinate,final int destinationCoordinate){
            for (final Move move: board.getAllLegalMoves()){
                if(move.getCurrentCoordinate().equals(currentCoordinate) &&
                        move.getDestinationCoordinate().equals(destinationCoordinate) ){
                    return move;
                }
            }

            return NUll_MOVE;
        }

    }



    public static void main(String[] args) {
    }



}