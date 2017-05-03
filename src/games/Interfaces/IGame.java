/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package games.Interfaces;

/**
 *
 * @author gpalomox
 * 20 checkers 10x10
 * 16 1king 1 queen 2rock 2 knights 2 bishops 8 pawns 8x8
 * 19 x 19 Go N Stones
 */
public interface IGame {
    

    public abstract void createGame();
    public abstract void saveGame();
    public abstract void loadGame();
    public abstract void checkMove(IPiece pPice);
    public abstract void printGame();
    public abstract void drawBoard();
    public abstract void addPice();
    
}
