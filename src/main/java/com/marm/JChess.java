/******************************************
 *CSCI 205 - Software Engineering and Design
 *Spring 2024
 *Instructor: Prof. Lily Romano / Prof. Joshua Stough
 *
 *Name: Miguel Romero
 *Section: 01
 *Date: 1/1/25
 *Time: 9:13 PM
 *
 *Project: chessEngine
 *Package: com.marm.chessengine
 *Class: JChess
 *Description:
 * **************************************** */
package com.marm;

import com.marm.chessengine.board.Board;
import com.marm.chessengine.board.Move;
import com.marm.chessengine.board.MutableCoordinate;
import com.marm.chessengine.board.Tile;
import com.marm.chessengine.player.MoveTransition;

import java.io.IOException;

public class JChess {

    public static void main(String[] args) throws IOException {
        Board board = Board.createStandardBoard();
        System.out.println(board.currentPlayer());
        final Move move = Move.MoveFactory.createMove(board, new MutableCoordinate(6,4), new MutableCoordinate(5,4));
        MoveTransition moveTransition = board.currentPlayer().makeMove(move);
        board = moveTransition.getTransitionBoard();
        board.currentPlayer().makeMove(move);
        System.out.println(board);
        System.out.println(board.currentPlayer());

    }
}