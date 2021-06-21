/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project_2;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JTextArea;

/**
 *
 * @author amnwaqar
 */
public class HighscoreMessage extends JTextArea {

    HighscoreMessage(String allScores) {
        append("       H I G H  S C O R E S :\n");
        append("----------------------");
        append(allScores);
        this.setAlignmentY(CENTER_ALIGNMENT);
        setBackground(new Color(234, 215, 200));
        setEditable(false);
    }
    
    public void updateHighscore(User user)
    {
        try 
        {
            BufferedWriter myWriter = new BufferedWriter(new FileWriter("./resources/highscores.txt", true));
            myWriter.append(user + "\n");
            myWriter.close();
        } 
        catch (IOException e) 
        {
            System.out.println("An error occurred.");
        }
    }
}
