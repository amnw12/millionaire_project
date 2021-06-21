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
public class ExitView extends JPanel{
    private DrawingPanel drawPanel;
    private GameEndMessage end;

    public ExitView() {
        super(new BorderLayout());
        drawPanel = new DrawingPanel();
        end = new GameEndMessage();
        end.append(end.gameOver());
        
        drawPanel.add(end, BorderLayout.CENTER);
        add(drawPanel, BorderLayout.CENTER);
    }
}
