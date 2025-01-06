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
import com.marm.chessengine.board.BoardUtils;
import com.marm.chessengine.board.MutableCoordinate;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public class ChessController {
    public BorderPane mainBorderPane;
    public GridPane chessBoardGrid;

    @FXML
    public void initialize(){
        setChessBoardGrid();

    }
    @FXML
    public void setChessBoardGrid(){
        Board board = Board.createStandardBoard();
        System.out.println(board);
        for(int i = 0; i < BoardUtils.NUM_TILES_PER_ROW; i++){
            for (int j = 0; j < BoardUtils.NUM_TILES_PER_ROW; j++){
                MutableCoordinate currentCoordinate = new MutableCoordinate(i,j);
                Label label = new Label();
                if(board.getTile(currentCoordinate).getPiece() == null){
                    label.setText(board.getTile(currentCoordinate).toString());
                }else{
                    label.setText(board.getTile(currentCoordinate).getPiece().getPieceAlliance().toString());
                }
                StackPane stackPane = new StackPane(label);
                if((currentCoordinate.getX()+ currentCoordinate.getY()) % 2 == 0){
                    stackPane.setId("stackPaneGreen");
                }else{
                    stackPane.setId("stackPaneOffWhite");
                }
                chessBoardGrid.add(stackPane, currentCoordinate.getY(),currentCoordinate.getX());

            }
        }

    }


}