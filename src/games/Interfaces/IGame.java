/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package games.Interfaces;

import games.Enumerations.PieceColor;

/**
 *
 * @author gpalomox
 * 20 checkers 10x10
 * 16 1king 1 queen 2rock 2 knights 2 bishops 8 pawns 8x8
 * 19 x 19 Go N Stones
 */
public interface IGame {
    public abstract void createGame();
    //Formato de guardado y carga de datos = 1°BLACK°2°5|1°BLACK°2°5
    public abstract void saveGame(String user1, String user2); //"src/Data.Database.Checkers/" + user1 + "_" + user2 + ".ext";
    //Procesar el string que retorna el DataHandler para instanciar la matríz con sus piezas
    public abstract void loadGame(String pFileName); // Formato nombre archivo: User1_Usert2.ext
    public abstract String makeMove(PieceColor pTurn, int pSourceX, int pSourceY, int pTargetX, int pTargetY);
    public abstract String printGame();
}
