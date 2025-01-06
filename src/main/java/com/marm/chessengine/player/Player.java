/******************************************
 *Name: Miguel Romero
 *Date: 1/2/25
 *Time: 12:56 AM
 *
 *Project: chessEngine
 *Package: com.marm.chessengine.player
 *Class: Player
 *Description:
 * **************************************** */
package com.marm.chessengine.player;

import com.marm.chessengine.Alliance;
import com.marm.chessengine.board.Board;
import com.marm.chessengine.board.Move;
import com.marm.chessengine.board.MutableCoordinate;
import com.marm.chessengine.board.Tile;
import com.marm.chessengine.pieces.King;
import com.marm.chessengine.pieces.Piece;
import com.marm.chessengine.pieces.Rook;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public abstract class Player {

    protected final Board board;
    protected final King playerKing;
    
    protected final Collection<Move> legalMoves;

    private final boolean isInCheck;


    public Player(Board board, Collection<Move> legalMoves, Collection<Move> opponentMoves){
        this.board= board;
        this.playerKing = establishKing();
        this.legalMoves = Stream.concat(legalMoves.stream(), calculateKingCastles(legalMoves,opponentMoves).stream()).toList();
        this.isInCheck = !Player.calculateAttacksOnTile(this.playerKing.getPiecePosition(), opponentMoves).isEmpty();

    }

    protected static Collection<Move> calculateAttacksOnTile(MutableCoordinate piecePosition, Collection<Move> moves){
        final List<Move> attackMoves = new ArrayList<>();
        /* Go through opponent moves*/
        for (final Move move: moves){
            /*If tile is part of candidate destination coordinates */
            if(piecePosition.equals(move.getDestinationCoordinate())){
                /*Add as a move*/
                attackMoves.add(move);
            }
        }
        return Collections.unmodifiableList(attackMoves);

    }

    public Collection<Move> getLegalMoves(){
        return this.legalMoves;
    }

    public abstract Collection<Piece> getActivePieces();

    private King establishKing() {
        for(final Piece piece: getActivePieces()){
            if(piece.getPieceType().isKing()){
                return (King) piece;
            }
        }
        throw new RuntimeException("Board Set Up is Incorrect");
//        return null;
    }


    public abstract Alliance getAlliance();

    public abstract Player getOpponent();

    protected abstract Collection<Move> calculateKingCastles(Collection<Move> playerLegals, Collection<Move> opponentLegals);

    public boolean isMoveLegal(final Move move){
        return this.legalMoves.contains(move);
    }
    public boolean isInCheck(){
        return this.isInCheck;
    }

    public boolean isInCheckMate(){
        return this.isInCheck && !hasEscapeMoves();
    }

    public boolean isInStaleMate(){
        return !this.isInCheck && !hasEscapeMoves();
    }

    protected boolean hasEscapeMoves() {
        for (final Move move: this.legalMoves){
            final MoveTransition transition = makeMove(move);
            if(transition.getMoveStatus().isDone()){
                return true;
            }
        }
        return false;
    }

    public boolean isCastled(){
        return false;
    }

    public MoveTransition makeMove(final Move move){
        if(!isMoveLegal(move)){
            return new MoveTransition(this.board,move,MoveStatus.ILLEGAL_MOVE);
        }
        final Board transitionBoard = move.execute();
        final Collection<Move> kingAttacks = Player.calculateAttacksOnTile(transitionBoard.currentPlayer().getOpponent().getPlayerKing().getPiecePosition(),
                transitionBoard.currentPlayer().getLegalMoves());

        if(!kingAttacks.isEmpty()){
            return new MoveTransition(this.board,move,MoveStatus.LEAVES_PLAYER_IN_CHECK);
        }
        return new MoveTransition(transitionBoard, move, MoveStatus.DONE) ;

    }

    public Piece getPlayerKing() {
        return this.playerKing;

    }


    protected void QueenSideCastleLogic(Collection<Move> opponentLegals, MutableCoordinate castlePathQueenTile1, MutableCoordinate castlePathQueenTile2, MutableCoordinate castlePathQueenTile3, MutableCoordinate castleQueenRook, List<Move> kingCastles) {
        if (!this.board.getTile(castlePathQueenTile1).isTileOccupied() &&
                !this.board.getTile(castlePathQueenTile2).isTileOccupied() &&
                !this.board.getTile(castlePathQueenTile3).isTileOccupied() &&
                this.board.getTile(castleQueenRook).getPiece().getPieceType().isRook()) {
            final Tile rookTile = this.board.getTile(castleQueenRook);
            if (rookTile.isTileOccupied() && rookTile.getPiece().isFirstMove()) {
                if (Player.calculateAttacksOnTile(castlePathQueenTile1, opponentLegals).isEmpty() &&
                        Player.calculateAttacksOnTile(castlePathQueenTile2, opponentLegals).isEmpty() &&
                        Player.calculateAttacksOnTile(castlePathQueenTile3, opponentLegals).isEmpty()) {
                    kingCastles.add(new Move.CastleMove.QueenSideCastleMove(
                            this.board,
                            this.playerKing,
                            castlePathQueenTile2,
                            (Rook)rookTile.getPiece(),
                            rookTile.getPiece().getPieceCoordinatePair(),
                            castlePathQueenTile3));
                }
            }

        }
    }

    protected void KingSideCastleLogic(Collection<Move> opponentLegals, MutableCoordinate castlePathKingTile1, MutableCoordinate castlePathKingTile2, MutableCoordinate castleKingRook, List<Move> kingCastles) {
        if(!this.board.getTile(castlePathKingTile1).isTileOccupied() &&
                !this.board.getTile(castlePathKingTile2).isTileOccupied() &&
                this.board.getTile(castleKingRook).getPiece().getPieceType().isRook()){
            final Tile rookTile = this.board.getTile(castleKingRook);
            if(rookTile.isTileOccupied() && rookTile.getPiece().isFirstMove()) {
                if(Player.calculateAttacksOnTile(castlePathKingTile1, opponentLegals).isEmpty() &&
                Player.calculateAttacksOnTile(castlePathKingTile2, opponentLegals).isEmpty()){
                    kingCastles.add(new Move.CastleMove.KingSideCastleMove(
                            this.board,
                            this.playerKing,
                            castlePathKingTile2,
                            (Rook)rookTile.getPiece(),
                            rookTile.getPiece().getPieceCoordinatePair(),
                            castlePathKingTile1));
                }
            }
        }
    }
}