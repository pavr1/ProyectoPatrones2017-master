/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Factory.GameFactory;
import games.Enumerations.GameTypes;
import java.io.BufferedReader;
import java.io.InputStreamReader;


/**
 *
 * @author gpalomox
 */
public class MindGames {

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {        
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        GameFactory newGame = new GameFactory();
        String opt = "";
        newGame.args=args;
        
        do{
        System.out.println("Chose an action for the game");
        System.out.println("1 New Chess Game");
        System.out.println("2 Checkers Game");
        System.out.println("3 GO Game");
        System.out.println("4 Exit");
        opt = in.readLine();
        switch(opt){
            case "1":
                newGame.CreateGame(GameTypes.CHESS);
                break;
            case "2":
                newGame.CreateGame(GameTypes.CHECKERS);
                break;
            case "3":
                newGame.CreateGame(GameTypes.GO);
                break;
            case "4":
                System.exit(0);
                break;
            default:System.out.println("Not a valid option\n");
        }
        }while(!"4".equals(opt)); 
        
        

    }
    
}