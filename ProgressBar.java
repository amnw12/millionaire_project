/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project_2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author amnwaqar
 */
public class ProgressBar 
{

    public ProgressBar() {
        setUpProgressBar();
    }
    
    public void setUpProgressBar()
   {
       try 
        {
            PrintWriter myWriter = new PrintWriter(new FileOutputStream("./resources/progress-ladder.txt"));
            myWriter.println("	     Y O U R  P R O G R E S S \n");
            myWriter.println("	-----------------------------------\n");
            myWriter.println("	  |  5 0 : 5 0 |      | S K I P |\n");
            myWriter.println("	-----------------------------------\n");
            myWriter.println("	  1 0         $ 1 , 0 0 0 , 0 0 0\n");
            myWriter.println("	-----------------------------------\n");
            myWriter.println("	   9          $ 9 0 0 , 0 0 0\n");
            myWriter.println("	   8          $ 8 0 0 , 0 0 0\n");
            myWriter.println("	   7          $ 7 0 0 , 0 0 0\n");
            myWriter.println("	   6          $ 6 0 0 , 0 0 0\n");
            myWriter.println("	-----------------------------------\n");
            myWriter.println("	   5          $ 5 0 0 , 0 0 0\n");
            myWriter.println("	   4          $ 4 0 0 , 0 0 0\n");
            myWriter.println("	   3          $ 3 0 0 , 0 0 0\n");
            myWriter.println("	   2          $ 2 0 0 , 0 0 0\n");
            myWriter.println("	-----------------------------------\n");
            myWriter.println("	   1          $ 1 0 0 , 0 0 0\n");
            myWriter.println("	-----------------------------------\n");
            myWriter.close();
        } 
        catch (IOException e) 
        {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
   }
    
    public String ProgressBar(int k)
    {
        try
        {
            FileReader fr = new FileReader("./resources/progress-ladder.txt");
            BufferedReader inputStream = new BufferedReader(fr);
            String line;
            String progressBar = "";
            
            while((line =inputStream.readLine())!=null)
            {
                if (k == 10)
                {
                    if (line.startsWith("	  1 0"))
                    {
                        line += " 	      		 * * Y O U  A R E  H E R E * *"; 
                    }   
                }
                else
                {
                   if (line.startsWith("	   " + k))
                    {
                        line += " 	      		 * * Y O U  A R E  H E R E * *"; 
                    } 
                }
                   
                progressBar += line + "\n";
            }
            
            return progressBar;
        }
        catch (FileNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
        catch (IOException e){
            System.out.println("Error reading from file progress-ladder.txt");
        }
        return null;
    }
    
    public void updateProgressBar50_50()
   {
       try {
        // input the (modified) file content to the StringBuffer "input"
        BufferedReader file = new BufferedReader(new FileReader("./resources/progress-ladder.txt"));
        StringBuffer inputBuffer = new StringBuffer();
        String line;

        while ((line = file.readLine()) != null) {
            
            if (line.startsWith("	  |  5 0 : 5 0 |"))
            {
                String update = "";
                for (int k = 0; k < line.length(); k++)
                {
                    
                    if (k > 5 && k < 15)
                    {
                        update += " ";
                    }
                    else
                    {
                        update += line.charAt(k);
                    }
                }
                
                line = update;
            }
            
            inputBuffer.append(line);
            inputBuffer.append('\n');
        }
        file.close();

        // write the new string with the replaced line OVER the same file
        FileOutputStream fileOut = new FileOutputStream("./resources/progress-ladder.txt");
        fileOut.write(inputBuffer.toString().getBytes());
        fileOut.close();

    } catch (Exception e) {
        System.out.println("Problem reading file.");
    }
   }
   
   public void updateProgressBarSKIP()
   {
       try {
        // input the (modified) file content to the StringBuffer "input"
        BufferedReader file = new BufferedReader(new FileReader("./resources/progress-ladder.txt"));
        StringBuffer inputBuffer = new StringBuffer();
        String line;

        while ((line = file.readLine()) != null) {
            
            if (line.endsWith("| S K I P |"))
            {
                String update = "";
                for (int k = 0; k < line.length(); k++)
                {
                    
                    if (k > 24 && k < 32)
                    {
                        update += " ";
                    }
                    else
                    {
                        update += line.charAt(k);
                    }
                }
                
                line = update;
            }
            
            inputBuffer.append(line);
            inputBuffer.append('\n');
        }
        file.close();

        // write the new string with the replaced line OVER the same file
        FileOutputStream fileOut = new FileOutputStream("./resources/progress-ladder.txt");
        fileOut.write(inputBuffer.toString().getBytes());
        fileOut.close();

        } catch (Exception e) {
            System.out.println("Problem reading file.");
        }
   }
}
