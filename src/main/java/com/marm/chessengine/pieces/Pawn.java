/******************************************
 *CSCI 205 - Software Engineering and Design
 *Spring 2024
 *Instructor: Prof. Lily Romano / Prof. Joshua Stough
 *
 *Name: Miguel Romero
 *Section: 01
 *Date: 12/31/24
 *Time: 2:17 PM
 *
 *Project: chessEngine
 *Package: com.marm.chessengine.pieces
 *Class: Pawn
 *Description:
 * **************************************** */
package com.marm.chessengine.pieces;

import com.marm.chessengine.Alliance;
import com.marm.chessengine.board.Board;
import com.marm.chessengine.board.BoardUtils;
import com.marm.chessengine.board.Move;
import com.marm.chessengine.board.MutableCoordinate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.marm.chessengine.board.Move.*;

public class Pawn extends Piece{
    private final static int[][] CANDIDATE_MOVE_COORDINATES = {{1,0} ,{2,0}, {1,1}, {1,-1}};
    public Pawn( final int pieceXCord, final int pieceYCord, final Alliance alliance) {
        super(pieceXCord, pieceYCord, alliance, PieceType.PAWN);
    }


    @Override
    public List<Move> calculateLegalMoves(Board board) {
        final List<Move> legalMoves = new ArrayList<>();
        for (final int[] currentCandidateVector: CANDIDATE_MOVE_COORDINATES){
            /*Takes into account what Alliance the piece is*/
            MutableCoordinate candidateDestinationCoordinate = new MutableCoordinate(this.pieceXCord + (currentCandidateVector[0]  * this.getPieceAlliance().getDirection()), this.pieceYCord + (currentCandidateVector[1] * this.getPieceAlliance().getDirection()));
            /* Move to the next iteration tile is out of board */
            if (!BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)){
                continue;
            }
            /* Not occupied able to add to moves*/
            System.out.print(board.getTile(candidateDestinationCoordinate));
            if (Arrays.equals(currentCandidateVector, new int[]{1,0}) && !board.getTile(candidateDestinationCoordinate).isTileOccupied()) {
                legalMoves.add(new MajorMove(board,this, candidateDestinationCoordinate));
                /* Logic for pawn jump move, has to be first move and either on second row or seventh row depends on if black or white */

            }else if ((Arrays.equals(currentCandidateVector, new int[]{2,0} )  && (this.isFirstMove())) &&
                    ((BoardUtils.SECOND_ROW[this.pieceXCord][this.pieceYCord] && this.pieceAlliance.isBlack()) ||
                    (BoardUtils.SEVENTH_ROW[this.pieceXCord][this.pieceYCord] && this.pieceAlliance.isWhite())) ){
                final MutableCoordinate behindCandidateDestinationCoordinate = new MutableCoordinate(this.pieceXCord + (this.getPieceAlliance().getDirection()), this.pieceYCord );
                if(!board.getTile(behindCandidateDestinationCoordinate).isTileOccupied()  && !board.getTile(candidateDestinationCoordinate).isTileOccupied()){
                    legalMoves.add(new MajorMove(board,this, candidateDestinationCoordinate));
                }
            } else if (Arrays.equals(currentCandidateVector, new int[] {1,1})   ||  Arrays.equals(currentCandidateVector,new int[]{1,-1})) {
                if (board.getTile(candidateDestinationCoordinate).isTileOccupied()) {
                    final Piece pieceOnCandidate = board.getTile(candidateDestinationCoordinate).getPiece();
                    if (this.pieceAlliance != pieceOnCandidate.getPieceAlliance()) {
                        legalMoves.add(new MajorMove(board, this, candidateDestinationCoordinate));
                    }
                }
            }

        }
        return Collections.unmodifiableList(legalMoves);
    }



    @Override
    public String toString(){
        return PieceType.PAWN.toString();
    }

    @Override
    public Pawn movePiece(final Move move) {
        return new Pawn(move.getDestinationCoordinate().getX(),move.getDestinationCoordinate().getY(),move.getMovedPiece().getPieceAlliance());
    }


    public static void main(String[] args) {
        Piece test = new Pawn(3,4, Alliance.WHITE);
    }
}