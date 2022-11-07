package entity.mobile;

import java.io.IOException;

import entity.IMap;
import entity.Permeability;
import entity.Sprite;
import entity.motionless.MotionlessElementsFactory;

/**
 * <h1>The MyVehicle Class.</h1>
 *
 * @author Laetitia
 */
public class MyPlayer extends Mobile {

	/** La declaration de la Constante SPRITE pour definir le joueur quand il fait rien */
	private static final Sprite sprite = new Sprite('H', "pNope.png");
	/** La declaration de la Constante SPRITE pour definir le joueur quand il va vers la gauche */
	private static final Sprite spriteTurnLeft = new Sprite('H', "pLeft.png");
	/** La declaration de la Constante SPRITE pour definir le joueur quand il va vers la droite */
	private static final Sprite spriteTurnRight = new Sprite('H', "pRight.png");
	/** La declaration de la Constante SPRITE pour definir le joueur quand il va vers le haut */
	private static final Sprite spriteTurnUp = new Sprite('H', "pUp.png");
	/** La declaration de la Constante SPRITE pour definir le joueur quand il va vers le bas */
	private static final Sprite spriteTurnDown = new Sprite('H', "pDown.png");
	/** La declaration de la Constante SPRITE pour definir le joueur quand il meurt */
	private static final Sprite spriteDead = new Sprite('H', "pDead.png");
	/** La declaration de la Constante SPRITE pour definir le joueur quand il gagne */
	private static final Sprite spriteWin = new Sprite('H', "pWin.png");

	/** Le compteur de diamand
	 * @see nb_diamonds 
	 */
	private int nb_diamonds;

	/**
	 * Instanciation d'un nouveau joueur
	 *
	 * @see MyPlayer
	 * @param x   the x
	 * @param y   the y
	 * @param map the map
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public MyPlayer(final int x, final int y, final IMap map) throws IOException {
		super(x, y, sprite, map, Permeability.WALL);
		spriteTurnLeft.loadImage();
		spriteTurnRight.loadImage();
		spriteTurnUp.loadImage();
		spriteTurnDown.loadImage();
		spriteDead.loadImage();
		spriteWin.loadImage();
	}


	/**
	 * Methode pour deplacer le joueur vers la gauche
	 * @see moveLeft
	 */
	@Override
	public final void moveLeft() {
		if ((getMap().getOnTheMapXY((getX() - 1), ((getY()))).getPermeability() == Permeability.WALL) || (getMap().getOnTheMapXY((getX() - 1), ((getY()))).getPermeability() == Permeability.BOULDER)) {
			doNothing();
			pushBoulderLeft();
			this.setSprite(spriteTurnLeft);
		} else {
			super.moveLeft();
			this.setSprite(spriteTurnLeft);
			dig();
			grabDiamond();
			this.setHasMoved();
		}
	}

	/**
	 * Methode pour deplacer le joueur vers la droite
	 * @see moveRight
	 */
	@Override
	public final void moveRight() {
		if (getMap().getOnTheMapXY(getX() + 1, getY()).getPermeability() == Permeability.WALL || (getMap().getOnTheMapXY((getX() + 1), ((getY()))).getPermeability() == Permeability.BOULDER)) {
			doNothing();
			pushBoulderRight();
			this.setSprite(spriteTurnRight);
		} else {
			super.moveRight();
			this.setSprite(spriteTurnRight);
			dig();
			grabDiamond();
			this.setHasMoved();
		}
	}

	/**
	 * Methode pour deplacer le joueur vers le bas
	 * @see moveDown
	 */
	public final void moveDown() {
		if ((getMap().getOnTheMapXY((getX()), (getY() + 1)).getPermeability() != Permeability.WALL) && (getMap().getOnTheMapXY((getX()), (getY() + 1))).getPermeability() != Permeability.BOULDER) {
			super.moveDown();
			this.setSprite(spriteTurnDown);
			dig();
			grabDiamond();
			this.setHasMoved();
		} else {
			doNothing();
		}
	}

	/**
	 * Methode pour deplacer le joueur vers le haut
	 * @see moveUp
	 */
	public final void moveUp() {
		if ((getMap().getOnTheMapXY((getX()), (getY() - 1)).getPermeability() != Permeability.WALL) && (getMap().getOnTheMapXY((getX()), (getY() - 1))).getPermeability() != Permeability.BOULDER) {
			grabDiamond();
			super.moveUp();
			this.setSprite(spriteTurnUp);
			dig();
			grabDiamond();
			this.setHasMoved();
		} else {
			doNothing();
		}
	}

	/**
	 * Methode pour verifier si le joueur est mort
	 * @see die
	 * 
	 */
	@Override
	public final void die() {
		super.die();
		this.setSprite(spriteDead);
	}

	/**
	 * Methode pour verifier si le joueur a gagné
	 * @see win
	 *
	 */
	@Override
	public final void win() {
		this.setSprite(spriteWin);
	}

	/**
	 *  Methode pour verifier si le joueur ne fait rien
	 * @see doNothing
	 */
	@Override
	public final void doNothing() {
 		super.doNothing();
		this.setSprite(sprite);
		this.dig();
		this.grabDiamond();
	}

	/**
	 * Methode pour creuser
	 * @see dig
	 */
	public void dig() {
		if (this.getMap().getOnTheMapXY(this.getX(), this.getY()).getPermeability() == Permeability.DIGGABLE) {
			this.getMap().setOnTheMapXY(MotionlessElementsFactory.createBackground(), this.getX(), this.getY());
			this.setHasMoved();
		}
	}


	/**
	 * Methode pour obtenir le diamand horizontalement
	 * @see grabDiamond
	 * @return nb_diamonds++
	 */
	public void grabDiamond() {
		if (this.getMap().getOnTheMapXY(this.getX(), this.getY()).getPermeability() == Permeability.DIAMOND) {
			this.getMap().setOnTheMapXY(MotionlessElementsFactory.createBackground(), this.getX(), this.getY());
			nb_diamonds++;
		}
	}

	/**
	 * Methode permettant au joueur de pousser le roche vers la droite
	 * @see pushBoulderRight
	 */
	public void pushBoulderRight() {
		if (this.getMap().getOnTheMapXY(this.getX() + 1, this.getY()).getPermeability() == Permeability.BOULDER && this.getMap().getOnTheMapXY(this.getX() + 2, this.getY()).getPermeability() == Permeability.WALKABLE) {
			this.getMap().setOnTheMapXY(MotionlessElementsFactory.createBackground(), this.getX() + 1, this.getY());
			super.moveRight();
			this.getMap().setOnTheMapXY(MobileElementsFactory.createRock(), this.getX() + 1, this.getY());
			this.setHasMoved();
		}
	}

	/**
	 * Methode permettant au joueur de pousser le roche vers la gauche
	 * @see pushBoulderLeft
	 */
	public void pushBoulderLeft() {
		if (this.getMap().getOnTheMapXY(this.getX() - 1, this.getY()).getPermeability() == Permeability.BOULDER && this.getMap().getOnTheMapXY(this.getX() - 2, this.getY()).getPermeability() == Permeability.WALKABLE) {
			this.getMap().setOnTheMapXY(MotionlessElementsFactory.createBackground(), this.getX() - 1, this.getY());
			super.moveLeft();
			this.getMap().setOnTheMapXY(MobileElementsFactory.createRock(), this.getX() - 1, this.getY());
			this.setHasMoved();
		}
	}

	/**
	 * Methode pour obtenir le diamand
	 * @see getDiamonds
	 * @return nb_diamonds
	 */
	public int getDiamonds() {
		return nb_diamonds;
	}
}