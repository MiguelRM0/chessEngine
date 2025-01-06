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

import java.util.Objects;

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
   @Override
   public boolean equals(Object o){
       if (this == o) return true;
       if (o == null || getClass() != o.getClass()) return false;
       MutableCoordinate that = (MutableCoordinate) o;
       return this.x == that.x && this.y == that.y;
   }

   @Override
   public int hashCode(){
       return Objects.hash(x,y);
   }

   @Override
   public String toString(){
       return String.format("(%d,%d)", this.x,this.y);
   }


    public static void main(String[] args) {
        MutableCoordinate p1 = new MutableCoordinate(5,3);
//        MutableCoordinate p2 = new MutableCoordinate(5,3);
        System.out.println(p1.equals(new MutableCoordinate(5,3)));
        System.out.println(p1);


    }
}