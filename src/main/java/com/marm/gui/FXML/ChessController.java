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
import com.marm.chessengine.board.Move;
import com.marm.chessengine.board.MutableCoordinate;
import com.marm.chessengine.player.MoveTransition;
import com.marm.gui.logic.ChessBoardInitializer;
import com.marm.gui.logic.ChessMoveManager;
import com.marm.gui.logic.CreateChessMove;
import com.marm.gui.logic.TileState;
import javafx.application.Platform;
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

    public ChessMoveManager chessMoveManager;



    @FXML
    public void initialize(){
        setChessBoardGrid();
        setClickOnStackPane();

    }
    @FXML
    public void setChessBoardGrid(){
        chessBoardInitializer = new ChessBoardInitializer();
        chessBoardInitializer.initializeChessBoard(chessBoardGrid);
        chessMoveManager = new ChessMoveManager(chessBoardInitializer.getBoard(),chessBoardInitializer.getGridMapCordToPane(),chessBoardGrid);



    }

    public void setClickOnStackPane(){
        for (Map.Entry<MutableCoordinate,  StackPane> entry : chessMoveManager.getGridMapCordToPane().entrySet()){
            entry.getValue().setOnMouseClicked(mouseEvent -> {
                     chessMoveManager.processTileClick(entry);
            });

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