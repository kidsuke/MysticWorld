/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysticworld;

/**
 *
 * @author ADMIN
 */
import java.io.Serializable;
import java.util.ArrayList;

public class Inventory extends Item implements Serializable{
    private int capacity;
    private int filled;
    private boolean checkAvai;
    private ArrayList<Item> itemList;
    
    Inventory (String name)
    {
        super(name);
        this.filled = 0;
        itemList = new ArrayList<Item>();
        this.checkAvai = false;
    }
    
    public void setCapacity(int s)
    {
        this.capacity = s;
    }
    
    public int getCapacity()
    {
        return this.capacity;
    }
       
    public void printItem()
    {
        System.out.println("Inventory: (" + (this.filled) + "/" + this.capacity + ")");
        for (Item n: this.itemList)
        {
            //System.out.println(n.getName() + " (" + n.getQuantity() + ")");
            System.out.println(n);
        }
    }
    
    public void addItem(Item sth)
    {
        this.itemList.add(sth);
        this.filled++;
    }
    
    public boolean checkItem(String s)
    {
       for (Item n: this.itemList)
       {
           if (n.getName().contains(s))
               return true;
       }
       return false;
    }
    
     public void setAvai(boolean s)
    {
        this.checkAvai = s;
    }
    
    public boolean getAvai()
    {
        return this.checkAvai;
    }
    
    public void removeItem(String s)
    {
        for (Item n: this.itemList)
       {
           if (n.getName().equals(s))
           {
               this.itemList.remove(n);
               this.filled--;
               break;
           }
       }
    }
}
