/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Data.DataHandler;
import games.List.Checkers;

/**
 *
 * @author gpalomox
 */
public class MindGames {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)throws Exception {     
        
        DataHandler dt=new DataHandler();
        dt.writeFile("Jose.sgf","kikiki");
         //dt.writeFile("Jose.sgf");
        //dt.readFile("Jse.sgf",new Checkers());
         // dt.readFile("Jose.sgf");
        
       /* IGame newGame;
        
        //Chess Game
        newGame = new Chess();
        newGame.drawBoard();
        //Checkers Game
        newGame = new Checkers();
        newGame.drawBoard();
        //Go Game
        newGame = new Go();
        newGame.drawBoard();*/
    }
    
}
