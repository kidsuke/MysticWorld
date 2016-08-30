
     /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysticworld;

import java.util.Scanner;
import java.io.*;
import java.lang.Thread;
import java.util.ArrayList;
import java.util.Random;
/**
 *
 * @author ADMIN
 */
public class GameStart implements Serializable{
    private GameStart save, load;
    private transient FileReader file;
    private transient Scanner reader;
    private Location HomeTown, DarkForest, PortCity, SteamingMt, SacredStone, FirstCave, RockyWL;
    private Location sacredSub;
    private Character blacksmith, sorcerer, merchant, granny;
    private MainCharacter player;
    private MainMystic puncha, vulpes, luna, yourMys, ghost, bossC1, bossC2;
    private Mystic ahihi, ahoho, ahaha, ahehe;
    private Inventory bag;
    private Item food, potion, torch;
    private String input;
    private int forestState = 0, stMountainState = 0, sacredState = 0, wastelandState = 0, portCityState = 0;
    private int graveState = 0, tombState = 0, templeState = 0, castleState = 0, stPoolState = 0, rockTowerState = 0, shrineState = 0, stCave = 0;
    private int toSave = 0, sacredSubState = 0;
    private String listOfQuestions[][];
    private long seek;
    private ArrayList<Mystic> listOfMystic;
    private boolean torchOn = false;
    
    GameStart() {
        // TODO code application logic here
        this.seek = 0;
        this.input = "";
        reader = new Scanner(System.in);
        
        //Load map
        this.InitMap();
        
        //Load Mystics
        this.InitMystic();
        
        //Load Items
        this.InitItem();
        
        //Load Characters
        this.InitChar();
        
        //Load Boss
        this.InitBoss();
        
        //Load Questions
        this.InitQues();
        
        //Show the menu
   

    }
    
    public void startGame()
    {
        this.input = "";
        
        this.Menu();
        this.input = this.Input();

        if (this.input.equals("start"))
        {            
            //Load Prologue
            
            this.Prologue();
            if (this.input.contains("1") || this.input.contains("one") || this.input.contains("first"))
                this.yourMys = puncha;
            else if (this.input.contains("2") || this.input.contains("two") || this.input.contains("second"))
                this.yourMys = luna;
            else if (this.input.contains("3") || this.input.contains("three") || this.input.contains("third"))
                this.yourMys = vulpes;
            else 
                return;
            
            //Load Chapter 1
            this.Chapter1();

            //Load Chapter 2
            this.Chapter2();
        }
        else
            System.out.println("I don't understand what you mean...");
    }
    
    public void readState(String s, String a, String b)
    {
    try{    
        this.file = new FileReader(s);
        BufferedReader bufferedreader = new BufferedReader(this.file);
        String n="", replace ="";
        int c;
        char letter;
        while ((n = bufferedreader.readLine()) != null)
        {
            if (n.equalsIgnoreCase("(player)"))
            {
                n = "[" + a + "]";
                System.out.println(n);
                Thread.sleep(400);
            }
            else if (n.equalsIgnoreCase("(Mystic)"))
            {
                n = "[" + b + "]";
                System.out.println(n);
                Thread.sleep(400);
            }
            else if (n.contains("player"))
            {
                replace = n.replaceAll("player", a);
                System.out.println(replace);
                Thread.sleep(400);
            }
            else if (n.contains("yourmys"))
            {
                replace = n.replaceAll("yourmys", b);
                System.out.println(replace);
                Thread.sleep(400);
            }
            else
            {
                System.out.println(n);
                Thread.sleep(400);
            }  
        }
        this.file.close();
    }catch (FileNotFoundException e){
        System.out.println("FILE NOT FOUND");
    }catch (IOException e){
        e.printStackTrace();
    }catch (InterruptedException e){
        e.printStackTrace();
    }
}
    
