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

import com.marm.chessengine.Alliance;
import com.marm.chessengine.board.Board;
import com.marm.chessengine.board.Move;
import com.marm.chessengine.board.MutableCoordinate;
import com.marm.chessengine.board.Tile;
import com.marm.chessengine.pieces.Piece;
import com.marm.chessengine.pieces.Rook;
import com.marm.chessengine.player.MoveTransition;
import com.marm.gui.logic.ChessBoardInitializer;
import com.marm.gui.logic.ChessMoveManager;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.util.List;
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
//        test();

    }

    public void bindTilesToBoard() {
        chessMoveManager.getBoardProperty().getBoardProperty().addListener(((observableValue, board1, t1) -> {
            Map<MutableCoordinate, Tile> map = board1.diffBetweenBoards(t1);
            System.out.println(map);
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
        chessMoveManager = new ChessMoveManager(chessBoardInitializer.getBoard(),chessBoardInitializer.getGridMapCordToPane(), chessBoardInitializer.getPropertyMap(), chessBoardInitializer.getBoardProperty());

    }
    @FXML
    public void test(){
        Board board = Board.createStandardBoard();
        Move move = Move.MoveFactory.createMove(board,new MutableCoordinate(6,0), new MutableCoordinate(4,0));
        MoveTransition moveTransition = board.currentPlayer().makeMove(move);
        board = moveTransition.getTransitionBoard();
        System.out.println(chessMoveManager.getBoard().diffBetweenBoards(board));
    }

    //TODO Work on removing Old tile once new tile gets updated, maybe try a different approach of binding

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
                    // Reapply click handlers after updating the grid ma
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