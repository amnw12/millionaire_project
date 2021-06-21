/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project_2;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author amnwaqar
 */
public class LosingView extends JPanel {
    private DrawingPanel drawPanel;
    private GameEndMessage lost;
    private JPanel losingMessage, buttons;

    public LosingView(JButton highscore, JButton playAgain, JButton exit, JLabel score) {
        super(new BorderLayout());
        drawPanel = new DrawingPanel();
        losingMessage = new JPanel(new BorderLayout());
        lost = new GameEndMessage();
        lost.append(lost.losingOutput());
        
        buttons = new JPanel(new BorderLayout());
        buttons.add(highscore, BorderLayout.WEST);
        buttons.add(playAgain, BorderLayout.CENTER);
        buttons.add(exit, BorderLayout.EAST);
        buttons.add(score, BorderLayout.SOUTH);
        buttons.setBackground(new Color(234, 215, 200));
        
        losingMessage.add(lost, BorderLayout.CENTER);
        losingMessage.add(buttons, BorderLayout.SOUTH);
        
        drawPanel.add(losingMessage, BorderLayout.CENTER);
        add(drawPanel, BorderLayout.CENTER);
    }
}
