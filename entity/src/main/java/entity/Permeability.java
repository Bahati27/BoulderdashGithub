package entity;

/**
 * <h1>La class enumeratrice Permeability.</h1>
 *
 * @author Benjamin
 */
public enum Permeability {

	/** Le mur.
	 * @return wall */
	WALL,
	/** Accessible pour la marche.
	 * @return walkable */
	WALKABLE,
	/** Tuable 
	 * @return killable*/
	KILLABLE,
	/** Rocher. 
	 * @return boulder*/
	BOULDER,
	/** La sortie.
	 * @return exit */
	EXIT,
	/** Creusable
	 * @return diggable */
	DIGGABLE,
	/** Le diamand
	 * @return diamond*/
	DIAMOND;

}