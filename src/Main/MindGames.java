/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Factory.GameFactory;
import static games.Enumerations.GameTypes.CHECKERS;
import static games.Enumerations.GameTypes.CHESS;
import static games.Enumerations.GameTypes.GO;
import games.Enumerations.PieceColor;
import games.Interfaces.IGame;
import games.List.Chess;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author gpalomox
 */
public class MindGames {

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    public static String[] pArgs;
    public static void main(String[] args) throws IOException, Exception {    
        
        pArgs = args;
        mostrarMenuPrincipal();
        
    }     
     public static void createNewCheckersGame(IGame pgame){
       
      try {
            IGame game=pgame;

            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            int option = 0;

            while (option != 6) {
                
              option = mostrarMenuOpcionesJuego();

                switch (option) {
                    case 1:
                        game.createGame();

                        System.out.println("The game has been created");

                        System.out.println(game.printGame());

                        break;
                    case 2:
                        System.out.println("Turno -> Color " + game.GetTurn().toString());
                        System.out.print("Please provide the piece's coordinates (x,y): ");
                        String pieceCoordinates = (String) in.readLine();

                        if (!pieceCoordinates.contains(",")) {
                            System.out.println("Invalid piece coordinates format");;
                            continue;
                        }

                        System.out.print("Please provide the target coordinates (x,y): ");
                        String targetCoordinates = (String) in.readLine();

                        if (!targetCoordinates.contains(",")) {
                            System.out.println("Invalid target coordinates format");;
                            continue;
                        }

                        String[] pieceCoordinatesArr = pieceCoordinates.split(",");
                        String[] targetCoordinatesArr = targetCoordinates.split(",");

                        int pieceY = -1;
                        int pieceX = -1;
                        int targetY = -1;
                        int targetX = -1;

                        if (game.GetTurn() == PieceColor.BLACK) {
                            pieceY = Integer.parseInt(pieceCoordinatesArr[0]);
                            pieceX = Integer.parseInt(pieceCoordinatesArr[1]);
                            targetY = Integer.parseInt(targetCoordinatesArr[0]);
                            targetX = Integer.parseInt(targetCoordinatesArr[1]);
                        } else {
                            pieceX = Integer.parseInt(pieceCoordinatesArr[0]);
                            pieceY = Integer.parseInt(pieceCoordinatesArr[1]);
                            targetX = Integer.parseInt(targetCoordinatesArr[0]);
                            targetY = Integer.parseInt(targetCoordinatesArr[1]);
                        }

                        String message = game.makeMove(game.GetTurn(), pieceX, pieceY, targetX, targetY);

                        if ("".equals(message)) {
                            System.out.println("Movimiento realizado! ");

                            System.out.println(game.printGame());
                        } else {
                            System.out.println("Este movimiento no es permitido! Detalle: " + message);
                        }

                        break;
                    case 3:
                        System.out.println(game.printGame());
                        break;
                    case 4:
                      game.saveGame("juan", "pedro");
                      break;

                     case 5:
                        mostrarMenuJuego();
                        break;
                     default:
                         System.out.println("Invalid Option");
                }
            }
        } catch (Exception ex) {

        }
    
    }     
    public static void createNewChessGame(IGame pgame){
       
       try{
            IGame game = pgame;
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            int option = 0;
            String pieceCoordinates="";
            
            while (option !=4){
                
                 option = mostrarMenuOpcionesJuego();
                 
                 switch(option){
                     case 1:
                         System.out.println("The game has been created" );
                         System.out.println(game.printGame());
                         break;
                     case 2:
                         System.out.println("Select Move" );
                         game.createGame();                         
                         break;
                     case 3:
                         System.out.println(game.printGame());
                         game.loadGame("juan_pedro.pgn");
                         break;
                      
                      case 4:
                       game.saveGame("juan", "pedro");
                       break;
                         
                      case 5:
                         mostrarMenuJuego();
                         break;
                      default:
                          System.out.println("Invalid Option");
                 }
            }
       }catch(Exception ex){
           
       }
    
    }
    
