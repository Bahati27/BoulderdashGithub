package model;

import java.io.IOException;

import contract.IModel;
import entity.IMap;
import entity.mobile.IMobile;
import entity.mobile.MyPlayer;

/**
 * <h1>La classe BoulderDashModel.</h1>
 *
 * @author Benjamin
 *
 */
public class BoulderDashModel implements IModel {

	/** La carte. */
	private IMap map;

	/** Le joueur. */
	private IMobile myPlayer;

	/**
	 * Le constructeur de la classe BoulderDashModel.
	 *
	 * @param myPlayerStartX la position de départ de mon joueur X
	 * @param myPlayerStartY la position de départ de mon joueur Y
	 * @throws IOException Signale qu'une exception s'est produite.
	 */
	public BoulderDashModel(final String mapFile, final int myPlayerStartX, final int myPlayerStartY) {
		try {
			this.setMap(new Map(mapFile));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			this.setMyPlayer(new MyPlayer(myPlayerStartX, myPlayerStartY, this.getMap()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Récupère la carte
	 */
	@Override
	public final IMap getMap() {
		return this.map;
	}

	/**
	 * Modifie la carte.
	 *
	 * @param map la carte modifié
	 */
	private void setMap(final IMap map) {
		this.map = map;
	}

	/**
	 * Récupère le joueur
	 */
	@Override
	public final IMobile getMyPlayer() {
		return this.myPlayer;
	}

	/**
	 * Modifie le joueur.
	 *
	 * @param myPlayer Le joueur modifié
	 */
	private void setMyPlayer(final IMobile myPlayer) {
		this.myPlayer = myPlayer;
	}

}
