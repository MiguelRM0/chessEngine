/******************************************
 *CSCI 205 - Software Engineering and Design
 *Spring 2024
 *Instructor: Prof. Lily Romano / Prof. Joshua Stough
 *
 *Name: Miguel Romero
 *Section: 01
 *Date: 12/30/24
 *Time: 11:50 PM
 *
 *Project: chessEngine
 *Package: com.marm.chessengine.board
 *Class: MutableCoordinate
 *Description:
 * **************************************** */
package com.marm.chessengine.board;

public class MutableCoordinate {
    private int x;
    private int y;

   public MutableCoordinate(int x, int y){
       this.x = x;
       this.y = y;
   }

   public int getX(){
       return this.x;
   }

   public int getY(){
       return this.y;
   }

   public void setX(int newX){
       this.x = newX;
   }

   public void setY(int  newY){
       this.y = newY;
   }

}