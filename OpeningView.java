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
public class OpeningView extends JPanel {
    
    public final int PANEL_WIDTH = 1550, PANEL_HEIGHT = 1000;
    private DrawingPanel drawPanel;
    private IntroMessage intro;
    private JPanel beginMessage, usernamePanel;

    public OpeningView(JButton begin, JTextField usernameField) {
        super(new BorderLayout());
        drawPanel = new DrawingPanel();
        beginMessage = new JPanel(new BorderLayout());
        usernamePanel = new JPanel(new BorderLayout());
        intro = new IntroMessage();
        
        usernamePanel.add(usernameField,BorderLayout.NORTH);
        usernamePanel.add(begin, BorderLayout.SOUTH);
        usernamePanel.setBackground(new Color(234, 215, 200));
        
        beginMessage.add(intro, BorderLayout.CENTER);
        beginMessage.add(usernamePanel, BorderLayout.SOUTH);
        
        drawPanel.add(beginMessage, BorderLayout.CENTER);
        add(drawPanel, BorderLayout.CENTER);
    }
}
