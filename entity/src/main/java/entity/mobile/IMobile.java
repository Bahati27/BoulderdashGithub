package entity.mobile;

import java.awt.Point;

import fr.exia.showboard.*;


import entity.IElement;

/**
 * <h1>L'Interface IMobile</h1>
 * @author Benjamin
 * @see IPawn
 * @see IElement
 */
public interface IMobile extends IPawn, IElement {

    /**
     * Methode pour le deplacement vers le haut
     */
    void moveUp();

    /**
     * Methode pour le deplacement vers la gauche
     */
    void moveLeft();

    /**
     * Methode pour le deplacement vers le bas
     */
    void moveDown();

    /**
     * Methode pour le deplacement vers la droite
     */
    void moveRight();

    /**
     * Methode que l'on utilisera lorsque le joueur ne fait rien
     */
    void doNothing();
    /**
     * Methode pour obtenir l'axe des abscisses
     * @return the x
     */
    @Override
    int getX();

    /**
     * Methode pour obtenir l'axe des ordonnées
     * @return the y
     */
    @Override
    int getY();

    /**
     *	Methode pour verifier si le joueur est en vie
     * @return alive
     */
    Boolean isAlive();


    /**
     * Methode pour obtenir la position
     * @see getPosition
     */
    @Override
    Point getPosition();

	/**
	 * Methode pour definir si le joueur ou le monstre est mort 
	 * @see die
	 */
	public void die();

    /**
     * Methode pour definir si le joueur a gagné
     * @see win
     */
    public void win();
	
	/**
	 * Methode pour obtenir le diamand
	 * @see getDiamonds
	 * @return int
	 * 
	 */
	public int getDiamonds();

    /**
     * Methode pour obtenir le diamand horizontalement
     * @see grabDiamond
     * @return int
     */
    public void grabDiamond();

}