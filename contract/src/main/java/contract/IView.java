package contract;

import fr.exia.showboard.BoardFrame;
/**
 * <h1> L'interface IView </h1>
 * @author Lyba
 *
 */
public interface IView {

	void displayMessage(final String message);
	
	void followMyPlayer();

	void updateView();

	BoardFrame getBoardFrame();

}
