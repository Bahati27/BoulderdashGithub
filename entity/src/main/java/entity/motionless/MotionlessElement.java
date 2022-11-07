package entity.motionless;

import entity.Element;
import entity.Permeability;
import entity.Sprite;

/**
 * <h1> La classe MotionlessElment </h1>
 * @author Benjamin
 *
 */
public class MotionlessElement extends Element {
	
	/**
	 * Constructeur MotionlessElement
     * Instanciation d'un nouvel element motionless
     * @param sprite
     * @param permeability
     */
    MotionlessElement(final Sprite sprite, final Permeability permeability) {
        super(sprite, permeability);
    }

}