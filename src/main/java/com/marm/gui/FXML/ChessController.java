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

import com.marm.chessengine.board.Move;
import com.marm.chessengine.board.MutableCoordinate;
import com.marm.chessengine.board.Tile;
import com.marm.gui.logic.ChessBoardInitializer;
import com.marm.gui.logic.TileHighLighter;
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

    public  TileHighLighter tileHighLighter;

    @FXML
    public void initialize(){
        setChessBoardGrid();
//        darkMode = new MenuItem("ToggleDarkMode");
        backGroundAnchorPane.getStyleClass().add("dark-mode");
//        mainBorderPane.getStyleClass().add("dark-mode");
        mainBorderPane.getStyleClass().add("customBorderPane");
        tileHighLighter = new TileHighLighter(chessBoardInitializer.getBoard(), chessBoardInitializer.getGridMapCoordToPane(), null);
//        tileHighLighter = new TileHighLighter(chessBoardInitializer.getBoard(), chessBoardInitializer.getGridMapCoordToPane());
        setClickOnStackPane();

    }
    @FXML
    public void setChessBoardGrid(){
        chessBoardInitializer = new ChessBoardInitializer();
        chessBoardInitializer.initializeChessBoard(chessBoardGrid);
    }

    public void setClickOnStackPane(){
        for (Map.Entry<MutableCoordinate,  StackPane> entry : chessBoardInitializer.getGridMapCoordToPane().entrySet()){
            entry.getValue().setOnMouseClicked(mouseEvent -> {
//                tileHighLighter.highLightDestinationTiles(entry);
//                tileHighLighter.turnEmAllRed();
                tileHighLighter.setPiece(chessBoardInitializer.getBoard().getTile(new MutableCoordinate(entry.getKey().getY(), entry.getKey().getX())).getPiece());
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