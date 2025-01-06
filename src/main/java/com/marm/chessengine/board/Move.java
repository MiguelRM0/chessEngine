/******************************************
 *Name: Miguel Romero
 *Date: 12/30/24
 *Time: 7:42PM
 *
 *Project: chessEngine
 *Package: com.marm.chessengine.board
 *Class: Move
 *Description:
 * **************************************** */
package com.marm.chessengine.board;

import com.marm.chessengine.pieces.Pawn;
import com.marm.chessengine.pieces.Piece;
import com.marm.chessengine.pieces.Rook;

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

    public boolean isAttack(){
        return false;
    }

    public boolean isCastlingMove(){
        return false;
    }

    public Piece getAttackedPiece(){
        return null;
    }
    @Override
    public int hashCode(){
        final int prime = 31;
        int result = 1;

        result = prime * result + this.destinationCoordinate.hashCode();
        result = prime * result + this.movedPiece.hashCode();
        return result;
    }
    @Override
    public boolean equals(Object other){
        if (this == other){
            return true;
        }
        if (!(other instanceof Move)){
            return false;
        }

        final Move that = (Move) other;
        return this.getDestinationCoordinate().equals(that.getDestinationCoordinate()) &&
                this.getMovedPiece().equals(that.getMovedPiece());

    }

    public Board execute() {
        final Board.Builder builder = new Board.Builder();
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
        public int hashCode(){
            return this.attackedPiece.hashCode() + super.hashCode();
        }
        @Override
        public boolean equals(final Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof AttackMove)) {
                return false;
            }
            final AttackMove that = (AttackMove) other;
            return super.equals(that) && getAttackedPiece().equals(that.getAttackedPiece());
        }

        @Override
        public Board execute() {
            return null;
        }

        @Override
        public boolean isAttack(){
            return true;
        }

        @Override
        public Piece getAttackedPiece(){
            return this.attackedPiece;
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

        @Override
        public Board execute(){
            final Board.Builder builder = new Board.Builder();
            for (final Piece piece : this.board.currentPlayer().getActivePieces()){
                if(!(this.movedPiece.equals(piece))){
                    builder.setPiece(piece);
                }
            }

            for (final Piece piece : this.board.currentPlayer().getOpponent().getActivePieces()){
                builder.setPiece(piece);
            }
            final Pawn movedPawn = (Pawn)this.movedPiece.movePiece(this);
            builder.setPiece(movedPawn);
            builder.setEnPassantPawn(movedPawn);
            builder.setMoveMaker(this.board.currentPlayer().getOpponent().getAlliance());
            return builder.build();
        }
    }

    public static abstract class CastleMove extends Move{
        protected final Rook castleRook;
        protected final MutableCoordinate castleRookStart;
        protected final MutableCoordinate castleRookDestination;


        private CastleMove(final Board board,
                           final Piece movedPiece,
                           final MutableCoordinate destinationCoordinate,
                           final Rook castleRook,
                           final MutableCoordinate castleRookStart,
                           final MutableCoordinate castleRookDestination) {
            super(board, movedPiece, destinationCoordinate);
            this.castleRook = castleRook;
            this.castleRookStart = castleRookStart;
            this.castleRookDestination = castleRookDestination;
        }
        public Rook getCastleRook(){
            return this.castleRook;
        }
        @Override
        public boolean isCastlingMove(){
            return true;
        }
        @Override
        public Board execute(){
            final Board.Builder builder = new Board.Builder();
            for (final Piece piece : this.board.currentPlayer().getActivePieces()){
                if(!(this.movedPiece.equals(piece)) && !this.castleRook.equals(piece)){
                    builder.setPiece(piece);
                }
            }

            for (final Piece piece : this.board.currentPlayer().getOpponent().getActivePieces()){
                builder.setPiece(piece);
            }

            builder.setPiece(this.movedPiece.movePiece(this));
            builder.setPiece(new Rook(this.castleRookDestination.getX(), this.castleRookDestination.getY(), this.castleRook.getPieceAlliance()));
            builder.setMoveMaker(this.board.currentPlayer().getOpponent().getAlliance());
            return builder.build();


        }

        public static final class KingSideCastleMove extends CastleMove{


            public KingSideCastleMove(final Board board,
                                      final Piece movedPiece,
                                      final MutableCoordinate destinationCoordinate,
                                      final Rook castleRook,
                                      final MutableCoordinate castleRookStart,
                                      final MutableCoordinate castleRookDestination) {
                super(board, movedPiece, destinationCoordinate,castleRook,castleRookStart,castleRookDestination);
            }
            @Override
            public String toString() {
                return "O-O";
            }


        }


        public static final class QueenSideCastleMove extends CastleMove{

            public QueenSideCastleMove(final Board board,
                                       final Piece movedPiece,
                                       final MutableCoordinate destinationCoordinate,
                                       final Rook castleRook,
                                       final MutableCoordinate castleRookStart,
                                       final MutableCoordinate castleRookDestination) {
                super(board, movedPiece, destinationCoordinate,castleRook,castleRookStart,castleRookDestination);
            }

            public String toString(){
                return "O-O-O";
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

        public static Move createMove(final Board board,final MutableCoordinate currentCoordinate,final MutableCoordinate destinationCoordinate){
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