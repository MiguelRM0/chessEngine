/******************************************
 *CSCI 205 - Software Engineering and Design
 *Spring 2024
 *Instructor: Prof. Lily Romano / Prof. Joshua Stough
 *
 *Name: Miguel Romero
 *Section: 01
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
import com.marm.chessengine.pieces.King;
import com.marm.chessengine.pieces.Piece;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public abstract class Player {

    protected final Board board;
    protected final King playerKing;
    
    protected final Collection<Move> legalMoves;

    private final boolean isInCheck;


    public Player(Board board, Collection<Move> legalMoves, Collection<Move> opponentMoves){
        this.board= board;
        this.playerKing = establishKing();
        this.legalMoves = legalMoves;
        this.isInCheck = !Player.calculateAttacksOnTile(this.playerKing.getPiecePosition(), opponentMoves).isEmpty();

    }

    private static Collection<Move> calculateAttacksOnTile(MutableCoordinate piecePosition, Collection<Move> moves){
        final List<Move> attackMoves = new ArrayList<>();
        /* Go through opponent moves*/
        for (final Move move: moves){
            /*If king is part of candidate destination coordinates */
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
    }

    public abstract Alliance getAlliance();

    public abstract Player getOpponent();

    public boolean isMoveLegal(final Move move){
        return this.legalMoves.contains(move);
    }
    // TODO Implement these methods below !!!
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


}