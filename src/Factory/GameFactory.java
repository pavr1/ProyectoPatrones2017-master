/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Factory;

import games.Enumerations.GameTypes;
import games.Enumerations.PieceColor;
import games.Interfaces.IGame;
import games.List.Checkers;
import games.List.Chess;
import games.List.Go;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author pavr1
 */
public class GameFactory {
    public String[] args;
    
    public IGame CreateGame(GameTypes pGameType) throws Exception{
        IGame game = null;        
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));        
        String  optSub = null;        
        menuloop:
        switch(pGameType){
            case CHECKERS:                
                
                 try{
                     game = new Checkers();
                     int option = 0;
                     while (option !=4){
                         System.out.println("Select action:");
                         System.out.println("1. Create Game");
                         System.out.println("2. Make Move");
                         System.out.println("3. Print Board");
                         System.out.print("Select a value: ");
                         option = Integer.parseInt(in.readLine());
                         switch(option){
                             case 1:                                 
                                 game.createGame();
                                 System.out.println("The game has been created" );
                                 System.out.println(game.printGame());
                                 break;
                     case 2:
                         System.out.println("Turno -> Color " + game.GetTurn().toString());
                         System.out.print("Please provide the piece's coordinates (x,y): ");
                         String pieceCoordinates = (String)in.readLine();
                         
                         if(!pieceCoordinates.contains(","))
                         {
                             System.out.println("Invalid piece coordinates format");;
                             continue;
                         }
                         
                         System.out.print("Please provide the target coordinates (x,y): ");
                         String targetCoordinates = (String)in.readLine();
                         
                         if(!targetCoordinates.contains(","))
                         {
                             System.out.println("Invalid target coordinates format");;
                             continue;
                         }
                         
                         String[] pieceCoordinatesArr = pieceCoordinates.split(",");
                         String[] targetCoordinatesArr = targetCoordinates.split(",");
                         
                         int pieceY = -1;
                         int pieceX = -1;
                         int targetY = -1;
                         int targetX = -1;
                         
                         if(game.GetTurn() == PieceColor.BLACK){
                            pieceY = Integer.parseInt(pieceCoordinatesArr[0]);
                            pieceX = Integer.parseInt(pieceCoordinatesArr[1]);
                            targetY = Integer.parseInt(targetCoordinatesArr[0]);
                            targetX = Integer.parseInt(targetCoordinatesArr[1]);
                         }else{
                            pieceX = Integer.parseInt(pieceCoordinatesArr[0]);
                            pieceY = Integer.parseInt(pieceCoordinatesArr[1]);
                            targetX = Integer.parseInt(targetCoordinatesArr[0]);
                            targetY = Integer.parseInt(targetCoordinatesArr[1]);
                         }
                         
                         String message = game.makeMove(game.GetTurn(), pieceX, pieceY, targetX, targetY);
                         
                         if(message == "")
                         {
                             System.out.println("Movimiento realizado!");
                             
                             System.out.println(game.printGame());
                         }else{
                             System.out.println("Este movimiento no es permitido! Detalle: " + message);
                         }
                         
                         break;
                     case 3:
                         System.out.println(game.printGame());
                         break;
                 }
            }
       }catch(Exception ex){
           
       }
                break;
            case CHESS:
                game = new Chess();
                Chess.args = this.args;
                while (!"3".equals(optSub)){
                        System.out.println("Chose an action for the game");
                        System.out.println("1 New Game");
                        System.out.println("2 Load Game");
                        System.out.println("3 Quite Game");
                        optSub = in.readLine();                        
                        switch (optSub) {
                            case "1":                                
                                game.createGame();
                            case "2":
                            case "3":                              
                                break;                                 
                            default:System.out.println("Not a valid option\n");
                        }
                }break;
            case GO:
                game = new Go();
                break;
            default:
                throw new Exception("Juego '" + pGameType + "' no soportado!");
        }
        
        return game;
    }       
}