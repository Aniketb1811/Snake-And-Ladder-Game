package com.example.snakeladdergame;

import javafx.util.Pair;

import java.util.ArrayList;

public class Board {

    ArrayList<Pair<Integer, Integer>> positionCoordinates;

    static ArrayList<Integer> snakeLadderPosition;

    public Board(){
        positionCoordinates = new ArrayList<>();
        populateSnakeLadder();
        populatePositionCoordinates();
    }

    public void populatePositionCoordinates(){
        positionCoordinates.add(new Pair<>(0, 0)); //dummy input

        for (int i = 0; i < SnakeLadder.height; i++) {
            for (int j = 0; j < SnakeLadder.width; j++) {
                //x co-ordinates
                int xCord = 0;
                if(i%2==0){
                    xCord = j*SnakeLadder.tileSize + SnakeLadder.tileSize/2;
                }
                else{
                    xCord = SnakeLadder.tileSize * SnakeLadder.height - (j*SnakeLadder.tileSize)-SnakeLadder.tileSize/2;
                }
                //y co-ordinates
                int yCord = SnakeLadder.tileSize * SnakeLadder.height - (i*SnakeLadder.tileSize)-SnakeLadder.tileSize/2;
                positionCoordinates.add(new Pair<>(xCord,yCord));

            }

        }

    }

//    public static void print(String[] args){
//        for (int i = 0; i < 101; i++) {
//            System.out.println(snakeLadderPosition.get(i));
//
//        }
//    }

    private void populateSnakeLadder(){
        snakeLadderPosition = new ArrayList<>();
        for (int i = 0; i < 101; i++) {
            snakeLadderPosition.add(i);
        }

        snakeLadderPosition.set(2,23);
        snakeLadderPosition.set(6,45);
        snakeLadderPosition.set(20,59);
        snakeLadderPosition.set(43,17);
        snakeLadderPosition.set(50,5);
        snakeLadderPosition.set(52,72);
        snakeLadderPosition.set(56,8);
        snakeLadderPosition.set(57,96);
        snakeLadderPosition.set(71,92);
        snakeLadderPosition.set(73,15);
        snakeLadderPosition.set(84,58);
        snakeLadderPosition.set(87,49);
        snakeLadderPosition.set(98,40);

        for (int i = 0; i < 101; i++) {
            System.out.println(snakeLadderPosition.get(i));

        }
    }

    public int getNewPosition(int currentPosition){
        if(currentPosition>0 && currentPosition<=100){
            return snakeLadderPosition.get(currentPosition);
        }
        return -1;
    }

    int getXCoordinate(int position){
        if(position >= 1 && position <= 100)
            return positionCoordinates.get(position).getKey();

        return -1;
    }

    int getYCoordinate(int position){
        if(position >= 1 && position <= 100)
            return positionCoordinates.get(position).getValue();

        return -1;
    }


//    public static void main(String[] args) {
//        Board board = new Board();
//        for (int i = 0; i < board.positionCoordinates.size(); i++) {
//            System.out.println(i + "$ x :" + board.positionCoordinates.get(i).getKey() +
//            " y :" + board.positionCoordinates.get(i).getValue()
//            );
//
//        }
//
//        for (int i = 0; i < 101; i++) {
//            System.out.println(snakeLadderPosition.get(i));
//
//        }
//    }

}
