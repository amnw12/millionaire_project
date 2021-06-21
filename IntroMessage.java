/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project_2;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JTextArea;

/**
 *
 * @author amnwaqar
 */
public class IntroMessage extends JTextArea{

    public IntroMessage() {
        append(intro());
        this.setAlignmentY(CENTER_ALIGNMENT);
        setForeground(new Color(102, 51, 0));
        setBackground(new Color(234, 215, 200));
        setEditable(false);
    }
    
    public String intro()
   {
       try
        {
            FileReader fr = new FileReader("./resources/intro.txt");
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
            System.out.println("Error reading from file intro.txt");
        } 
       return null;
   }
   
}
