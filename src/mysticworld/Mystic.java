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
import java.util.Random;
import java.util.Collections;
public class Mystic implements Serializable{
    private ArrayList<String> listOfName;
    private int level, chance, numberOfQuestion;
    boolean alive;
    
    Mystic ()
    {
        this.alive = true;
        this.listOfName = new ArrayList<String>();
        this.listOfName.add("Feroah");
        this.listOfName.add("Tramarex");
        this.listOfName.add("Kaios");
        this.listOfName.add("Firuba");
        this.listOfName.add("Chilellia");
        this.listOfName.add("Nadrian");
        this.listOfName.add("Krokodin");
        this.listOfName.add("Plossom");
        this.listOfName.add("Amiyom");
        this.listOfName.add("Obelious");
    }
    
    public String getName()
    {
        Random random = new Random();
        int n = random.nextInt(this.listOfName.size() - 1) + 1;
        return this.listOfName.get(n);
    }
    
    public int getLevel()
    {
        return this.level;
    }
    
    public void setLevel(int level)
    {
        this.level = level;
        if (level == 1) // 1 question   
        {
            this.numberOfQuestion = 1;
            this.chance = 2;
        }
        else if (level == 2) // 1 question
        {
            this.numberOfQuestion = 1;
            this.chance = 1;
        }
        else if (level == 3)// 2 question
        {
            this.numberOfQuestion = 2;
            this.chance = 3;
        }
        else if (level == 4)// 2 question
        {
            this.numberOfQuestion = 2;
            this.chance = 2;
        }
        else // level 5 -> 3 question
        {
            this.numberOfQuestion = 3;
            this.chance = 3;
        }
    }
    
    public int getChance()
    {
        return this.chance;
    }
    
    public int getNumberOfQuestion()
    {
        return this.numberOfQuestion;
    }
    
    public void setAlive(boolean s)
    {
        this.alive = s;
    }
    
    public boolean getAlive()
    {
        return this.alive;
    }
}
