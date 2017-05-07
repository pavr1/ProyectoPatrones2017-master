/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package games.List;

import Data.DataHandler;
import games.Enumerations.PieceColor;
import games.Interfaces.IGame;
import games.Interfaces.IPiece;
import games.Pieces.CheckersPiece;
import java.util.logging.Level;
import java.util.logging.Logger;

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
           board[0][1] = new CheckersPiece(1, PieceColor.BLACK, 1, 0);
           board[0][3] = new CheckersPiece(2, PieceColor.BLACK, 3, 0);
           board[0][5] = new CheckersPiece(3, PieceColor.BLACK, 5, 0);
           board[0][7] = new CheckersPiece(4, PieceColor.BLACK, 7, 0);
           
           board[1][0] = new CheckersPiece(5, PieceColor.BLACK, 0, 1);
           board[1][2] = new CheckersPiece(6, PieceColor.BLACK, 2, 1);
           board[1][4] = new CheckersPiece(7, PieceColor.BLACK, 4, 1);
           board[1][6] = new CheckersPiece(8, PieceColor.BLACK, 6, 1);

           board[2][1] = new CheckersPiece(9, PieceColor.BLACK, 1, 2);
           board[2][3] = new CheckersPiece(10, PieceColor.BLACK, 3, 2);
           board[2][5] = new CheckersPiece(11, PieceColor.BLACK, 5, 2);
           board[2][7] = new CheckersPiece(12, PieceColor.BLACK, 7, 2);

           board[5][0] = new CheckersPiece(13, PieceColor.WHITE, 0, 5);
           board[5][2] = new CheckersPiece(14, PieceColor.WHITE, 2, 5);
           board[5][4] = new CheckersPiece(15, PieceColor.WHITE, 4, 5);
           board[5][6] = new CheckersPiece(26, PieceColor.WHITE, 6, 5);
           
           board[6][1] = new CheckersPiece(17, PieceColor.WHITE, 1, 6);
           board[6][3] = new CheckersPiece(18, PieceColor.WHITE, 3, 6);
           board[6][5] = new CheckersPiece(19, PieceColor.WHITE, 5, 6);
           board[6][7] = new CheckersPiece(20, PieceColor.WHITE, 7, 6);
           
           board[7][0] = new CheckersPiece(21, PieceColor.WHITE, 0, 7);
           board[7][2] = new CheckersPiece(22, PieceColor.WHITE, 2, 7);
           board[7][4] = new CheckersPiece(23, PieceColor.WHITE, 4, 7);
           board[7][6] = new CheckersPiece(24, PieceColor.WHITE, 6, 7);
        }catch(Exception ex){
            throw ex;
        }
    }

    @Override
    public void saveGame(String user1, String user2) {
        try {
            String filePath = "//src/Data.Database.Checkers/" + user1 + "_" + user2 + ".ext";
            String data = "";

            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    IPiece piece = board[i][j];

                    if(piece == null)
                        continue;

                    data += piece.GetID() + "°" + piece.GetColor().toString() + "°" + piece.GetX() + "°" + piece.GetY() + "|";
                }
            }

            DataHandler dataHandler = new DataHandler();
        
            dataHandler.writeFile(filePath, data);
        } catch (Exception ex) {
            Logger.getLogger(Checkers.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void loadGame(String pFileName) {
        try{
            DataHandler dataHandler = new DataHandler();
            //dataHandler.readFile(pFileName, this) => qué es ese IGame que se pasa por parámetro???
            String data = "";
            String[] piecesData = data.split("|");
            
            board = new IPiece[8][8];
            
            for (int i = 0; i < piecesData.length; i++) {
                String[] pieceInfo = piecesData[i].split("°");
                
                PieceColor color = PieceColor.valueOf(pieceInfo[1]);
                int x = Integer.parseInt(pieceInfo[2]);
                int y = Integer.parseInt(pieceInfo[3]);
                
                board[y][x] = new CheckersPiece(Integer.parseInt(pieceInfo[0]), color, x, y);   
            }
        }catch(Exception ex){
            throw ex;
        }
    }

    @Override
    public String makeMove(PieceColor pTurn, int pSourceX, int pSourceY, int pTargetX, int pTargetY) {
        try{
            IPiece piece = board[pSourceY][pSourceX];
            
            if(piece == null){                
                return "Advertencia: Pieza no encontrada!";
            }
            
            if(piece.GetColor() != pTurn){
                return "Advertencia: Turno Incorrecto!";
            }
            
            if(board[pTargetY][pTargetX] != null){
                return "Advertencia: Posición Ocupada!";
            }
                        
            Boolean isValidMovement = piece.isValid(pTargetX, pTargetY);
            
            if(!isValidMovement){
                return "Advertencia: Movimiento no permitido!";
            }
            
            String message = "";
            
            if(((pSourceX - pTargetX) == 2) || ((pSourceY - pTargetY) == 2)){
                int x = -1;
                int y = -1;
                
                if(pSourceX < pTargetX){
                    x = 1;
                }else{
                    x = -1;
                }
                
                if(pSourceY < pTargetY){
                    y = 1;
                }else{
                    y = -1;
                }
                
                if(board[pSourceY + y][pSourceX + x] == null){
                    return "Advertencia: No se puede hacer movimientos de 2 piezas sin comer una pieza enemiga!";
                }
                
                board[pSourceY + y][pSourceX + x] = null;
                
                message = "Felicidades, ha comido una pieza enemiga!";
            }
            
            piece.UpdateCoordinates(pTargetX, pTargetY);
            
            board[pSourceY][pSourceX] = null;
            board[pTargetY][pTargetX] = piece;
            
            if(pTurn == PieceColor.BLACK){
                turn = PieceColor.WHITE;
            }else{
                turn = PieceColor.BLACK;
            }
            
            return message;
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
