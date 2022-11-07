package entity.mobile;
/**
 * <h1>La classe MobileElementsFactory </h1>
 * @author Benjamin
 *
 */
public class MobileElementsFactory {

	/**
	 * Le monstre vert
	 */
	private final static MonsterG monsterG = new MonsterG();
	/**
	 * Le monstre rouge
	 */
	private final static MonsterR monsterR = new MonsterR();
	/**
	 * Le diamand
	 */
	private final static Diamond diamond = new Diamond();
	/**
	 * Le rocher
	 */
	private final static Boulder boulder = new Boulder();

	/**
	 * La liste des elements mobile
	 */
	private static Mobile[] mobileElements = { monsterR, monsterG, diamond, boulder};
	/*
	 * 1. on a intacié tout les elements dans une liste
	 * 2. on va utiliser cette methode pour lire le symobe provenant du fichier text
	 */
	/**
	 * Methode pour obtenir un character provenant du fichier
	 * @param fileSymbol
	 * @return l'element mobile
	 */
	public static Mobile getFromFileSymbol(final char fileSymbol) {
		for (final Mobile mobileElement : mobileElements) {
			if (mobileElement.getSprite().getConsoleImage() == fileSymbol) {
				return mobileElement;
			}
		}
		return boulder;
	}

	/**
	 * La creation du monstre rouge
	 * @return le monstre rouge
	 */
	public static Mobile createMonsterR() {
		return monsterR;
	}
	
	/**
	 * La creation du monstre vert
	 * @return le monstre vert
	 */
	public static Mobile createMonsterG() {
		return monsterG;
	}

	/**
	 * La creation de diamand
	 * @return le diamand
	 */
	public static Mobile createDiamond() {
		return diamond;
	}

	/**
	 * La creation des roches
	 * @return la roche
	 */
	public static Mobile createRock() {
		return boulder;
	}

}