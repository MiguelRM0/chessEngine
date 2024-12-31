/******************************************
 *CSCI 205 - Software Engineering and Design
 *Spring 2024
 *Instructor: Prof. Lily Romano / Prof. Joshua Stough
 *
 *Name: Miguel Romero
 *Section: 01
 *Date: 12/30/24
 *Time: 10:32 PM
 *
 *Project: chessEngine
 *Package: com.marm.chessengine.board
 *Class: BoardUtils
 *Description:
 * **************************************** */
package com.marm.chessengine.board;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BoardUtils {

    public static final int NUM_TILES_PER_ROW = 8;

    private BoardUtils(){
        throw new RuntimeException("You cannot instantiate me");
    }

    public static boolean isValidTileCoordinate(MutableCoordinate candidateDestinationCoordinate) {

        /* Maybe will make my own Class to store a mutable Coordinate system consisting of (x,y) pairs */
        int xCord = candidateDestinationCoordinate.getX();
        int yCord = candidateDestinationCoordinate.getY();
        if ((xCord >= 0 && xCord < NUM_TILES_PER_ROW) && (yCord >= 0 && yCord < NUM_TILES_PER_ROW)) {
            return true;
        }
        return false;

    }

    public static void main(String[] args) {
        MutableCoordinate test = new MutableCoordinate(8,5);
        System.out.println(isValidTileCoordinate(test));
    }
}