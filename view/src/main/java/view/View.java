package view;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.Observable;

import contract.IOrderPerformer;
import contract.IView;
import contract.UserOrder;
import entity.mobile.IMobile;

import javax.swing.*;

import fr.exia.showboard.BoardFrame;

import entity.IMap;

/**
 * <h1> La classe View <h1>
 *
 * @author Benjamin
 */
public final class View extends Observable implements IView, KeyListener {

	/** La fenetre du jeu (Frame) */
	private BoardFrame boardFrame;

	/** La vue du jeu (partie de la carte que nous voyons) */
	final Rectangle gameView = new Rectangle(0, 0, 11, 11);

	/** La carte. */
	private IMap map;

	/** Le joueur */
	private IMobile myPlayer;

	/** L'exécutant de la commande. */
	private IOrderPerformer orderPerformer;


	/**
	 * 
	 * Constructeur de la classe view.
	 * 
	 * @param map
	 * @param myPlayer
	 * @throws IOException
	 */
	public View(final IMap map, final IMobile myPlayer) {
		try {
			this.setmap(map);
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.setmyPlayer(myPlayer);
		try {
			this.getmyPlayer().getSprite().loadImage();
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.boardFrame = new BoardFrame();
		this.boardFrame.setDimension(new Dimension(this.getmap().getWidth(), this.getmap().getHeight()));
		this.boardFrame.setDisplayFrame(this.gameView);
		this.boardFrame.addKeyListener(this);

		this.boardFrame.addPawn(this.getmyPlayer());

		this.updateView();
	}

	/*
	 *
	 *
	 * Deplacement du joueur par rapport à la carte.
	 */
	@Override
	public final void followMyPlayer() {
		if (this.getmyPlayer().getX() >= this.gameView.width / 2 && this.getmyPlayer().getX() <= this.getmap().getWidth() - this.gameView.width / 2 - 1) {
			this.getgameView().x = this.getmyPlayer().getX() - this.gameView.width / 2;
		}
		if (this.getmyPlayer().getY() >= this.gameView.height / 2 && this.getmyPlayer().getY() <= this.getmap().getHeight() - this.gameView.height / 2 - 1) {
			this.getgameView().y = this.getmyPlayer().getY() - this.gameView.height / 2;
		}
	}

	/**
	 *  Mettre à jour la vue avec la nouvelle position des cadres.
	 */
	public void updateView() {
		for (int x = 0; x < this.getmap().getWidth(); x++) {
			for (int y = 0; y < this.getmap().getHeight(); y++) {
				boardFrame.addSquare(this.map.getOnTheMapXY(x, y), x, y);
			}
		}
		this.getmap().getObservable().addObserver(boardFrame.getObserver());
	}

	/**
	 * Code clé pour la commande de l'utilisateur.
	 *
	 * @param keyCode the key code
	 * @return the user order
	 */
	private static UserOrder keyCodeToUserOrder(final int keyCode) {
		UserOrder userOrder;
		switch (keyCode) {
		case KeyEvent.VK_RIGHT:
			userOrder = UserOrder.RIGHT;
			break;
		case KeyEvent.VK_LEFT:
			userOrder = UserOrder.LEFT;
			break;
		case KeyEvent.VK_UP:
			userOrder = UserOrder.UP;
			break;
		case KeyEvent.VK_DOWN:
			userOrder = UserOrder.DOWN;
			break;
		default:
			userOrder = UserOrder.NOP;
			break;
		}
		return userOrder;
	}

	/*
	 * 
	 *
	 * Affiche les différents message d'erreurs lors de l'ouverture de notre fenetre
	 * 
	 * 
	 */
	@Override
	public final void displayMessage(final String message) {
		//JOptionPane.showMessageDialog(null, message);
		int choice = JOptionPane.showInternalConfirmDialog(null, message, "BoulderDash",JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

		if (choice == 0) {
			boardFrame.dispose();
		} else {
			System.exit(0);
		}
	}

	/*
	 * 
	 * Gérer l'ordre de type de touche à utiliser par l'utilisateur
	 * 
	 */
	@Override
	public void keyTyped(final KeyEvent keyEvent) {
		// Nop
		try {
			this.getOrderPerformer().orderPerform(keyCodeToUserOrder(keyEvent.getKeyCode()));
		} catch (final IOException exception) {
			exception.printStackTrace();
		}
	}

	/*
	 * 
	 * 
	 * Gérer le commande de touche pessée par l'utilisateur
	 */
	@Override
	public final void keyPressed(final KeyEvent keyEvent) {
		try {
			this.getOrderPerformer().orderPerform(keyCodeToUserOrder(keyEvent.getKeyCode()));
		} catch (final IOException exception) {
			exception.printStackTrace();
		}
	}

	/*
	 * 
	 * 
	 * 
	 */
	@Override
	public void keyReleased(final KeyEvent keyEvent) {
		// Nop
	}

	/**
	 * Récupère la carte.
	 *
	 * @return La carte
	 */
	private IMap getmap() {
		return this.map;
	}

	/**
	 * Affiche la carte
	 *
	 * @param map the new map
	 * @throws IOException Signale qu'une exception s'est produite.
	 */
	private void setmap(final IMap map) throws IOException {
		this.map = map;
		for (int x = 0; x < this.getmap().getWidth(); x++) {
			for (int y = 0; y < this.getmap().getHeight(); y++) {
				this.getmap().getOnTheMapXY(x, y).getSprite().loadImage();
			}
		}
	}

	/**
	 * Récupère le joueur.
	 *
	 * @return Le joueur
	 */
	private IMobile getmyPlayer() {
		return this.myPlayer;
	}

	/**
	 * Modifie le joueur.
	 * 
	 * @param myPlayer
	 */
	private void setmyPlayer(final IMobile myPlayer) {
		this.myPlayer = myPlayer;
	}


	/**
	 * Récupère la gameView(la vue du jeu).
	 *
	 * @return la gameView(la vue du jeu)
	 */
	private Rectangle getgameView() {
		return this.gameView;
	}

	/**
	 *  Récupère l'ordre de performance.
	 *
	 * @return l'ordre de performance
	 */
	private IOrderPerformer getOrderPerformer() {
		return this.orderPerformer;
	}

	/**
	 * Modifie l'ordre de performance.
	 *
	 * @param orderPerformer nouvel ordre de performance
	 */
	public final void setOrderPerformer(final IOrderPerformer orderPerformer) {
		this.orderPerformer = orderPerformer;
	}

	/**
	 * Récupère l'ordre BoardFrame.
	 *
	 * @return le BoardFrame
	 */
	public BoardFrame getBoardFrame() {
		return boardFrame;
	}
}