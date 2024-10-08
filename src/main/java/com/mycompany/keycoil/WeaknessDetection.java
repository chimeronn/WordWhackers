/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package com.mycompany.keycoil;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 *
 * @author camer
 */
//This is the screen displayed that tells the user their weaknesses
public class WeaknessDetection extends javax.swing.JInternalFrame implements KeyListener{
    
    public static ArrayList<String> weaknesses = new ArrayList<>(); //This stores the physical string that is displayed to inform the user of their weaknesses
    public static boolean proceed;

    /**
     * Creates new form WeaknessDetection
     */
    public WeaknessDetection() {
        
        proceed = false;
        weaknesses.clear(); //Clears the array list so the array list is not doubled in size everytime this window is loaded
        Typo.letterTypos.clear();
        initComponents();
        
        this.setLayout(new BorderLayout());
        this.setSize(585, 325);
        this.setVisible(true);
        this.setFocusable(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        //this.getContentPane().setBackground(Color.darkGray);
        this.addKeyListener(this);
        this.revalidate();
        this.getContentPane().add(weaknessScreen);
        
        
        Typo.detect(); //Used to tell the program which regions and letters the user is weak in
        
        //this.getContentPane().add(jScrollBar1);
                
        if(Typo.rightWeak){
            weaknesses.add("Right Side Weakness Detected");
        }
        if(Typo.midWeak){
            weaknesses.add("Middle Weakness Detected");
        }
        if(Typo.leftWeak){
            weaknesses.add("Left Side Weakness Detected");
        }
        if(Typo.topWeak){
            weaknesses.add("Top Row Weakness Detected");
        }
        if(Typo.midRowWeak){
            weaknesses.add("Middle Row Weakness Detected");
        }
        if(Typo.botWeak){
            weaknesses.add("Bottom Row Weakness Detected");
        }
        if(Profile.avgWPM < 50){
            weaknesses.add("Looking at Keyboard detected");
        }
        
        for(String letter : Typo.letterTypos){
            weaknesses.add (letter + " Weakness Detected");
        }
        
        if(weaknesses.isEmpty()){
            weaknesses.add("No Weaknesses Detected");
        }
        
        //Adds a statement to the array list that stores the physical statements that are displayed in the window
    }
    
    @Override
    public void paint(Graphics g){ //Used to paint the frame so that the user can actually see the weaknesses
        super.paint(g);
        draw(g);
    }
    
    public void draw(Graphics g){ //Helper method for painting the window
        g.setFont(new Font("MV Boli", Font.PLAIN, 20));
        
        int x = 0;
        for(String weakness : weaknesses){
            
            g.drawString(weakness, 40, 20 + x);
            x += 30;
            
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        weaknessScreen = new javax.swing.JDesktopPane();

        javax.swing.GroupLayout weaknessScreenLayout = new javax.swing.GroupLayout(weaknessScreen);
        weaknessScreen.setLayout(weaknessScreenLayout);
        weaknessScreenLayout.setHorizontalGroup(
            weaknessScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 394, Short.MAX_VALUE)
        );
        weaknessScreenLayout.setVerticalGroup(
            weaknessScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 274, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(weaknessScreen)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(weaknessScreen, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public void keyTyped(KeyEvent e) { //Used to detect when the user wants to move on from the weaknesses screen
        if(e.getKeyChar() == KeyEvent.VK_ENTER){
            
            weaknessScreen.removeAll(); //Used to clear the Desktop Pane so that it does not throw an error
            WordWhackersInternal w = new WordWhackersInternal(20, true); //Calls the main typing test window
            
            weaknessScreen.add(w); //Add the typing test window to the Desktop Pane
            
        }
    }

    @Override
    public void keyPressed(KeyEvent e) { 
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane weaknessScreen;
    // End of variables declaration//GEN-END:variables
}