    public static void createNewGoGame(IGame pgame){
       
        try{
            IGame game = pgame;
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            int option = 0;
            String pieceCoordinates="";
            
            while (option !=4){
                
                 option = mostrarMenuOpcionesJuego();
                 
                 switch(option){
                     case 1:
                         game.createGame();
                         
                         System.out.println("The game has been created" );
                         
                         System.out.println(game.printGame());
                         
                         break;
                     case 2:
                         System.out.println("Turno -> Color " + game.GetTurn().toString());
                         if (game.GetTurn() == PieceColor.BLACK) {
                             pieceCoordinates = "0,0";
                             
                         }else{
                             pieceCoordinates = "8,8";
                         }
                         if(!pieceCoordinates.contains(","))
                         {
                             System.out.println("Invalid piece coordinates format");
                             continue;
                         }
                         
                         
                         System.out.print("Please provide the target coordinates (x,y): ");
                         String targetCoordinates = (String)in.readLine();
                         
                         System.out.print("Please provide the location (topRight, topLeft, bottomRight, bottomLeft): ");
                         String location = (String)in.readLine();
                         
                         if(!targetCoordinates.contains(","))
                         {
                             System.out.println("Invalid target coordinates format");
                             continue;
                         }
                         
                         String[] pieceCoordinatesArr = pieceCoordinates.split(",");
                         String[] targetCoordinatesArr = targetCoordinates.split(",");
                         
                         int pieceY;
                         int pieceX;
                         int targetY;
                         int targetX;
                         int squareLocation = 0;
                         
                         if(game.GetTurn() == PieceColor.BLACK){
                            pieceY = Integer.parseInt(pieceCoordinatesArr[0]);
                            pieceX = Integer.parseInt(pieceCoordinatesArr[1]);
                            targetY = Integer.parseInt(targetCoordinatesArr[0]);
                            targetX = Integer.parseInt(targetCoordinatesArr[1]);
                            squareLocation = 1;
                         }else{
                            pieceX = Integer.parseInt(pieceCoordinatesArr[0]);
                            pieceY = Integer.parseInt(pieceCoordinatesArr[1]);
                            targetX = Integer.parseInt(targetCoordinatesArr[0]);
                            targetY = Integer.parseInt(targetCoordinatesArr[1]);
                            squareLocation = 1;
                         }
                         
                         String message = pgame.makeMove(game.GetTurn(), pieceX, pieceY, targetX, targetY);
                         
                         if("".equals(message))
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
                      
                      case 4:
                       game.saveGame("juan", "pedro");
                       break;
                         
                      case 5:
                         mostrarMenuJuego();
                         break;
                      default:
                          System.out.println("Invalid Option");
                 }
            }
       }catch(Exception ex){
           
       }
    
    }    
    public static void mostrarMenuPrincipal() throws IOException, Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        System.out.println("********Main Menu********");
        System.out.println();
        System.out.println("Select an option:");
        System.out.println("1. Register user");
        System.out.println("2. Select Game");
        System.out.println("3. Quit");
        int opcion = Integer.parseInt(br.readLine());
        seleccionarOpcion(opcion);
    }     
    public static int mostrarMenuOpcionesJuego() throws IOException{
        System.out.println("Select an option:");
        System.out.println("1. Create Game");
        System.out.println("2. Make Move");
        System.out.println("3. Print Board");
        System.out.println("4. Save game");
        System.out.println("5. Select Game");
        int option = Integer.parseInt(in.readLine());
        return option;
                 
    }    
    public static boolean seleccionarOpcion(int popcion) throws IOException, Exception{
        switch(popcion){
            case 1:
                createUser();
                break;
            case 2:
                mostrarMenuJuego();
                break;
            case 3:
               System.exit(0);
            default:
                System.out.println("Invalid Option");
                break;   
        }
        return true;
    }    
    public static void mostrarMenuJuego() throws IOException, Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Select a game:");
        System.out.println("1. Checkers");
        System.out.println("2. Chess");
        System.out.println("3. Go");
        System.out.println("4. Main Menu");
        int opcion = Integer.parseInt(br.readLine());
        seleccionarJuego(opcion);
    }    
    public static void seleccionarJuego(int popcion) throws Exception{
        IGame newGame;
        GameFactory gf = new GameFactory();
       
        switch(popcion){
            case 1:
                newGame = GameFactory.CreateGame(CHECKERS);
                createNewCheckersGame(newGame);
                break;
            case 2:
                newGame=GameFactory.CreateGame(CHESS);                
                createNewChessGame(newGame);
                break;
            case 3:
                newGame = GameFactory.CreateGame(GO);
                createNewGoGame(newGame);
                break;
            case 4:
                mostrarMenuPrincipal();
                break;
            default:
                System.out.println("Opcion invalida");
                break;   
        }
    }    
    public static void createUser() throws IOException, Exception{
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       System.out.println("-----Crear usuario-----");
       System.out.println("Ingrese el nombre de usuario: ");
       String user = br.readLine();
       System.out.println("Ingrese el correo: ");
       String mail = br.readLine();
       System.out.println("Ingrese la contrasena: ");
       String password = br.readLine();
       Gestor gest = new Gestor();
       gest.createUser(user, mail, password);
       mostrarMenuPrincipal();
    }    
    public static boolean VerifyColor(IGame pgame, String ppieceCoordinates) throws IOException, Exception{
         System.out.println("estoy en everifycolor");
         System.out.println("Turno -> Color " + pgame.GetTurn().toString());
        if (pgame.GetTurn() == PieceColor.BLACK) {
            ppieceCoordinates = "0,0";

        }else{
            ppieceCoordinates = "8,8";
        }
        if(!ppieceCoordinates.contains(","))
        {
            System.out.println("Invalid piece coordinates format");
            return false;
        }
        return true;
    }     
    public static String verifyTurn(IGame pgame, String[] ppieceCoordinatesArr, String[] ptargetCoordinatesArr) throws IOException, Exception{
        int pieceY;
        int pieceX;
        int targetY;
        int targetX;

        if(pgame.GetTurn() == PieceColor.BLACK){
           pieceY = Integer.parseInt(ppieceCoordinatesArr[0]);
           pieceX = Integer.parseInt(ppieceCoordinatesArr[1]);
           targetY = Integer.parseInt(ptargetCoordinatesArr[0]);
           targetX = Integer.parseInt(ptargetCoordinatesArr[1]);
        }else{
           pieceX = Integer.parseInt(ppieceCoordinatesArr[0]);
           pieceY = Integer.parseInt(ppieceCoordinatesArr[1]);
           targetX = Integer.parseInt(ptargetCoordinatesArr[0]);
           targetY = Integer.parseInt(ptargetCoordinatesArr[1]);
        }
        
        String message = pgame.makeMove((PieceColor) pgame.GetTurn(), pieceX, pieceY, targetX, targetY);
        return message;
    }    
    public static void verifyMovement(IGame pgame, String pmessage) throws IOException, Exception{
        if("".equals(pmessage))
        {
            System.out.println("Movimiento realizado!");

            System.out.println(pgame.printGame());
        }else{
            System.out.println("Este movimiento no es permitido! Detalle: " + pmessage);
        }
    }     
    public static String[] InsertTargetCoordinates() throws IOException, Exception{
        System.out.print("Please provide the target coordinates (x,y): ");
        String targetCoordinates = (String)in.readLine();

        if(!targetCoordinates.contains(","))
        {
            System.out.println("Invalid target coordinates format");
        }
        
        String[] targetCoordinatesArr = targetCoordinates.split(",");
        return targetCoordinatesArr;
    }
}
