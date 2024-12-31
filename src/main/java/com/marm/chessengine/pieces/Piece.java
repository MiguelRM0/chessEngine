package com.marm.chessengine.pieces;

import com.marm.chessengine.Alliance;
import com.marm.chessengine.board.Board;
import com.marm.chessengine.board.Move;
import javafx.util.Pair;

import java.util.List;

public abstract class Piece{

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

    public abstract List<Move> calculateLegalMoves(final Board board);



}