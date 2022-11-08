
package contract;
import entity.IMap;
import entity.mobile.IMobile;
/**
 * <h1> L'interface IModel </h1>
 * @author Jean Marie
 */
public interface IModel {

	IMap getMap();

    IMobile getMyPlayer();

}
