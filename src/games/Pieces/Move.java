/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package games.Pieces;

import games.List.Chess;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Gabo-PC
 */
public class Move {
    public int srcx, srcy, destx, desty;
    public boolean capture;  /* true if this was a capture move */

    public Move() {}

    public Move(int x0, int y0, int x1, int y1, boolean c) {
	srcx = x0;
	srcy = y0;  
	destx = x1;
	desty = y1;
	capture = c;
    }
    
    public Move(String s) {
	srcx = s.charAt(0) - 'a';
	srcy = s.charAt(1) - '1';
	destx = s.charAt(2) - 'a';
	desty = s.charAt(3) - '1';
    }

    public void copyMove(Move m) {
	srcx = m.srcx;
	srcy = m.srcy;
	destx = m.destx;
	desty = m.desty;
	capture = m.capture;	
    }

    public boolean equals(Move m) {
	return (m.srcx == srcx && m.srcy == srcy && m.destx == destx && m.desty == desty);
    }
    public Move randomMove(Chess board) {

	Random rand = new Random();
	List moveList = board.generateMoves();
	if (moveList.isEmpty()) return null;  // if the game is over, just return null

	Move [] moveArray = (Move[]) moveList.toArray(new Move[0]);
	Move [] captureArray = new Move[moveArray.length];
	int capCount = 0;
        for (Move moveArray1 : moveArray) {
            if (moveArray1.capture) {
                captureArray[capCount++] = moveArray1;
            }
        }
	if (capCount > 0) return captureArray[rand.nextInt(capCount)];
	return moveArray[rand.nextInt(moveArray.length)];
    }
    
    @Override
    public String toString() {
	return new String (new byte[] {
	    (byte) ('a'+srcx), (byte)('1'+srcy), (byte)('a'+destx), (byte)('1'+desty)});
    }
}
