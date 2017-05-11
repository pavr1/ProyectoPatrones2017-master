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
 * @author nelson
 */
public class Stone implements IPiece{

   
   private int id = -1;
    private PieceColor color = PieceColor.WHITE;
    private int x = -1;
    private int y = -1;
    
    
    private int[][] whiteAvailableMoves = new int[2][2];
    private int[][] blackAvailableMoves = new int[2][2];
    
      public Stone(int pId, PieceColor pColor, int px, int py){
        id = pId;
        color = pColor;
        x = px;
        y = py;
        
     /*   whiteAvailableMoves[0][0] = 1;
        whiteAvailableMoves[0][1] = -1;
        whiteAvailableMoves[1][0] = -1;
        whiteAvailableMoves[1][1] = -1;
        
        blackAvailableMoves[0][0] = 1;
        blackAvailableMoves[0][1] = 1;
        blackAvailableMoves[1][0] = -1;
        blackAvailableMoves[1][1] = 1;*/
    }
    
    public boolean isValid(int px, int py) {
        int[][] list = null;
    /*    
        if(color == PieceColor.WHITE){
            list = whiteAvailableMoves;
        }else{
            list = blackAvailableMoves;
        }
*/
        
        for (int i = 0; i < list.length; i++) {
            int availableX = list[i][0];
            int availableY = list[i][1];
            
            if((px == (x + availableX)) && (py == (y + availableY)))
                return true;
        }
        
        return false;
    }

    public PieceColor GetColor() {
       return color;
    }

    @Override
    public void UpdateCoordinates(int x, int y) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int GetID() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int GetX() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int GetY() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}