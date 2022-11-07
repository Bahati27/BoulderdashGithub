
package contract;
import entity.IMap;
import entity.mobile.IMobile;
/**
 * 
 * @author Jean Marie
 *
 */
public interface IModel {

	IMap getMap();

    IMobile getMyPlayer();

}
