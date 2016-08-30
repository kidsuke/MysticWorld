/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysticworld;

import java.io.Serializable;

/**
 *
 * @author ADMIN
 */
public class MainCharacter extends Character implements Serializable
{
    private int exp;
    
    MainCharacter(String name)
    {
        super(name);
        this.exp = 0;
    }
    
    //Experience
    public int getExp()
    {
        return this.exp;
    }
    
    public void gainExp(int n)
    {
        this.exp += n;
    }
    
    public void loseExp(int n)
    {
        if (this.exp - n  < 0)
            this.exp = 0;
        else
            this.exp -= n;
    }
}
