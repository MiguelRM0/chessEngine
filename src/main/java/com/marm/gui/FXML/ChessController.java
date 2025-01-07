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
import javafx.event.ActionEvent;
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

    @FXML
    public void initialize(){
        setChessBoardGrid();
//        darkMode = new MenuItem("ToggleDarkMode");
        backGroundAnchorPane.getStyleClass().add("dark-mode");
//        mainBorderPane.getStyleClass().add("dark-mode");
        mainBorderPane.getStyleClass().add("customBorderPane");
        setClickOnStackPane();

    }
    @FXML
    public void setChessBoardGrid(){
        chessBoardInitializer = new ChessBoardInitializer();
        chessBoardInitializer.initializeChessBoard(chessBoardGrid);
    }

    public void setClickOnStackPane(){
        for (Map.Entry<MutableCoordinate,  StackPane> entry : chessBoardInitializer.getCoordinateStackPair().entrySet()){
            entry.getValue().setOnMouseClicked(mouseEvent -> {
                System.out.println("You clicked " + entry.getKey() + " coordinate");
                MutableCoordinate chessCoordinate = new MutableCoordinate(entry.getKey().getY(), entry.getKey().getX());
                System.out.print(chessBoardInitializer.getBoard().getTile(chessCoordinate).getPiece() + " ");
                if (chessBoardInitializer.getBoard().getTile(chessCoordinate).getPiece() != null) {
                    System.out.println(chessBoardInitializer.getBoard().getTile(chessCoordinate).getPiece().getPieceAlliance());
                }

                if(chessBoardInitializer.getBoard().getTile(chessCoordinate).isTileOccupied()){
                    for (Move move :chessBoardInitializer.getBoard().getTile(chessCoordinate).getPiece().calculateLegalMoves(chessBoardInitializer.getBoard())){
//                        chessBoardInitializer.getCoordinateStackPair().get(move.getDestinationCoordinate()).getStyleClass().removeAll();
//                        chessBoardInitializer.getCoordinateStackPair().get(move.getDestinationCoordinate()).getStyleClass().add("high-lighted-tile");
//                        move.getDestinationCoordinate();
                        MutableCoordinate reverse = new MutableCoordinate(move.getDestinationCoordinate().getY(), move.getDestinationCoordinate().getX());
                        System.out.println("Coordinate " + reverse );
                        System.out.print("The id is " + chessBoardInitializer.getCoordinateStackPair().get(reverse).getId());
                        System.out.println(chessBoardInitializer.getCoordinateStackPair().get(reverse));
//
//                        destinationStackPane.applyCss();
//                        destinationStackPane.layout();
                        StackPane destinationStackPane = chessBoardInitializer.getCoordinateStackPair().get(reverse);
                        destinationStackPane.getStyleClass().removeAll();
                        destinationStackPane.setId("high-lighted-tile");
                        destinationStackPane.applyCss();

                        System.out.println(destinationStackPane.getStyleClass().toString());
                    }

                }
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