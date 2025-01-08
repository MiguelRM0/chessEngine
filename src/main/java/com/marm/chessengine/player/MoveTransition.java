/******************************************
 *CSCI 205 - Software Engineering and Design
 *Spring 2024
 *Instructor: Prof. Lily Romano / Prof. Joshua Stough
 *
 *Name: Miguel Romero
 *Section: 01
 *Date: 1/2/25
 *Time: 1:29 AM
 *
 *Project: chessEngine
 *Package: com.marm.chessengine.player
 *Class: MoveTransition
 *Description:
 * **************************************** */
package com.marm.chessengine.player;

import com.marm.chessengine.board.Board;
import com.marm.chessengine.board.Move;

import java.util.concurrent.Future;

public class MoveTransition {
    private final Board transitionBoard;
    private final Move move;
    private  final MoveStatus moveStatus;

    public MoveTransition(final Board transitionBoard, final Move move, final MoveStatus moveStatus){
        this.transitionBoard = transitionBoard;
        this.move = move;
        this.moveStatus = moveStatus;
    }

    public Board getTransitionBoard(){
        return this.transitionBoard;
    }


    public MoveStatus getMoveStatus() {
        return this.moveStatus;

    }
}