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
public class MysticWorld implements Serializable{

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        // TODO code application logic here
        GameStart game = new GameStart();
        game.startGame();
    }
}
