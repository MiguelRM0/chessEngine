
package com.marm.chessengine.board;


import com.marm.chessengine.pieces.Piece;

import java.util.*;

public abstract class Tile {
    protected final int xCord;
    protected final int yCord;

    protected final MutableCoordinate tileCoordinatePair;

    private static final Map<MutableCoordinate, EmptyTile> EMPTY_TILE_CACHE = createAllPossibleEmptyTiles();

    private static Map<MutableCoordinate, EmptyTile> createAllPossibleEmptyTiles() {
        final Map<MutableCoordinate, EmptyTile> emptyTileMap = new HashMap<>();
        for ( int i = 0; i < BoardUtils.NUM_TILES_PER_ROW; i++){
            for (int j = 0; j < BoardUtils.NUM_TILES_PER_ROW; j++){
                MutableCoordinate coordinate = new MutableCoordinate(i, j);
                EmptyTile emptyTile = new EmptyTile(i,j);
                emptyTileMap.put(coordinate, emptyTile);
            }

        }

        return Collections.unmodifiableMap(emptyTileMap);
    }

    private String emptyTileCoordinateMap(){
        StringBuilder result = new StringBuilder();
        for ( int i = 0; i < BoardUtils.NUM_TILES_PER_ROW; i++){
            for (int j = 0; j < BoardUtils.NUM_TILES_PER_ROW; j++){
                result.append(new MutableCoordinate(i,j));
            }
            result.append("\n");

        }
        return result.toString();
    }

    public static Tile createTile(final int xCoord, final int yCoord, final Piece piece){
        return piece != null ? new OccupiedTile(xCoord, yCoord, piece) : EMPTY_TILE_CACHE.get(new MutableCoordinate(xCoord,yCoord));
    }

    public Map<MutableCoordinate,EmptyTile> getEmptyTileMap(){
        return EMPTY_TILE_CACHE;
    }

    private Tile(int xCoord, int yCoord){
        this.xCord = xCoord;
        this.yCord = yCoord;


        this.tileCoordinatePair = new MutableCoordinate(this.xCord, this.yCord);
    }

    public abstract boolean isTileOccupied();

    public abstract Piece getPiece();


    public static final class EmptyTile extends Tile{
        private EmptyTile(final int xCoord, final int yCoord){
            super(xCoord, yCoord);
        }
        @Override
        public boolean isTileOccupied(){
            return false;
        }

        @Override
        public Piece getPiece(){
            return null;
        }
        @Override
        public String toString(){
            return "-";
        }
    }




    public static final class OccupiedTile extends Tile{

        private final Piece pieceOnTile;

        private OccupiedTile(final int xCoord, final int yCoord, Piece pieceOnTile) {
            super(xCoord, yCoord);
            this.pieceOnTile = pieceOnTile;
        }

        @Override
        public boolean isTileOccupied() {return true;}

        @Override
        public Piece getPiece() {return this.pieceOnTile;}

        public String toString(){
            return this.pieceOnTile.toString();
        }
    }

public static void main(String[] args) {

    Tile emptyTile = new EmptyTile(5,3);
    System.out.println(emptyTile.emptyTileCoordinateMap());

    }
}