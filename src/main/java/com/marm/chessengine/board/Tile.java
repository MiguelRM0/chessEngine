
package com.marm.chessengine.board;


import com.marm.chessengine.Alliance;
import com.marm.chessengine.pieces.Pawn;
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
        for ( int i = BoardUtils.NUM_TILES_PER_ROW -1; i >= 0; i--){
            for (int j = BoardUtils.NUM_TILES_PER_ROW -1; j >=0; j--){
                result.append(new MutableCoordinate(i,j));
            }
            result.append("\n");

        }
        return result.toString();
    }


    private String emptyTileCoordinateMapStraight(){
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
        @Override
        public boolean equals(Object object){
            if (object == this) return true;
            if (!(object instanceof EmptyTile)) return false;
            EmptyTile other = (EmptyTile) object;
            return this.tileCoordinatePair.equals(other.tileCoordinatePair);
        }

        public int hashCode(){
            return tileCoordinatePair.hashCode();
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

        @Override
        public boolean equals(Object object){
            if (object == this) return true;
            if (!(object instanceof OccupiedTile)) return false;
            OccupiedTile other = (OccupiedTile) object;
            return this.tileCoordinatePair .equals(other.tileCoordinatePair)  &&
                    this.pieceOnTile.equals(other.getPiece());
        }

        public int hashCode(){
            int result = 31  +tileCoordinatePair.hashCode();
            result = 31 * result + getPiece().hashCode();
            return result;
        }


    }

    public static void main(String[] args) {
        Tile tile = new EmptyTile(5,5);
//    System.out.println(tile.emptyTileCoordinateMap());
//    System.out.println(tile.emptyTileCoordinateMapStraight());
        Map<MutableCoordinate , Tile> newTile = new HashMap<>();
//        for (Map.Entry<MutableCoordinate,EmptyTile> entry: tile.getEmptyTileMap().entrySet()){
//            newTile.put(new MutableCoordinate(  8 - entry.getValue().xCord - 1, 8-entry.getValue().yCord), entry.getValue());
//        }





    }
}