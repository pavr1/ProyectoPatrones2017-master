/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package games.Board;

import games.Interfaces.IPiece;

/**
 *
 * @author gpalomox
 */
public class BoardPosition {
    int x;
    int y;
    IPiece piece;

    public BoardPosition(int x, int y) {
        super();
        this.x = x;
        this.y = y;
        piece = null;
    }

    public void occupySpot(IPiece piece){
        //if piece already here, delete it, i. e. set it dead
        if(this.piece != null)
            this.piece.isAvailable();
        //place piece here
        this.piece = piece;
    }

    public boolean isOccupied() {
        return piece != null;
    }

    public IPiece releaseSpot() {
        IPiece releasedPiece = this.piece;
        this.piece = null;
        return releasedPiece;
    }

}
