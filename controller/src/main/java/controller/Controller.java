package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import contract.IOrderPerformer;
import contract.IBoulderDashController;
import contract.IModel;
import contract.IView;
import contract.UserOrder;
import entity.Permeability;
import entity.mobile.MobileElementsFactory;
import entity.motionless.MotionlessElementsFactory;
/**
 * <h1> La classe Controller </h1>
 * @author Kelvin
 *
 */
public final class Controller implements IBoulderDashController, IOrderPerformer,Runnable {

    private static final int speed = 200;
    private static final int PAUSE = 600;
    private int compteur;
	private IView view;
    private IModel model;
    private UserOrder stackOrder;
    private final int diamondGoal = 12;
    private boolean hasWon = false;
    private final Random rand = new Random();
    private int rdirection;
    private int gDirection;
    private boolean canChange = true;
    public Controller(final IView view, final IModel model) {
        this.setView(view);
        this.setModel(model);
        this.clearStackOrder();
        this.compteur=100;
        Thread compteur=new Thread(this);
        compteur.start();
    }
    @Override
    public final void play() {
        while (this.getModel().getMyPlayer().isAlive() == true && hasWon == false) {

            // --- Game Speed ---
            try {
                Thread.sleep(speed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // --- Joueur ---
            this.movePlayer();
            this.killPlayer();
            this.winPlayer();

            gDirection = gDirection + 1;
            rdirection = rand.nextInt(4);

            // --- Mis à jour des elements se trouvant sur la carte
            for (int y = this.getModel().getMap().getHeight() - 1; y > 0; y--) {
                for (int x = this.getModel().getMap().getWidth() - 1; x > 0; x--) {

                    for (int g = 0; g < this.getModel().getMap().getHasChanged().size(); g = g + 2) {
                        if (this.getModel().getMap().getHasChanged().get(g) == x || this.getModel().getMap().getHasChanged().get(g + 1) == y) {
                            this.canChange = false;
                        }
                    }

                    if (canChange) {
                        // --- Rocher ---
                        this.gravityBoulder(x, y);
                        this.gravityDiagBoulder(x, y);

                        // --- Diamands ---
                        this.gravityDiamond(x, y);
                        this.gravityDiagDiamond(x, y);

                        // --- Monstres ---
                        this.killMonster(x, y);
                        this.moveGMonster(x, y);
                        this.moveRMonster(x, y);
                    }
                    this.canChange = true;
                }
            }

            // --- Mis à jour de la vue ---
            this.getModel().getMap().getHasChanged().clear();
            this.canChange = true;
            this.getView().followMyPlayer();
            this.getView().updateView();
            this.getView().getBoardFrame().setTitle("BoulderDash - Diamond Recuperer: " + this.getModel().getMyPlayer().getDiamonds()+"      Le temps restant: "+this.getCompteur());
        }
    }


    /**
     * la methode deplacer le joueur
     * @see movePlayer   
     */
    public void movePlayer() {
        switch (this.getStackOrder()) {
            case UP:
                this.getModel().getMyPlayer().moveUp();

                break;
            case DOWN:
                this.getModel().getMyPlayer().moveDown();

                break;
            case RIGHT:
                this.getModel().getMyPlayer().moveRight();

                break;
            case LEFT:
                this.getModel().getMyPlayer().moveLeft();

                break;
            case NOP:
            default:
                this.getModel().getMyPlayer().doNothing();
                break;
        }
        this.clearStackOrder();
    }

    /**
     * La methode permettant de tuer le joueur
     * @see killPlayer
     */
    public void killPlayer() {
        if ((this.getModel().getMap().getOnTheMapXY((this.getModel().getMyPlayer().getX()), ((this.getModel().getMyPlayer().getY()))).getPermeability() == Permeability.KILLABLE)) {
            this.getModel().getMyPlayer().die();
            this.getView().displayMessage("Vous avez échoué! Voulez-vous continuer ?");
        }else if(this.getCompteur()==0) {
        	this.getModel().getMyPlayer().die();
            this.getView().displayMessage("Vous avez échoué! Voulez-vous continuer ?");
        }
    }

    public void winPlayer() {
        if ((this.getModel().getMap().getOnTheMapXY((this.getModel().getMyPlayer().getX()), ((this.getModel().getMyPlayer().getY()))).getPermeability() == Permeability.EXIT) && this.getModel().getMyPlayer().getDiamonds() >= diamondGoal) {
            this.hasWon = true;
            this.getModel().getMyPlayer().win();
            this.getView().displayMessage("Vous avez gagné, Felicitation! Voulez-vous recommencer ?");
        }
    }


    /**
     * La methode permettant de deplacer le roche vers le bas
     * @see gravityBoulder
     */
    public void gravityBoulder(int x, int y) {
        if (this.getModel().getMap().getOnTheMapXY(x, y).getPermeability() == Permeability.BOULDER && this.getModel().getMap().getOnTheMapXY(x, y + 1).getPermeability() == Permeability.WALKABLE) {
            if (x != this.getModel().getMyPlayer().getX() || y + 1 != this.getModel().getMyPlayer().getY()) {
                this.getModel().getMap().setOnTheMapXY(MotionlessElementsFactory.createBackground(), x, y);
                this.getModel().getMap().setOnTheMapXY(MobileElementsFactory.createRock(), x, y + 1);
            }
        }
    }

    /**
     * La methode permettant de deplacer le roche horizontalement
     * @see gravityDiagBoulder
     */
    public void gravityDiagBoulder(int x, int y) {
        if (this.getModel().getMap().getOnTheMapXY(x, y).getPermeability() == Permeability.BOULDER && this.getModel().getMap().getOnTheMapXY(x, y + 1).getPermeability() == Permeability.BOULDER) {
            if (this.getModel().getMap().getOnTheMapXY(x + 1, y + 1).getPermeability() == Permeability.WALKABLE && this.getModel().getMap().getOnTheMapXY(x + 1, y).getPermeability() == Permeability.WALKABLE) {
                if (x + 1 != this.getModel().getMyPlayer().getX() || y + 1 != this.getModel().getMyPlayer().getY()) {
                    if (x + 1 != this.getModel().getMyPlayer().getX() || y != this.getModel().getMyPlayer().getY()) {
                        this.getModel().getMap().setOnTheMapXY(MotionlessElementsFactory.createBackground(), x, y);
                        this.getModel().getMap().setOnTheMapXY(MobileElementsFactory.createRock(), x + 1, y);
                    }
                }
            }
        }
        if (this.getModel().getMap().getOnTheMapXY(x, y).getPermeability() == Permeability.BOULDER && this.getModel().getMap().getOnTheMapXY(x, y + 1).getPermeability() == Permeability.BOULDER) {
            if (this.getModel().getMap().getOnTheMapXY(x - 1, y + 1).getPermeability() == Permeability.WALKABLE && this.getModel().getMap().getOnTheMapXY(x - 1, y).getPermeability() == Permeability.WALKABLE) {
                if (x - 1 != this.getModel().getMyPlayer().getX() || y + 1 != this.getModel().getMyPlayer().getY()) {
                    if (x - 1 != this.getModel().getMyPlayer().getX() || y != this.getModel().getMyPlayer().getY()) {
                        this.getModel().getMap().setOnTheMapXY(MotionlessElementsFactory.createBackground(), x, y);
                        this.getModel().getMap().setOnTheMapXY(MobileElementsFactory.createRock(), x - 1, y);
                    }
                }
            }
        }
    }


    /**
     * La methode permettant de deplacer le diamant verticalement
     * @see gravityDiamond
     */
    public void gravityDiamond(int x, int y) {
        if (this.getModel().getMap().getOnTheMapXY(x, y).getPermeability() == Permeability.DIAMOND && this.getModel().getMap().getOnTheMapXY(x, y + 1).getPermeability() == Permeability.WALKABLE) {
            if (x != this.getModel().getMyPlayer().getX() || y + 1 != this.getModel().getMyPlayer().getY()) {
                this.getModel().getMap().setOnTheMapXY(MotionlessElementsFactory.createBackground(), x, y);
                this.getModel().getMap().setOnTheMapXY(MobileElementsFactory.createDiamond(), x, y + 1);
            } else if (x == this.getModel().getMyPlayer().getX() && y + 1 == this.getModel().getMyPlayer().getY()) {
                this.getModel().getMap().setOnTheMapXY(MotionlessElementsFactory.createBackground(), x, y);
                this.getModel().getMap().setOnTheMapXY(MobileElementsFactory.createDiamond(), x, y + 1);
            }
        }
    }

    /**
     *La methode permettant de deplacer le diamant horizontalement
     * @see gravityDiagDiamond
     */
    public void gravityDiagDiamond(int x, int y) {
        if (this.getModel().getMap().getOnTheMapXY(x, y).getPermeability() == Permeability.DIAMOND && this.getModel().getMap().getOnTheMapXY(x, y + 1).getPermeability() == Permeability.DIAMOND) {
            if (this.getModel().getMap().getOnTheMapXY(x + 1, y + 1).getPermeability() == Permeability.WALKABLE && this.getModel().getMap().getOnTheMapXY(x + 1, y).getPermeability() == Permeability.WALKABLE) {
                this.getModel().getMap().setOnTheMapXY(MotionlessElementsFactory.createBackground(), x, y);
                this.getModel().getMap().setOnTheMapXY(MobileElementsFactory.createDiamond(), x + 1, y);
            }
        }
        if (this.getModel().getMap().getOnTheMapXY(x, y).getPermeability() == Permeability.DIAMOND && this.getModel().getMap().getOnTheMapXY(x, y + 1).getPermeability() == Permeability.DIAMOND) {
            if (this.getModel().getMap().getOnTheMapXY(x - 1, y + 1).getPermeability() == Permeability.WALKABLE && this.getModel().getMap().getOnTheMapXY(x - 1, y).getPermeability() == Permeability.WALKABLE) {
                this.getModel().getMap().setOnTheMapXY(MotionlessElementsFactory.createBackground(), x, y);
                this.getModel().getMap().setOnTheMapXY(MobileElementsFactory.createDiamond(), x - 1, y);
            }
        }
    }


    /**
     * La methode permettant de tuer le monstre
     * @see killMonster
     */
    public void killMonster(int x, int y) {
        if (this.getModel().getMap().getOnTheMapXY(x, y).getPermeability() == Permeability.KILLABLE && this.getModel().getMap().getOnTheMapXY(x, y - 1).getPermeability() == Permeability.BOULDER || this.getModel().getMap().getOnTheMapXY(x, y).getPermeability() == Permeability.KILLABLE && this.getModel().getMap().getOnTheMapXY(x, y - 1)
                .getPermeability() == Permeability.DIAMOND) {
            this.getModel().getMap().setOnTheMapXY(MobileElementsFactory.createDiamond(), x, y);

            for (int i = x - 1; i < x + 2; i++) {
                for (int j = y - 1; j < y + 2; j++) {
                    if (getModel().getMap().getOnTheMapXY(i, j).getPermeability() == Permeability.WALKABLE) {
                        this.getModel().getMap().setOnTheMapXY(MobileElementsFactory.createDiamond(), i, j);
                    }
                }
            }
        }
    }

    /**
     * La methode permettant le deplacement du monstre vert
     * @see moveGMonster
     */
    public void moveGMonster(int x, int y) {
        if (gDirection <= 10) {
            MGMoveRight(x, y);
        } else if (gDirection <= 20) {
            MGMoveLeft(x, y);
        } else {
            gDirection = 0;
        }

    }

    /**
     * La methode permettant le deplacement du monstre vert vers la droite
     * @see MGMoveRight
     */
    public void MGMoveRight(int x, int y) {
        if (this.getModel().getMap().getOnTheMapXY(x, y).getSprite().getConsoleImage() == 'G' && this.getModel().getMap().getOnTheMapXY(x + 1, y).getPermeability() == Permeability.WALKABLE) {
            this.getModel().getMap().setOnTheMapXY(MotionlessElementsFactory.createBackground(), x, y);
            this.getModel().getMap().setOnTheMapXY(MobileElementsFactory.createMonsterG(), x + 1, y);
        }
    }

     /**
     * La methode permettant le deplacement du monstre vert vers la gauche
     * @see MGMoveLeft
     */
    public void MGMoveLeft(int x, int y) {
        if (this.getModel().getMap().getOnTheMapXY(x, y).getSprite().getConsoleImage() == 'G' && this.getModel().getMap().getOnTheMapXY(x - 1, y).getPermeability() == Permeability.WALKABLE) {
            this.getModel().getMap().setOnTheMapXY(MotionlessElementsFactory.createBackground(), x, y);
            this.getModel().getMap().setOnTheMapXY(MobileElementsFactory.createMonsterG(), x - 1, y);
        }
    }

    /**
     * La methode permettant le deplacement du monstre rougeg
     * @see moveRMonster
     * @throws InterruptedException
     */
    public void moveRMonster(int x, int y) {
        switch (rdirection) {
            case 1:
                MRMoveRight(x, y);
                break;
            case 0:
                MRMoveLeft(x, y);
                break;
            case 2:
                MRMoveUp(x, y);
                break;
            case 3:
                MRMoveDown(x, y);
                break;

            default:
                break;
        }
    }

    /**
     * La methode permettant le deplacement du monstre rouge vers la droite
     * @see MRMoveRight
     */
    public void MRMoveRight(int x, int y) {
        if (this.getModel().getMap().getOnTheMapXY(x, y).getSprite().getConsoleImage() == 'R' && this.getModel().getMap().getOnTheMapXY(x + 1, y).getPermeability() == Permeability.WALKABLE) {
            this.getModel().getMap().setOnTheMapXY(MotionlessElementsFactory.createBackground(), x, y);
            this.getModel().getMap().setOnTheMapXY(MobileElementsFactory.createMonsterR(), x + 1, y);
        }
    }

    /**
     * La methode permettant le deplacement du monstre rouge vers la gauche
     * @see MRMoveLeft
     */
    public void MRMoveLeft(int x, int y) {
        if (this.getModel().getMap().getOnTheMapXY(x, y).getSprite().getConsoleImage() == 'R' && this.getModel().getMap().getOnTheMapXY(x - 1, y).getPermeability() == Permeability.WALKABLE) {
            this.getModel().getMap().setOnTheMapXY(MotionlessElementsFactory.createBackground(), x, y);
            this.getModel().getMap().setOnTheMapXY(MobileElementsFactory.createMonsterR(), x - 1, y);
        }
    }

    /**
     * La methode permettant le deplacement du monstre rouge vers le haut
     * @see MRMoveUp
     */
    public void MRMoveUp(int x, int y) {
        if (this.getModel().getMap().getOnTheMapXY(x, y).getSprite().getConsoleImage() == 'R' && this.getModel().getMap().getOnTheMapXY(x, y - 1).getPermeability() == Permeability.WALKABLE) {
            this.getModel().getMap().setOnTheMapXY(MotionlessElementsFactory.createBackground(), x, y);
            this.getModel().getMap().setOnTheMapXY(MobileElementsFactory.createMonsterR(), x, y - 1);
        }
    }

    /**
     * * La methode permettant le deplacement du monstre rouge vers le bas
     * @see MRMoveDown
     */
    public void MRMoveDown(int x, int y) {
        if (this.getModel().getMap().getOnTheMapXY(x, y).getSprite().getConsoleImage() == 'R' && this.getModel().getMap().getOnTheMapXY(x, y + 1).getPermeability() == Permeability.WALKABLE) {
            this.getModel().getMap().setOnTheMapXY(MotionlessElementsFactory.createBackground(), x, y);
            this.getModel().getMap().setOnTheMapXY(MobileElementsFactory.createMonsterR(), x, y + 1);
        }
    }


    /**
     * La methode permettant de retourner la vue provenant de l'interface IView
     * @see IView
     * @return this.view
     */
    private IView getView() {
        return this.view;
    }

    /**
     * La methode permettant de modifier la vue
     * @see setView
     * @param view
     */
    private void setView(final IView view) {
        this.view = view;
    }

    /**
     * La methode permettant d'obtenir le model
     * @see getModel
     * @return this.model
     */
    private IModel getModel() {
        return this.model;
    }

    /**
     * La methode permettant de modifier la vue
     * @see setModel
     * @param model
     */
    private void setModel(final IModel model) {
        this.model = model;
    }

    /**
     * La methode permettant d'obtenir la valeur donner par le joueur
     * @see UserOrder
     * @return this.stackOrder
     */
    private UserOrder getStackOrder() {
        return this.stackOrder;
    }

    /**
     * La methode permettant de modifier l'ordre donner par le joueur
     * @see setStackOrder
     * @param stackOrder
     */
    private void setStackOrder(final UserOrder stackOrder) {
        this.stackOrder = stackOrder;
    }

    /**
     * La methode permettant d'initiliser les ordres de le joueur
     * @see User0rder
     * clearStackOrder method
     */
    private void clearStackOrder() {
        this.stackOrder = UserOrder.NOP;
    }

    /**
     * La methode permettant d'obtenir les interpretations des ordres faitent par le joueur
     * @see getOrderPerformer
     */
    @Override
    public IOrderPerformer getOrderPerformer() {
        return this;
    }

    /**
     * @see IOrderPerformer
     * @param orderPerform
     */
    @Override
    public final void orderPerform(final UserOrder userOrder) throws IOException {
        this.setStackOrder(userOrder);
    }
    /**
     * La methode permettant d'obtenir le compteur
     * @return compteur
     */
    public int getCompteur() {
		return compteur;
	}
    /**
     * La methode servant de compteur de temps
     * @see run
     */
	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(PAUSE);
			}catch(InterruptedException e) {
				
			}this.compteur--;
		}
		
	}

}