package Main;

import Authentication.User;
import Data.DataHandler;
import Factory.GameFactory;
import games.Enumerations.GameTypes;
import games.Enumerations.PieceColor;
import games.Interfaces.IGame;
import java.util.Iterator;

public class Gestor {
    private String user1;
    private String user2;
    private IGame currentGame;
    private DataHandler dataHandler;

    public Gestor() {
        dataHandler = new DataHandler();
    }

    public String checkMovement(PieceColor piece, int pSourceX, int pSourceY, int pTargetX, int pTargetY) {
        return currentGame.makeMove(piece, pSourceX, pSourceY, pTargetX, pTargetY);
    }

    public void createGame(GameTypes pgameType) {
        currentGame = GameFactory.CreateGame(pgameType);
    }

    public void loadGame(String pfileName) {
        currentGame.loadGame(pfileName);
    }

    public void saveGame() {
        currentGame.saveGame(user1, user2);
    }

    public String printGame() {
        return currentGame.printGame();
    }

    public void createUser(String pusername, String pemail, String ppassword) throws Exception {
        User usr = new User();
        usr.createPlayer(pusername, ppassword, pemail);
    }

    public Iterator<String> loadUsersList() {
        return dataHandler.loadUsersList();
    }

    public void loadPlayers(String puser1, String puser2) {
        user1 = puser1;
        user2 = puser2;
    }
}
