package entity;

import java.awt.Image;

public abstract class Element implements IElement {
	
	/**
	 *   <h1> La classe Element </h1>
	 * @author Benjamin
	 *
	 */

    /**
     * Le sprite
     * @see Sprite
     */
    private Sprite sprite;

    /**
     * la permeabilite
     * @see Permeability
     */
    private Permeability permeability;

    private Boolean hasChanged = false;

    /**
     * @param sprite
     * @param permeability
     */
    public Element(final Sprite sprite, final Permeability permeability) {
        this.setSprite(sprite);
        this.setPermeability(permeability);
    }


    /**
     * La methode permettant d'obtenir le Sprite
     * @see Sprite
     */
    public final Sprite getSprite() {
        return this.sprite;
    }

    /**
     * Sets the sprite.
     *La methode permettant de modifier le sprite
     * @param this.sprite = new sprite
     */

    protected final void setSprite(final Sprite sprite) {
        this.sprite = sprite;
    }

    /**
     * La methode permettant de retourner la permeabilité obetenu 
     * 
     */
    public final Permeability getPermeability() {
        return this.permeability;
    }

    /**
     *La methode permettan de modifier la permeabilité
     * @param new permeability
     */
    private void setPermeability(final Permeability permeability) {
        this.permeability = permeability;
    }

    /**
     * La methode permettant d'obtenir une image
     * @see getImage
     *
     */
    public final Image getImage() {
        return this.getSprite().getImage();
    }

	/**
	 * La methode permettant d'obtenir le changement des elements
	 * @see getHasChanged
	 * @param hasChanged
	 */
    public Boolean getHasChanged() {
        return hasChanged;
    }
    /**
	 * La methode permettant le midification de changement des elements
	 * @see setHasChanged
	 */
    public void setHasChanged(Boolean hasChanged) {
        this.hasChanged = hasChanged;
    }
}