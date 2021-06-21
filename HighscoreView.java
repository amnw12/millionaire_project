/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project_2;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author amnwaqar
 */
public class HighscoreView extends JPanel{
    
    private DrawingPanel drawPanel;
    private HighscoreMessage highscore;
    private JPanel highscoreMessage, buttons;

    public HighscoreView(JButton playAgain, JButton exit, String allScores) {
        super(new BorderLayout());
        drawPanel = new DrawingPanel();
        highscore = new HighscoreMessage(allScores);
        highscoreMessage = new JPanel(new BorderLayout());
        
        buttons = new JPanel(new BorderLayout());
        buttons.add(playAgain, BorderLayout.WEST);
        buttons.add(exit, BorderLayout.EAST);
        buttons.setBackground(new Color(234, 215, 200));
        
        highscoreMessage.add(highscore, BorderLayout.CENTER);
        highscoreMessage.add(buttons, BorderLayout.SOUTH);
        
        drawPanel.add(highscoreMessage, BorderLayout.CENTER);
        
        add(drawPanel, BorderLayout.CENTER);
    }
}