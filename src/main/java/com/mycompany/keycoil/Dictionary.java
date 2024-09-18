/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.keycoil;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author camer
 */
public class Dictionary {
    private ArrayList<String> dictionary; //Variable used to store the words for the typing test
        
    public Dictionary(){
        dictionary = new ArrayList<String>();
        try {
            Scanner file;
            file = new Scanner(new File("dictionary.txt")); //Take in words from a dictionary file
            while(file.hasNextLine()){
                dictionary.add(file.nextLine()); //Add in all of the words from the dictionary file to this array list
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Dictionary.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String getRandomWord(){ //Returns random words from the dictionary array list
        int rand = (int) (Math.random() * 10000);
        return dictionary.get(rand);
    }
}
