package contract;
import java.io.IOException;
/**
 * 
 * @author Kelvin
 *
 */
public interface IOrderPerformer {
	void orderPerform(UserOrder userOrder) throws IOException;

}

