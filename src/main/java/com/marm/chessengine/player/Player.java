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
import com.marm.chessengine.pieces.King;
import com.marm.chessengine.pieces.Piece;

import java.security.PublicKey;
import java.util.Collection;
import java.util.Collections;

public abstract class Player {

    protected final Board board;
    protected final King playerKing;
    
    protected final Collection<Move> legalMoves;


    public Player(Board board, Collection<Move> legalMoves, Collection<Move> opponentMoves){
        this.board= board;
        this.playerKing = establishKing();
        this.legalMoves = legalMoves;

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
        return false;
    }

    public boolean isInCheckMate(){
        return false;
    }

    public boolean isInStaleMate(){
        return false;
    }

    public boolean isCastled(){
        return false;
    }

    public MoveTransition makeMove(final Move move){
        return null;
    }




}