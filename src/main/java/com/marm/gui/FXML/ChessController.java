/******************************************
 *CSCI 205 - Software Engineering and Design
 *Spring 2024
 *Instructor: Prof. Lily Romano / Prof. Joshua Stough
 *
 *Name: Miguel Romero
 *Section: 01
 *Date: 1/6/25
 *Time: 4:03 AM
 *
 *Project: chessEngine
 *Package: com.marm.gui.FXML
 *Class: ChessController
 *Description:
 * **************************************** */
package com.marm.gui.FXML;

import com.marm.chessengine.board.Board;
import com.marm.chessengine.board.MutableCoordinate;
import com.marm.gui.logic.ChessBoardInitializer;
import com.marm.gui.logic.CreateChessMove;
import com.marm.gui.logic.TileState;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

import java.util.Map;

public class ChessController {
    public BorderPane mainBorderPane;
    public GridPane chessBoardGrid;

    public ChessBoardInitializer chessBoardInitializer;
    public AnchorPane backGroundAnchorPane;
    public Menu menuFile;

    public MenuItem darkMode;

    public CreateChessMove createChessMove;

    TileState tileState;

    public Board board;

    private Map<MutableCoordinate,StackPane> gridMapCordToPane;





    @FXML
    public void initialize(){
        setChessBoardGrid();
        setClickOnStackPane();
        tileState = TileState.NOT_ON_SOURCE_TILE;

    }
    @FXML
    public void setChessBoardGrid(){
        chessBoardInitializer = new ChessBoardInitializer();
        chessBoardInitializer.initializeChessBoard(chessBoardGrid);
        board = chessBoardInitializer.getBoard();
        gridMapCordToPane = chessBoardInitializer.getGridMapCordToPane();


    }

    public void setClickOnStackPane(){
        for (Map.Entry<MutableCoordinate,  StackPane> entry : gridMapCordToPane.entrySet()){
            entry.getValue().setOnMouseClicked(mouseEvent -> {
                extracted(entry);
            });

        }

    }

    private void extracted(Map.Entry<MutableCoordinate, StackPane> entry) {
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
            createChessMove.setDestinationCoordinate(currentEntryOnEngine);
            tileState = TileState.NOT_ON_SOURCE_TILE;
        }
    }

    public void setDarkMode(){
        darkMode.setOnAction(actionEvent -> {
            if (backGroundAnchorPane.getStyleClass().contains("dark-mode")){
                mainBorderPane.getStyleClass().remove("dark-mode");
                mainBorderPane.getStyleClass().add("light-mode");

                backGroundAnchorPane.getStyleClass().remove("dark-mode");
                backGroundAnchorPane.getStyleClass().add("light-mode");
            }else{
                mainBorderPane.getStyleClass().remove("light-mode");
                mainBorderPane.getStyleClass().add("dark-mode");

                backGroundAnchorPane.getStyleClass().remove("light-mode");
                backGroundAnchorPane.getStyleClass().add("dark-mode");
            }

        });

    }





}