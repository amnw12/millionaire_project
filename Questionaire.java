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

import java.util.ArrayList;
import java.util.Random;

public class Questionaire 
{
    private final ArrayList<Question> questionaire;
    private int[] questionOrder;
    

    public Questionaire() 
    {
        questionaire = new ArrayList<>();
        questionaire.add(new Question("What does the word loquacious mean?", "Chatty", new String[] {"Happy", "Angry", "Stressed"}));
        questionaire.add(new Question("Which of these landmarks was completed first:", "Big Ben tower", new String[] {"Eiffel tower", "Statue of liberty", "Sky tower"}));
        questionaire.add(new Question("What animal is Walt Disney’s Dumbo?", "Elephant", new String[] {"Rabbit", "Cow", "Dog"}));
        questionaire.add(new Question("Finish the expression “A bird in the hand is worth two in the...”?", "Tree", new String[] {"Window", "Forest", "Sky"}));
        questionaire.add(new Question("How often is Bi-Annually?", "Every 2 years", new String[] {"Every 2 months", "Every 2 days", "Every 2 weeks"}));
        questionaire.add(new Question("Which of these countries is not in Africa?", "Argentina", new String[] {"Namibia", "Mali", "Sudan"}));
        questionaire.add(new Question("Where is Machu Picchu located?", "Peru", new String[] {"Mexico", "Columbia", "Brazil"}));
        questionaire.add(new Question("What is the capital of China?", "Beijing", new String[] {"Shanghai", "Hong Kong", "Wuhan"}));
        questionaire.add(new Question("How many faces are on Mount Rushmore, USA?", "4", new String[] {"6", "3", "5"}));
        questionaire.add(new Question("Victoria Falls located in Zimbabwe is the largest waterfall in the world. How many litres of water tumble over the edges per minute?", "500 million litres", new String[] {"200 million litres", "600 million litres", "300 million litres"}));
        questionaire.add(new Question("What year was Michael Jackson born?", "1958", new String[] {"1957", "1954", "1961"}));
        questionaire.add(new Question("What was the first Disney movie released?", "Snow White", new String[] {"Sleeping Beauty", "Fantasia", "Pinocchio"}));
        questionaire.add(new Question("What nut is in the middle of Ferrero Rocher?", "Hazelnut", new String[] {"Peanut", "Almond", "Cashew"}));
        questionaire.add(new Question("What is a baby rabbit called?", "Kit", new String[] {"Kid", "Fawn", "Pup"}));
        questionaire.add(new Question("What does Hg stand for on the periodic table?", "Mercury", new String[] {"Hydrogen", "Rhodium", "Gold"}));
        
        this.setQuestionOrder();
    }

    public void setQuestionOrder() 
    {
        this.questionOrder = new int[] {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14};
        Random rand = new Random();
        
        for (int i = 0; i < questionOrder.length; i++) {
            int randomIndexToSwap = rand.nextInt(questionOrder.length);
            int temp = questionOrder[randomIndexToSwap];
            questionOrder[randomIndexToSwap] = questionOrder[i];
            questionOrder[i] = temp;
	}
    }

    public int[] getQuestionOrder() {
        return questionOrder;
    }
    
    public int getQuestionToPrint(int k)
    {
        return this.questionOrder[k];
    }
    
    public boolean isCorrect(String letter, int k)
    {
        Question current = this.questionaire.get(getQuestionToPrint(k));
        return current.isCorrect(letter);
    }
    
    public String getQuestion(int k)
    {
        Question current = this.questionaire.get(getQuestionToPrint(k));
        return current.getQuestion();
    }
    
    public String getOptions(int k, int j)
    {
        Question current = this.questionaire.get(getQuestionToPrint(k));
        String[] choice = current.getChoice(j);
        return choice[1];
    }
    
    public int getKeepIndex(int k, int keep)
    {
        Question current = this.questionaire.get(getQuestionToPrint(k));
        
        for (int i = 0; i < 4; i++)
        {
            String[] choice = current.getChoice(i);
            
            if (choice[1].equalsIgnoreCase(current.getOption(keep)))
            {
                return i;
            }
        }
        return -1;
    }
    
    public int getAnswerIndex(int k)
    {
        Question current = this.questionaire.get(getQuestionToPrint(k));
        
        for (int i = 0; i < 4; i++)
        {
            String[] choice = current.getChoice(i);
            
            if (choice[1].equalsIgnoreCase(current.getAnswer()))
            {
                return i;
            }
        }
        return -1;
    }
}