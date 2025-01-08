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

    private final Board board;

    private final Piece movedPiece;


    private final TileHighLighter tileHighLighter;

    private final List<StackPane> destinationStackPanes;




    public CreateChessMove(Board board, Piece piece, Map<MutableCoordinate, StackPane> gridMapCordToPane){
        this.board = board;
        this.movedPiece = piece;
        tileHighLighter = new TileHighLighter(this.board, gridMapCordToPane, this.movedPiece);
        destinationStackPanes = tileHighLighter.getDestinationStackPanes();


    }


    public Board createMove(MutableCoordinate destinationCoordinate){
        final Move move = Move.MoveFactory.createMove(board, movedPiece.getPieceCoordinatePair(), destinationCoordinate);
        final MoveTransition transition = board.currentPlayer().makeMove(move);
        // If valid move do move and return new chessBoard
        if(transition.getMoveStatus().isDone()){
            //TODO add the move that was made to the move log
            return transition.getTransitionBoard();

        }
        // Else not valid move keep chess board
        return board;
    }

    public TileHighLighter getTileHighLighter(){
        return this.tileHighLighter;
    }




}