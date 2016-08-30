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
public class Item implements Serializable{
    private String name;
    private int quantity;
    
    Item (String name)
    {
        this.name = name;
    }
    
    Item (String name, int quantity)
    {
        this.name = name;
        this.quantity = quantity;
    }
    
    public void setName(String s)
    {
        this.name = s;
    }
    
    public String getName()
    {
        return this.name;
    }
   
    public void setQuantity(int s)
    {
        this.quantity = s;
    }
    
    public int getQuantity()
    {
        return this.quantity;
    }
    
    public String toString ()
    {
        return this.name + " (" + this.quantity + ")";
    }
}
