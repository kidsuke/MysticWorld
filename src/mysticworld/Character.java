/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysticworld;

import java.io.Serializable;

/**
 *
 * @author hongloans9
 */
public class Character implements Serializable{
    private String name;
    private Location currentLocation, oldLocation;
    private int state;
    
    Character(String name)
    {
        this.name = name;
        this.state = 0;//You havent talked to this character yet
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public String getName()
    {
        return this.name;
    }
    
    public Location getCurrentLoca()
    {
        return this.currentLocation;
    }
    
    public void setLoca(Location newLocation)
    {
        this.oldLocation = this.currentLocation;
        this.currentLocation = newLocation;
    }
    
    public void nextLoca(String dir)
    {
        if (dir.contains("north") || dir.contains("south") || dir.contains("east") || dir.contains("west")) {
            this.setLoca(this.currentLocation.getDirection(dir));
        }
    }
    
    //Set state of the character
    public void setState(int s)
    {
        this.state = s;
    }
    
    public int getState()
    {
        return this.state;
    }
}
