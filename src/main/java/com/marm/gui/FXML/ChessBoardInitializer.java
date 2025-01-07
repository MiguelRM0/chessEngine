/******************************************
 *CSCI 205 - Software Engineering and Design
 *Spring 2024
 *Instructor: Prof. Lily Romano / Prof. Joshua Stough
 *
 *Name: Miguel Romero
 *Section: 01
 *Date: 1/6/25
 *Time: 5:09 PM
 *
 *Project: chessEngine
 *Package: com.marm.gui.FXML
 *Class: ChessBoardInitializer
 *Description:
 * **************************************** */
package com.marm.gui.FXML;

import com.marm.chessengine.Alliance;
import com.marm.chessengine.board.Board;
import com.marm.chessengine.board.BoardUtils;
import com.marm.chessengine.board.MutableCoordinate;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

import java.util.*;

public class ChessBoardInitializer {

    public Map<MutableCoordinate,StackPane> coordinateStackPair;

    Board board;

    public ChessBoardInitializer() {
        coordinateStackPair = new HashMap<>();
    }
    

    public  void initializeChessBoard(GridPane chessBoardGrid) {
        board = Board.createStandardBoard();
        System.out.println(board);

        for (int i = 0; i < BoardUtils.NUM_TILES_PER_ROW; i++) {
            for (int j = 0; j < BoardUtils.NUM_TILES_PER_ROW; j++) {
                MutableCoordinate currentCoordinate = new MutableCoordinate(i, j);

                ImageView imageView = getBoardImages(currentCoordinate);
                StackPane stackPane = tileColorSetUp( currentCoordinate);
                stackPane.getChildren().add(imageView);


                MutableCoordinate gridSetUpCord = new MutableCoordinate(currentCoordinate.getY(), currentCoordinate.getX());
                coordinateStackPair.put(gridSetUpCord, stackPane);

                chessBoardGrid.add(stackPane, gridSetUpCord.getX(),gridSetUpCord.getY());
            }
        }
    }

    private ImageView getBoardImages(MutableCoordinate currentCoordinate) {
        ImageView imageView = new ImageView();
        if (this.board.getTile(currentCoordinate).isTileOccupied()){
            if (this.board.getTile(currentCoordinate).getPiece().getPieceAlliance()== Alliance.WHITE) {
                imageView.setImage(this.board.getTile(currentCoordinate).getPiece().getWhiteImg());
            }
            else{
                imageView.setImage(this.board.getTile(currentCoordinate).getPiece().getBlackImg());

            }

        }
        return imageView;
    }

    private static StackPane tileColorSetUp( MutableCoordinate currentCoordinate) {
        StackPane stackPane = new StackPane();

        if ((currentCoordinate.getX() + currentCoordinate.getY()) % 2 == 0) {
            stackPane.setId("evenSquareGreen");
        } else {
            stackPane.setId("oddSquareOffWhite");
        }

        return stackPane;
    }


}

