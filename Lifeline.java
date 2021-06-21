/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project_2;

import java.util.Random;

/**
 *
 * @author amnwaqar
 */
public abstract class Lifeline{
    protected boolean used;
    protected Random rand;

    public Lifeline() {
        used = false;
        rand = new Random();
    }
    
    public abstract int use();
    
    public void reset()
    {
        used = false;
    }
}
