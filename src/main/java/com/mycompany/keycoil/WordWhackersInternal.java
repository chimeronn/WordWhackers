/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package com.mycompany.keycoil;

import static com.mycompany.keycoil.Typo.typos;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 *
 * @author camer
 */
public class WordWhackersInternal extends javax.swing.JInternalFrame implements ActionListener, KeyListener, Runnable{

    /**
     * Creates new form WordWhackersInternal
     */
    private static int numWords; //The number of words in the typing test
    private boolean running = true;
    private boolean ended = false;
    private boolean restart = true;
    
    private ArrayList<String> words; //Array list used to store the words in the typing test
    private String whole = ""; //String used to put all of the words in the typing test into
    private int charsTyped = 0; //The number of characters that the user has typed
    private String typed = ""; //Everything the user has typed put into a string
    private boolean typo = false; //Returns true if the user has made a typo
    private int chars = 0; //The number of characters in the entirety of the typing test
    private boolean timerStart = false; //Returns true if the timer has started
    
    private int seconds = 0; //Elapsed time
    public static double wordspermin = 0; //WPM
    public static boolean done = false; //Returns true if the typing test is done
    boolean customTraining = false; //Returns true if this is a custom training course made from typo data
    
    public WordWhackersInternal(int numWords, boolean customTraining) { //Constructor used to initialize the typing test
        WeaknessDetection.weaknesses.clear();
        done = false;
        initComponents();
                
        this.numWords = numWords;
        
        if(running){
            this.repaint();
        }
        if(ended){
            this.repaint();
        }
        
        initComponents();
        
        this.setLayout(new BorderLayout());
        this.setSize(585, 325);
        this.setVisible(true);
        this.setFocusable(true);
        this.addKeyListener(this);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane().setBackground(Color.darkGray);
        
        this.customTraining = customTraining;
    }
    
    //Used to repaint the window everytime a letter is typed
    @Override
    public void paint(Graphics g){
        super.paint(g);
        draw(g);
    }
    
