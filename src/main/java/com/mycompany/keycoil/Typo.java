/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.keycoil;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author camer
 */
//This class is used to detect the typos the user makes and make generalizations based off of the data
public class Typo {
    static HashMap<String, Integer> typos; //This HashMap stores the number of typos per letter that the user makes
    static boolean leftWeak = false; //This boolean is true when the user mistypes a lot of keys on the left side of the keyboard
    static boolean rightWeak = false; //This boolean is true when the user mistypes a lot of keys on the right side of the keyboard
    static boolean midWeak = false; //This boolean is true when the user mistypes a lot of keys in the middle of the keyboard
    static boolean topWeak = false; //This boolean is true when the user mistypes a lot of keys in the top row of the keyboard
    static boolean midRowWeak = false; //This boolean is true when the user mistypes a lot of keys in the middle row of the keyboard
    static boolean botWeak = false; //This boolean is true when the user mistypes a lot of keys in the bottom row of the keyboard
    static ArrayList<String> letterTypos = new ArrayList<>(); //This array list stores which letters the user mistypes a lot
    
    public Typo(){ //This constructor resets the HashMap for a new user
        typos = new HashMap<>();
        typos.put("a", 0);
        typos.put("b", 0);
        typos.put("c", 0);
        typos.put("d", 0);
        typos.put("e", 0);
        typos.put("f", 0);
        typos.put("g", 0);
        typos.put("h", 0);
        typos.put("i", 0);
        typos.put("j", 0);
        typos.put("k", 0);
        typos.put("l", 0);
        typos.put("m", 0);
        typos.put("n", 0);
        typos.put("o", 0);
        typos.put("p", 0);
        typos.put("q", 0);
        typos.put("r", 0);
        typos.put("s", 0);
        typos.put("t", 0);
        typos.put("u", 0);
        typos.put("v", 0);
        typos.put("w", 0);
        typos.put("x", 0);
        typos.put("y", 0);
        typos.put("z", 0);
    }
    
    public static void add(String character){ //This method adds one to a certain letter in the HashMap if the user mistypes a letter
        typos.put(character, typos.get(character) + 1);
    }
    
    public static void detect(){ //This method makes generalizations based off of the typo patterns
        char letter = 'a';
        double sum = 0;
        for(int i = 0; i < 26; i++){ 
            //This loop loops through all of the letter
            if((double) typos.get(letter + "") / Profile.numGames > 0.5){ //This if statement detects whether the letter is mistyped a lot relative to the number of games played
                letterTypos.add(letter + ""); //Added to the array list if it is
            }
            letter++;
        }
        
        double rightSide = typos.get("p") + typos.get("o") + typos.get("i") + 
                typos.get("l") + typos.get("k") + typos.get("j") +
                typos.get("m") + typos.get("n");    
                //This variable stores the number of typos made on the right side of the keyboard
        
        double leftSide = typos.get("q") + typos.get("w") + typos.get("e") +
                typos.get("a") + typos.get("s") + typos.get("d") + 
                typos.get("z") + typos.get("x") + typos.get("c");
                //This variable stores the number of typos made on the left side of the keyboard
        
        double middle = typos.get("r") + typos.get("t") + typos.get("y") +
                typos.get("f") + typos.get("g") + typos.get("h") +
                typos.get("v") + typos.get("b");
                //This variable stores the number of typos made in the middle of the keyboard
        
        double top = typos.get("q") + typos.get("w") + typos.get("e") + typos.get("r") +
                typos.get("t") + typos.get("y") + typos.get("u") + typos.get("u") +
                typos.get("i") + typos.get("o") + typos.get("p");
                //This variable stores the number of typos made in the top row of the keyboard
        
        double middleRow = typos.get("a") + typos.get("s") + typos.get("d") + typos.get("f") +
                typos.get("g") + typos.get("h") + typos.get("j") + typos.get("k")
                + typos.get("l");
                //This variable stores the number of typos made in the middle row of the keyboard
        
        double bottom = typos.get("z") + typos.get("x") + typos.get("c") + typos.get("v") + 
                typos.get("b") + typos.get("n") + typos.get("m");
                //This variable stores the number of typos made in the bottom row of the keyboard
        
        rightSide /= 8;
        leftSide /= 9;
        middle /= 8;
        top /= 9;
        middleRow /= 9;
        bottom /= 7;
        //All of the values are averaged
        
        if((double) rightSide / Profile.numGames >= 0.4)
            rightWeak = true;
        if((double) leftSide / Profile.numGames >= 0.4)
            leftWeak = true;
        if((double) middle / Profile.numGames >= 0.4)
            midWeak = true;
        if((double) top / Profile.numGames >= 0.4)
            topWeak = true;
        if((double) middleRow / Profile.numGames >= 0.4)
            midRowWeak = true;
        if((double) bottom / Profile.numGames >= 0.4)
            botWeak = true;
        
        //These statements detect if the number of typos in each region of the keyboard is a lot relative to the numer of games played
    }
}
