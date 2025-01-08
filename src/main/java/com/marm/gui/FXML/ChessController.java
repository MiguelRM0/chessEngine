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

import com.marm.chessengine.board.MutableCoordinate;
import com.marm.chessengine.pieces.Piece;
import com.marm.gui.logic.ChessBoardInitializer;
import com.marm.gui.logic.ChessMoveManager;
import com.marm.gui.logic.TileProperty;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

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

    private void bindTilesToBoard() {
        for (Map.Entry<MutableCoordinate, StackPane> entry : chessMoveManager.getGridMapCordToPane().entrySet()) {
            MutableCoordinate coordinate = entry.getKey();
            StackPane tilePane = entry.getValue();



            // Get the TileState from the board
            TileProperty tileProperty = chessBoardInitializer.getPropertyMap().get(coordinate);

            // Bind the tile's graphical representation to the TileState
            tileProperty.getPieceProperty().addListener((obs, oldPiece, newPiece) -> {
                Platform.runLater(() -> updateTileDisplay(tilePane, newPiece));
            });
        }
    }

    private void updateTileDisplay(StackPane stackpane, Piece piece ){
        stackpane.getChildren().clear();
        if(piece != null){
            ImageView imageView =new ImageView();
//            imageView.setImage(piece.);
//            stackpane.getChildren().add(piece.);
        }
    }


    public void setClickOnStackPane(){
        for (Map.Entry<MutableCoordinate,  StackPane> entry : chessMoveManager.getGridMapCordToPane().entrySet()){
            entry.getValue().setOnMouseClicked(mouseEvent -> {
                Platform.runLater(() -> {
                    chessMoveManager.processTileClick(entry);
                    // Reapply click handlers after updating the grid map
                    setClickOnStackPane();
                });
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