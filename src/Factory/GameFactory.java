/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Factory;

import games.Enumerations.GameTypes;
import games.Interfaces.IGame;
import games.List.Checkers;
import games.List.Chess;
import games.List.Go;
import games.Pieces.Move;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author pavr1
 */
public class GameFactory {
    public String[] args;
    private static boolean logfile = false;
    
    public IGame CreateGame(GameTypes pGameType) throws Exception{
        IGame game = null;        
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        InputStreamReader stdin; 
        String  optSub = null;        
        
        switch(pGameType){
            case CHECKERS:
                
                game = new Checkers();
                break;
            case CHESS:
                
                while (!"3".equals(optSub)){
                        System.out.println("Chose an action for the game");
                        System.out.println("1 New Game");
                        System.out.println("2 Load Game");
                        System.out.println("3 Quite Game");
                        optSub = in.readLine();
                        menuLoop:
                        switch (optSub) {
                             case "1":                                 
                                 if( args.length >= 2 && args[0].equals("-l") ){
                                     stdin = new FileReader(args[1]);
                                     logfile = true;
                                     System.out.println(" Reading command script from file '"+args[1]+"'...");
                                 }
                                 else{
                                     stdin = new InputStreamReader(System.in);
                                 }
                                 boolean printMoves = true;
                                 if( args.length >= 1 && args[0].equals("-m") ){
                                     printMoves = false;
                                 }else if( args.length >= 3 && args[2].equals("-m")){
                                     printMoves = false;
                                 }
                                 Move[] moveArray;
                                 Chess b, temp;
                                 String command, prompt;
                                 Move m, randomMove;                                 
                                 System.out.println("Computer player: ");                                 
                                 while(true) {                                   
                                   game = new Chess();
                                   temp = (Chess)game;
                                    m = new Move();
                                    randomMove = m;
                                 
                                 while(true) {                                   
                                   b = (Chess)game;
                                   randomMove = m;
                                 if (temp.getTurn() == Chess.WHITE){
                                     prompt = "White";
                                 }else{
                                     prompt = "Black";
                                 }
                                 System.out.println("\n\nPosition ("+prompt+" to move):\n"+b);
                                 moveArray = (Move[]) b.generateMoves().toArray(new Move[0]);
                                 if (moveArray.length == 0) {
                                     if (b.inCheck()){
                                         System.out.println("Checkmate");
                                     }else{
                                         System.out.println("Stalemate");
                                     }
                                     break;
                                 }
                                 if(printMoves){
                                     System.out.println("Moves:");
                                     System.out.print("   ");
                                     for (int i=0; i<moveArray.length; i++) {
                                         if ((i % 10) == 0 && i>0) System.out.print("\n   ");
                                         System.out.print(moveArray[i]+" ");
                                     }
                                 }
                                 System.out.println();
                                       OUTER:
                                       while (true) {
                                           System.out.print(prompt + " move (or \"go\" or \"quit\")> ");
                                           command = readCommand(stdin);
                                           switch (command) {
                                               case "go":                                           
                                                   m = randomMove.randomMove(b);
                                                   System.out.println("Computer Moves: " + m);
                                                   break;
                                               case "quit":                                                   
                                                   break menuLoop;
                                               default:
                                                   m = null;
                                                  for (Move moveArray1 : moveArray) {
                                                     if (command.equals(moveArray1.toString())) {
                                                     m = moveArray1;
                                                     break;
                                                     }
                                                   }if (m != null) {
                                                       break OUTER;
                                                   }
                                                   System.out.println("\""+command+"\" is not a legal move");
                                                   break;
                                           }
                                       }                                 
                                 b.makeMove(m);
                                 //System.out.println(prompt + " made move "+m);
                               }                        
                        while(true) {
                            System.out.print("Play again? (y/n):");
                            command = readCommand(stdin);
                            if (command.equals("n")){
                                System.exit(1);
                            }if (command.equals("y")) {
                                break;
                            }
                        }
                       }
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
    
        public static String readCommand(InputStreamReader stdin) throws IOException {
        final int MAX = 100;
        int len = 0;
        char[] cbuf = new char[MAX];
        //len = stdin.read(cbuf, 0, MAX);
        for(int i=0; i<cbuf.length; i++){
            if(logfile && !stdin.ready()) return "quit"; // file is done.
            
            cbuf[i] = (char)stdin.read();
            len++;
            if(cbuf[i] == '\n')
                break;
            if(cbuf[i] == -1){
                System.out.println("An error occurred reading input");
                System.exit(1);
            }
        }
        return new String(cbuf, 0, len).trim();  /* trim() removes \n in unix and \r\n in windows */
    }
}