/******************************************
 *Name: Miguel Romero
 *Date: 12/30/24
 *Time: 7:42PM
 *
 *Project: chessEngine
 *Package: com.marm.chessengine.board
 *Class: Baord
 *Description:
 * Board used in chess engine implemented with  the Builder Design pattern
 * **************************************** */
package com.marm.chessengine.board;

import com.marm.chessengine.Alliance;
import com.marm.chessengine.pieces.*;
import com.marm.chessengine.player.BlackPlayer;
import com.marm.chessengine.player.Player;
import com.marm.chessengine.player.WhitePlayer;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Board {
    private final Map<MutableCoordinate, Tile> gameBoard;
    private final Collection<Piece> whitePieces;
    private final Collection<Piece> blackPieces;

    private final WhitePlayer whitePlayer;
    private final BlackPlayer blackPlayer;
    private final Player currentPlayer;


    public Board(final Builder builder){
        this.gameBoard = createGameBoard(builder);
        this.whitePieces = calculateActivePieces(this.gameBoard, Alliance.WHITE);
        this.blackPieces = calculateActivePieces(this.gameBoard, Alliance.BLACK);
        final Collection<Move> whiteStandardLegalMoves = calculateLegalMoves(this.whitePieces);
        final Collection<Move> blackStandardLegalMoves = calculateLegalMoves(this.blackPieces);
        this.whitePlayer = new WhitePlayer(this, whiteStandardLegalMoves, blackStandardLegalMoves);
        this.blackPlayer = new BlackPlayer(this, whiteStandardLegalMoves, blackStandardLegalMoves);
        this.currentPlayer = builder.nextMoveMaker.choosePlayer(this.whitePlayer,this.blackPlayer);

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

    public Collection<Piece> getWhitePieces(){
        return this.whitePieces;
    }

    public Collection<Piece> getBlackPieces(){
        return this.blackPieces;
    }

    public Player whitePlayer(){
        return this.whitePlayer;
    }

    public Player blackPlayer(){
        return this.blackPlayer;
    }


    public static Board createEmptyBoard() {
        final Builder builder = new Builder();
        builder.createEmptyBoard();
//        builder.setMoveMaker(Alliance.WHITE); // Default move maker
        return builder.build();
    }

    public void diffBetweenObjects(Board board){
        List<MutableCoordinate> mismatchedCords= this.gameBoard.entrySet().stream()
                .filter(entry -> !board.gameBoard.get(entry.getKey()).equals(entry.getValue()))
                .map(Map.Entry::getKey).toList();

        System.out.println(mismatchedCords);
    }

    @Override
    public String toString(){
        final StringBuilder builder = new StringBuilder();
        builder.append("\n");
        for (int i = 0; i< BoardUtils.NUM_TILES_PER_ROW; i++){
            for (int j = 0; j < BoardUtils.NUM_TILES_PER_ROW; j++){
                final String tileText = prettyPrint(this.gameBoard.get(new MutableCoordinate(i,j)));
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

    public Player currentPlayer(){
        return this.currentPlayer;
    }

    public List<Move> getAllLegalMoves() {
        List<Move> allLegalMoves = Stream.concat(whitePlayer.getLegalMoves().stream(), blackPlayer.getLegalMoves().stream()).toList();
        return allLegalMoves;

    }

    public static class Builder {

        Map<MutableCoordinate, Piece> boardConfig;
        Alliance nextMoveMaker;

        Pawn enPassantPawn;

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

        public Builder createEmptyBoard() {
            this.boardConfig.clear(); // Ensure no pieces on the board
            return this;
        }


        public Board build(){
            return new Board(this);
        }

        public void setEnPassantPawn(Pawn movedPawn) {
            this.enPassantPawn = movedPawn;
        }
    }

    public static void main(String[] args) {
//        Board board = Board.createEmptyBoard();
//        System.out.println(board);
    }

}