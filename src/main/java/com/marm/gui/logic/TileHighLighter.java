/******************************************
 *CSCI 205 - Software Engineering and Design
 *Spring 2024
 *Instructor: Prof. Lily Romano / Prof. Joshua Stough
 *
 *Name: Miguel Romero
 *Section: 01
 *Date: 1/7/25
 *Time: 4:40 AM
 *
 *Project: chessEngine
 *Package: com.marm.gui.logic
 *Class: TileHighLighter
 *Description:
 * **************************************** */
package com.marm.gui.logic;

import com.marm.chessengine.board.Board;
import com.marm.chessengine.board.Move;
import com.marm.chessengine.board.MutableCoordinate;
import com.marm.chessengine.pieces.Piece;
import javafx.scene.layout.StackPane;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TileHighLighter {

    private final Map<MutableCoordinate, StackPane> coordinateStackPaneMap;
    private  final Board board;

    private  Piece piece;

    Map<Move, StackPane> destinationCoordinateStackPaneMap;

    public TileHighLighter(Board board ,Map<MutableCoordinate, StackPane> coordinateStackPaneMap, Piece piece){
        this.coordinateStackPaneMap = coordinateStackPaneMap;
        this.board  = board;
        this.piece = piece;
        destinationCoordinateStackPaneMap = new HashMap<>();
        calculateAllCurrentTiles();

    }

    public void setPieceRedCandidates(){
        List<Move> legalMove = piece.calculateLegalMoves(this.board);
        for (Move move : destinationCoordinateStackPaneMap.keySet()){
            if (legalMove.contains(move)){
                MutableCoordinate reverse  = new MutableCoordinate(move.getDestinationCoordinate().getY(), move.getDestinationCoordinate().getX());
                coordinateStackPaneMap.get(reverse).setId("high-lighted-tile");
            }
        }

    }

    public void setPiece(Piece piece){
        if (this.piece != null){
            this.removeHighLightTiles();
        }
        this.piece = piece;
        this.setPieceRedCandidates();
    }

    public void calculateAllCurrentTiles(){
        for (Move move : this.board.getAllLegalMoves()){
            MutableCoordinate reverse = new MutableCoordinate(move.getDestinationCoordinate().getY(), move.getDestinationCoordinate().getX());
            StackPane thatStackPane = this.coordinateStackPaneMap.get(reverse);
            destinationCoordinateStackPaneMap.put(move, thatStackPane);
        }
    }

    public void turnEmAllRed(){
        for (StackPane stackPane : destinationCoordinateStackPaneMap.values()){
            stackPane.setId("high-lighted-tile");
        }
    }




//    public void highLightDestinationTiles(Map.Entry<MutableCoordinate, StackPane> entry ){
////        System.out.println("You clicked " + entry.getKey() + " coordinate");
//        MutableCoordinate chessCoordinate = new MutableCoordinate(entry.getKey().getY(), entry.getKey().getX());
//        // TODO make this its own class (Want a class that will take in a piece and highlight its destination tiles it should also remove the other highlights when a new piece is click)
//        if(this.board.getTile(chessCoordinate).isTileOccupied()){
//
//            for (Move move :this.board.getTile(chessCoordinate).getPiece().calculateLegalMoves(this.board)){
//                MutableCoordinate reverseDestinationCord = new MutableCoordinate(move.getDestinationCoordinate().getY(), move.getDestinationCoordinate().getX());
//
//                StackPane destinationStackPane = this.coordinateStackPaneMap.get(reverseDestinationCord);
//
//                destinationStackPane.getStyleClass().removeAll();
//                destinationStackPane.setId("high-lighted-tile");
//                stackPaneList.add(destinationStackPane);
//
//
//            }
//
//        }
//
//    }

    private void removeHighLightTiles() {
        List<Move> legalMove = piece.calculateLegalMoves(this.board);
        for (Move move : destinationCoordinateStackPaneMap.keySet()){
            if (legalMove.contains(move)){
                MutableCoordinate reverse  = new MutableCoordinate(move.getDestinationCoordinate().getY(), move.getDestinationCoordinate().getX());
                coordinateStackPaneMap.get(reverse).setId("non-high-lighted-tile");
            }
        }
    }
}