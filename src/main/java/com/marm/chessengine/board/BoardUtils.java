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

public class BoardUtils {

    private BoardUtils(){
        throw new RuntimeException("You cannot instantiate me");
    }
    public static boolean isValidTileCoordinate(Pair<Integer, Integer> candidateDestinationCoordinate) {
        Integer xCord = candidateDestinationCoordinate.getKey();
        Integer yCord = candidateDestinationCoordinate.getValue();
        if ((xCord >= 0 && xCord < 8) && (yCord >= 0 && yCord < 8)) {
            return true;
        }
        return false;

    }
}