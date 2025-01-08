/******************************************
 *Name: Miguel Romero
 *Date: 1/7/25
 *Time: 10:20PM
 *
 *Project: chessEngine
 *Package: com.marm.gui.logic
 *Class: ChessMoveManager
 *Description:
 * **************************************** */
package com.marm.gui.logic;

import com.marm.chessengine.board.Board;
import com.marm.chessengine.board.Move;
import com.marm.chessengine.board.MutableCoordinate;
import com.marm.chessengine.pieces.Piece;
import com.marm.chessengine.player.MoveTransition;
import javafx.scene.layout.StackPane;

import java.util.List;
import java.util.Map;


public class CreateChessMove {

    private Map<MutableCoordinate, StackPane> gridMapCordToPane;

    private  Board board;

    private final Piece movedPiece;



    TileHighLighter tileHighLighter;

    List<StackPane> destinationStackPanes;

    MutableCoordinate destinationCoordinates;


    public CreateChessMove(Board board, Piece piece, Map<MutableCoordinate, StackPane> gridMapCordToPane){
        this.board = board;
        this.movedPiece = piece;
        this.gridMapCordToPane = gridMapCordToPane;
        tileHighLighter = new TileHighLighter(this.board, this.gridMapCordToPane, piece);
        destinationStackPanes = tileHighLighter.getDestinationStackPanes();

    }

    public void setDestinationCoordinate(MutableCoordinate destinationCoordinate){
        this.destinationCoordinates = destinationCoordinate;
        createMove();
    }

    public void createMove(){
        final Move move = Move.MoveFactory.createMove(board, movedPiece.getPieceCoordinatePair(), destinationCoordinates);
        final MoveTransition transition = board.currentPlayer().makeMove(move);
        if(transition.getMoveStatus().isDone()){
            board = transition.getTransitionBoard();
        }
    }

    public TileHighLighter getTileHighLighter(){
        return this.tileHighLighter;
    }




}