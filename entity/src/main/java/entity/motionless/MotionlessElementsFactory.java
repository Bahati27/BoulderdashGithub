package entity.motionless;

/**
 * <h1>La classe MotionlessElements</h1>
 *
 * @author Benjamin
 */
public abstract class MotionlessElementsFactory {

	private static final Wall wall = new Wall();
	private static final Door door = new Door();
	private static final Background background = new Background();
	private static final Ground ground = new Ground();

	/**
	 * La liste des elements possible appartenant au motionlessElements(elements statics)
	 */
	private static MotionlessElement[] motionlessElements = {wall, background, door, ground};

	/**
	 * La creation du mur
	 * @return wall
	 */
	public static MotionlessElement createWall() {
		return wall;
	}

	/**
	 * La creation de la porte
	 * @return door
	 */
	public static MotionlessElement createDoor() {
		return door;
	}

	/**
	 * La creation du fonds
	 * @return background
	 */
	public static MotionlessElement createBackground() {
		return background;
	}
	
	/**
	 * La creation du sol
	 * @return ground
	 */
	public static MotionlessElement createGround() {
		return ground;
	}

	/**
	 * Methode permettant d'obtenir le symbole se retrouvant sur la carte
	 * @param fileSymbol
	 * @return background
	 */
	public static MotionlessElement getFromFileSymbol(final char fileSymbol) {
		for (final MotionlessElement motionlessElement : motionlessElements) {
			if (motionlessElement.getSprite().getConsoleImage() == fileSymbol) {
				return motionlessElement;
			}
		}
		return background;
	}
}