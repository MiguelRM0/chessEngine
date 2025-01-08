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

import java.util.*;

public class TileHighLighter {

    private final Map<MutableCoordinate, StackPane> coordinateStackPaneMap;
    private  final Board board;

    private  final Piece piece;

    private final List<StackPane> destinationStackPanes;

    public TileHighLighter(Board board ,Map<MutableCoordinate, StackPane> coordinateStackPaneMap, Piece piece){
        this.coordinateStackPaneMap = coordinateStackPaneMap;
        this.board  = board;
        this.piece = piece;
        destinationStackPanes = calcDestinationPane();
        setHighLightTiles();

    }


    public List<StackPane> calcDestinationPane(){
        List<StackPane> destinationLegalMoves = new ArrayList<>();

        List<Move> pieceLegalMoves = this.piece.calculateLegalMoves(this.board);
        for (Move pieceLegalMove: pieceLegalMoves){
            MutableCoordinate reverseCord = new MutableCoordinate(pieceLegalMove.getDestinationCoordinate().getY(), pieceLegalMove.getDestinationCoordinate().getX());
            destinationLegalMoves.add(coordinateStackPaneMap.get(reverseCord));
        }

        return destinationLegalMoves;

    }

    public void setHighLightTiles(){
        for( StackPane stackPane : destinationStackPanes){
            stackPane.setId("high-lighted-tile");
        }

    }

    public void removeHighLightTiles() {
        for (StackPane stackPane : destinationStackPanes){
            stackPane.setId("non-high-lighted-tile");
        }
    }
}