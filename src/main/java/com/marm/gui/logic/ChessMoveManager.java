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
import javafx.scene.layout.StackPane;

import java.util.Map;

public class ChessMoveManager {

    private  Board board;

    private  CreateChessMove createChessMove;

    private TileClickState tileClickState;

    private  Map<MutableCoordinate, StackPane> gridMapCordToPane;


    private final BoardProperty boardProperty;

    public ChessMoveManager(Board board,
                            Map<MutableCoordinate, StackPane> gridMapCordToPane,
                            BoardProperty boardProperty){

        this.board = board;
        this.gridMapCordToPane = gridMapCordToPane;
        this.tileClickState = TileClickState.NOT_ON_SOURCE_TILE;
        this.boardProperty = boardProperty;


    }

    public void printAllProperties(){
        System.out.println( "Board "  + board);
        System.out.println("GridStackPane" + gridMapCordToPane);
        System.out.println("Tile Click State " + tileClickState);
        System.out.println("Board property " + boardProperty);
    }

    public void setGridMapCordToPane(Map<MutableCoordinate, StackPane> gridMapCordToPane){
        this.gridMapCordToPane = gridMapCordToPane;
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
        if(tileClickState == TileClickState.NOT_ON_SOURCE_TILE && board.getTile(currentEntryOnEngine).isTileOccupied()){
            tileClickState = TileClickState.ON_SOURCE_TILE;
            createChessMove = new CreateChessMove(board,board.getTile(currentEntryOnEngine).getPiece(), gridMapCordToPane);
            // On a source tile but clicked new piece change source tile
        }else if (tileClickState == TileClickState.ON_SOURCE_TILE && board.getTile(currentEntryOnEngine).isTileOccupied() ){
            createChessMove = new CreateChessMove(board,board.getTile(currentEntryOnEngine).getPiece(), gridMapCordToPane);
            // On source tile clicked empty source tile possible destination
        }else if (tileClickState == TileClickState.ON_SOURCE_TILE  && !board.getTile(currentEntryOnEngine).isTileOccupied() ){
            Board board = createChessMove.createMove(currentEntryOnEngine);
            this.board = board;
            boardProperty.setBoard(board);
            tileClickState = TileClickState.NOT_ON_SOURCE_TILE;
        }
    }

    public BoardProperty getBoardProperty(){
        return this.boardProperty;
    }



    public Map<MutableCoordinate, StackPane> getGridMapCordToPane(){
        return this.gridMapCordToPane;
    }

    public Board getBoard(){
        return this.board;
    }






    public static void main(String[] args) {
        Board board = Board.createStandardBoard();
//        ChessMoveManager moveManager = new ChessMoveManager(board, nu)
    }

}