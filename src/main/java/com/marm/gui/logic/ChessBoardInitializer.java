/******************************************
 *Name: Miguel Romero
 *Date: 1/6/25
 *Time: 5:09PM
 *
 *Project: chessEngine
 *Package: com.marm.gui.logic
 *Class: ChessBoardInitializer
 *Description:
 * **************************************** */
package com.marm.gui.logic;

import com.marm.chessengine.Alliance;
import com.marm.chessengine.board.Board;
import com.marm.chessengine.board.BoardUtils;
import com.marm.chessengine.board.MutableCoordinate;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

import java.util.*;

public class ChessBoardInitializer {

    private Map<MutableCoordinate,StackPane> gridMapCordToPane;

    private Board board;


    public ChessBoardInitializer() {
        gridMapCordToPane = new HashMap<>();
    }
    

    public  void initializeChessBoard(GridPane chessBoardGrid) {
        board = Board.createStandardBoard();

        for (int i = 0; i < BoardUtils.NUM_TILES_PER_ROW; i++) {
            for (int j = 0; j < BoardUtils.NUM_TILES_PER_ROW; j++) {
                MutableCoordinate currentCoordinate = new MutableCoordinate(i, j);

                ImageView imageView = getBoardImage(currentCoordinate);
                StackPane stackPane = tileColorSetUp( currentCoordinate);
                stackPane.getChildren().add(imageView);


                MutableCoordinate gridSetUpCord = new MutableCoordinate(currentCoordinate.getY(), currentCoordinate.getX());
                gridMapCordToPane.put(gridSetUpCord, stackPane);

                chessBoardGrid.add(stackPane, gridSetUpCord.getX(),gridSetUpCord.getY());
            }
        }
    }

    public Map<MutableCoordinate, StackPane> getGridMapCordToPane(){
        return this.gridMapCordToPane;
    }

    public Board getBoard(){
        return this.board;
    }



    private ImageView getBoardImage(MutableCoordinate currentCoordinate) {
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
            stackPane.getStyleClass().add("evenSquareGreen");
        } else {
            stackPane.getStyleClass().add("oddSquareOffWhite");
        }

        return stackPane;
    }



}

