/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project_2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author amnwaqar
 */
public class WelcomeBanner 
{

    public WelcomeBanner() {
        
    }
    
    public String Welcome()
    {
        try
        {
            FileReader fr = new FileReader("./resources/welcome_banner.txt");
            BufferedReader inputStream = new BufferedReader(fr);
            String line;
            String welcome = "\n";
            
            while((line =inputStream.readLine())!=null)
            {
               welcome += line + "\n";
            }
            
            return welcome;
        }
        catch (FileNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
        catch (IOException e){
            System.out.println("Error reading from file welcome_banner.txt");
        }  
        return null;
    }
}
