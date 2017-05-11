/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package games.Pieces;

import games.Enumerations.PieceColor;
import games.Interfaces.IPiece;

/**
 *
 * @author pavr1
 */
public class CheckersPiece implements IPiece{
    private int id = -1;
    private PieceColor color = PieceColor.WHITE;
    private int x = -1;
    private int y = -1;
    
    private int[][] whiteAvailableMoves = new int[4][4];
    private int[][] blackAvailableMoves = new int[4][4];
    
    public PieceColor GetColor(){
        return color;
    }
    
    public CheckersPiece(int pId, PieceColor pColor, int pX, int pY){
        id = pId;
        color = pColor;
        x = pX;
        y = pY;
        
        whiteAvailableMoves[0][0] = 1;
        whiteAvailableMoves[0][1] = -1;
        whiteAvailableMoves[1][0] = -1;
        whiteAvailableMoves[1][1] = -1;
        whiteAvailableMoves[2][0] = 2;
        whiteAvailableMoves[2][1] = -2;
        whiteAvailableMoves[3][0] = -2;
        whiteAvailableMoves[3][1] = -2;
        
        blackAvailableMoves[0][0] = 1;
        blackAvailableMoves[0][1] = 1;
        blackAvailableMoves[1][0] = -1;
        blackAvailableMoves[1][1] = 1;
        blackAvailableMoves[2][0] = 2;
        blackAvailableMoves[2][1] = 2;
        blackAvailableMoves[3][0] = -2;
        blackAvailableMoves[3][1] = 2;
    }
    
    /*
    @Override
    public void move(int pLocation, int pDestination) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public boolean isAvailable(int x, int y) {
        return false;
    }
*/
    @Override
    public boolean isValid(int pX, int pY) {
        int[][] list = null;
        
        if(color == PieceColor.WHITE){
            list = whiteAvailableMoves;
        }else{
            list = blackAvailableMoves;
        }
        
        for (int i = 0; i < list.length; i++) {
            int availableX = list[i][0];
            int availableY = list[i][1];
            
            if((pX == (x + availableX)) && (pY == (y + availableY)))
                return true;
        }
        
        return false;
    }

    @Override
    public void UpdateCoordinates(int pX, int pY) {
        x = pX;
        y = pY;
    }

    @Override
    public int GetID() {
        return id;
    }

    @Override
    public int GetX() {
        return x;
    }

    @Override
    public int GetY() {
        return y;
    }
    
}