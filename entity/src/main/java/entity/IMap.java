package entity ;

import java.util.List;
import java.util.Observable;

/**
 * <h1>L'interface IMap.</h1>
 *
 * @author Benjamin
 */
public interface IMap {

    /**
     * Methode d'obtention de la longueur
     * @see getWidth
     * @return le width(la longueur)
     */
    int getWidth();

    /**
     * Methode d'obtention de la largeur
     * @see getHeight
     * @return le Height(la largeur)
     */
    int getHeight();

    /**
     * Methode d'obtention les elements sur la cartes/map
     * l'axe des abscisses
     * @param x
     * l'axe des ordonnées
     * @param y
     * @see getOnTheMapXY
     * @return the on the road XY
     */
    IElement getOnTheMapXY(int x, int y);
    
    /**
     * Methode pour modifier les elements sur la cartes/map
     * l'élément
     * @param element
     * l'axe des abscisses
     * @param x
     * l'axe des ordonnées
     * @param y
     */
    void setOnTheMapXY(IElement element, final int x, final int y);

    /**
     * Methode pour faire des modifications sur le changement de l'element
     */
    void setMobileHasChanged();

    /**
     * Methode d'obtention de la la classe observable.
     *@see getObservable
     * @return observable
     */
    Observable getObservable();
    /**
     * Methode pour verifier si le changement est correct
     * @see isCorrect
     * @return getHasChanged
     */
    boolean isCorrect();

    List<Integer> getHasChanged();


}