/******************************************
 *CSCI 205 - Software Engineering and Design
 *Spring 2024
 *Instructor: Prof. Lily Romano / Prof. Joshua Stough
 *
 *Name: Miguel Romero
 *Section: 01
 *Date: 1/2/25
 *Time: 1:30 AM
 *
 *Project: chessEngine
 *Package: com.marm.chessengine.player
 *Class: MoveStatus
 *Description:
 * **************************************** */
package com.marm.chessengine.player;

import com.marm.chessengine.board.Move;

public enum MoveStatus {
    DONE {
        @Override
        boolean isDone() {
            return true;
        }
    },ILLEGAL_MOVE {
        @Override
        boolean isDone() {
            return false;
        }
    },LEAVES_PLAYER_IN_CHECK {
        @Override
        boolean isDone() {
            return false;
        }
    };


    abstract boolean isDone();
}