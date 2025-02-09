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
package com.marm.chessengine;

import com.marm.chessengine.board.Board;
import com.marm.chessengine.board.MutableCoordinate;
import com.marm.chessengine.board.Tile;

public class JChess {

    public static void main(String[] args) {
        Board board = Board.createStandardBoard();
        System.out.println(board);

    }
}