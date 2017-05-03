/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package games.Pieces;

import games.Interfaces.IPiece;
import games.Board.Board;

/**
 *
 * @author gpalomox
 */
public class Rock implements IPiece{

    @Override
    public void move(int pLocation, int pDestination) {
        
    }

    @Override
    public boolean isAvailable() {
        return false;
    }

    @Override
    public boolean isValid(Board board, int fromX, int fromY, int toX, int toY) {
        return false;
    }
    
}
