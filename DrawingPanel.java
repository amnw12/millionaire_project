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
public class DrawingPanel extends JPanel {

    public final int PANEL_WIDTH = 1550, PANEL_HEIGHT = 1000;
    private WelcomeMessage welcome;
    
    public DrawingPanel() {
        welcome = new WelcomeMessage();
        
        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        setBackground(new Color(234, 215, 200));
        
        add(welcome, BorderLayout.NORTH);
    }
}
