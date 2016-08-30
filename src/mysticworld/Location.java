/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//abcd
//def

package mysticworld;

import java.io.Serializable;

/**
 *
 * @author hongloans9
 */
public class Location implements Serializable{
    private String name;
    private Location N, S, E, W;
    private boolean checkAvai;
  
    Location(String name)
    {
        this.name = name;
        this.checkAvai = false;// Cannot go to this location unless it is set to true
    }
     
    public void setAvai(boolean s)
    {
        this.checkAvai = s;
    }
    
    public boolean getAvai()
    {
        return this.checkAvai;
    }
    
    public void setDirection(Location N, Location S, Location E, Location W)
    {
        this.N = N;
        this.S = S;
        this.E = E;
        this.W = W;
    }
    
    public Location getDirection(String direction) {
        if (direction.contains("north")) {
            return this.N;
        } else if (direction.contains("south")) {
            return this.S;
        } else if (direction.contains("west")) {
            return this.W;
        } else if (direction.contains("east")) {
            return this.E;
        }
        return null;
    }
    
    //public Location getN()
    //{
    //    return this.N;
    //}
    //
    //public Location getS()
    //{
    //    return this.S;
    //}
    //
    //public Location getE()
    //{
    //    return this.E;
    //}
    //
    //public Location getW()
    //{
    //    return this.W;
    //}
    
    public String getName()
    {
        return this.name;
    }

}
