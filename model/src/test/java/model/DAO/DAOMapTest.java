package model.DAO;

import org.junit.Test;

/**
 * <h1> La classe DAOMapTest </h1>
 * @author Kelvin
 *
 */
public class DAOMapTest {


	DAOMap dao ;
	private String monFichier = "map.txt";
	/**
	 * Test pour le niveau 0
	 * @throws Exception
	 */
	@Test(expected = Exception.class)
	public void testApplyToMap() throws Exception {
		this.dao.loadlevel(monFichier, "0");
	}
}