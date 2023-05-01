package com.example.snakeladdergame;

public class Dice {
    public  int getRollDiceValue(){
        return (int) (Math.random() * 6 + 1);
    }
}
