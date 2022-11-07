package entity;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import entity.mobile.Boulder;
/**
 * <h1> La classe ElementTest </h1>
 * @author Kelvin
 *
 */
public class ElementTest {

	
	static Boulder boulder;
	
	/**
	 * Test sur l'instanciation de l'element boulder
	 * @throws Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		boulder = new Boulder();
	}
	/**
	 * Test sur la valeur de la permeabilite obtenu, si on a un boulder
	 */
	@Test
	public void testgetPermeability() {
		final Permeability PExpected = Permeability.BOULDER;
		assertEquals(PExpected, boulder.getPermeability());
	}
	/**
	 * Test pour voir si le sprite boulder n'est pas null
	 */
	@Test
	public void testgetSprite() {
		assertNotNull(boulder.getSprite());
	}
}
