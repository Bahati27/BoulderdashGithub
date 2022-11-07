package entity.mobile;

import entity.Permeability;
import entity.Sprite;
/**
 * <h1> La classe MonstreG </h1>
 * @author Benjamin
 *
 */
public class MonsterG extends Mobile{

	/** La declaration de la Constante SPRITE pour definir le monstre vert */
	private static final Sprite SPRITE = new Sprite('G', "greenMonster.png");

	/**
	 * Instantiates a new monster.
	 * L'instationtion du monstre vert
     * @see MonsterG
     * @return KILLABLE
	 */
	MonsterG() {
		super(SPRITE, Permeability.KILLABLE);
	}

	@Override
	public void win() {
		return;
	}
	/**
	 *  Methode pour obtenir le diamand en horizontale
	 *  @return 0
	 */
	@Override
	public int getDiamonds() {
		return 0;
	}
	/**
	 * Methode pour obtenir le diamand en horizontale
	 */
	@Override
	public void grabDiamond() {
		return;
	}
}