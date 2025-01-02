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
    private final Collection<Piece> whitePieces;
    private final Collection<Piece> blackPieces;


    public Board(Builder builder){
        this.gameBoard = createGameBoard(builder);
        this.whitePieces = calculateActivePieces(this.gameBoard, Alliance.WHITE);
        this.blackPieces = calculateActivePieces(this.gameBoard, Alliance.BLACK);
        final Collection<Move> whiteStandardLegalMoves = calculateLegalMoves(this.whitePieces);
        final Collection<Move> blackStandardLegalMoves = calculateLegalMoves(this.blackPieces);

    }

    private Collection<Move> calculateLegalMoves(final Collection<Piece> Pieces) {
        final List<Move> legalMoves = new ArrayList<>();

        for( final Piece piece:Pieces ){
            legalMoves.addAll(piece.calculateLegalMoves(this));
        }
        return Collections.unmodifiableList(legalMoves);
    }

    private static  Collection<Piece> calculateActivePieces(final Map<MutableCoordinate, Tile> gameBoard, final Alliance alliance) {
        final List<Piece> activePieces = new ArrayList<>();

        for (MutableCoordinate key: gameBoard.keySet()){
            final Tile currentTile = gameBoard.get(key);
            if( currentTile.isTileOccupied()){
                final Piece piece = currentTile.getPiece();
                if(piece.getPieceAlliance() == alliance){
                    activePieces.add(piece);
                }
            }

        }

        return Collections.unmodifiableList(activePieces);
    }

    @Override
    public String toString(){
        final StringBuilder builder = new StringBuilder();
        for (int i = 0; i< BoardUtils.NUM_TILES_PER_ROW; i++){
            for (int j = 0; j < BoardUtils.NUM_TILES_PER_ROW; j++){
                final String tileText = prettyPrint(this.gameBoard.get(null));
                builder.append(String.format("%3s", tileText));

            }
            builder.append("\n");
        }
        return builder.toString();
    }

    private static String prettyPrint(final Tile tile) {
        if(tile.isTileOccupied()){
            return tile.getPiece().getPieceAlliance().isBlack() ? tile.toString().toLowerCase() :
                    tile.toString();
        }
        return tile.toString();


    }

    private static Map<MutableCoordinate,Tile> createGameBoard(final Builder builder){
        final Map<MutableCoordinate,Tile>  tiles = new HashMap<>();
        for (int i = 0; i < BoardUtils.NUM_TILES_PER_ROW; i++){
            for (int j = 0 ; j < BoardUtils.NUM_TILES_PER_ROW; j++){
                MutableCoordinate coordinate = new MutableCoordinate(i,j);
                tiles.put(coordinate, Tile.createTile(i,j,builder.boardConfig.get(coordinate)));


            }

        }
        return Collections.unmodifiableMap(tiles);

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
            builder.setPiece(new Pawn(6,j, Alliance.WHITE));
        }

        builder.setMoveMaker(Alliance.WHITE);

        return builder.build();
    }

    public Tile getTile(MutableCoordinate coordinatePair){
        return gameBoard.get(coordinatePair);
    }

    public static class Builder {

        Map<MutableCoordinate, Piece> boardConfig;
        Alliance nextMoveMaker;

        public  Builder() {
            this.boardConfig = new HashMap<>();

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

        Map<List<Integer>,Integer> testing = new HashMap<>();
    }

}