package entity.mobile;

import entity.Permeability;
import entity.Sprite;

/**
 * <h1> La classe diamand </h1>
 * @author Benjamin
 *
 */
public class Diamond extends Mobile{

	/** La declaration de la Constante SPRITE pour definir le diamand */
	private static final Sprite SPRITE = new Sprite('*', "diamond.png");

	/**
     * L'instationtion du diamand
     * @see Diamand
     * @return DIAMOND
     */
	 Diamond() {
		super(SPRITE, Permeability.DIAMOND);
	}


	@Override
	public void win() {
		return;
	}

	/**
	 * Methode pour l'obtention de diamand
	 * @see getDiamonds
	 */
	@Override
	public int getDiamonds() {
		return 0;
	}

	/**
	 * Methode pour l'obtention de diamand en horizontale
	 * @see grabDiamond
	 */
	@Override
	public void grabDiamond() {
		return;
	}
}