/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project_2;

import java.awt.Color;
import javax.swing.JTextArea;

/**
 *
 * @author amnwaqar
 */
public class WelcomeMessage extends JTextArea{

    WelcomeBanner welcome;
    
    public WelcomeMessage() {
        welcome = new WelcomeBanner();
        append(welcome.Welcome());
        setWrapStyleWord(true);
        setEditable(false);
        setBackground(new Color(255, 242, 230));
    }
}
