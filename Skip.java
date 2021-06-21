/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project_2;

/**
 *
 * @author amnwaqar
 */
public class Skip extends Lifeline{
    
    public Skip() {
        super();
    }
    
    @Override
    public int use() {
        
        if (!used)
        {
            used = true;
            
            int index = rand.nextInt(4) + 10;
        
            return index;
        }
        
        throw new LifelineUsedException("Skip already used!");
    }

    
}
