/******************************************
 *CSCI 205 - Software Engineering and Design
 *Spring 2024
 *Instructor: Prof. Lily Romano / Prof. Joshua Stough
 *
 *Name: Miguel Romero
 *Section: 01
 *Date: 1/8/25
 *Time: 5:24 PM
 *
 *Project: chessEngine
 *Package: com.marm.gui.logic
 *Class: TileProperty
 *Description:
 * **************************************** */
package com.marm.gui.logic;

import com.marm.chessengine.board.Board;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class BoardProperty {
    private final ObjectProperty<Board> boardObjectProperty;

    public BoardProperty() {
        this.boardObjectProperty = new SimpleObjectProperty<>(null);
    }

    public ObjectProperty<Board> getBoardProperty(){
        return this.boardObjectProperty;
    }

    public void setBoard(Board board){
        this.boardObjectProperty.set(board);
    }

    public Board getBoard(){
        return this.boardObjectProperty.get();
    }

}