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

    private final BoardProperty boardProperty;


    public ChessBoardInitializer() {
        gridMapCordToPane = new HashMap<>();
        board = Board.createStandardBoard();
        boardProperty = new BoardProperty();
        boardProperty.setBoard(board);
    }

    public BoardProperty getBoardProperty(){
        return this.boardProperty;
    }
    

    public  void initializeChessBoard(GridPane chessBoardGrid) {

        for (int i = 0; i < BoardUtils.NUM_TILES_PER_ROW; i++) {
            for (int j = 0; j < BoardUtils.NUM_TILES_PER_ROW; j++) {

                MutableCoordinate currentCoordinate = new MutableCoordinate(i, j);




                ImageView imageView = getBoardImage(currentCoordinate, this.board);
                imageView.setId("imageView" + i + "," + j);
                StackPane stackPane = tileColorSetUp(currentCoordinate);

//                StackPane stackPane = new StackPane();
                stackPane.getChildren().add(imageView);







                gridMapCordToPane.put(currentCoordinate, stackPane);
                chessBoardGrid.add(stackPane,  currentCoordinate.getY(),currentCoordinate.getX());
            }
        }


    }

    public static GridPane flipBoard(GridPane gridPane, Board board){
        Map<MutableCoordinate, StackPane> gridMapCordToPane = new HashMap<>();
        for (int i = BoardUtils.NUM_TILES_PER_ROW -1; i >=0; i--) {
            for (int j = BoardUtils.NUM_TILES_PER_ROW -1; j>=0; j--) {

                MutableCoordinate currentCoordinate = new MutableCoordinate(i, j);


                ImageView imageView = getBoardImage(currentCoordinate, board);

//                imageView.setId("imageView" + i + "," + j);
                StackPane stackPane = tileColorSetUp(currentCoordinate);

//                StackPane stackPane = new StackPane();
                stackPane.getChildren().add(imageView);



                gridMapCordToPane.put(currentCoordinate, stackPane);
                gridPane.add(stackPane,  BoardUtils.NUM_TILES_PER_ROW - currentCoordinate.getY() -1 , BoardUtils.NUM_TILES_PER_ROW-   currentCoordinate.getX() -1);
            }
        }
        return gridPane;

    }

    public void initializeBackwards(GridPane gridPane){
        for (int i = BoardUtils.NUM_TILES_PER_ROW -1; i >=0; i--) {
            for (int j = BoardUtils.NUM_TILES_PER_ROW -1; j>=0; j--) {

                MutableCoordinate currentCoordinate = new MutableCoordinate(i, j);


                ImageView imageView = getBoardImage(currentCoordinate, this.board);

                imageView.setId("imageView" + i + "," + j);
                StackPane stackPane = tileColorSetUp(currentCoordinate);

//                StackPane stackPane = new StackPane();
                stackPane.getChildren().add(imageView);




                gridMapCordToPane.put(currentCoordinate, stackPane);
                gridPane.add(stackPane,  BoardUtils.NUM_TILES_PER_ROW- currentCoordinate.getY() -1 , BoardUtils.NUM_TILES_PER_ROW-   currentCoordinate.getX() -1);
            }
        }

    }

    public static Map<MutableCoordinate, StackPane> drawBoard(GridPane chessBoardGrid, Board board){
        // Clear grid map and main grid
        Map<MutableCoordinate, StackPane> gridMapCordToPane = new HashMap<>();
        chessBoardGrid.getChildren().clear();
        for (int i = 0; i < BoardUtils.NUM_TILES_PER_ROW; i++){
            for (int j = 0; j< BoardUtils.NUM_TILES_PER_ROW; j++){
                MutableCoordinate currentCoordinate = new MutableCoordinate(i,j);

                ImageView imageView = ChessBoardInitializer.getBoardImage(currentCoordinate,board);
                StackPane stackPane = tileColorSetUp(currentCoordinate);


                stackPane.getChildren().add(imageView);
                gridMapCordToPane.put(currentCoordinate, stackPane);
                chessBoardGrid.add(stackPane,  BoardUtils.NUM_TILES_PER_ROW- currentCoordinate.getX() -1 , BoardUtils.NUM_TILES_PER_ROW-   currentCoordinate.getY() -1);

            }
        }

        return gridMapCordToPane;

    }

    public void test(){

    }






    public Map<MutableCoordinate, StackPane> getGridMapCordToPane(){
        return this.gridMapCordToPane;
    }

    public Board getBoard(){
        return this.board;
    }



    public static ImageView getBoardImage(MutableCoordinate currentCoordinate, Board board) {
        ImageView imageView = new ImageView();

        if (board.getTile(currentCoordinate).isTileOccupied()){
            imageView.setImage(board.getTile(currentCoordinate).getPiece().getImage());

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

