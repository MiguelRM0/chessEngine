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

    private final  Map<MutableCoordinate,StackPane> gridMapCordToPane;

    private final Board board;


    public ChessBoardInitializer() {
        gridMapCordToPane = new HashMap<>();
        board = Board.createStandardBoard();
    }
    

    public  void initializeChessBoard(GridPane chessBoardGrid) {

        for (int i = 0; i < BoardUtils.NUM_TILES_PER_ROW; i++) {
            for (int j = 0; j < BoardUtils.NUM_TILES_PER_ROW; j++) {
                MutableCoordinate currentCoordinate = new MutableCoordinate(i, j);
//                StackPane stackPane = new StackPane();
                ImageView imageView = getBoardImage(currentCoordinate);
//                StackPane stackPane = tileColorSetUp( currentCoordinate);
                 StackPane stackPane = tileColorSetUp(currentCoordinate);
                 stackPane.getChildren().add(imageView);

//                assignTileColor(chessBoardGrid, stackPane, currentCoordinate);


//                MutableCoordinate gridSetUpCord = new MutableCoordinate(currentCoordinate.getY(), currentCoordinate.getX());
                gridMapCordToPane.put(currentCoordinate, stackPane);
                chessBoardGrid.add(stackPane, currentCoordinate.getY(),currentCoordinate.getX());
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

//    private void assignTileColor(GridPane chessBoardGrid, StackPane stackPane, MutableCoordinate currentCoordinate){
//        String tileClass = ((currentCoordinate.getX() + currentCoordinate.getY()) %2 == 0){
//            stackPane
//        }
//    }

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

