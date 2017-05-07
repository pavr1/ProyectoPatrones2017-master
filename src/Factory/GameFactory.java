package Factory;

import games.Enumerations.GameTypes;
import games.Interfaces.IGame;
import games.List.Checkers;
import games.List.Chess;
import games.List.Go;

public class GameFactory {

    public static IGame CreateGame(GameTypes pGameType) {

        switch (pGameType) {
            case CHECKERS:
                return new Checkers();
            case CHESS:
                return new Chess();
            case GO:
                return new Go();
            default:
                throw new IllegalArgumentException("Juego '" + pGameType + "' no soportado!");
        }
    }
}
