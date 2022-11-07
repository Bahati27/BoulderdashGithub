package entity.mobile;

import java.awt.Point;

import fr.exia.showboard.IBoard;
import entity.Element;
import entity.IMap;
import entity.Permeability;
import entity.Sprite;

/**
 * <h1>La classe Mobile </h1>
 * @author Benjamin
 */
abstract class Mobile extends Element implements IMobile {

	/**
	 * La declaration de la position :XY
	 * @see Point
	 * @return position
	 */
	private Point position;

	/** La vie
	 * @return alive 
	 */
	private Boolean alive = true;

	/** La carte 
	 * @see IMap
	 */
	private IMap map;

	/** Le tableau
	 * @see IBoard 
	 */
	private IBoard board;

	/**
	 * Instaciation d'un nouvelle element mobile
	 * @param sprite
	 * @param map
	 * @param permeability
	 */
	Mobile(final Sprite sprite, final IMap map, final Permeability permeability) {
		super(sprite, permeability);
		this.setMap(map);
		this.position = new Point();
	}

	/**
	 * Instaciation d'un nouvelle element mobile
	 * @param sprite
	 * @param permeability
	 */
	Mobile(final Sprite sprite, final Permeability permeability) {
		super(sprite, permeability);
		this.position = new Point();
	}

	/**
	 * Instaciation d'un nouvelle element mobile
	 * @param x
	 * @param y
	 * @param sprite
	 * @param map
	 * @param permeability
	 */
	Mobile(final int x, final int y, final Sprite sprite, final IMap map, final Permeability permeability) {
		this(sprite, map, permeability);
		this.setX(x);
		this.setY(y);
	}

	/**
	 * Methode pour deplacement vers le haut
	 * @see moveUp
	 * 
	 */
	@Override
	public void moveUp() {
		this.setY(this.getY() - 1);
		this.setHasMoved();
	}

	/**
	 * Methode pour deplacement vers la gauche
	 * @see moveLeft
	 */
	@Override
	public void moveLeft() {
		this.setX(this.getX() - 1);
		this.setHasMoved();
	}

	/**
	 * Methode pour deplacement vers le bas
	 * @see moveDown
	 * 
	 */
	@Override
	public void moveDown() {
		this.setY(this.getY() + 1);
		this.setHasMoved();
	}

	/**
	 * Methode pour deplacement vers la droite
	 * @see moveRight
	 */
	@Override
	public void moveRight() {
		this.setX(this.getX() + 1);
		this.setHasMoved();
	}

	/**
	 * Methode pour verifier si l'element ne bouge pas Ne fait rien
	 * @see doNothing
	 */
	@Override
	public void doNothing() {
		this.setHasMoved();
	}

	/**
	 * Methode pour definir si l'element a changer
	 * @see setHasMoved
	 */
	protected void setHasMoved() {
		this.getMap().setMobileHasChanged();
	}

	/**
	 * Methode pour Obtenir X
	 * @see getX
	 */
	@Override
	public final int getX() {
		return this.getPosition().x;
	}

	/**
	 * Methode pour modifier X
	 *
	 * @param new x
	 * @see setX
	 */
	public final void setX(final int x) {
		this.getPosition().x = x;

	}

	/**
	 * Methode pour Obtenir Y
	 * @see getY
	 */
	@Override
	public final int getY() {
		return this.getPosition().y;
	}

	/**
	 *  Methode pour modifier y.
	 *
	 * @param y
	 * @see setY
	 */
	public final void setY(final int y) {
		this.getPosition().y = y;

	}

	/**
	 * Methode pour Obtenir le map.
	 * @see IMap
	 * @return map
	 */
	public IMap getMap() {
		return this.map;
	}

	/**
	 * Methode pour modifier le map.
	 * 
	 * @param map the new map
	 * @see setMap
	 */
	private void setMap(final IMap map) {
		this.map = map;
	}

	/**
	 * Methode pour verifier si le joueur, le monstre est en vie
	 * @return this.alive	 
	 */
	@Override
	public Boolean isAlive() {
		return this.alive;
	}

	/**
	 * Methode pour verifier si le joueur, le monstre est mort
	 * @see die
	 */
	public void die() {
		this.alive = false;
		this.setHasMoved();
	}

	/**
	 * Methode pour obtenir la position
	 * @return this.position
	 */
	@Override
	public Point getPosition() {
		return this.position;
	}

	/**
	 * Methode pour modifier la position.
	 *
	 * @see Point
	 * @param position
	 */
	public void setPosition(final Point position) {
		this.position = position;
	}

	/**
	 * Methode pour obtenir le tableau
	 *
	 * @return board(tableau)
	 */
	protected IBoard getBoard() {
		return this.board;
	}

}