package contract;
import java.io.IOException;
/**
 * <h1> L'interface IOrderPerformer </h1>
 * @author Jean Marie
 */
public interface IOrderPerformer {
	void orderPerform(UserOrder userOrder) throws IOException;

}

