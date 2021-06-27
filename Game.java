package com.company;

import java.util.ArrayList;

public class Game {

    public static Word currentWord;
    private static int isFirstGame = 1;


    public static void newGame(){
        PaintComponent.clearPanel = 0;
        if(isFirstGame == 1){
            new GUI(isFirstGame);
            isFirstGame = 0;
        }
    }

}