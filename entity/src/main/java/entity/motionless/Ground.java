package entity.motionless;

import entity.Permeability;
import entity.Sprite;

/**
 * <h1> La classe Ground </h1>
 * @author Benjamin
 *
 */
public class Ground extends MotionlessElement{
	/** La declaration de la Constante SPRITE pour definir le sol */
    private static final Sprite SPRITE = new Sprite(' ', "Ground.png");

    /**
     * L'instationtion de l'élément sole
	 * @see Ground
     * @return DGGABLE
     */
    Ground() {
        super(SPRITE, Permeability.DIGGABLE);
    }

}