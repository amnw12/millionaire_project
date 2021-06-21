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

import java.util.Random;

public class Question 
{
    private String question;
    private String answer;
    private String[] options;
    private String[][] choices;

    public Question(String question, String answer, String[] options) {
        this.question = question;
        this.answer = answer;
        this.options = options;
        this.choices = new String[4][2];
        this.setChoices();
    }
    
    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getOption(int k) {
        return options[k];
    }

    public void setOptions(String[] options) {
        this.options = options;
    }

    public String[] getChoice(int k) {
        return choices[k];
    }

    public void setChoices() {
        String[] multichoice = {this.getAnswer(), this.options[0], this.options[1], this.options[2]};
        
        Random rand = new Random();
		
        for (int i = 0; i < multichoice.length; i++) {
            int randomIndexToSwap = rand.nextInt(multichoice.length);
            String temp = multichoice[randomIndexToSwap];
            multichoice[randomIndexToSwap] = multichoice[i];
            multichoice[i] = temp;
	}
        
        this.choices[0][0] = "A";
        this.choices[0][1] = multichoice[0];
        this.choices[1][0] = "B";
        this.choices[1][1] = multichoice[1];
        this.choices[2][0] = "C";
        this.choices[2][1] = multichoice[2];
        this.choices[3][0] = "D";
        this.choices[3][1] = multichoice[3];
    }

    public boolean isCorrect(String letter)
    {
        boolean correct =  false;
        switch (letter)
        {
            case "A":
            case "a":
            {
                if (this.choices[0][1] == this.getAnswer())
                {
                    correct = true;
                }
                else
                {
                    correct = false;
                }
            }
            break;
            case "B":
            case "b":
            {
                if (this.choices[1][1] == this.getAnswer())
                {
                    correct = true;
                }
                else
                {
                    correct = false;
                }
            }
            break;
            case "C":
            case "c":
            {
                if (this.choices[2][1] == this.getAnswer())
                {
                    correct = true;
                }
                else
                {
                    correct = false;
                }
            }
            break;
            case "D":
            case "d":
            {
                if (this.choices[3][1] == this.getAnswer())
                {
                    correct = true;
                }
                else
                {
                    correct = false;
                }
            }
            break;
        }
        
        return correct;
    }
    
    @Override
    public String toString() {
        String toPrint = this.getQuestion();
        
        for (int k = 0; k < this.choices.length; k++)
        {
           toPrint += "\n\n\t" + this.choices[k][0] + "   " + this.choices[k][1];
        }
        
        return toPrint; 
    }
    
    
}