    public long readFile (String s, String a, String b, long seek)
    {
        long forSeek = seek;
        try 
        {    
            RandomAccessFile file = new RandomAccessFile(s,"r");
            String read, replace = "";
            
            file.seek(forSeek);
            
            //if (file.readLine() != null)
               
                while (!(read = file.readLine()).equals(""))
                {
                    if (read.equalsIgnoreCase("(player)"))
                    {
                        read = "[" + a + "]";
                        System.out.println(read);
                        Thread.sleep(400);
                    }
                    else if (read.equalsIgnoreCase("(mystic)"))
                    {
                        read = "[" + b + "]";
                        System.out.println(read);
                        Thread.sleep(400);
                    }
                    else if (read.contains("player"))
                    {
                        replace = read.replaceAll("player", a);
                        System.out.println(replace);
                        Thread.sleep(400);
                    }
                    else if (read.contains("yourmys"))
                    {
                        replace = read.replaceAll("yourmys", b);
                        System.out.println(replace);
                        Thread.sleep(400);
                    }
                    else
                    {
                        System.out.println(read);
                        Thread.sleep(400);
                    }  
                }
                if (read != null)
                    forSeek = file.getFilePointer();
                else
                    forSeek = 0;
            
            
            file.close();
           
        }catch (IOException e){
            e.printStackTrace();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return forSeek;
    }
    
    //Modify input
    public static String modifyInput(String s)
    {
        s = s.trim();
        s = s.toLowerCase();
        return s;
    }
    
    //Initialize Your Character
    public void InitChar()
    {
        //Main Characters
        this.player = new MainCharacter("Player");
        this.player.setLoca(HomeTown);  
        //Sub Characters
        //Joensu Town
        //BlackSmith
        this.blacksmith = new Character("BlackSmith");
        this.blacksmith.setLoca(HomeTown);
        //Socerer
        this.sorcerer = new Character("BlackSmith");
        this.sorcerer.setLoca(HomeTown);
        //Merchant
        this.merchant = new Character("Merchant");
        this.merchant.setLoca(HomeTown);
        //Granny//
        this.granny = new Character("Grany");
        this.granny.setLoca(HomeTown);
    }
    
    //Initialize Boss
    public void InitBoss()
    {
        this.puncha = new MainMystic("Puncha");
        this.vulpes = new MainMystic("Vulpes");
        this.luna = new MainMystic("Luna");
        
        //Chapter 1 boss//
        this.bossC1 = new MainMystic("Krei");
        this.bossC1.setLevel(3);
        this.ghost = new MainMystic("Soul of Aris");
        this.ghost.setLevel(2);
        //Chapter 2 boss//
        this.bossC2 = new MainMystic("Pheonatrix");
        this.bossC2.setLevel(4);
    }
    
    //Initialize Mystics
    public void InitMystic()
    {
        this.listOfMystic = new ArrayList<Mystic>();
        
        this.ahihi = new Mystic();
        this.ahihi.setLevel(1);
        
        this.ahoho = new Mystic();
        this.ahoho.setLevel(2);
        
        this.ahaha = new Mystic();
        this.ahaha.setLevel(3);
        
        this.ahehe = new Mystic();
        this.ahehe.setLevel(4);
        
        this.listOfMystic.add(ahihi);
        this.listOfMystic.add(ahoho);
        this.listOfMystic.add(ahaha);
        this.listOfMystic.add(ahehe);
    }
    
    //Initialize Map
    public void InitMap()
    {
        //Main places
        //Your Mystic's Cave
        this.FirstCave = new Location("Your Mystic's Cave");
        this.FirstCave.setAvai(true);
        //Joensu Town
        this.HomeTown = new Location("Joensu Town");
        this.HomeTown.setAvai(true);
        //Dark Forest
        this.DarkForest = new Location("Dark Forest");
        //Volcano
        this.SteamingMt = new Location("Steaming Mountain");
        //Port City
        this.PortCity = new Location("Port City");
        this.PortCity.setAvai(false);
        // Rocky Wasteland
        this.RockyWL = new Location("Rock Wasteland");
        this.SacredStone = new Location("Sacred Stone");
        this.sacredSub = new Location ("Sacred Stone sub-place");
        
        //Sub places
        
        this.HomeTown.setDirection(this.DarkForest, this.PortCity, this.RockyWL, this.sacredSub);
        this.DarkForest.setDirection(null, this.HomeTown, null, null);
        this.sacredSub.setDirection(this.SacredStone,null,this.HomeTown,this.SteamingMt);
        this.SacredStone.setDirection(null, this.sacredSub, null, null);
        
        
    }
    
    public void checkLocaAvai(String direct)
    {
        if (this.input.contains("north") || this.input.contains("south") || this.input.contains("east") || this.input.contains("west")) {
            Location name = this.player.getCurrentLoca().getDirection(direct);
            if (name == null)
            {
                System.out.println("[" + this.player.getName() + "]");
                System.out.println("There is nothing in that direction");
            }
            else if (name.getAvai() == false) {
               if (name == this.DarkForest) {
                   System.out.println("[" + this.player.getName() + "]");
                   System.out.println("Talk to all the NPCs first");
                } else if (name == this.SteamingMt) {
                   System.out.println("[" + this.player.getName() + "]");
                   System.out.println("The bridge to Steamming Mountain has collapsed");
                } else if (name == this.RockyWL) {
                   System.out.println("[" + this.player.getName() + "]");
                   System.out.println("The way to Rocky Wasteland has been blocked by rocks");
                } else {
                   System.out.println("[" + this.player.getName() + "]");
                   System.out.println("You can't go that way now");
                }
            }else {
                this.player.nextLoca(direct);
            }
        }
    }
    
    //Initialize Item
    public void InitItem()
    {
        this.bag = new Inventory("Fabric Bag");
        this.food = new Item("Food");
        this.torch = new Item("Torch");
        this.potion = new Item("Potion");
    }
    
    //Initialize Question
    public void InitQues()
    {
        this.listOfQuestions = new String[2][34];
        
        this.listOfQuestions[0][0] = " Take off my skin - I won't cry, but you will! What am I?";
        this.listOfQuestions[1][0] = "onion";
        
        this.listOfQuestions[0][1] = " What is it that's always coming but never arrives?";
        this.listOfQuestions[1][1] = "tomorrow";
        
        this.listOfQuestions[0][2] = " I am the beginning of the end, You can see me twice in a decade but once in a year and not in day but once in june and twice in a week.  Who am I?";
        this.listOfQuestions[1][2] = "e";
        
        this.listOfQuestions[0][3] = " What comes down but never goes up?";
        this.listOfQuestions[1][3] = "rain";
        
        this.listOfQuestions[0][4] = " What gets wetter the more it dries?";
        this.listOfQuestions[1][4] = "towel";
        
        this.listOfQuestions[0][5] = " I am up high in the sky with heat \n I am yellow and sweet \n I am a ball of fire \n In the morning I raise up higher and higher \n I am hot \n That looks like a big dot";
        this.listOfQuestions[1][5] = "sun";
        
        this.listOfQuestions[0][6] = " What gets bigger when it eats, but die weaker when its drink?";
        this.listOfQuestions[1][6] = "fire";
	
	this.listOfQuestions[0][7] = " If you look at me \n I will look at you \n If you grin at me \n I will grin at you \n If you dance \n I will also dance \n But if you shout \n Sorry, i can't.";
        this.listOfQuestions[1][7] = "mirror";

	this.listOfQuestions[0][8] = " I am 5 letter word \n If all the 5 letters are available I am a talent in you \n If you remove my first letter I will die \n If you remove my first 2 letters I will be sick. \n Who am I?";
        this.listOfQuestions[1][8] = "skill";

	this.listOfQuestions[0][9] = " What is black when you buy it \n Red when you are using it \n And grey when you throw it away?";
        this.listOfQuestions[1][9] = "charcoal";
	
	this.listOfQuestions[0][10] = " If you have it, you want to share it. \n If you share it, you don't have it. \n What is it?";
        this.listOfQuestions[1][10] = "secret";

	this.listOfQuestions[0][11] = " I am made of 5 letters \nIf you remove the first letter \n It is part of our body. \n Then continue  remove the second letter \n It is everywhere, people can’t live without it. \n Guess what ?";
        this.listOfQuestions[1][11] = "chair";

	this.listOfQuestions[0][12] = " As tall as a tree, as small as a bug \n As fast as an eagle, as slow as a slug. \n Many times in front or behind but never on top, always aligned. \n What is this?";
        this.listOfQuestions[1][12] = "shadow";

	this.listOfQuestions[0][13] = " What kind of room has no doors or windows? \n Or What room can no one enter?";
        this.listOfQuestions[1][13] = "mushroom";

	this.listOfQuestions[0][14] = " What invention lets you look right through a wall?";
        this.listOfQuestions[1][14] = "window";

	this.listOfQuestions[0][15] = " What is at the end of a rainbow?";
        this.listOfQuestions[1][15] = "w";

	this.listOfQuestions[0][16] = " What building has the most stories?";
        this.listOfQuestions[1][16] = "library";

	this.listOfQuestions[0][17] = " What cannot talk, but will always respond when spoken to?";
        this.listOfQuestions[1][17] = "echo";

	this.listOfQuestions[0][18] = " The more there is, the less you see. What is it?";
        this.listOfQuestions[1][18] = "darkness";

	this.listOfQuestions[0][19] = " Longer than a decade and shorter than a millennium?";
        this.listOfQuestions[1][19] = "century";

	this.listOfQuestions[0][20] = " A game where everyone wants To run home and stealing is encouraged!";
        this.listOfQuestions[1][20] = "baseball";

	this.listOfQuestions[0][21] = " A woman has six daughters and they each have a brother. How many children does she have?";
        this.listOfQuestions[1][21] = "7";

	this.listOfQuestions[0][22] = " What loses its head every morning but gets it back night?";
        this.listOfQuestions[1][22] = "pillow";

	this.listOfQuestions[0][23] = " They come out at night without being called, \n And are lost in the day without being stolen. \n What are they?";
        this.listOfQuestions[1][23] = "stars";

	this.listOfQuestions[0][24] = " What flies without wings?";
        this.listOfQuestions[1][24] = "time";

	this.listOfQuestions[0][25] = " What always runs but never walks, \n Often murmurs, never talks, \n Has a bed but never sleeps, \n Has a mouth but never eats? ";
        this.listOfQuestions[1][25] = "river";

	this.listOfQuestions[0][26] = " What two things can always see what the other sees, but can never see each other?";
        this.listOfQuestions[1][26] = "eyes";

	this.listOfQuestions[0][27] = " What belongs to you, but is used more by others?";
        this.listOfQuestions[1][27] = "name";

	this.listOfQuestions[0][28] = " This animal is pink with a curly tail. He makes tasty BBQ sandwiches!";
        this.listOfQuestions[1][28] = "pig";

	this.listOfQuestions[0][29] = " This company makes billions of dollars selling Windows?";
        this.listOfQuestions[1][29] = "microsoft";

	this.listOfQuestions[0][30] = " This creature is often considered the King of animals?";
        this.listOfQuestions[1][30] = "lion";

	this.listOfQuestions[0][31] = " I have keys but no locks. \n I have space but no room. \n You can enter, but can't go outside. \n What am I?";
        this.listOfQuestions[1][31] = "keyboard";

	this.listOfQuestions[0][32] = " What goes up, but never goes down?";
        this.listOfQuestions[1][32] = "age";

	this.listOfQuestions[0][33] = " What jumps when it walks and sits when it stands?";
        this.listOfQuestions[1][33] = "kangaroo";
    }
    
    //Create Menu
    public void Menu()
    {
        System.out.println("----------------WELCOME TO THE MYSTIC's WORLD----------------");
        System.out.println("*                          MENU                             *");
        System.out.println("*  1. Type 'start' if you are ready to begin this journey   *");
        System.out.println("*  2. Type 'help' if you need any information               *");
        System.out.println("*  3. Type 'save' to save the game                          *");
        System.out.println("*  4. Type 'load' to load the game                          *");
        System.out.println("*  5. Type 'quit' if you cannot bear this journey anymore   *");
        System.out.println("*************************************************************");
    }
    
    public void help()
    {
        this.readState("story/readme.txt", "asd", "asd");
    }
    
    //Save Game
    public void saveGame()
    {
        //this.save = new gameStart();
        try{
            FileOutputStream fileoutput = new FileOutputStream("save.dat");
            ObjectOutputStream objectoutput = new ObjectOutputStream(fileoutput);
            objectoutput.writeObject(this);
            objectoutput.flush();
            objectoutput.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    //Load Game
    public GameStart loadGame()
    {
        this.load = new GameStart();
        try{
            FileInputStream fileinput = new FileInputStream("save.dat");
            ObjectInputStream objectinput = new ObjectInputStream(fileinput);
            this.load = (GameStart)objectinput.readObject();
            objectinput.close();
            
        }catch(Exception e){
            e.printStackTrace();
        }
        return this.load;
    }
    
    //Fight monster
    public void RandomMonster()
    {
        Random random = new Random();
        int n = random.nextInt(9);
        if (n >= 0 && n <= 2)
            this.Fight(ahihi);
        else if(n >= 3 && n <= 4)
            this.Fight(ahoho);
        else if (n >= 5 && n <= 6)
            this.Fight(ahaha);
        else if (n == 7)
            this.Fight(ahehe);
        else
            System.out.println("No monster found. Try again!");
    }
    
    public boolean Fight(Mystic a)
    {
        int noq = a.getNumberOfQuestion();
        int n, chance = a.getChance(), count = 0;
        Random random = new Random();
        String monsName = a.getName();
        System.out.println("[" + monsName + " APPEARED (Level " + a.getLevel() + ", " + a.getNumberOfQuestion() + " Question(s), " + a.getChance() + " Chance(s))]");
        for (int i = 1 ; i <= noq; i++)
        {
            System.out.println("");
            System.out.println("[Question " + i + "]");
            n = random.nextInt(33); 
            System.out.println(this.listOfQuestions[0][n]);
            while (chance > 0)
            { 
                this.input = this.Input();
                chance--;
                System.out.println("");
                if (this.input.contains("potion")) { //new part
                    count++;
                    System.out.println(this.player.getName() + " gained " + a.getLevel() + " exp");
                    if (this.yourMys.getName().equals("Vulpes"))
                        this.player.gainExp(a.getLevel()+1);
                    else
                        this.player.gainExp(a.getLevel());
                    System.out.println("");
                    break;
                } else if (this.input.contains(this.listOfQuestions[1][n]))
                {
                    System.out.println("[ANSWER CORRECT]");
                    System.out.println("Chance(s) left: " + chance);
                    count++;
                    System.out.println(this.player.getName() + " gained " + a.getLevel() + " exp");
                    if (this.yourMys.getName().equals("Vulpes"))
                        this.player.gainExp(a.getLevel()+1);
                    else
                        this.player.gainExp(a.getLevel());
                    System.out.println("");
                    break;
                }
                else
                {
                    System.out.println("[ANSWER INCORRECT]");
                    System.out.println("Chance(s) left: " + chance);
                    if (this.yourMys.getName().equals("Puncha"))
                        this.player.loseExp(a.getLevel()/2);
                    else
                        this.player.loseExp(a.getLevel());
                    System.out.println(this.player.getName() + " lost " + a.getLevel() + " exp");
                    System.out.println(this.player.getName() + " has " + this.player.getExp() + " exp left");
                    System.out.println("");
                }
                
            }
            if (chance == 0)
                break;
        }
        if (count == noq)
        {
            System.out.println(monsName + " has been defeated");
            a.setAlive(false);
            return true;
        }
        else
        {
            System.out.println("You lose.");
            return false;
        }
    }
    
    //All Location States = 0//
    
    //PROLOGUE
    public void Prologue()
    {
        int gameState = 0;
        while (this.player.getCurrentLoca() != this.FirstCave)
        {
            switch (gameState) {
                case 0:
                    System.out.println("");
                    this.seek = this.readFile("story/prologue/prologue.txt", "", "", 0);
                    this.input = this.Input();
                    while (!this.input.contains("look") && !this.input.contains("check"))
                    {
                        if (this.input.equals("quit"))
                            return;
                        if (this.input.contains("north") || this.input.contains("south") || this.input.contains("east") || this.input.contains("west"))
                            System.out.println("> I'm new to this place. I don't think it's a good idea.");
                        else if (this.input.contains("where") || this.input.contains("what"))
                            System.out.println("I don't know for sure...");
                        else
                            System.out.println("> I dont' understand what you mean...");
                        this.input = this.Input();
                    }
                    gameState = 1;
                    break;
                case 1:
                    System.out.println("");
                    this.seek = this.readFile("story/prologue/prologue.txt", "", "", this.seek);
                    this.input = this.Input();
                    while (!this.input.contains("check") && !this.input.contains("examine") && (!this.input.contains("come") && !this.input.contains("closer")) && (!this.input.contains("get")&& !this.input.contains("closer")))
                    {
                        if (this.input.equals("quit"))
                            return;
                        if (this.input.contains("north") || this.input.contains("south") || this.input.contains("east") || this.input.contains("west"))
                            System.out.println("> I'm new to this place. I don't think it's a good idea.");
                        else
                            System.out.println("> I dont' understand what you mean...");
                        this.input = this.Input();
                    }
                    gameState = 2;
                    break;
                case 2:
                    System.out.println("");
                    this.seek = this.readFile("story/prologue/prologue.txt", "", "", this.seek);
                    this.input = this.Input();
                    while (!this.input.contains("examine") && !this.input.contains("check") && !(this.input.contains("come") && this.input.contains("closer")) && !(this.input.contains("get")&& this.input.contains("closer")) && !this.input.contains("back"))
                    {
                        if (this.input.equals("quit"))
                            return;
                        if (this.input.contains("north") || this.input.contains("south") || this.input.contains("east") || this.input.contains("west"))
                            System.out.println("> I'm new to this place. I don't think it's a good idea.");
                        else
                            System.out.println("> I dont' understand what you mean...");
                        this.input = this.Input();
                    }
                    if (this.input.contains("back"))
                    {
                        gameState = 1;
                        break;
                    }
                    else
                        gameState = 3;                 
                    break;
                case 3:
                    System.out.println("");
                    this.seek = this.readFile("story/prologue/prologue.txt", "", "", this.seek);
                    this.input = this.Input();   
                    while (!this.input.contains("wake") && !this.input.contains("tease") && !this.input.contains("touch") && !this.input.contains("yell") && !this.input.contains("ignore") && !this.input.contains("run") && !this.input.contains("back"))
                    {
                        if (this.input.equals("quit"))
                            return;
                        if (this.input.contains("north") || this.input.contains("south") || this.input.contains("east") || this.input.contains("west"))
                            System.out.println("> I'm new to this place. I don't think it's a good idea.");
                        else
                            System.out.println("> I dont' understand what you mean...");
                        this.input = this.Input();                     
                    }
                    if (this.input.contains("back"))
                    {
                        gameState = 2;
                        break;
                    }                   
                    else
                        gameState = 4;                    
                    break;
                case 4:
                    if (this.input.contains("wake") || this.input.contains("tease") || this.input.contains("touch") || this.input.contains("yell"))
                    {
                        System.out.println("");
                        this.readState("story/prologue/state0.txt", "", "");
                    }
                    else if (this.input.contains("ignore"))
                    {
                        System.out.println("");
                        this.readState("story/prologue/state1.txt", "", "");
                    }
                    else if (this.input.contains("run"))
                    {
                        System.out.println("");
                        this.readState("story/prologue/state2.txt", "", "");
                    }
                    this.input = this.Input();
                    while (!this.input.contains("run"))
                    {
                        if (this.input.equals("quit"))
                            return;
                        if (this.input.contains("north") || this.input.contains("south") || this.input.contains("east") || this.input.contains("west"))
                            System.out.println("> I'm new to this place. I don't think it's a good idea.");
                        else
                            System.out.println("> I dont' understand what you mean...");
                        this.input = this.Input();
                    }
                    gameState = 5;
                    break;
                case 5:
                    System.out.println("");
                    this.seek = this.readFile("story/prologue/prologue.txt", "", "", this.seek);
                    this.input = this.Input();
                    while (!this.input.equals("1") && !this.input.equals("2") && !this.input.equals("3") && !this.input.equals("4") && !this.input.equals("one") && !this.input.equals("first") && !this.input.equals("two") && !this.input.equals("second") && !this.input.equals("three") && !this.input.equals("third") && !this.input.equals("four") && !this.input.equals("fourth"))
                    {
                        if (this.input.contains("north") || this.input.contains("south") || this.input.contains("east") || this.input.contains("west"))
                            System.out.println("> I'm new to this place. I don't think it's a good idea.");
                        else
                            System.out.println("> I dont' understand what you mean...");
                        this.input = this.Input();                    
                    }
                    if (this.input.equals("1") || this.input.equals("2") || this.input.equals("3") || this.input.equals("one") || this.input.equals("first") || this.input.equals("two") || this.input.equals("second") || this.input.equals("three") || this.input.equals("third"))
                        gameState = 6;
                    else if (this.input.equals("4") || this.input.equals("four") || this.input.equals("fourth"))
                        gameState = 7;
                    break;
                case 6:
                    System.out.println("");
                    this.seek = this.readFile("story/prologue/prologue.txt", "", "", this.seek);
                    this.player.setLoca(FirstCave);
                    break;
                case 7:
                    System.out.println("");
                    System.out.println("I run as fast as I can if this is the last day of my life.");
                    System.out.println("\"Crack....ack...\"");
                    System.out.println("What? This bridge is rotten! It's going down!");
                    System.out.println("Shit! I'm DEAD! This is the last day of my life...you bad gamer...");
                    return;
                default:
                    break;
            }
        }
    }
    
    //CHAPTER 1    
    public void Chapter1()
    {
        this.seek = 0;
        if (this.toSave == 0)
        {
            this.grannyHouse1();
            this.player.setLoca(HomeTown);
        }
        this.toSave = 1;
        while(this.bossC1.getAlive() == true)
        {
            String a = this.player.getCurrentLoca().getName();
           
            switch(a)
            { 
                case "Joensu Town": //In town
                    this.joensuTown(); 
                    break;
                case "Dark Forest":
                    this.DarkForest(); 
                    break;
                default: 
                    break;
            }
    
        }
    }
    
    public void grannyHouse1()
    {
        int gameState = 0;
        while (gameState != 4)
        {
            switch (gameState)
            {
                //The attic - begin
                case 0:
                    System.out.println("");
                    this.seek = this.readFile("story/chapter1/grannyhouse/grannyhouse.txt", "", this.yourMys.getName(), this.seek);
                    this.input = this.Input();
                    while (!this.input.contains("look") && !this.input.contains("check"))
                    {
                        if (this.input.equals("quit"))
                            return;
                        if (this.input.contains("north") || this.input.contains("south") || this.input.contains("east") || this.input.contains("west"))
                            System.out.println("> I cannot do that now.");
                        else
                            System.out.println("> I dont' understand what you mean...");
                        System.out.print("> "); 
                        this.input = reader.nextLine();
                        this.input = modifyInput(this.input);
                    }
                    gameState = 1;
                    break;
                //The attic - stuffs
                case 1:
                    System.out.println("");
                    this.seek = this.readFile("story/chapter1/grannyhouse/grannyhouse.txt", "", this.yourMys.getName(), this.seek);
                    this.input = this.Input();
                    while(!this.input.contains("picture") && !this.input.contains("table") && !this.input.contains("bookshelf") && !this.input.contains("box") && !this.input.contains("boxes") && !this.input.contains("sword") && !this.input.contains("shield") && !this.input.contains("windows") && !this.input.contains("staircase") && !this.input.contains("downstair"))
                    {
                        if (this.input.equals("quit"))
                            return;
                        if (this.input.contains("north") || this.input.contains("south") || this.input.contains("east") || this.input.contains("west"))
                            System.out.println("> I cannot do that now.");
                        else
                            System.out.println("> I dont' understand what you mean...");
                        this.input = this.Input();
                        while (this.input.contains("picture") || this.input.contains("table") || this.input.contains("bookshelf") || this.input.contains("box") || this.input.contains("boxes") || this.input.contains("sword") || this.input.contains("shield") || this.input.contains("windows"))
                        {
                            if (this.input.contains("picture"))
                            {
                                System.out.println("This an abstract painting, just like Picasso or something.");
                                System.out.println("I doubt that mom put it here.");
                                System.out.println("I'm never interested in paintings anyway.");
                            }
                            else if (this.input.contains("table") || this.input.contains("bookshelf") || this.input.contains("box") || this.input.contains("boxes"))
                            {
                                System.out.println("There are bunch of of old stuffs like glasses, books, notes,...They are all covered by a thick layer of dust.");
                                System.out.println("The owner of this room must read a lot. He may be a writer.");
                            }
                            else if (this.input.contains("sword") || this.input.contains("shield"))
                            {
                                System.out.println("These weapons seem to be made from steel.");
                                System.out.println("They look like those weapons in Middle Age from video games which I've played.");
                            }
                            else if (this.input.contains("windows"))
                            {
                                System.out.println("Oh! Outside is a view of town, whose people wearing clothes that I've never seen before.");
                            }
                            this.input = this.Input();
                        }
                    }
                    gameState = 2;
                    break;
                //Meet granny and Mystics and ...
                case 2:
                    System.out.println("");
                    this.seek = this.readFile("story/chapter1/grannyhouse/grannyhouse.txt", "", this.yourMys.getName(), this.seek);
                    this.input = this.Input();
                    this.player.setName(input);
                    this.seek = this.readFile("story/chapter1/grannyhouse/grannyhouse.txt", this.player.getName(), this.yourMys.getName(), this.seek);
                    this.input = this.Input();
                    while (!this.input.contains("out") && !this.input.contains("eat") && !this.input.contains("finish"))
                    {
                        if (this.input.contains("north") || this.input.contains("south") || this.input.contains("east") || this.input.contains("west"))
                        {
                            System.out.println("[" + this.player.getName() + "]");
                            System.out.println("I cannot do that now.");
                        }
                        else
                        {
                            System.out.println("[" + this.player.getName() + "]");
                            System.out.println("I dont' understand what you mean...");
                        }
                        this.input = this.Input();
                    }
                    gameState = 3;
                    break;
                case 3:
                    System.out.println("");
                    this.seek = this.readFile("story/chapter1/grannyhouse/grannyhouse.txt", this.player.getName(), this.yourMys.getName(), this.seek);
                    this.food.setQuantity(2);
                    this.bag.setCapacity(1);
                    this.bag.addItem(food);
                    System.out.println("");
                    System.out.println("[RECEIVED " + this.bag.getName() + "]");
                    System.out.println("Press [I] to open your INVENTORY");
                    this.input = this.Input();
                    while (!this.input.equals("i"))
                    {
                        System.out.println("[" + this.player.getName() + "]");
                        System.out.println("Press [I] to open your INVENTORY");
                        this.input = this.Input();
                    }
                    this.bag.printItem();
                    this.bag.setAvai(true);
                    this.seek = this.readFile("story/chapter1/grannyhouse/grannyhouse.txt", this.player.getName(), this.yourMys.getName(), this.seek);
                    gameState = 4;
                    break;
                default:
                    break;
            }
        }
    }
    
    public void joensuTown()
    {
        System.out.println("[JOENSU TOWN]");
        System.out.println("NPC: BlackSmith , Your Mystic");
        System.out.println("Sites: Market, Portal, Granny House");
        
        while (this.player.getCurrentLoca() == this.HomeTown)
        {
            if (this.blacksmith.getState() != 0 && this.merchant.getState() != 0 && this.sorcerer.getState() != 0) {
                this.DarkForest.setAvai(true);
            }    
            this.input=this.Input();
            if (this.input.contains("blacksmith")) {
                this.BlackSmith();
            } else if (this.input.contains("granny")) {
                this.Granny();
            } else if (this.input.contains("market")) {
                this.Merchant();
            } else if (this.input.contains("mystic")) {
                this.Mystic();
            } else if (this.input.contains("portal")) {
                this.Sorcerer();
            } else if (this.input.contains("north") || this.input.contains("south") || this.input.contains("east") || this.input.contains("west")) {
                this.checkLocaAvai(this.input);
            } else {
                System.out.println("[" + this.player.getName() + "]");
                System.out.println("I don't understand what you mean.");
            }
        }
    }
    
    public String Input()
    {
        Scanner reader2 = new Scanner(System.in);
        do
        {    
            System.out.print("> ");
            this.input = reader2.nextLine();
            this.input = modifyInput(this.input);
            System.out.println("");
        } while (this.checkInput(this.input));
        
        return this.input;
    }
        
    public boolean checkInput(String s)
    {
        if (s.equals("quit"))
        {
            System.exit(0);
            return true;
        }
        else if (s.equals("help"))
        {
            this.help();
            return true;
        }
        else if (s.equals("save"))
        {
            if (this.toSave == 0)
            {
                System.out.println("You have to finish the prologue in order to save game.");
            }
            else
                this.saveGame();
            return true;
        }
        else if (this.input.equals("load"))
        {
            if (this.loadGame().toSave == 1)
            {
                this.loadGame().Chapter1();
                this.loadGame().Chapter2();
                return false;
            }
            else if (this.loadGame().toSave == 2)
            {
                this.loadGame().Chapter2();
                return false;
            }
            else
            {
                System.out.println("No game save");
                return true;
            }
        }
        else if (this.bag.getAvai() == true && s.equals("i"))
        {
            this.bag.printItem();
            return true;
        }
        else
            return false;
    }
    
    public void Granny()
    {
        if (this.granny.getState() == 0) {
            System.out.println("");
            System.out.println("[Granny]");
            System.out.println(this.yourMys.getName() + " will take you around the town." );
        }
        if (this.granny.getState() == 1) {
            System.out.println("");
            System.out.println("[Granny]");
            System.out.println("Thanks to you, all of the lost villagers have returned from the Dark Forest.");
            System.out.println("[" + this.yourMys.getName() + "]");
            System.out.println("I gave him a hand, they should thank me either.");
            System.out.println("[Granny]");
            System.out.println("Oh " + this.yourMys.getName() + " , what you did for us is priceless. You are our hero since that day.");
            System.out.println("[" + this.player.getName() + "]");
            System.out.println("What happened on that day?");
            System.out.println("[Granny]");
            System.out.println("Oh, my dear. It was a dark horrible day of our village. The Mystic King, he wanted to exterminate humankind. \n" +
                               "However, this little Mystics, alongside with Ravi, they defeated the Mystic King and brought peace to the world.");
            System.out.println("[" + this.player.getName() + "]");
            System.out.println("Who's Ravi?");
            System.out.println("[Granny]");
            System.out.println("He was legend. He was just like you, came from a strange world and save our world from destruction.\n" +
                                "But then, he disappeared with no reason. No one knows where he went.");
            System.out.println("[" + this.player.getName() + "]");
            System.out.println("Did you just say he came from another wolrd like me? ");
            System.out.println("[Granny]");
            System.out.println("Yes, he came from the Earth or whatever he said it was. \n" +
                                "There was a rumor that the last time people saw him was in Steaming Mountain, the highest volcano on the West of Joensu.");
            System.out.println("[" + this.yourMys.getName() + "]");        
            System.out.println("The bridge leadding to volcano has been repaired. Let's go there!");
            this.SteamingMt.setAvai(true);
            this.sacredSub.setAvai(true);
            this.SacredStone.setAvai(true);
            System.out.println("[Granny]");
            System.out.println("Oh, please hold on my dear. I have to give you one thing. Here, take this backpack, it is bigger than that leather bag.");
            System.out.println("[RECEIVE BACKPACK]");
            this.bag.setName("backpack");
            this.bag.setCapacity(5);
            this.granny.setState(1);
        } else if (this.granny.getState() == 1) {
            System.out.println("");
            System.out.println("[Granny]");
            System.out.println("People said that he was at Steaming Mountain before vanishing. You should check that place!");
        }
    }
    
    public void BlackSmith()
    {
        if (this.blacksmith.getState() == 0)
        {
            this.readState("story/chapter1/blacksmith/state0.txt", this.player.getName(), this.yourMys.getName());
            this.blacksmith.setState(1);
        }
        else if (this.blacksmith.getState() == 1)
        {
            this.readState("story/chapter1/blacksmith/state1.txt", this.player.getName(), this.yourMys.getName());
        }
        else if (this.blacksmith.getState() == 2)
        {
            this.readState("story/chapter1/blacksmith/state2.txt", this.player.getName(), this.yourMys.getName());
            this.bag.setCapacity(2);
            this.blacksmith.setState(3);
        }
        else
            System.out.println("asdasdasd");
    }
    
    public void Merchant()
    {
        if (this.merchant.getState() == 0)
        {
            this.readState("story/chapter1/merchant/state0.txt", this.player.getName(), this.yourMys.getName());
            this.merchant.setState(1);
        }
        else if (this.merchant.getState() == 1)
        {
            this.readState("story/chapter1/merchant/state1.txt", this.player.getName(), this.yourMys.getName());
        }
    }
    
    public void Mystic()
    {
       this.readState("story/chapter1/mystic/mystic.txt", this.player.getName(), this.yourMys.getName());
    }
    
    public void Sorcerer()
    {
         if (this.sorcerer.getState() == 0)
        {
            this.readState("story/chapter1/sorcerer/sorcerer.txt", this.player.getName(), this.yourMys.getName());
            this.sorcerer.setState(1);
        }
        else if (this.sorcerer.getState() == 1)
        {
            System.out.println("");
            System.out.println("[Sorcerer]");
            System.out.println("Now, there is no place available. Maybe another time then");
        }
    }
    
    public void DarkForest()
    {
        while (this.player.getCurrentLoca() == this.DarkForest) {
            switch (this.forestState) {
                case 0:
                    this.seek = this.readFile("story/chapter1/darkforest/state0.txt", this.player.getName(), this.yourMys.getName(), 0);
                    System.out.println("");
                    if(!this.Fight(ahihi))
                    {
                        this.player.setLoca(HomeTown);
                        System.out.println("Go back to Joensu Towm");
                        break;
                    }
                    this.seek = this.readFile("story/chapter1/darkforest/state0.txt", this.player.getName(), this.yourMys.getName(), this.seek);
                    this.blacksmith.setState(2);
                    this.forestState = 1;
                    this.input=this.Input();
                    if (this.input.contains("north") || this.input.contains("south") || this.input.contains("east") || this.input.contains("west")) {
                        this.checkLocaAvai(this.input);
                    } else {
                        System.out.println("[" + this.player.getName() + "]");
                        System.out.println("I don't understand what you mean");
                    }
                    break;
                case 1:
                    System.out.println("[DARK FOREST]");
                    System.out.println("Sites: Graveyard, Temple");
                    System.out.println("There is a few ghost monsters here, you can type \"go or wander around\" to fight them.");
                    while (this.player.getCurrentLoca() == DarkForest)
                    {
                        this.input=this.Input();
                        if (this.input.contains("north") || this.input.contains("east") || this.input.contains("west"))                      
                        {
                           System.out.println("[" + this.player.getName() + "]");
                           System.out.println("I cannot go further.");
                        }
                        else if (this.input.contains("graveyard"))
                        {
                            this.Graveyard();
                            if (this.player.getCurrentLoca() != HomeTown)
                            {
                                System.out.println("[DARK FOREST]");
                                System.out.println("Sites: Graveyard, Temple");
                                System.out.println("There is a few ghost monsters here, you can type \"go or wander around\" to fight them.");
                            }
                            else
                            {
                                System.out.println("[Back To Joensu Town]");
                                System.out.println("");
                            }
                        }
                        else if (this.input.contains("temple"))
                        {
                            this.Temple();
                            if (this.player.getCurrentLoca() != HomeTown)
                            {
                                System.out.println("[DARK FOREST]");
                                System.out.println("Sites: Graveyard, Temple");
                                System.out.println("There is a few ghost monsters here, you can type \"go or wander around\" to fight them.");
                            }
                            else
                            {
                                System.out.println("[Back To Joensu Town]");
                                System.out.println("");
                            }
                        }
                        else if (this.input.contains("south") || this.input.contains("back"))
                        {
                            this.player.nextLoca(this.input);
                            System.out.println("[Back to Joensu Town]");
                        }
                        else if (this.input.contains("wander") || this.input.contains("around"))
                            this.RandomMonster();
                        else
                        {
                            System.out.println("[" + this.player.getName() + "]");
                            System.out.println("I dont' understand what you mean...");
                        }
                    }
                    break;

            }
        }
    }
    
    public void Graveyard()
    {
        System.out.println("[Graveyard]");
        System.out.println("In the middle is tomb leading somewhere, maybe the underground");
        System.out.println("There is also bones of some kind of animal");
        System.out.println("There is a few ghost monsters here, you can type \"go or wander around\" to fight them.");
        this.input=this.Input();
        while (!this.input.contains("dark forest") && !this.input.contains("back"))
        {
            if (this.input.contains("tomb"))
            {
                this.Tomb();
                if (this.player.getCurrentLoca() != HomeTown)
                {    
                    System.out.println("[Graveyard]");
                    System.out.println("In the middle is tomb leading somewhere, maybe the underground");
                    System.out.println("There is also bones of some kind of animal");
                    System.out.println("There is a few ghost monsters here, you can type \"go or wander around\" to fight them.");
                }
            }
            else if (this.input.contains("bone"))
            {
                this.Bone();
                System.out.println("[Graveyard]");
                System.out.println("In the middle is tomb leading somewhere, maybe the underground");
                System.out.println("There is also bones of some kind of animal");
                System.out.println("There is a few ghost monsters here, you can type \"go or wander around\" to fight them.");
            }
            else if (this.input.contains("north") || this.input.contains("east") || this.input.contains("west") || this.input.equals("south"))                                            
            {
                System.out.println("[" + this.player.getName() + "]");
                System.out.println("I cannot go further. You can go back to the Dark Forest");
            }
            else if (this.input.contains("wander") || this.input.contains("around"))
                this.RandomMonster();
            else if (this.input.contains("back") || this.input.contains("dark forest"))
                this.DarkForest();
            else
            {
                System.out.println("[" + this.player.getName() + "]");
                System.out.println("I don't understand what you mean...");
            }
            if (this.player.getCurrentLoca() == HomeTown)
                break;
            this.input=this.Input();
        }    
    }
        
    public void Tomb()
    {
        System.out.println("[TOMB]");
        System.out.println("The door of the tomb is locked");
        System.out.println("There is a few ghost monsters here, you can type \"go or wander around\" to fight them.");
        this.input = this.Input();
        while (!this.input.contains("graveyard") && !this.input.contains("back"))
        {
            if (this.input.contains("key"))
            {
                if (this.bag.checkItem("Key"))
                {
                    System.out.println("[Used Key - Door Unlocked]");
                    this.bag.removeItem("Key");
                    if(!this.Fight(ghost))
                    {
                        this.player.setLoca(HomeTown);
                        System.out.println("Go back to Joensu Towm");
                        break;
                    }
                    if (this.ghost.getAlive() == false)
                    {
                        this.bag.addItem(new Item("Dark Stone"));
                        System.out.println("[RECEIVED DARK STONE]");
                    }
                }
                else
                {
                    System.out.println("[" + this.player.getName() + "]");
                    System.out.println("I haven't got the key yet.");
                }
            }
            else if (this.input.contains("wander") || this.input.contains("around")) 
            {
                this.RandomMonster();
            }
            else if (this.input.contains("north") || this.input.contains("east") || this.input.contains("west") || this.input.contains("south"))                      
            {
                System.out.println("[" + this.player.getName() + "]");
                System.out.println("I cannot go further. You can go back to the graveyard.");
            }
            else
            {
                System.out.println("[" + this.player.getName() + "]");
                System.out.println("I dont' understand what you mean...");
            }
            this.input = this.Input();
        }
        System.out.println("[Back to Graveyard]");
    }
    
    public void Bone()
    {
        System.out.println("[BONES OF SOME CREATURE]");
        System.out.println("There is a key in those bones");
        this.input = this.Input();
        while (!this.input.contains("graveyard") && !this.input.contains("back"))
        {
            if (this.input.contains("key"))
            {
                System.out.println("[RECEIVED KEY]");
                this.bag.addItem(new Item("Key", 1));
            }
            else if (this.input.contains("wander") || this.input.contains("around")) 
                System.out.println("There is no monster here to fight.");
            else if (this.input.contains("bone"))
                System.out.println("There is a key in those bones");
            else if (this.input.contains("north") || this.input.contains("east") || this.input.contains("west") || this.input.contains("south"))                      
            {
                System.out.println("[" + this.player.getName() + "]");
                System.out.println("I cannot go further. You can go back to the graveyard.");
            }
            else
            {
                System.out.println("[" + this.player.getName() + "]");
                System.out.println("I dont' understand what you mean...");
            }
            this.input = this.Input();
        }
        System.out.println("[Back to Graveyard]");
    }
    
    public void Temple()
    {
        switch (this.templeState) {
            case 0:
                System.out.println("[TEMPLE]");
                System.out.println("There is a strange hole on the wall");
                System.out.println("There a few monsters around here, you can go around to fight them.");
                this.input=this.Input();
                while (!this.input.contains("dark forest") && !this.input.contains("back"))
                {
                    if (this.input.contains("dark stone"))
                    {
                        if (this.bag.checkItem("Dark Stone"))
                        {
                            System.out.println("(Used Dark Stone)");
                            this.bag.removeItem("Dark Stone");
                            if (!this.Fight(bossC1))
                            {
                                this.player.setLoca(HomeTown);
                                System.out.println("Go back to Joensu Towm");
                                break;
                            }
                        }
                        else
                            System.out.println("You have not got it yet!");
                        if(this.bossC1.getAlive() == false)
                        {
                            System.out.println("");
                            System.out.println("[" + this.yourMys.getName() + "]");
                            System.out.println("Well done, you have defeated Krei.");
                            System.out.println("Let's go back to Joensu Town and talk to Granny");
                            this.templeState=1;
                            this.granny.setState(1);
                            this.player.setLoca(HomeTown); 
                            break;
                        }
                    }
                    else if (this.input.contains("north") || this.input.contains("east") || this.input.contains("west") || this.input.equals("south"))                      
                    {
                        System.out.println("[" + this.player.getName() + "]");
                        System.out.println("I cannot go further. You can go back to the Dark Forest");
                    }
                     else if (this.input.contains("wander") || this.input.contains("around")) 
                    {
                        this.RandomMonster();
                    }
                    else 
                        System.out.println("You should put something into that hole. Find it");
                    this.input=this.Input();
                }
                System.out.println("[Back to Dark Forest]");
                break;
            case 1:  
                System.out.println("[TEMPLE]");
                System.out.println("There is a few monsters around here, you can go around to fight them.");
                if (this.input.contains("wander") || this.input.contains("around")) {
                    this.RandomMonster();
                } else if (this.input.contains("back") || this.input.contains("forest")) {
                    this.DarkForest();
                } else {
                    System.out.println("[" + this.player.getName() + "]");
                    System.out.println("I dont' understand what you mean...");
                }
                break;
        }      
    }
    
   
     //CHAPTER 2   
    public void Chapter2()
    {
        
        while(this.bossC2.getAlive() == true) 
        {
            String a = this.player.getCurrentLoca().getName();

            switch(a) { 
                case "Joensu Town": //In town
                    this.joensuTown(); break;
                case "Dark Forest":
                    this.DarkForest(); break;
                case "Sacred Stone sub-place":
                    this.sacredSub(); break;
                case "Sacred Stone":
                    this.sacredStone(); break;
                case "Steaming Mountain":
                    this.steamingMt(); break;
                default: break;
            }
        } 
    }
    
    public void sacredSub()
    {
        switch(this.sacredSubState) {
            case 0:
                System.out.println("[" + this.player.getName() + "]");
                System.out.println("Look, there's an riparian road heading north. Should I check it out?");
                System.out.println("[" + this.yourMys.getName() + "]");
                System.out.println("Just the sacred stone up there. It's up to you");
                System.out.println("");
                this.input = this.Input();
                while (this.player.getCurrentLoca() == this.sacredSub) {
                    if (this.input.contains("north") || this.input.contains("east") || this.input.contains("west") || this.input.contains("south")) { 
                            this.checkLocaAvai(input);
                    } else {
                        System.out.println("[" + this.player.getName() + "]");
                        System.out.println("I don't understand what you mean.");
                        System.out.println("You should choose a direction to go!");
                        System.out.println("");
                        this.input = this.Input();
                    }
                }
                this.sacredSubState = 1;
                break;
            default:
                System.out.println("[" + this.yourMys.getName() + "]");
                System.out.println("Where do you wanna go?");
                System.out.println("Head north to the Sacred Stone or ...");
                System.out.println("Continue west to the Steaming Mountain");
                System.out.println("");
                this.input = this.Input();
                while (this.player.getCurrentLoca() == this.sacredSub) {
                    if (this.input.contains("north") || this.input.contains("east") || this.input.contains("west") || this.input.contains("south")) { 
                        this.checkLocaAvai(input);
                    } else {
                        System.out.println("[" + this.player.getName() + "]");
                        System.out.println("I don't understand what you mean.");
                        System.out.println("You should choose a direction to go!");
                        System.out.println("");
                        this.input = this.Input();
                    }
                }
        }
        
    }
    
    public void sacredStone()
    {
        switch (this.sacredState) {
            case 0:
                System.out.println("[" + this.player.getName() + "]");
                System.out.println("This sacred stone is huge. I wonder what it is for?");
                System.out.println("[" + this.yourMys.getName() + "]");
                System.out.println("People use it to hold some kind of rituals in special occassions. Nothing to do here now, we should go back.");
                System.out.println("");
                this.input = this.Input();
                while (this.player.getCurrentLoca() == this.SacredStone) {
                    if (this.input.contains("north") || this.input.contains("east") || this.input.contains("west") || this.input.contains("south")) { 
                        this.checkLocaAvai(input);
                    } else {
                        System.out.println("[" + this.player.getName() + "]");
                        System.out.println("I don't understand what you mean.");
                        System.out.println("We should go back!");
                        System.out.println("");
                        this.input = this.Input();
                    }
                }
                this.sacredState = 1;
                break;
            default:
                System.out.println("[" + this.yourMys.getName() + "]");
                System.out.println("People use it to hold some kind of rituals in special occassions.");
                System.out.println("There is a few grass and rock monsters here, you can go around to fight them.");
                System.out.println("");
                while (this.player.getCurrentLoca() == this.SacredStone) {
                    this.input = this.Input();
                    if (this.input.contains("wander") || this.input.contains("around")) {
                        this.RandomMonster();
                    } else if (this.input.contains("north") || this.input.contains("east") || this.input.contains("west") || this.input.contains("south")) { 
                        this.checkLocaAvai(input);
                    } else {
                        System.out.println("[" + this.player.getName() + "]");
                        System.out.println("I dont' understand what you mean...");
                    }
                }           
                break;
        } 
    }
    
    public void steamingMt() 
    {
        switch (this.stMountainState) {
            case 0:
                this.readState("story/chapter2/steamingMountain/stage0.txt", this.player.getName(), this.yourMys.getName());
                System.out.println("");
                while (this.player.getCurrentLoca() == this.SteamingMt) {
                    this.input=this.Input();
                    if (this.input.contains("north") || this.input.contains("south") || this.input.contains("east") || this.input.contains("west")) {
                        this.checkLocaAvai(this.input);
                    } else if (this.input.contains("wander") || this.input.contains("around")) {
                        this.RandomMonster();
                    } else if (this.input.contains("pool")) {
                        this.SteamingPool();
                    } else if (this.input.contains("healing")) {
                        this.HealingCenter();
                    } else if (this.input.contains("shrine")) {
                        this.Shrine();
                    } else if (this.input.contains("cave")) {
                        this.SteamingMoutainCave();
                    } else if (this.input.contains("tower")) {
                        this.RockTower();
                    } else {
                        System.out.println("[" + this.player.getName() + "]");
                        System.out.println("I dont' understand what you mean...");
                        System.out.println("You should type place's name to go to!");
                        System.out.println("");
                    }
                }
                this.stMountainState = 1;
                break;
            case 1:
                System.out.println("[STEAMING MOUNTAIN]");
                System.out.println("Sites: Steaming Pool, Shrine, Healing Center, Cave");
                System.out.println("There is a few fire and rock monsters here, you can type \"go or wander around\" to fight them.");
                while (this.player.getCurrentLoca() == this.SteamingMt) {
                    this.input=this.Input();
                    if (this.input.contains("north") || this.input.contains("south") || this.input.contains("east") || this.input.contains("west")) {
                        this.checkLocaAvai(this.input);
                    } else if (this.input.contains("wander") || this.input.contains("around")) {
                        this.RandomMonster();
                    } else if (this.input.contains("pool")) {
                        this.SteamingPool();
                    } else if (this.input.contains("healing")) {
                        this.HealingCenter();
                    } else if (this.input.contains("shrine")) {
                        this.Shrine();
                    } else if (this.input.contains("cave")) {
                        this.SteamingMoutainCave();
                    } else if (this.input.contains("tower")) {
                        this.RockTower();
                    } else {
                        System.out.println("[" + this.player.getName() + "]");
                        System.out.println("I dont' understand what you mean...");
                        System.out.println("You should type place's name to go to!");
                        System.out.println("");
                    }
                }
                break;
        }
    }
    
    public void SteamingPool() 
    {
        switch (this.stPoolState) {
            case 0:
                System.out.println("[Dennis]");
                System.out.println("Welcome to this pool. Please, please, soak yourself in this pool and relax. You will feel your mind is releasing.");
                System.out.println("[y/n]?");
                System.out.println("");
                this.input = this.Input();
                if (this.input.contains("y") || this.input.contains("yes")) {
                    System.out.println("[Dennis]");
                    System.out.println("Thank you, thank you. Please, please keep this Potion, it will help you solve one puzzle in battle. ");
                    System.out.println("[RECEIVED " + this.potion.getName() + "]");
                    System.out.println("");
                    this.potion.setQuantity(1);
                    this.bag.addItem(potion);
                    this.stPoolState = 1;
                } else {
                    System.out.println("[Dennis]");
                    System.out.println("See you next time!");
                    System.out.println("");
                }
                break;
            default:
                System.out.println("[Dennis]");
                System.out.println("Welcome to this pool. Please, please, soak yourself in this pool and relax. You will feel your mind is releasing.");
                System.out.println("[y/n]?");
                System.out.println("");
                this.input = this.Input();
                if (this.input.contains("y") || this.input.contains("yes")) {
                    System.out.println("[" + this.player.getName() + "]");
                    System.out.println("My mind is released and my body feels relaxed");
                    System.out.println("");
                } else {
                    System.out.println("[Dennis]");
                    System.out.println("See you next time!");
                    System.out.println("");
                }
                break;
        }
    }
    
    public void Shrine() 
    {
        switch (this.shrineState) {
            case 0:
                System.out.println("[Monk Parrish]");
                System.out.println("You want to know about the legend hero. Not much do I know but since the fight between our hero and the Mystic King, \n" +
                                   "the volcano has erupted unusually. There must be something wrong with it. Please help us to find out.\n" +
                                   "By the way, this will help you to discover the Cave.");
                System.out.println("[RECEIVE TORCH]");
                System.out.println("");
                this.torch.setQuantity(1);
                this.bag.addItem(torch);
                this.shrineState = 1;
                break;
            case 1:
                System.out.println("[Monk Parrish]");
                System.out.println("You want to know about the legend hero. Not much do I know but since the fight between our hero and the Mystic King, \n" +
                                   "the volcano has erupted unusually. There must be something wrong with it. Please help us to find out.");
                System.out.println("");
                break;
            default:
                System.out.println("[Monk Parrish]");
                System.out.println("Thank you for saving us from the eruption. We are really grateful for what you did.");
                System.out.println("");
                break;
        }
    }
    
    public void SteamingMoutainCave() 
    {
        switch (this.stCave) {
            case 0:
                System.out.println("[" + this.player.getName() + "]");
                System.out.println("This cave is huge. It leads to the top of the volcano, right?");
                System.out.println("[" + this.yourMys.getName() + "]");
                System.out.println("Yes, we should figure out what is happening to this volcano?");
                System.out.println("");
                this.input = this.Input();
                while (!this.torchOn) {
                    if ((this.input.contains("torch") || this.input.contains("light")) && this.bag.checkItem("torch")) {
                            this.torchOn = true;
                            System.out.println("[" + this.player.getName() + "]");
                            System.out.println("It's better now. You should type \"north\", \"south\", \"east\", \"west\" to go around the cave. Type \"exit\" to exit the cave.");
                            System.out.println("You may encouter some rock monsters here.");
                    } else {
                        System.out.println("[" + this.player.getName() + "]");
                        System.out.println("It's too dark inside. I can't see anything");
                        this.input = this.Input();
                    }
                }
                while (this.torchOn) {
                    System.out.println("[" + this.yourMys.getName() + "]");
                    System.out.println("There's somthing written on the wall");
                    this.input = this.Input();
                    while (this.input.contains("check") || this.input.contains("read") || this.input.contains("look") || this.input.contains("examine")) {
                        System.out.println("Kill the bees in the NEBS, you will find where to go");
                    }
                }
        }
    }
    
    public void Maze()
    {
        boolean exit = false;
        System.out.println("You entered a MAZE");
        while (!exit)
        {
            this.input = this.Input();
            if (this.input.contains("north"))
            {
                System.out.println("[" + this.player.getName() + "]");
                System.out.println("Hmm..It seems the way out..");
                this.input = this.Input();
                if (this.input.contains("east"))
                {
                    System.out.println("[" + this.player.getName() + "]");
                    System.out.println("Hmm..It seems the way out..");
                    this.input = this.Input();
                    if (this.input.contains("south"))
                    {
                        System.out.println("You have escaped the MAZE");
                        exit = true;
                    }
                    else
                        System.out.println("You go further in the MAZE");
                }
                else
                    System.out.println("You go further in the MAZE");
            }
            else if (this.input.contains("east") || this.input.contains("south") || this.input.contains("west"))
                System.out.println("You go further in the MAZE");
            else
                System.out.println("I don't understand what you mean...");
        }
    }
    
    public void RockTower()
    {
        System.out.println("[" + this.yourMys.getName() + "]");
        System.out.println("This Tower seems to be pretty ancient. We should not enter.");
    }
    
    public void HealingCenter()
    {
        System.out.println("To be constructed in game version 2.0");
    }
}  