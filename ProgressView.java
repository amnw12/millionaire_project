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
public class ProgressView extends JTextArea 
{
    ProgressBar progress;
    
    public ProgressView() {
        progress = new ProgressBar();
        append("\n");
        append(progress.ProgressBar(1));
        setForeground(Color.yellow);
        setBackground(new Color(128, 128, 128));
        setEditable(false);
    }
    
    public void reset()
    {
        progress.setUpProgressBar();
    }
    
    public void update(int k)
    {
        setText("\n" + progress.ProgressBar(k));
        
    }
    
    public void update50_50()
    {
        progress.updateProgressBar50_50();
    }
    
    public void updateSkip()
    {
        progress.updateProgressBarSKIP();
    }
}
