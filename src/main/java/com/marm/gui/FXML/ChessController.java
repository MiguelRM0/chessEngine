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
import com.marm.chessengine.board.Tile;
import com.marm.gui.logic.BoardProperty;
import com.marm.gui.logic.ChessBoardInitializer;
import com.marm.gui.logic.ChessMoveManager;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.util.Map;

public class ChessController {
    public BorderPane mainBorderPane;
    public GridPane chessBoardGrid;


    public AnchorPane backGroundAnchorPane;
    public Menu menuFile;

    public MenuItem darkMode;

    public ChessMoveManager chessMoveManager;
    public ChessBoardInitializer chessBoardInitializer;



    @FXML
    public void initialize(){
        setChessBoardGrid();
        setClickOnStackPane();
        bindTilesToBoard();
//        chessMoveManager.printAllProperties();
//        Board rotatedBoard = chessMoveManager.getBoard().rotateBoard();
//        System.out.println(rotatedBoard);
//        Map<MutableCoordinate,StackPane> gridMapCordToPane = ChessBoardInitializer.drawBoard(chessBoardGrid,rotatedBoard);
//        chessMoveManager.setBoard(rotatedBoard);
//        chessMoveManager.setGridMapCordToPane(gridMapCordToPane);
//        chessMoveManager.getBoardProperty().setBoard(rotatedBoard);
//        chessMoveManager.printAllProperties();
//
//        bindTilesToBoard();
//        setClickOnStackPane();
        //TODO Create set up a board that is flipped
    }

    public void bindTilesToBoard() {
        chessMoveManager.getBoardProperty().getBoardProperty().addListener(((observableValue, board1, t1) -> {
            Map<MutableCoordinate, Tile> map = board1.diffBetweenBoards(t1);
            for (Map.Entry<MutableCoordinate, Tile> entry: map.entrySet()){
                if (entry.getValue().isTileOccupied()){
                    updateTileDisplay(chessMoveManager.getGridMapCordToPane().get(entry.getKey()) , entry.getValue().getPiece().getImage());
                }else{
                    updateTileDisplay(chessMoveManager.getGridMapCordToPane().get(entry.getKey()),null );
                }
            }
        }));
    }


    @FXML
    public void setChessBoardGrid(){
        chessBoardInitializer = new ChessBoardInitializer();
        chessBoardInitializer.initializeChessBoard(chessBoardGrid);
        chessMoveManager = new ChessMoveManager(chessBoardInitializer.getBoard(),chessBoardInitializer.getGridMapCordToPane(), chessBoardInitializer.getBoardProperty());

    }

    @FXML
    public void updateTileDisplay(StackPane stackpane, Image img){
        if (stackpane.getChildren().get(0) != null) {
            ImageView imageView = (ImageView) stackpane.getChildren().get(0);
            imageView.setImage(img);
        }
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
            ChessBoardInitializer.flipBoard(chessBoardGrid,chessMoveManager.getBoard());
//            if (backGroundAnchorPane.getStyleClass().contains("dark-mode")){
//                mainBorderPane.getStyleClass().remove("dark-mode");
//                mainBorderPane.getStyleClass().add("light-mode");
//
//                backGroundAnchorPane.getStyleClass().remove("dark-mode");
//                backGroundAnchorPane.getStyleClass().add("light-mode");
//            }else{
//                mainBorderPane.getStyleClass().remove("light-mode");
//                mainBorderPane.getStyleClass().add("dark-mode");
//
//                backGroundAnchorPane.getStyleClass().remove("light-mode");
//                backGroundAnchorPane.getStyleClass().add("dark-mode");
//            }

        });

    }





}