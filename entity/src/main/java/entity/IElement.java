package entity;


import fr.exia.showboard.ISquare;

/**
 *   <h1> L'Interface IElement </h1>
 * @author Benjamin
 *
 */
public interface IElement extends ISquare {
	/**
	 * Methode d'obtention de sprite.
	 * @see getSprite
	 * @return le sprite
	 */
	Sprite getSprite();

	/**
	 * Methode d'obtention de la perméabilité.
	 *@see getPermeability
	 * @return la perméabilité
	 */
	Permeability getPermeability();
	/**
	 * Methode d'obtention du changement de l'element
	 *@see getHasChanged
	 * @return le changement
	 */
	Boolean getHasChanged();
	/**
	 * Methode pour modifier du changement de l'element
	 *@see setHasChanged
	 */
	void setHasChanged(Boolean hasChanged);

}