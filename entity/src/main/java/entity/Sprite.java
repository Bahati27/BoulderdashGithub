package entity;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * <h1>la classe Sprite </h1>
 *
 * @author Benjamin
 */

public class Sprite {

	/** L'image.
	 * @return image */
    private Image   image;

    /** 
     * Le nom de l'image.
     * @return imageName */
    private String  imageName;

    /** Le console de l'image.
     * @return consoleImage */
    private char    consoleImage;

    /** Le chargement de l'image.
     * @return imageLoaded */
    private boolean imageLoaded;
    /**
     * Le constructeur de la classe Sprite avec 2 parametres
     * @see Sprite
     * @param character
     * @param imageName
     */
    public Sprite(final char character, final String imageName) {
        this.setConsoleImage(character);
        this.setImageName(imageName);
    }
    /**
     * Le constructeur de la classe Sprite
     * @see Sprite
     * @param character
     */
    public Sprite(final char character) {
        this(character, "noimage.jpg");
    }

    public final Image getImage() {
        return this.image;
    }

    public final void loadImage() throws IOException {
        this.setImage(ImageIO.read(new File("sprites\\" + this.getImageName())));
    }

    public final char getConsoleImage() {
        return this.consoleImage;
    }

    private void setImage(final Image image) {
        this.image = image;
    }

    private void setConsoleImage(final char consoleImage) {
        this.consoleImage = consoleImage;
    }

    public final String getImageName() {
        return this.imageName;
    }

    private void setImageName(final String imageName) {
        this.imageName = imageName;
    }

    public final boolean isImageLoaded() {
        return this.imageLoaded;
    }

    public final void setImageLoaded(final boolean isImageLoaded) {
        this.imageLoaded = isImageLoaded;
    }
}
