/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mindgames;

import games.Checkers;
import games.Chess;
import games.Go;
import games.IGame;

/**
 *
 * @author gpalomox
 */
public class MindGames {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {        
        
        IGame newGame;
        
        //Chess Game
        newGame = new Chess();
        newGame.drawBoard();
        //Checkers Game
        newGame = new Checkers();
        newGame.drawBoard();
        //Go Game
        newGame = new Go();
        newGame.drawBoard();
    }
    
}
