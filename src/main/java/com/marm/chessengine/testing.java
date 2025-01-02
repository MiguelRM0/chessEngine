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
import java.util.HashMap;
import java.util.Map;

public class testing {
    public static void main(String[] args) {
        Map<AbstractMap.SimpleEntry<Integer,Integer>, Integer> testing = new HashMap<>();
//        testing.put(firstEntry, 5);

        AbstractMap.SimpleEntry<Integer,Integer> secondEntry = new AbstractMap.SimpleEntry<>(5,4);
        testing.put(secondEntry, 5);
        System.out.println(testing.get(new AbstractMap.SimpleEntry<>(5,4)));

    }
}