/******************************************
 *CSCI 205 - Software Engineering and Design
 *Spring 2024
 *Instructor: Prof. Lily Romano / Prof. Joshua Stough
 *
 *Name: Miguel Romero
 *Section: 01
 *Date: 1/8/25
 *Time: 2:21 AM
 *
 *Project: chessEngine
 *Package: com.marm.gui.logic
 *Class: ChessMoveManager
 *Description:
 * **************************************** */
package com.marm.gui.logic;

import com.marm.chessengine.board.Board;
import com.marm.chessengine.board.MutableCoordinate;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

import java.util.Map;

public class ChessMoveManager {

    private  Board board;

    private  CreateChessMove createChessMove;

    private TileState tileState;

    private Map<MutableCoordinate, StackPane> gridMapCordToPane;

    public ChessMoveManager(Board board,
                            Map<MutableCoordinate, StackPane> gridMapCordToPane){
        this.board = board;
        this.gridMapCordToPane = gridMapCordToPane;
        this.tileState= TileState.NOT_ON_SOURCE_TILE;
    }

    public void setBoard(Board board){
        this.board = board;
    }






}