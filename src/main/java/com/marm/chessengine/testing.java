/******************************************
 *CSCI 205 - Software Engineering and Design
 *Spring 2024
 *Instructor: Prof. Lily Romano / Prof. Joshua Stough
 *
 *Name: Miguel Romero
 *Section: 01
 *Date: 1/1/25
 *Time: 9:46 PM
 *
 *Project: chessEngine
 *Package: com.marm.chessengine
 *Class: testing
 *Description:
 * **************************************** */
package com.marm.chessengine;

import com.marm.chessengine.board.MutableCoordinate;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class testing {
    public static void main(String[] args) {
//        Map<MutableCoordinate, Integer> testing = new HashMap<>();
//        MutableCoordinate entry = new MutableCoordinate(4,3);
//        testing.put(entry , 5);
//        System.out.println(testing.get(new MutableCoordinate(4,3)));
        int[] test = new int[2];
        test[0] = 1;
        test[1] = 1;
        System.out.println(Arrays.equals(test, new int[]{1,1}));

    }
}