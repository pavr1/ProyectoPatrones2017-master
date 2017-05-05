/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package games.Interfaces;

/**
 *
 * @author gpalomox
 */
public interface IPiece {
    public abstract void move(int pLocation, int pDestination); 
    public abstract boolean isAvailable();
    public abstract boolean isValid(int fromX, int fromY, int toX, int toY);
}
