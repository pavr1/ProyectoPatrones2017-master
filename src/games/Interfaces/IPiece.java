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
 */
public interface IPiece {
    public abstract boolean isValid(int x, int y);
    public PieceColor GetColor();
    public void UpdateCoordinates(int x, int y);
    public int GetID();
    public int GetX();
    public int GetY();
}