    //Helper method to repaint the window
    public void draw(Graphics g){
        if(restart){
            
            //Intiailizes the words array list and the dictionary class
            words = new ArrayList<>();
            Dictionary d = new Dictionary();
            
            //If the test is not a custom training course then generate random words
            if(!customTraining){
                for(int i = 0; i < numWords; i++){
                    words.add(d.getRandomWord());
                }
            }
            //If the test is a custom training course, then generate words based off of the user's frequent typos
            else{             
                //Generate 20 words
                //Valid letters are letters that can be in words generated in the custom training course
                for(int i = 0; i < 20; i++){
                    
                    ArrayList<String> letters = new ArrayList<String>();
                    
                        //If the user is weak with the top row, then the top row letters are valid
                        if(Typo.topWeak){
                            
                            letters.add("q");
                            letters.add("w");
                            letters.add("e");
                            letters.add("r");
                            letters.add("t");
                            letters.add("y");
                            letters.add("u");
                            letters.add("i");
                            letters.add("o");
                            letters.add("p");
                            
                        }
                        //If the user is weak with the middle row, the the middle row letters are valid
                        if(Typo.midRowWeak){
                            
                            letters.add("a");
                            letters.add("s");
                            letters.add("d");
                            letters.add("f");
                            letters.add("g");
                            letters.add("h");
                            letters.add("j");
                            letters.add("k");
                            letters.add("l");
                            
                        }
                        //If the user is weak with the bottom row then the bottom row letters are valid
                        if(Typo.botWeak){
                            
                            letters.add("z");
                            letters.add("x");
                            letters.add("c");
                            letters.add("v");
                            letters.add("b");
                            letters.add("n");
                            letters.add("m");
                            
                        }
                        //If the user is weak with the left region, then the left region letters are valid
                        if(Typo.leftWeak){
                            
                            letters.add("q");
                            letters.add("w");
                            letters.add("e");
                            letters.add("a");
                            letters.add("s");
                            letters.add("d");
                            letters.add("z");
                            letters.add("x");
                            letters.add("c");
                            
                        }
                        //If the user is weak with the middle region, then the middle region letters are valid
                        if(Typo.midWeak){
                            
                            letters.add("r");
                            letters.add("t");
                            letters.add("y");
                            letters.add("f");
                            letters.add("g");
                            letters.add("h");
                            letters.add("v");
                            letters.add("b");
                            
                        }
                        //If the user is weak with the right region, then the right region letters are valid
                        if(Typo.rightWeak){
                            
                            letters.add("p");
                            letters.add("o");
                            letters.add("i");
                            letters.add("l");
                            letters.add("k");
                            letters.add("j");
                            letters.add("m");
                            letters.add("n");
                            
                        }
                        //Determines which letters the user is weak in and makes it valid
                        char letter = 'a';
                        for(int j = 0; i < 26; i++){
                            
                            if((double) typos.get(letter + "") / Profile.numGames > 0.5){
                                letters.add(letter + "");
                            }
                            letter++;
                        }
                        
                    //Adds words to the words array list until there are 20
                    while(words.size() < 20){
                        
                        //Keeps generating random words until it has at least 2 valid
                        String word = d.getRandomWord();
                        char[] characters = word.toCharArray();
                        
                        int valid = 0;
                        for(char c : characters){
                            for(String theLetter : letters){
                                if((c + "").equals(theLetter)){
                                    valid++;
                                }
                            }
                        }
                        System.out.println(word);
                        if(valid >= 2){
                            words.add(word);
                        }
                    }
                }
            }
            restart = false;
                        
            for (int i = 0; i < numWords - 1; i++) {
                
                whole += words.get(i) + " ";
                chars += words.get(i).length();
                
            }
            
            whole += words.get(numWords - 1);
            chars += words.get(numWords - 1).length();
        }
                
        g.setFont(new Font("MV Boli", Font.BOLD, getWidth() / 30));
        g.setColor(Color.WHITE);
        
        if(running){
            //Display 37 characters on screen
            int end = 37 + typed.length();
            
            if(end > whole.length())
                end = whole.length();
            
            //Draw the words
            g.drawString(whole.substring(typed.length(), end), getWidth() / 8, getHeight() / 2);
            
            /*g.setColor(Color.GREEN);
            if (typed.length() > 0) {
                g.drawString(typed, getWidth() / 8, getHeight() / 2);
            }*/
            
            if(typo){
                
                //If there is a typo, set the character to red and add it to the Typo HashMap
                if(!(whole.charAt(typed.length()) + "").equals(" "))
                    Typo.add(whole.charAt(typed.length()) + "");
                
                //System.out.println(Typo.typos);
                g.setColor(Color.RED);
                g.drawString(whole.substring(typed.length(), typed.length() + 1), getWidth() / 8, getHeight() / 2);
                typo = false;
            }
            
            if(typed.length() == whole.length()){
                //End the game and calculate the WPM if the length of what the user has typed is equal to the length of the typing test
                Profile.numGames++;
                timerStart = false;
                running = false;
                double words = chars / 4.7;
                double mins = seconds / 60;
                double wpm = words / seconds * 60;
                wordspermin = wpm;
                Profile.avgWPM += wpm;
                
                if(wpm > Profile.highestWPM){
                    Profile.highestWPM = wpm;
                }
                
                //Update profile stats
                
                done = true;
                
                winLabel.setText((int) wpm + " WPM");
                desktopPane.setLayout(new BorderLayout());
                desktopPane.setSize(585, 325);
                desktopPane.setVisible(true);
                desktopPane.setFocusable(true);
                desktopPane.setDefaultCloseOperation(EXIT_ON_CLOSE);
                desktopPane.revalidate();
                
                try {
                    //Write the new data to the profile file after a game has been played
                    FileWriter fw = new FileWriter(new File("src\\" + Profile.username + ".txt"));
                    fw.write(Profile.username + "\n");
                    fw.write(Profile.pfp.getAbsolutePath() + "\n");
                    fw.write(Profile.avgWPM + "\n");
                    fw.write(Profile.highestWPM + "\n");
                    fw.write(Profile.numGames + "\n");
                    
                    fw.write(Typo.typos.get("a") + "\n");
                    fw.write(Typo.typos.get("b")+ "\n");
                    fw.write(Typo.typos.get("c")+ "\n");
                    fw.write(Typo.typos.get("d")+ "\n");
                    fw.write(Typo.typos.get("e")+ "\n");
                    fw.write(Typo.typos.get("f")+ "\n");
                    fw.write(Typo.typos.get("g")+ "\n");
                    fw.write(Typo.typos.get("h")+ "\n");
                    fw.write(Typo.typos.get("i")+ "\n");
                    fw.write(Typo.typos.get("j")+ "\n");
                    fw.write(Typo.typos.get("k")+ "\n");
                    fw.write(Typo.typos.get("l")+ "\n");
                    fw.write(Typo.typos.get("m")+ "\n");
                    fw.write(Typo.typos.get("n")+ "\n");
                    fw.write(Typo.typos.get("o")+ "\n");
                    fw.write(Typo.typos.get("p")+ "\n");
                    fw.write(Typo.typos.get("q")+ "\n");
                    fw.write(Typo.typos.get("r")+ "\n");
                    fw.write(Typo.typos.get("s")+ "\n");
                    fw.write(Typo.typos.get("t")+ "\n");
                    fw.write(Typo.typos.get("u")+ "\n");
                    fw.write(Typo.typos.get("v")+ "\n");
                    fw.write(Typo.typos.get("w")+ "\n");
                    fw.write(Typo.typos.get("x")+ "\n");
                    fw.write(Typo.typos.get("y")+ "\n");
                    fw.write(Typo.typos.get("z")+ "\n");

                    fw.close();
                    
                    //Save the data to the profile file
                } catch (IOException ex) {
                    Logger.getLogger(WordWhackersInternal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    int count = 0;
    
    @Override
    public void keyTyped(KeyEvent e) {
        count++;
        
        if(count == 1){
            //Starts the timer when 1 character has been typed
            new Thread(this, "timer").start();
            
            //System.out.println(count);
            timerStart = true;
        }
        
        revalidate();
        repaint();
        
        if(whole.length() > 0){
            
            char[] wordChars = whole.toCharArray();
            
            if(charsTyped < wordChars.length){
                running = true;
                
                if(e.getKeyChar() == wordChars[charsTyped]){
                    //If the letter the user types is the same as what they were supposed to type
                    //then add the letter to typed and add to the number of characters the user has typed
                    typed = typed + wordChars[charsTyped];
                    charsTyped++;
                }
                else{
                    typo = true;
                    //If it does not match then the user has made a typo
                }
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() >= 65 && e.getKeyCode() <= 90 && running == false){
        }
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    
    //The thread that runs the timer
    @Override
    public void run(){
        while(timerStart){
            try{
                System.out.println(seconds);
                Thread.sleep(1000);
                seconds++;
            }
            catch(InterruptedException e){
                System.out.println(e);
            }
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

        desktopPane = new javax.swing.JInternalFrame();
        typeLabel = new javax.swing.JLabel();
        winLabel = new javax.swing.JLabel();
        endLabel = new javax.swing.JLabel();

        desktopPane.setVisible(true);

        winLabel.setText("jLabel2");

        endLabel.setIcon(new javax.swing.ImageIcon("C:\\Users\\camer\\Downloads\\keycoil\\Keycoil\\src\\main\\java\\Images\\mcqueen.gif"));

        javax.swing.GroupLayout desktopPaneLayout = new javax.swing.GroupLayout(desktopPane.getContentPane());
        desktopPane.getContentPane().setLayout(desktopPaneLayout);
        desktopPaneLayout.setHorizontalGroup(
            desktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(desktopPaneLayout.createSequentialGroup()
                .addGroup(desktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(desktopPaneLayout.createSequentialGroup()
                        .addGap(143, 143, 143)
                        .addComponent(typeLabel))
                    .addGroup(desktopPaneLayout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(endLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 472, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(desktopPaneLayout.createSequentialGroup()
                        .addGap(263, 263, 263)
                        .addComponent(winLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(345, Short.MAX_VALUE))
        );
        desktopPaneLayout.setVerticalGroup(
            desktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, desktopPaneLayout.createSequentialGroup()
                .addComponent(winLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(endLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addComponent(typeLabel)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 134, Short.MAX_VALUE)
                .addComponent(desktopPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JInternalFrame desktopPane;
    private javax.swing.JLabel endLabel;
    private javax.swing.JLabel typeLabel;
    private javax.swing.JLabel winLabel;
    // End of variables declaration//GEN-END:variables
}
