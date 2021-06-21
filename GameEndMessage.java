/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project_2;

/**
 *
 * @author amnwaqar
 */

import java.awt.Color;
import java.io.*;
import java.io.IOException; 
import javax.swing.JTextArea;

public class GameEndMessage extends JTextArea 
{

    public GameEndMessage() 
    {
        this.setAlignmentY(CENTER_ALIGNMENT);
        setBackground(new Color(234, 215, 200));
        setEditable(false);
    }
    
    public String gameOver()
    {
        try
        {
            FileReader fr = new FileReader("./resources/gameOver.txt");
            BufferedReader inputStream = new BufferedReader(fr);
            String line;
            String text = "\n\n";
            
            while((line =inputStream.readLine())!=null)
            {
                text += line + "\n";
            }
            
            text += "\n";
            return text;
        }
        catch (FileNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
        catch (IOException e){
            System.out.println("Error reading from file gameOver.txt");
        }  
        return null;
    }
    
    
   public String winningOutput()
    {
        try
        {
            FileReader fr = new FileReader("./resources/winning-output.txt");
            BufferedReader inputStream = new BufferedReader(fr);
            String line;
            String text = "\n\n";
            
            while((line =inputStream.readLine())!=null)
            {
                text += line + "\n";
            }
            
            text += "\n";
            return text;
        }
        catch (FileNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
        catch (IOException e){
            System.out.println("Error reading from file winning-output.txt");
        }  
        return null;
    }
   
   public String losingOutput()
   {
       try
        {
            FileReader fr = new FileReader("./resources/losing-output.txt");
            BufferedReader inputStream = new BufferedReader(fr);
            String line;
            String text = "\n\n";
            
            while((line =inputStream.readLine())!=null)
            {
                text += line + "\n";
            }
            
            text += "\n";
            return text;
        }
        catch (FileNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
        catch (IOException e){
            System.out.println("Error reading from file winning-output.txt");
        }  
        return null;
   }
}