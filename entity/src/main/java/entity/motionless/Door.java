package entity.motionless;

import entity.Permeability;
import entity.Sprite;

/**
 * <h1> La classe Door </h1>
 * @author Benjamin
 *
 */
public class Door extends MotionlessElement {
	
	/** La declaration de la Constante SPRITE pour definir la porte */
	    private static final Sprite SPRITE = new Sprite('D', "Door.png");

	    /**
	     * L'instationtion de l'élément porte
	     * @see Door
	     * @return EXIT
	     */
	    Door() {
	        super(SPRITE, Permeability.EXIT);
	    }
	}