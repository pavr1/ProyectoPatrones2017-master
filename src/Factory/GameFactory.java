/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Factory;

import games.Enumerations.GameTypes;
import games.Interfaces.IGame;
import games.List.Checkers;
import games.List.Chess;
import games.List.Go;

/**
 *
 * @author pavr1
 */
public class GameFactory {
    
    public IGame CreateGame(GameTypes pGameType) throws Exception{
        IGame game = null;
        
        switch(pGameType){
            case CHECKERS:
                game = new Checkers();
                break;
            case CHESS:
                game = new Chess();
                break;
            case GO:
                game = new Go();
                break;
            default:
                throw new Exception("Juego '" + pGameType + "' no soportado!");
        }
        
        return game;
    }
}
