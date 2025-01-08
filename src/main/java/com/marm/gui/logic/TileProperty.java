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

import com.marm.chessengine.pieces.Piece;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class TileProperty {
    private final ObjectProperty<Piece> pieceProperty;

    TileProperty() {
        this.pieceProperty = new SimpleObjectProperty<Piece>(null);
    }

    public ObjectProperty<Piece> getPieceProperty(){
        return this.pieceProperty;
    }

    public void setPiece(Piece pieceProperty){
        this.pieceProperty.set(pieceProperty);
    }

    public Piece getPiece(){
        return this.pieceProperty.get();
    }

}