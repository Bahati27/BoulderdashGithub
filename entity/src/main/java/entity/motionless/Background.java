package entity.motionless;

import entity.Permeability;
import entity.Sprite;
import entity.mobile.Diamond;

/**
 * <h1> La classe Background </h1>
 * @author Benjamin
 *
 */
public class Background extends MotionlessElement{
	
	/** La declaration de la Constante SPRITE pour definir le fond */
    private static final Sprite SPRITE = new Sprite('.', "Background.png");

    /**
     * L'instationtion du fond
     * @see Background
     * @return WALKABLE
     */
    Background() {
        super(SPRITE, Permeability.WALKABLE);
    }

}