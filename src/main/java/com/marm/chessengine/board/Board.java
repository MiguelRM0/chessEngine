/******************************************
 *CSCI 205 - Software Engineering and Design
 *Spring 2024
 *Instructor: Prof. Lily Romano / Prof. Joshua Stough
 *
 *Name: Miguel Romero
 *Section: 01
 *Date: 12/30/24
 *Time: 7:42 PM
 *
 *Project: chessEngine
 *Package: com.marm.chessengine.board
 *Class: Baord
 *Description:
 * **************************************** */
package com.marm.chessengine.board;

import com.marm.chessengine.Alliance;
import com.marm.chessengine.pieces.*;

import java.util.*;

public class Board {
    private final Map<MutableCoordinate, Tile> gameBoard;
    public Board(Builder builder){
        this.gameBoard = createGameBoard(builder);

    }

    private static Map<MutableCoordinate,Tile> createGameBoard(final Builder builder){
        final Map<MutableCoordinate,Tile>  tiles = new HashMap<>();
        for (int i = 0; i < BoardUtils.NUM_TILES_PER_ROW; i++){
            final List<Tile> row = new ArrayList<>();
            for (int j = 0 ; j < BoardUtils.NUM_TILES_PER_ROW; j++){
                MutableCoordinate coordinate = new MutableCoordinate(i,j);
                tiles.put(coordinate, Tile.createTile(i,j,builder.boardConfig.get(coordinate)));


            }

        }
        return tiles;

    }

    public static Board createStandardBoard(){
        final Builder builder = new Builder();
        builder.setPiece(new Rook(0,0,Alliance.BLACK));
        builder.setPiece(new Knight(0,1,Alliance.BLACK));
        builder.setPiece(new Bishop(0,2,Alliance.BLACK));
        builder.setPiece(new Queen(0,3,Alliance.BLACK));
        builder.setPiece(new King(0,4,Alliance.BLACK));
        builder.setPiece(new Bishop(0,5,Alliance.BLACK));
        builder.setPiece(new Knight(0,6,Alliance.BLACK));
        builder.setPiece(new Rook(0,7,Alliance.BLACK));
        for (int i = 0; i < BoardUtils.NUM_TILES_PER_ROW; i++){
            builder.setPiece(new Pawn(1,i, Alliance.BLACK));
        }
        builder.setPiece(new Rook(7,0,Alliance.WHITE));
        builder.setPiece(new Knight(7,1,Alliance.WHITE));
        builder.setPiece(new Bishop(7,2,Alliance.WHITE));
        builder.setPiece(new Queen(7,3,Alliance.WHITE));
        builder.setPiece(new King(7,4,Alliance.WHITE));
        builder.setPiece(new Bishop(7,5,Alliance.WHITE));
        builder.setPiece(new Knight(7,6,Alliance.WHITE));
        builder.setPiece(new Rook(7,7,Alliance.WHITE));
        for (int j = 0; j < BoardUtils.NUM_TILES_PER_ROW; j++){
            builder.setPiece(new Pawn(6,j, Alliance.BLACK));
        }

        return builder.build();
    }

    public Tile getTile(MutableCoordinate coordinatePair){
        return gameBoard.get(coordinatePair);
    }

    public static class Builder {

        Map<MutableCoordinate, Piece> boardConfig;
        Alliance nextMoveMaker;

        public  Builder() {

        }

        public Builder setPiece(final Piece piece){
            this.boardConfig.put(piece.getPieceCoordinatePair(), piece);
            return this;
        }

        public Builder setMoveMaker(final Alliance nextMoveMaker){
            this.nextMoveMaker = nextMoveMaker;
            return this;
        }


        public Board build(){
            return new Board(this);
        }
    }

    public static void main(String[] args) {
//        int[] test = new int[5];
        Tile[] test = new Tile[5];
        System.out.println(Arrays.toString(test));

        Board board = new Board(new Builder());
        board.gameBoard.size();
    }

}