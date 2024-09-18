/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.keycoil;

import java.io.File;

/**
 *
 * @author hong_890424
 */
//This class stores all of the user's profile data
public class Profile {
    //All of the variables are static and public so that they can be accessed without having to call the constructor
    public static String username;
    public static int tag;
    public static File pfp;
    public static String banner;
    public static int numGames = 0;
    public static double avgWPM = 0;
    public static double highestWPM = 0;

    public Profile(String username, int tag, File pfp, String banner) { //Constructor that initializes all of the variables
        this.username = username;
        this.tag = tag;
        this.pfp = pfp;
    }

}
