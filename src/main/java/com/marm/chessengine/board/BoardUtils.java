/******************************************
 *Name: Miguel Romero
 *Date: 12/30/24
 *Time: 10:32PM
 *
 *Project: chessEngine
 *Package: com.marm.chessengine.board
 *Class: BoardUtils
 *Description:
 * **************************************** */
package com.marm.chessengine.board;

import javafx.util.Pair;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class BoardUtils {

    public static final int NUM_TILES_PER_ROW = 8;

    public static final int NUM_TILES = 64;

    public static final boolean[][] SECOND_ROW  = initRow(1);
    public static final boolean[][] SEVENTH_ROW = initRow(6 );

    private BoardUtils(){
        throw new RuntimeException("You cannot instantiate me");
    }

    private static  boolean[][] initRow(int desiredRow){
        boolean[][] desiredRowBoolean = new boolean[NUM_TILES_PER_ROW][NUM_TILES_PER_ROW];
        for (int i = 0; i < NUM_TILES_PER_ROW; i++){
            for (int j = 0; j < NUM_TILES_PER_ROW; j++){
                desiredRowBoolean[i][j] = i == desiredRow;

            }
        }
        return desiredRowBoolean;
    }

    public static boolean isValidTileCoordinate(MutableCoordinate candidateDestinationCoordinate) {

        int xCord = candidateDestinationCoordinate.getX();
        int yCord = candidateDestinationCoordinate.getY();
        return (xCord >= 0 && xCord < NUM_TILES_PER_ROW) && (yCord >= 0 && yCord < NUM_TILES_PER_ROW);

    }

    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(SECOND_ROW));
    }


}