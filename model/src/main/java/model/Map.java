package model;

import java.io.BufferedReader;

import entity.IElement;
import entity.IMap;
import entity.mobile.MobileElementsFactory;
import entity.motionless.MotionlessElementsFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * <h1>La classe Map.</h1>
 *
 * @author Benjamin
 */

class Map extends Observable implements IMap {

	/** La longueur. */
	private int width;

	/** La hauteur. */
	private int height;

	/** Liste des élément sur la carte. */
	private IElement[][] onTheMap;

	private boolean isCorrect = true;

	private List<Integer> hasChanged = new ArrayList<Integer>();

	/**
	 * Constructeur de la classe Map.
	 *
	 * @param fileName le nom du fichier où se trouve la carte
	 * @throws IOException Signale qu'une exception s'est produite.
	 */
	Map(final String fileName) throws IOException {
		super();
		this.loadFile(fileName);
	}

	/**
	 * Charger le fichier.
	 *
	 * @param fileName le nom du fichier
	 * @throws IOException Signale qu'une exception s'est produite.
	 */
	private void loadFile(final String fileName) throws IOException {
		// BufferedReader utilisée pour simplifier la lecture de texte à partir de flux d'entrée de caractères
		//InputStreamReader établit un pont entre les flux d'octets et les flux de caractères.
		//FileInputStream permet d'ouvrir un fichier en lecture et d'en lire le contenu.
		final BufferedReader buffer = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
		String line;

		try {
			line = buffer.readLine();
			this.setWidth(Integer.parseInt(line));
			line = buffer.readLine();
			this.setHeight(Integer.parseInt(line));
		} catch (Exception | Error e) {
			isCorrect = false;
		}

		if (isCorrect) {
			this.onTheMap = new IElement[this.getWidth()][this.getHeight()];

			int y = 0;
			line = buffer.readLine();
			while (line != null) {
				if (line.toCharArray().length == this.width) {
					for (int x = 0; x < line.toCharArray().length; x++) {
						if (line.toCharArray()[x] == 'R' || line.toCharArray()[x] == 'O' || line.toCharArray()[x] == 'G' || line.toCharArray()[x] == '*') {
							this.setOnTheMapXY(MobileElementsFactory.getFromFileSymbol(line.toCharArray()[x]), x, y);
						} else {
							this.setOnTheMapXY(MotionlessElementsFactory.getFromFileSymbol(line.toCharArray()[x]), x, y);
						}
					}
				} else {
					this.isCorrect = false;
				}
				line = buffer.readLine();
				y++;
			}
			buffer.close();

			if (y != this.height) {
				this.isCorrect = false;
			}
		}
	}

	/*
	 * Définit les éléments de la liste
	 *
	 */
	@Override
	public final IElement getOnTheMapXY(final int x, final int y) {
		return this.onTheMap[x][y];
	}

	/**
	 * Modifie et rajoute les éléments de la liste sur la carte.
	 *
	 * @param element les éléments
	 * @param x       l'axe de X
	 * @param y       l'axe de Y
	 */
	public void setOnTheMapXY(IElement element, int x, int y) {
		this.onTheMap[x][y] = element;
		this.hasChanged.add(x);
		this.hasChanged.add(y);

	}

	/*
	 * Change la position des éléments mobiles
	 *
	 */
	@Override
	public final void setMobileHasChanged() {
		this.setChanged();
		this.notifyObservers();
	}

	/*
	 * Récupère la longueur
	 * 
	 */
	@Override
	public final int getWidth() {
		return this.width;
	}

	/**
	 * Modifie la longueur.
	 *
	 * @param width the new width
	 */
	private void setWidth(final int width) {
		this.width = width;
	}

	/*
	 * Récupère la hauteur
	 * 
	 */
	@Override
	public final int getHeight() {
		return this.height;
	}

	/**
	 * Modifie la hauteur.
	 *
	 * @param height the new height
	 */
	private void setHeight(final int height) {
		this.height = height;
	}

	/*
	 * Récupère l'observable
	 * 
	 */
	@Override
	public Observable getObservable() {
		return this;
	}

	public boolean isCorrect() {
		return isCorrect;
	}


	public List<Integer> getHasChanged() {
		return hasChanged;
	}

}
