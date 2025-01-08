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

    public void processTileClick(Map.Entry<MutableCoordinate, StackPane> entry) {
        MutableCoordinate currentEntryOnEngine =  new MutableCoordinate(entry.getKey().getX(), entry.getKey().getY());
        if(createChessMove != null){
            createChessMove.getTileHighLighter().removeHighLightTiles();
        }
        // Not on any piece tile create new source tile and change state
        if(tileState == TileState.NOT_ON_SOURCE_TILE && board.getTile(currentEntryOnEngine).isTileOccupied()){
            tileState = TileState.ON_SOURCE_TILE;
            createChessMove = new CreateChessMove(board,board.getTile(currentEntryOnEngine).getPiece(), gridMapCordToPane);
            // On a source tile but clicked new piece change source tile
        }else if (tileState == TileState.ON_SOURCE_TILE && board.getTile(currentEntryOnEngine).isTileOccupied() ){
            createChessMove = new CreateChessMove(board,board.getTile(currentEntryOnEngine).getPiece(), gridMapCordToPane);
            // On source tile clicked empty source tile possible destination
        }else if (tileState == TileState.ON_SOURCE_TILE  && !board.getTile(currentEntryOnEngine).isTileOccupied() ){
            this.board = createChessMove.createMove(currentEntryOnEngine);
            tileState = TileState.NOT_ON_SOURCE_TILE;
        }
    }

    public Map<MutableCoordinate, StackPane> getGridMapCordToPane(){
        return this.gridMapCordToPane;
    }

    public Board getBoard(){
        return this.board;
    }






}