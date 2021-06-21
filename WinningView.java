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
public class WinningView extends JPanel {
    private DrawingPanel drawPanel;
    private GameEndMessage won;
    private JPanel winningMessage, buttons;

    public WinningView(JButton highscore, JButton playAgain, JButton exit, JLabel score) {
        super(new BorderLayout());
        drawPanel = new DrawingPanel();
        winningMessage = new JPanel(new BorderLayout());
        won = new GameEndMessage();
        won.append(won.winningOutput());
        
        buttons = new JPanel(new BorderLayout());
        buttons.add(highscore, BorderLayout.WEST);
        buttons.add(playAgain, BorderLayout.CENTER);
        buttons.add(exit, BorderLayout.EAST);
        buttons.add(score, BorderLayout.SOUTH);
        buttons.setBackground(new Color(234, 215, 200));
        
        winningMessage.add(won, BorderLayout.CENTER);
        winningMessage.add(buttons, BorderLayout.SOUTH);
        
        drawPanel.add(winningMessage, BorderLayout.CENTER);
        add(drawPanel, BorderLayout.CENTER);
    }
}
