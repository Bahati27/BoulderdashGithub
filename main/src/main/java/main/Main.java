/**
 * @author Kelvin, Benjamin, Lyba
 */
package main;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import contract.IBoulderDashController;
import contract.IModel;
import controller.Controller;
import model.BoulderDashModel;
import model.DAO.DAOMap;
import view.View;

import javax.swing.*;

/**
 * <h1> La classe Principale(Main) </h1>
 *
 * @author Kelvin, Benjamin, Lyba
 */
public abstract class Main {


    private static String[] filenames = new String[] {"sprites/map.txt", "sprites/background.png", "sprites/boulder.png", "sprites/diamond.png", "sprites/door.png", "sprites/greenMonster.png", "sprites/ground.png", "sprites/pDead.png", "sprites/pDown.png", "sprites/pLeft.png", "sprites/pNope.png", "sprites/pRight.png", "sprites/pUp.png", "sprites/pWin.png", "sprites/redMonster.png", "sprites/wall.png"};

    private static View view;
    private static IModel model;
    private static  IBoulderDashController controller;

    /**
     * La methode principale(main)
     * @param args
     * @throws IOException
     * @throws InterruptedException
     */
    public static void main(final String[] args) {
        checkFiles();
        menu();
        game();

        System.exit(0);
    }



    static void checkFiles() {
        File file;
        for (String filename : filenames) {
            file = new File(filename);
            if (!file.exists()){
                JOptionPane.showMessageDialog(null, "Le fichier \"" + filename + "\" n'est pas retouvé.\nS'il vous plait, place le fichier au bon endroit et recommancer.", "BoulderDash - Echecs", JOptionPane.ERROR_MESSAGE);
                System.exit(0);
                return;
            }
        }
    }

    static void menu() {

        int choice = JOptionPane.showInternalConfirmDialog(null, "Veux-tu charger la carte de la base de données ?", "BloulderDash - Menu",JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
        DAOMap databaseConnection = null;
        Object[] maps = null;

        if (choice == 0) {
            try {
                databaseConnection = new DAOMap();
            } catch (ClassNotFoundException | SQLException e) {
                JOptionPane.showMessageDialog(null, "Vous ne pouvez pas vous connecter à la base de donnéese!\nVoulez-vous lancer le jeu sans charger la carte.", "BoulderDash - Echecs", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                maps = databaseConnection.getLevels();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Vous ne pouvez-pas retirer la carte de la base de données!\nLancer le jeux sans charger la carte.", "BoulderDash - Echecs", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String map = (String)JOptionPane.showInputDialog(null, "Quelle carte voulez-vous charger?", "BoulderDash - Carte chargée", JOptionPane.PLAIN_MESSAGE, null, maps, maps[0]);

            if (map != null) {
                try {
                    databaseConnection.loadlevel(filenames[0], map);
                } catch ( SQLException | IOException e) {
                    JOptionPane.showMessageDialog(null, "Vous ne pouvez pas charger la carte dans votre fichier txt!\nLancer le jeux sans charger la nouvel carte.", "BoulderDash - Echecs", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            return;
        }
    }

    static void game() {
        do {
            model = new BoulderDashModel(filenames[0], 1, 1);

            if (model.getMap().isCorrect()) {
                view = new View(model.getMap(), model.getMyPlayer());
                controller = new Controller(view, model);
                view.setOrderPerformer(controller.getOrderPerformer());

                controller.play();

            } else {
                JOptionPane.showMessageDialog(null, "La carte n'est pas dans le bon format!\nVerifier le fichier (map.txt).", "BoulderDash - Echecs", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        while(true);
    }
}