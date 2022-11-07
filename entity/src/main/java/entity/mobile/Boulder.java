package entity.mobile;

import entity.Permeability;
import entity.Sprite;
/**
 * <h1> La classe Boulder </h1>
 * @author Benjamin
 *
 */
public class Boulder extends Mobile {

	 /** La declaration de la Constante SPRITE pour definir la roche */
    private static final Sprite SPRITE = new Sprite('O', "boulder.png");

    /**
     * L'instationtion de la roche
     * @see Boulder
     * @return BOULDER
     */
    public Boulder() {
        super(SPRITE, Permeability.BOULDER);
    }
    /**
     * La methode deplacement à droite
     * @see moveRight
     */
    @Override
    public void moveRight() {
    	super.moveRight();
    }

    @Override
    public void win() {
        
        return;
    }
    /**
     * La methode deplacement à gauche
     * @see moveLeft
     */
    @Override
    public void moveLeft() {
    	super.moveLeft();
    }
    /**
     * La methode deplacement vers le bas
     * @see moveDown
     */
    @Override 
    public void moveDown() {
    	super.moveDown();
    }
    /**
     * La methode l'obtention de diamand
     * @see getDiamonds
     */
	@Override
	public int getDiamonds() {
		
		return 0;
	}
	/**
     * La methode permettant l'obtention de diamand gracer au roche par le deplacement vertical
     * @see grabDiamond
     */
    @Override
    public void grabDiamond() {
        return;
    }
	
	
}