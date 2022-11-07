package contract;

import fr.exia.showboard.BoardFrame;
/**
 * 
 * @author Jean Marie
 *
 */
public interface IView {

	void displayMessage(final String message);
	
	void followMyPlayer();

	void updateView();

	BoardFrame getBoardFrame();

}
