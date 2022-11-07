package contract;
import java.io.IOException;
/**
 * 
 * @author Jean Marie
 *
 */
public interface IOrderPerformer {
	void orderPerform(UserOrder userOrder) throws IOException;

}

