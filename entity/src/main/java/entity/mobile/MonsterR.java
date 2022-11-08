package entity.mobile;

import entity.Permeability;
import entity.Sprite;

/**
 * <h1> La classe MonstreR </h1>
 * @author Benjamin
 *
 */

public class MonsterR extends Mobile {

	/** La declaration de la Constante SPRITE pour definir le monstre rouge */
	private static final Sprite SPRITE = new Sprite('R', "redMonster.png");

	/**
	 * Instanciation du Monstre rouge
     * @see MonsterR
     * @return KILLABLE
	 */
	public MonsterR() {
		super(SPRITE, Permeability.KILLABLE);
	}

	@Override
	public void win() {
		return;
	}

	/**
	 * Methode pour obtenir le diamand
	 * @return 0
	 */
	@Override
	public int getDiamonds() {
		return 0;
	}

	/**
	 *Methode pour obtenir le diamand horizontalement
	 *@return
	 */
	@Override
	public void grabDiamond() {
		return;
	}
}