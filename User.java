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
public class User {
    private String username;
    private int score;

    public User() {
        username = "";
        score = 0;
    }
    
    public void updateScore(int k)
    {
        score += k;
    }

    @Override
    public String toString() {
        return username + "\t\t\t" + score;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getUsername() {
        return username;
    }

    public int getScore() {
        return score;
    }
    
    public void reset()
    {
        username = "";
        score = 0;
    }
}
