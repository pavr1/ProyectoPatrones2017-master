/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package games.List;

import Data.DataHandler;
import Main.MindGames;
import games.Enumerations.PieceColor;
import games.Interfaces.IGame;
import games.Interfaces.IPiece;
import games.Pieces.Move;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gpalomox
 */
public class Chess implements IGame{
    public byte[][] board = new byte[8][8];
    IGame game = null;
    public int previousDoublePush;
    public int turn, cH;
    private PieceColor turnP = PieceColor.WHITE;
    public boolean[] kingside = new boolean[2], queenside = new boolean[2];
    public boolean[] hascastled = new boolean[2];
    private String [] chessHistory1 = null;
    String chessHistory=" ";
    //public static String piecestr = "nbrqkp-XNBRQKPX";
    public static String piecestr = "♞♝♜♛♚♟ X♘♗♖♕♔♙X";
    public static PieceDesc[] pdesc = new PieceDesc[5];
    public static IPiece pawnA;
    private static boolean logfile = false;
    InputStreamReader stdin;
    public static String[] args;

    public static final int KNIGHT = 0;
    public static final int BISHOP = 1;
    public static final int ROOK   = 2;
    public static final int QUEEN  = 3;
    public static final int KING   = 4;
    public static final int PAWN   = 5;
    public static final int EMPTY  = 6;
    public static final int CBIT   = 8;

    public static final int BLACK  = 0;
    public static final int WHITE  = 1;
    
