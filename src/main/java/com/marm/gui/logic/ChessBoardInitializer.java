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
import javafx.scene.layout.Region;
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

//                styleGridCell(chessBoardGrid,j,i);


                ImageView imageView = getBoardImage(currentCoordinate, this.board);

                StackPane stackPane = tileColorSetUp(currentCoordinate);
                stackPane.getChildren().add(imageView);




                gridMapCordToPane.put(currentCoordinate, stackPane);
                chessBoardGrid.add(stackPane,  currentCoordinate.getY(),currentCoordinate.getX());
            }
        }
    }

    public static void drawBoard(GridPane chessBoardGrid, Board board){
        for (int i = 0; i < BoardUtils.NUM_TILES_PER_ROW; i++){
            for (int j = 0; j< BoardUtils.NUM_TILES_PER_ROW; j++){
                MutableCoordinate currentCoordinate = new MutableCoordinate(i,j);
                ImageView imageView = ChessBoardInitializer.getBoardImage(currentCoordinate,board);

            }
        }

    }

    private void styleGridCell(GridPane chessBoardGrid, int row, int col) {
        String color = ((row + col) % 2 == 0) ? "#evenSquareGreen" : "oddSquareOffWhite"; // Example alternating colors
        chessBoardGrid.add(createColoredRegion(color), col, row);
    }

    private StackPane createColoredRegion(String color) {
        StackPane region = new StackPane();
        region.getStyleClass().add(color);
        return region;
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
            if (board.getTile(currentCoordinate).getPiece().getPieceAlliance()== Alliance.WHITE) {
                imageView.setImage(board.getTile(currentCoordinate).getPiece().getWhiteImg());
            }
            else{
                imageView.setImage(board.getTile(currentCoordinate).getPiece().getBlackImg());

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

