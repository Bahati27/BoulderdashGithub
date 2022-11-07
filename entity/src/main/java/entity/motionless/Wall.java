package entity.motionless;

import entity.Permeability;
import entity.Sprite;

/**
 * <h1> La classe Background </h1>
 * @author Benjamin
 *
 */
class Wall extends MotionlessElement {

	/** La declaration de la Constante SPRITE pour definir le mur */
    private static final Sprite SPRITE = new Sprite('#', "Wall.png");

    /**
     * L'instationtion du mur
     * @see Wall
     * @return WALL
     */
    Wall() {
        super(SPRITE, Permeability.WALL);
    }
}