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
public class MainMystic extends Mystic implements Serializable{
    private String name;
    
    MainMystic(String name)
    {
        super();
        this.name = name;
        this.setLevel(1);
    }
    
    @Override
    public String getName()
    {
        return this.name;
    }
    
    
}