     static {
	pdesc[KNIGHT] = new PieceDesc(false,  new int[]
	    { 2, 1 , 1, 2 , 2, -1 , 1, -2 , -2, 1 , -1,2 , -2,-1 , -1,-2 });
	
	pdesc[BISHOP] = new PieceDesc(true, new int[]
	    { 1, 1 , 1,-1 , -1, -1 , -1,1 });
	
	pdesc[ROOK] = new PieceDesc(true, new int[]
	    { 1, 0 , 0, 1 , -1,0 , 0,-1 });
	
	pdesc[QUEEN] = new PieceDesc(true, new int[]
	    { 1, 0 , 0, 1 , -1,0 , 0,-1 , 1, 1 , 1,-1 , -1, -1 , -1,1 });
	
	pdesc[KING] = new PieceDesc(false, new int[]
	    { 1, 0 , 0, 1 , -1,0 , 0,-1 , 1, 1 , 1,-1 , -1, -1 , -1,1 });
    }
    public Chess() {
        args=MindGames.pArgs;
	turn = WHITE;
	for (int x=0; x<8; x++) {
	    for (int y=0; y<8; y++) {
		board[x][y] = EMPTY;
	    }
	}
	previousDoublePush = -1;
	for (int color = 0; color < 2; color++) {
	    kingside[color] = queenside[color] = true;
	    hascastled[color] = false;
	}
	board[0][0] = makesquare(ROOK, WHITE);
	board[1][0] = makesquare(KNIGHT, WHITE);
	board[2][0] = makesquare(BISHOP, WHITE);
	board[3][0] = makesquare(QUEEN, WHITE);
	board[4][0] = makesquare(KING, WHITE);
	board[5][0] = makesquare(BISHOP, WHITE);
	board[6][0] = makesquare(KNIGHT, WHITE);
	board[7][0] = makesquare(ROOK, WHITE);
	for (int x=0; x<8; x++) {
	    board[x][7] = makesquare(pieceof(board[x][0]), BLACK);
	    board[x][1] = makesquare(PAWN, WHITE);
	    board[x][6] = makesquare(PAWN, BLACK);
	}
    }
    public Chess(Chess b) {
	for (int x=0; x<8; x++) {
	    for (int y=0; y<8; y++) {
		board[x][y] = b.board[x][y];
	    }
	}

	previousDoublePush = b.previousDoublePush;
	turn = b.turn;
	for (int i=0; i<2; i++) {
	    kingside[i] = b.kingside[i];
	    queenside[i] = b.queenside[i];
	    hascastled[i] = b.hascastled[i];
	}
    }    
    @Override
    public void createGame() {        
        if( args.length >= 2 && args[0].equals("-l") ){
             try {
                 stdin = new FileReader(args[1]);
             } catch (FileNotFoundException ex) {
                 Logger.getLogger(Chess.class.getName()).log(Level.SEVERE, null, ex);
             }
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
                                 String command="", prompt;
                                 Move m = new Move();               
                                 System.out.println("Chess Game Started: ");
                                 cH =0;
                                 while(true) {                                   
                                   game = new Chess();
                                   temp = (Chess)game;
                                 while(true) {                                   
                                   b = (Chess)game;                                                            
                                 if (temp.getTurn() == Chess.WHITE){
                                     prompt = "White";
                                 }else{
                                     prompt = "Black";
                                 }
                                 System.out.println("\n\nPlayer ("+prompt+" to move):\n"+b);
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
                                         if ((i % 10) == 0 && i>0){
                                             System.out.print("\n   ");}
                                             System.out.print(moveArray[i].printGame()+" ");                                         
                                     }
                                 }
                                 System.out.println();
                                       OUTER:
                                       while (true) {
                                           System.out.print(prompt + " move (or \"go\" or \"quit\")> ");
                                       try {
                                           command = readCommand(stdin);
                                       } catch (IOException ex) {
                                           Logger.getLogger(Chess.class.getName()).log(Level.SEVERE, null, ex);
                                       }
                                           switch (command) {
                                               case "go":                                           
                                                   command = m.randomMove(b).printGame();
                                                   for (Move moveArray1 : moveArray) {
                                                     if (command.equals(moveArray1.printGame())) {
                                                     m = moveArray1;
                                                     break;
                                                     }
                                                   }if (m != null) {
                                                       break OUTER;
                                                   }
                                                   System.out.println("Computer Moves: " + m);
                                                   break;
                                               case "quit":
                                                   saveGame("juan", "pedro");
                                                   return;
                                               default:
                                                   m = null;
                                                  for (Move moveArray1 : moveArray) {
                                                     if (command.equals(moveArray1.printGame())) {
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
                                 cH++;
                                 System.out.println(prompt + " moved made "+m);
                                 
                                 chessHistory += "" + cH +". "+prompt+" "+m+" ";                                 
                               }
                                 chessHistory1 = new String[cH];
                                 
                        while(true) {
                            System.out.print("Play again? (y/n):");
                                       try {
                                           command = readCommand(stdin);
                                       } catch (IOException ex) {
                                           Logger.getLogger(Chess.class.getName()).log(Level.SEVERE, null, ex);
                                       }                            
                            if (command.equals("n")){
                                return;
                            }if (command.equals("y")) {
                                break;
                            }
                        }
                       }
    }
    @Override
    public void saveGame(String user1, String user2) {
        try {
            String filePath = System.getProperty("user.dir").concat("\\src\\Data\\Database\\Chess\\" + user1 + "_" + user2 + ".pgn");
            //+
            String data = "";

            //for (int i = 0; i < chessHistory.length; i++) {
                data += chessHistory;
            //}            
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
            String data = dataHandler.readFile(System.getProperty("user.dir").concat("\\src\\Data\\Database\\Chess\\" + pFileName));
            String[] piecesData = data.split(" ");
            String pMove= "";
            int ii = 0;
            
            if( args.length >= 2 && args[0].equals("-l") ){
             try {
                 stdin = new FileReader(args[1]);
             } catch (FileNotFoundException ex) {
                 Logger.getLogger(Chess.class.getName()).log(Level.SEVERE, null, ex);
             }
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
                                 String command="", prompt;
                                 Move m = new Move();               
                                 System.out.println("Chess Game Loaded: ");                                 
                                 while(true) {                                   
                                   game = new Chess();
                                   temp = (Chess)game;
                                 while(true) {                                   
                                   b = (Chess)game;                                                            
                                 if (temp.getTurn() == Chess.WHITE){
                                     prompt = "White";
                                 }else{
                                     prompt = "Black";
                                 }
                                 System.out.println("\n\nPlayer ("+prompt+" to move):\n"+b);
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
                                         if ((i % 10) == 0 && i>0){
                                             System.out.print("\n   ");}
                                             System.out.print(moveArray[i].printGame()+" ");                                         
                                     }
                                 }
                                 System.out.println();
                                       OUTER:
                                       while (true) {
                                           System.out.print(prompt + " move (or \"go\" or \"quit\")> \n");
                                       try {                                           
                                           if( piecesData.length >= ii ){                                            
                                               if("White".equals(piecesData[ii+1])){
                                                   command = piecesData[ii+2];
                                                   ii+=2;
                                               }else if ("Black".equals(piecesData[ii+1])){
                                                   command = piecesData[ii+2];
                                                   ii+=2;
                                               }
                                               ii++;
                                           }else{
                                               command = readCommand(stdin);
                                               break OUTER;
                                           }
                                       } catch (IOException ex) {
                                           Logger.getLogger(Chess.class.getName()).log(Level.SEVERE, null, ex);
                                       }                                       
                                           switch (command) {
                                               case "go":                                           
                                                   command = m.randomMove(b).printGame();
                                                   for (Move moveArray1 : moveArray) {
                                                     if (command.equals(moveArray1.printGame())) {
                                                     m = moveArray1;
                                                     break;
                                                     }
                                                   }if (m != null) {
                                                       break OUTER;
                                                   }
                                                   System.out.println("Computer Moves: " + m);
                                                   break;
                                               case "quit":
                                                   saveGame(prompt,prompt);
                                                   return;
                                               default:
                                                   m = null;
                                                  for (Move moveArray1 : moveArray) {
                                                     if (command.equals(moveArray1.printGame())) {
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
                                 cH++;
                                 System.out.println(prompt + " moved made "+m);
                                 
                                 chessHistory += "" + cH +". "+prompt+" "+m+" ";                                 
                               } 
                        while(true) {
                            System.out.print("Play again? (y/n):");
                                       try {
                                           command = readCommand(stdin);
                                       } catch (IOException ex) {
                                           Logger.getLogger(Chess.class.getName()).log(Level.SEVERE, null, ex);
                                       }                            
                            if (command.equals("n")){
                                return;
                            }if (command.equals("y")) {
                                break;
                            }
                        }
                       }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    @Override
    public String makeMove(PieceColor pTurn, int pSourceX, int pSourceY, int pTargetX, int pTargetY) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public String printGame() {
        String status = "";
	status += "    a b c  d  e f  g h\n";
	status += "  +--------------------+\n";
	for (int y=7; y>=0; y--) {
	    status += (y+1) + " |";
	    for (int x=0; x<8; x++) {
		status += piecestr.charAt(board[x][y]);
		if (x<5 || piecestr.charAt(board[x][y])!=' '){
                    status += " ";
                }
                if(piecestr.charAt(board[x][y])==' '){
                    status +=piecestr.charAt(board[x][y]);
                }
	    }
	    status += "| " + (y+1);
	    status += "\n";
	}
	status += "  +--------------------+\n";
	status += "    a b c  d  e f  g h\n";
	return status;
    }
    public String toString() {        
	return printGame();
    }
    @Override
     public PieceColor GetTurn() {
        return turnP;
    }
     public static byte makesquare(int p, int c) {
	return (byte) (p | (c<<3));
    }
     public static int pieceof(byte x) {
	return x & (~CBIT);
    }
     public static boolean isempty(byte x) {
	return (x & (~CBIT)) == EMPTY;
    }
     public static int colorof(byte x) {
	return (x & CBIT)>>3;
    }     
     public int getTurn() {
	return turn;
    }
     private boolean pieceCheck(int x, int y) {
	int cx, cy;
	boolean stopnow;
	byte enemyking = makesquare(KING, turn);
	int piece = pieceof(board[x][y]);
	for (int dir = 0; dir < pdesc[piece].delta.length; dir++) {
	    cx = x;
	    cy = y;
	    stopnow = !pdesc[piece].iterate;
	    while (true) {
		cx += pdesc[piece].delta[dir].x;
		cy += pdesc[piece].delta[dir].y;
		/* (cx, cy) is the current position */
		/* if I'm off the board or sitting on a friendly piece, break out */
		if (cx < 0 || cy < 0 || cx >=8 || cy >= 8) break;
		if (!isempty(board[cx][cy])) {
		    if (colorof(board[cx][cy]) == 1-turn) break;  /* hit a friendly piece */
		    /* hit an enemy piece */
		    stopnow = true;
		}
		/* now the move we're looking at is a good one */
		/* is it attacking the enemy king? */
		if (board[cx][cy] == enemyking) return true;
		if (stopnow) break;
	    }
	}
	return false;
    }     
     public SquareDesc getSquare(int x, int y) {
	if (isempty(board[x][y]))
	    return new SquareDesc(EMPTY, EMPTY, x, y);
	else
	    return new SquareDesc(pieceof(board[x][y]), colorof(board[x][y]), x , y);
    }
     public SquareDesc[] getPieces(int pturn) {

	LinkedList pieces = new LinkedList();

	for (int x=0; x<8; x++) {
	    for (int y=0; y<8; y++) {
		if (isempty(board[x][y])) continue;
		if (colorof(board[x][y]) != pturn) continue;
		pieces.addFirst(new SquareDesc(pieceof(board[x][y]), colorof(board[x][y]),x,y));
	    }
	}

	return (SquareDesc[]) pieces.toArray(new SquareDesc[0]);
    }
     private boolean pawnCheck(int x, int y) {
	int dy, dx;
	byte enemyking = makesquare(KING, turn);
	if (1-turn == WHITE) dy = 1; else dy = -1;
	if (y+dy >= 8 || y+dy < 0) return false;
	for (dx = -1; dx <= 1; dx += 2) {
	    if (x+dx < 0 || x+dx >= 8) continue;
	    if (board[x+dx][y+dy] == enemyking) return true;
	}
	return false;
    }
     public boolean inCheck() {
	for (int x=0; x<8; x++) {
	    for (int y=0; y<8; y++) {
		if (isempty(board[x][y])) continue;
		if (colorof(board[x][y]) != 1-turn) continue;
		if (pieceof(board[x][y]) == PAWN) {
		    if (pawnCheck(x, y)) return true;
		} else {
		    if (pieceCheck(x, y)) return true;
		}
	    }
	}
	return false;
    }
     public void makeMove(Move m) {

	if ((pieceof(board[m.srcx][m.srcy]) == KING) && 
	    ((m.srcx-m.destx >= 2) || (m.srcx-m.destx <= -2))) {
	    /* it must be a castling move */
	    makeCastlingMove(m);
	    return;
	}
	
	if ((pieceof(board[m.srcx][m.srcy]) == PAWN) && 
	    (isempty(board[m.destx][m.desty])) &&
	    (m.srcx != m.destx)) {
	    /* is it a diagonal pawn move where the destination square is empty? */
	    board[m.destx][m.srcy] = EMPTY;  /* remove the captured pawn */
	}

	if ((pieceof(board[m.srcx][m.srcy]) == PAWN) && 
	    (m.desty != m.srcy +1) && (m.desty != m.srcy -1)) {
	    /* is it a double push by a pawn? */
	    previousDoublePush = m.srcx;
	} else {
	    previousDoublePush = -1;
	}

	/* Handle the castling bits */
	if (pieceof(board[m.srcx][m.srcy]) == KING) {
	    kingside[turn] = queenside[turn] = false;
	}

	int castleRank = (turn == WHITE)? 0: 7;
	if ((m.destx == 0) && (m.desty == castleRank)) queenside[turn] = false;
	if ((m.destx == 7) && (m.desty == castleRank)) kingside[turn] = false;
	/* This is tricky.  We invalidate castling whenever we move a
	   piece INTO the place where the rook is supposed to be.
	   This will work correctly because before we allow castling we'll
	   check to make sure that the rook is where its supposed to be.
	   If a non-original rook is there, it must have moved in there, which
           would invalidate castling by the above test. */
    
	board[m.destx][m.desty] = board[m.srcx][m.srcy];
	board[m.srcx][m.srcy] = EMPTY;
    
	if ((pieceof(board[m.destx][m.desty]) == PAWN) && 
	    (m.desty == 7 || m.desty == 0)) {
	    /* promote to a queen */
	    board[m.destx][m.desty] = makesquare(QUEEN, turn);
	}
	turn = 1-turn;
    }
     private void makeCastlingMove(Move m) {
	board[m.destx][m.desty] = board[m.srcx][m.srcy];
	board[m.srcx][m.srcy] = EMPTY;
	if (m.destx == 6) {  // Kingside
	    board[5][m.desty] = board[7][m.srcy];
	    board[7][m.srcy] = EMPTY;  
	}

	if (m.destx == 2) {  // Queenside
	    board[3][m.desty] = board[0][m.srcy];
	    board[0][m.srcy] = EMPTY;  
	}
	kingside[turn] = queenside[turn] = false;
	hascastled[turn] = true;

	turn = 1-turn;
    }     
     private void generatePawnMoves(LinkedList moveList, int x, int y) {
	int dy, dx, ex, ey;
	Chess newp;
	
	if (turn == WHITE) dy = 1; else dy = -1;
	if (y+dy >= 8 || y+dy < 0) return;
	/* push 1 */
	if (isempty(board[x][y+dy])) {
	    newp = new Chess(this);
	    newp.board[x][y+dy] = newp.board[x][y];
	    newp.board[x][y] = EMPTY;
	    if (!newp.inCheck()) moveList.addFirst(new Move(x, y, x, y+dy, false));
	    /* push 2 */
	    if (((turn == WHITE && y == 1) || (turn == BLACK && y == 6)) && isempty(board[x][y+2*dy])) {
		newp.board[x][y+2*dy] = newp.board[x][y+dy];
		newp.board[x][y+dy] = EMPTY;	    
		if (!newp.inCheck()) moveList.addFirst(new Move(x, y, x, y+2*dy, false));
	    }
	}
	/* captures */
	for (dx = -1; dx <= 1; dx += 2) {
	    if (x+dx < 0 || x+dx >= 8) continue;
	    if (!isempty(board[x+dx][y+dy])) {
		if (colorof(board[x+dx][y+dy]) == turn) continue;
		/* ok, it's an enemy piece */
		ex = x;
		ey = y;
	    } else {
		/* could it be enpassant? */
		if (x+dx != previousDoublePush) continue;
		if (!((turn == WHITE && y == 4) || (turn == BLACK && y == 3))) continue;
		ex = x+dx;
		ey = y;
	    }
	    newp = new Chess(this);
	    newp.board[x+dx][y+dy] = newp.board[x][y];
	    newp.board[x][y] = EMPTY;
	    newp.board[ex][ey] = EMPTY;
	    if (!newp.inCheck()) moveList.addFirst(new Move(x, y, x+dx, y+dy, true));
	}
    }
     public List generateMoves() {
	LinkedList moveList = new LinkedList();

	for (int x=0; x<8; x++) {
	    for (int y=0; y<8; y++) {
		if (isempty(board[x][y])) continue;
		if (colorof(board[x][y]) != turn) continue;
		if (pieceof(board[x][y]) == PAWN) {
		    generatePawnMoves(moveList, x, y);
		} else {
		    generatePieceMoves(moveList, x, y);
		}
	    }
	}
	generateCastlingMoves(moveList);	
	return moveList;
    }
     private void generatePieceMoves(LinkedList moveList, int x, int y) {
	int cx, cy, dir;
	boolean stopnow, capture;
	Chess newp;
	int piece = pieceof(board[x][y]);
	stopnow = !pdesc[piece].iterate;
	for (dir = 0; dir < pdesc[piece].delta.length; dir++) {
	    cx = x;
	    cy = y;
	    capture = false;
	    while (true) {
		cx += pdesc[piece].delta[dir].x;
		cy += pdesc[piece].delta[dir].y;
		
		if (cx < 0 || cy < 0 || cx >=8 || cy >= 8) break;
		if (!isempty(board[cx][cy])) {
		    if (colorof(board[cx][cy]) == turn) break;       
		    capture = true;
		}
		
		newp = new Chess(this);
		newp.board[cx][cy] = newp.board[x][y];
		newp.board[x][y] = EMPTY;
		if (!newp.inCheck()) moveList.addFirst(new Move(x, y, cx, cy, capture));
		if (stopnow || capture) break;
	    }
	}
    }
     private void generateQueenCastling(int castleRank, LinkedList moveList) {    
	if (board[0][castleRank] != makesquare(ROOK, turn)) return;
	if (!isempty(board[3][castleRank])) return;
	if (!isempty(board[2][castleRank])) return;
	if (!isempty(board[1][castleRank])) return;
	/* now check squares on files 4 3 2 (numbering from 0) and make sure
           they're all not in check */
	Chess newp = new Chess(this);
	if (newp.inCheck()) return;
	newp.board[3][castleRank] = newp.board[4][castleRank];
	newp.board[4][castleRank] = EMPTY;
	if (newp.inCheck()) return;	
	newp.board[2][castleRank] = newp.board[3][castleRank];
	newp.board[3][castleRank] = EMPTY;
	if (newp.inCheck()) return;
	moveList.addFirst(new Move(4, castleRank, 2, castleRank, false));	
    }    
     private void generateKingCastling(int castleRank, LinkedList moveList) {    
	if (board[7][castleRank] != makesquare(ROOK, turn)) return;
	if (!isempty(board[5][castleRank])) return;
	if (!isempty(board[6][castleRank])) return;
	/* now check squares on files 4 5 6 (numbering from 0) and make sure
           they're all not in check */
	Chess newp = new Chess(this);
	if (newp.inCheck()) return;
	newp.board[5][castleRank] = newp.board[4][castleRank];
	newp.board[4][castleRank] = EMPTY;
	if (newp.inCheck()) return;	
	newp.board[6][castleRank] = newp.board[5][castleRank];
	newp.board[5][castleRank] = EMPTY;
	if (newp.inCheck()) return;
	moveList.addFirst(new Move(4, castleRank, 6, castleRank, false));	
    }
     private void generateCastlingMoves(LinkedList moveList) {
	int castleRank = (turn == WHITE)? 0: 7;
	if (board[4][castleRank] != makesquare(KING, turn)) return;
	if (kingside[turn]) generateKingCastling(castleRank, moveList);
	if (queenside[turn]) generateQueenCastling(castleRank, moveList);
    }     
     private static class PieceDesc {
	boolean iterate;
	Pair[] delta;
	PieceDesc(boolean iter, int[] deltas) {
	    iterate = iter;
	    delta = new Pair[deltas.length/2];
	    for (int i=0; i<deltas.length; i+=2) {
		delta[i/2] = new Pair(deltas[i], deltas[i+1]);
	    }
	}
    }     
     private static class Pair {
	int x, y;
	Pair(int xx, int yy) {
	    x = xx;
	    y = yy;
	}
    }
     public class SquareDesc {
	public SquareDesc() { }
	public SquareDesc(int t, int c, int ix, int iy) {
            type=t; color=c; x=ix; y=iy; 
        }

	public int type,color,x,y;
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
