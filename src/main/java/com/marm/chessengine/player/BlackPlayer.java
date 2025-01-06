/******************************************
 *CSCI 205 - Software Engineering and Design
 *Spring 2024
 *Instructor: Prof. Lily Romano / Prof. Joshua Stough
 *
 *Name: Miguel Romero
 *Section: 01
 *Date: 1/2/25
 *Time: 12:56 AM
 *
 *Project: chessEngine
 *Package: com.marm.chessengine.player
 *Class: BlackPlayer
 *Description:
 * **************************************** */
package com.marm.chessengine.player;

import com.marm.chessengine.Alliance;
import com.marm.chessengine.board.Board;
import com.marm.chessengine.board.Move;
import com.marm.chessengine.board.MutableCoordinate;
import com.marm.chessengine.board.Tile;
import com.marm.chessengine.pieces.Piece;
import com.marm.chessengine.pieces.Rook;

import static com.marm.chessengine.board.Move.CastleMove.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class BlackPlayer extends Player {
    public BlackPlayer(final Board board,
                       final Collection<Move> whiteStandardLegalMoves,
                       final Collection<Move> blackStandardLegalMoves) {
        super(board, blackStandardLegalMoves, whiteStandardLegalMoves);

    }

    @Override
    public Collection<Piece> getActivePieces() {
        return this.board.getBlackPieces();
    }

    @Override
    public Alliance getAlliance() {
        return Alliance.BLACK;
    }

    @Override
    public Player getOpponent() {
        return this.board.whitePlayer();
    }

    @Override
    protected Collection<Move> calculateKingCastles(final Collection<Move> playerLegals, final Collection<Move> opponentLegals) {
        final List<Move> kingCastles = new ArrayList<>();
        if (this.playerKing.isFirstMove() && !this.isInCheck()) {
            // Blacks king side castle
            MutableCoordinate castlePathKingTile1 = new MutableCoordinate(0, 5);
            MutableCoordinate castlePathKingTile2 = new MutableCoordinate(0, 6);
            MutableCoordinate castleKingRook = new MutableCoordinate(0, 7);
            KingSideCastleLogic(opponentLegals,castlePathKingTile1,castlePathKingTile2,castleKingRook,kingCastles);
            // Blacks queen side castle
            MutableCoordinate castlePathQueenTile1 = new MutableCoordinate(0, 1);
            MutableCoordinate castlePathQueenTile2 = new MutableCoordinate(0, 2);
            MutableCoordinate castlePathQueenTile3 = new MutableCoordinate(0, 3);
            MutableCoordinate castleQueenRook = new MutableCoordinate(0,0);

            QueenSideCastleLogic(opponentLegals, castlePathQueenTile1, castlePathQueenTile2, castlePathQueenTile3, castleQueenRook, kingCastles);
        }
        return Collections.unmodifiableList(kingCastles);
    }

}