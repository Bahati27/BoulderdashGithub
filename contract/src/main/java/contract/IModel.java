
package contract;
import entity.IMap;
import entity.mobile.IMobile;
/**
 * 
 * @author Kelvin
 *
 */
public interface IModel {

	IMap getMap();

    IMobile getMyPlayer();

}
