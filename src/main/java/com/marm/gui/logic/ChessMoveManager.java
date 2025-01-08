/******************************************
 *CSCI 205 - Software Engineering and Design
 *Spring 2024
 *Instructor: Prof. Lily Romano / Prof. Joshua Stough
 *
 *Name: Miguel Romero
 *Section: 01
 *Date: 1/7/25
 *Time: 10:20 PM
 *
 *Project: chessEngine
 *Package: com.marm.gui.logic
 *Class: ChessMoveManager
 *Description:
 * **************************************** */
package com.marm.gui.logic;

import com.marm.chessengine.board.Board;
import com.marm.chessengine.board.Move;
import com.marm.chessengine.board.MutableCoordinate;
import com.marm.chessengine.pieces.Piece;
import javafx.scene.layout.StackPane;

import java.util.Map;
import java.util.Stack;

public class ChessMoveManager {

    private Map<MutableCoordinate, StackPane> gridMapCordToPane;

    Board board;

    Piece movedPiece;


    StackPane sourceStackPane;

    TileHighLighter tileHighLighter;



    ChessMoveManager( Board board, Move move , Map<MutableCoordinate, StackPane> gridMapCordToPane){
        this.board = board;
        this.gridMapCordToPane = gridMapCordToPane;
        sourceStackPane = getSourceStackPane();
        tileHighLighter = new TileHighLighter(this.board, this.gridMapCordToPane, move.getMovedPiece());

    }

    private StackPane getSourceStackPane(){
        MutableCoordinate mutableCoordinateReverse = new MutableCoordinate(movedPiece.getPieceCoordinatePair().getY(), movedPiece.getPieceCoordinatePair().getX());
        return gridMapCordToPane.get(mutableCoordinateReverse);
    }


}