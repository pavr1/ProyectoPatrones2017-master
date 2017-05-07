/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package games.List;

import games.Enumerations.PieceColor;
import games.Interfaces.IGame;
import games.Interfaces.IPiece;
import games.Pieces.CheckersPiece;

/**
 *
 * @author gpalomox
 */
public class Checkers implements IGame{  
    private PieceColor turn = PieceColor.BLACK;
    private IPiece[][] board = new IPiece[8][8];
    
    public PieceColor GetTurn(){
        return turn;
    }
    
    public Checkers(){
        
    }
    
    @Override
    public void createGame() {
        try{
           board[0][1] = new CheckersPiece(1, PieceColor.BLACK, 0, 1);
           board[0][3] = new CheckersPiece(2, PieceColor.BLACK, 0, 3);
           board[0][5] = new CheckersPiece(3, PieceColor.BLACK, 0, 5);
           board[0][7] = new CheckersPiece(4, PieceColor.BLACK, 0, 7);
           
           board[1][0] = new CheckersPiece(5, PieceColor.BLACK, 1, 0);
           board[1][2] = new CheckersPiece(6, PieceColor.BLACK, 1, 2);
           board[1][4] = new CheckersPiece(7, PieceColor.BLACK, 1, 4);
           board[1][6] = new CheckersPiece(8, PieceColor.BLACK, 1, 6);

           board[2][1] = new CheckersPiece(9, PieceColor.BLACK, 2, 1);
           board[2][3] = new CheckersPiece(10, PieceColor.BLACK, 2, 3);
           board[2][5] = new CheckersPiece(11, PieceColor.BLACK, 2, 5);
           board[2][7] = new CheckersPiece(12, PieceColor.BLACK, 2, 7);

           board[5][0] = new CheckersPiece(13, PieceColor.WHITE, 5, 0);
           board[5][2] = new CheckersPiece(14, PieceColor.WHITE, 5, 2);
           board[5][4] = new CheckersPiece(15, PieceColor.WHITE, 5, 4);
           board[5][6] = new CheckersPiece(26, PieceColor.WHITE, 5, 6);
           
           board[6][1] = new CheckersPiece(17, PieceColor.WHITE, 6, 1);
           board[6][3] = new CheckersPiece(18, PieceColor.WHITE, 6, 3);
           board[6][5] = new CheckersPiece(19, PieceColor.WHITE, 6, 5);
           board[6][7] = new CheckersPiece(20, PieceColor.WHITE, 6, 7);
           
           board[7][0] = new CheckersPiece(21, PieceColor.WHITE, 7, 0);
           board[7][2] = new CheckersPiece(22, PieceColor.WHITE, 7, 2);
           board[7][4] = new CheckersPiece(23, PieceColor.WHITE, 7, 4);
           board[7][6] = new CheckersPiece(24, PieceColor.WHITE, 7, 6);
        }catch(Exception ex){
            throw ex;
        }
    }

    @Override
    public void saveGame(String user1, String user2) {
    }

    @Override
    public void loadGame(String pFileName) {
        
    }

    @Override
    public String makeMove(PieceColor pTurn, int pSourceX, int pSourceY, int pTargetX, int pTargetY) {
        try{
            IPiece piece = board[pSourceX][pSourceY];
            
            if(piece.GetColor() != pTurn){
                return "Turno Incorrecto!";
            }
            
            if(piece == null){                
                return "Pieza no encontrada!";
            }
            
            Boolean isValidMovement = piece.isValid(pTargetX, pTargetY);
            
            if(!isValidMovement){
                return "Movimiento no permitido!";
            }
            
            if(board[pTargetX][pTargetY] != null){
                return "Posición Ocupada!";
            }
            
            board[pSourceX][pSourceY] = null;
            board[pTargetX][pTargetY] = piece;
            
            if(pTurn == PieceColor.BLACK){
                turn = PieceColor.WHITE;
            }else{
                turn = PieceColor.BLACK;
            }
            
            return "";
        }catch(Exception ex){
            throw ex;
        }
    }

    @Override
    public String printGame() {
        String output = "|\t \t||\t 0 \t||\t 1 \t||\t 2 \t||\t 3 \t||\t 4 \t||\t 5 \t||\t 6 \t||\t 7 \t|";
        output += "\n";
        output += "-------------------------------------------------------------------------------------------------------------------------------------------------";
        output += "\n";
        
        for (int row=0; row < board.length; row++)
        {
            output += "|\t"+ row + "\t|"; 
            
            for (int col=0; col < board[row].length; col++)
            {
                IPiece piece = board[row][col];
                
                if(piece == null){
                    output += "|\t\t|";
                }else{
                    if(piece.GetColor() == PieceColor.WHITE){
                        output += "|\t○\t|";
                    }else{
                        output += "|\t●\t|";
                    }
                }
                
            }
            output += "\n";
            output += "-------------------------------------------------------------------------------------------------------------------------------------------------";
            output += "\n";
        }
        
        return output;
    }

    /*@Override
    public void drawBoard() {
        
    }

    @Override
    public void addPice() {
        
    }*/
}
