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
import games.Pieces.Stone;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nelson
 */
public class Go implements IGame{

    private PieceColor turn = PieceColor.BLACK;
    private IPiece[][] board = new IPiece[9][9];
    
    public PieceColor GetTurn(){
        return turn;
    }
    
    public Go(){
        
    }
    
    @Override
    public void createGame() {
        try{
            
            
    /*        for(int i=0 ; i<=4; i++){
               for (int j = 0; j <=8; j++) {  
                  board[i][j] = new Stone(i+1, PieceColor.BLACK, 0, 0);
               }
            }
        
            
            
           for(int i=4 ; i<=8; i++){
                
               for (int j = 0; j <=8; j++) {
                   board[i][j] = new Stone(i+1, PieceColor.WHITE, 8, 8);
               }
           }*/
      
        }catch(Exception ex){
            throw ex;
        }
    }

    @Override
    public void saveGame(String user1, String user2) {
        
         try {
            String filePath = "src\\Data\\Database\\Go\\" + user1 + "_" + user2 + ".sgf";
            String data = "";

            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    IPiece piece = board[i][j];

                    if (piece == null) {
                        continue;
                    }

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
         try {
            DataHandler dataHandler = new DataHandler();

            String data = dataHandler.readFile("src\\Data\\Database\\Go\\" + pFileName);

            String[] piecesData = data.split("|");

            board = new IPiece[8][8];

            for (int i = 0; i < piecesData.length; i++) {
                String[] pieceInfo = piecesData[i].split("°");

                PieceColor color = PieceColor.valueOf(pieceInfo[1]);
                int x = Integer.parseInt(pieceInfo[2]);
                int y = Integer.parseInt(pieceInfo[3]);

                board[y][x] = new Stone(Integer.parseInt(pieceInfo[0]), color, x, y);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
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
        
        String topLeft="\u2689";
        String topRight="\u2687";
        String output = "| \t|| 0 \t|| 1 \t|| 2 \t|| 3 \t|| 4 \t|| 5 \t|| 6 \t|| 7 \t|| 8 \t||";
        output += "\n";
        output += "--------"+topLeft+"-------"+topRight+"-------"+topLeft+"------"+topRight+"-------"+topRight+"------"+topRight+"-------"+topLeft+"-------"+topLeft+"-------"+topRight+"--------";
        output += "\n";
        String blackSymbol = "\u2689";
        String whiteSymbol = "\u2687";
        for (int row=1; row < board.length; row++)
        {
            output += "|"+ row + "\t|"; 
            
            for (int col=0; col < board[row].length; col++)
            {
                IPiece piece = board[row][col];
                
                if(piece == null){
                    output += "|\t|";
                }else{
                    if(piece.GetColor() == PieceColor.WHITE){
                        output += "|"+whiteSymbol+"\t|";
                    }else{
                        output += "|"+blackSymbol +"\t|";
                    }
                }
                
            }
            output += "\n";
            output += "--------"+topLeft+"-------"+topRight+"-------"+topRight+"------"+topRight+"-------"+topLeft+"------"+topRight+"-------"+topLeft+"-------"+topRight+"-------"+topRight+"--------";
            output += "\n";
        }
        
        return output;
    }
}
