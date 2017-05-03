/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package games.List;

import games.Interfaces.IGame;
import games.Board.Board;
import games.Interfaces.IPiece;

/**
 *
 * @author gpalomox
 */
public class Chess implements IGame{
    
    Board chessBoard;

    @Override
    public void createGame() {
        
    }

    @Override
    public void saveGame() {
        
    }

    @Override
    public void loadGame() {
        
    }

    @Override
    public void checkMove(IPiece pPice) {
        
    }

    @Override
    public void printGame() {
        
    }    

    @Override
    public void drawBoard() {
        
        chessBoard = new Board(8);
    }

    @Override
    public void addPice() {
        
    }
    
    
}
