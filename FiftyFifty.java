/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project_2;

import java.awt.event.ActionEvent;

/**
 *
 * @author amnwaqar
 */
public class FiftyFifty extends Lifeline{

    public FiftyFifty() {
        super();
    }
    
    @Override
    public int use() {
       
       if (!used)
       {
           used = true; 
           
           int keep = rand.nextInt(2);
           
           return keep;
       }
        
       throw new LifelineUsedException("50-50 already used!");
    }
}
