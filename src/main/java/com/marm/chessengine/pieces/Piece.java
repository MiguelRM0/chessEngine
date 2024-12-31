package com.marm.chessengine.pieces;

import com.marm.chessengine.Alliance;
import com.marm.chessengine.board.*;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Piece{

    private static final int[][] CANDIDATE_MOVE_VECTOR_COORDINATES = null ;
    protected final int pieceXCord;
    protected final int pieceYCord;

    protected final Pair<Integer, Integer> pieceCoordinatePair;

    protected final Alliance pieceAlliance;


    Piece(final int pieceXCord, final int pieceYCord, final Alliance alliance){
        this.pieceXCord = pieceXCord;
        this.pieceYCord = pieceYCord;
        this.pieceAlliance = alliance;
        this.pieceCoordinatePair = new Pair<>(this.pieceXCord, this.pieceYCord);

    }
    public Alliance getPieceAlliance(){
        return this.pieceAlliance;
    }

    public abstract List<Move> calculateLegalMoves(final Board board);


    protected List<Move> legalMovesQRB(final Board board, int[][] CANDIDATE_MOVE_VECTOR_COORDINATES){
        final List<Move> legalMoves = new ArrayList<>();
        for (final int[] candidateCoordinateVector : CANDIDATE_MOVE_VECTOR_COORDINATES){
            /* Grab the current position into another mutable list,so we don't modify our original position  */
            MutableCoordinate candidateDestinationCoordinate = new MutableCoordinate(this.pieceXCord, this.pieceYCord);
            /* While we are still in a valid candidate destination coordinate*/
            while(BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)){
                /* Add our current vector */
                candidateDestinationCoordinate.setX(candidateDestinationCoordinate.getX()+ candidateCoordinateVector[0]);
                candidateDestinationCoordinate.setY(candidateDestinationCoordinate.getY() + candidateCoordinateVector[1]);
                /* if the next move ,after adding the current vector, is valid */
                if (BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)){
                    /* Grab the tile */
                    final Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinate);
                    /* If not occupied add to legal moves*/
                    if(!candidateDestinationTile.isTileOccupied()){
                        legalMoves.add(new Move.MajorMove(board,this , candidateDestinationCoordinate));
                    } else {
                        /* Else is occupied and will need to break as that's when the piece stops*/
                        final Piece pieceAtDestination = candidateDestinationTile.getPiece();
                        final Alliance pieceAlliance = pieceAtDestination.getPieceAlliance();
                        if (this.pieceAlliance != pieceAlliance){
                            legalMoves.add(new Move.AttackMove(board,this,candidateDestinationCoordinate,pieceAtDestination));
                        }
                        break;
                    }
                }


            }
        }
        return Collections.unmodifiableList(legalMoves);
    }



}