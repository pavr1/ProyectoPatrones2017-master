package Main;

import Authentication.User;
import Data.DataHandler;
import Factory.GameFactory;
import games.Enumerations.GameTypes;
import games.Interfaces.IGame;
import games.Interfaces.IPiece;

public class Gestor {

    private GameFactory gameFactory;
    private User user;
    private IGame currentGame;
    private DataHandler dataHandler;

    public Gestor() {
        dataHandler = new DataHandler();
    }

    public boolean checkMovement(IPiece piece) {
        //return currentGame.checkMove(piece);
        return false;
    }

    public void createGame(GameTypes pgameType) {
        //currentGame = GameFactory.gameSerializer(pgameType);
    }

    public StringBuilder loadGame(String pfileName) {
        StringBuilder fileData = new StringBuilder();

        try {
           // fileData = dataHandler.readFile(pfileName);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return fileData;
    }

    public void saveGame() {

    }

    public StringBuilder printGame(String pfileName) {

        StringBuilder gameData = new StringBuilder();
        try {
           // gameData = dataHandler.readFile(pfileName);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return gameData;
    }

    public boolean createUser(String pusername, String pemail, String ppassword) throws Exception {

        boolean userCreated = false;

        if (!dataHandler.verifyUser(pusername)) {
            //user = new User(pname, pemail, ppassword);
            dataHandler.registerUserFile(pusername, ppassword);
            userCreated = true;
        }

        return userCreated;
    }
}
