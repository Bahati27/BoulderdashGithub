package model.DAO;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;


/**
 * <h1> La classe DBConnectionTest </h1>
 * @author Kelvin
 *
 */
public class DBConnectionTest {

	DBConnection DBC;
	final String USER = "root";
	final String PASSWD = "";
	final String URL = "jdbc:mysql://localhost/jpublankproject?autoReconnect=true&useSSL=false";
	

	/**
	 * @throws Exception
	 * @throws SQLException
	 */
	@Before
	public void setUp() throws Exception, SQLException {
		this.DBC= new DBConnection();
	}

	/**
	 * Test sur la connection
	 * @throws SQLException
	 */
	@Test
	public void testconnect() throws SQLException {
		assertNotNull(this.DBC);
	}

	/**
	 * test pour recevoir l'url
	 * @throws SQLException
	 */
	@Test
	public void testgetUrl() throws SQLException {
		assertEquals(this.URL, this.DBC.getURL());
	}
	
	/**
	 * test pour recevoir l'utilisateur(user)
	 * @throws SQLException
	 */
	@Test
	public void testgetUser() throws SQLException {
		assertEquals(this.USER, this.DBC.getUSER());
	}
	
	/**
	 * test pour recevoir le mot de passe
	 * @throws SQLException
	 */
	@Test
	public void testgetPassword() throws SQLException {
		assertEquals(this.PASSWD, this.DBC.getPASSWD());
	}
	
	
	/**
	 * test pour recevoir les instances
	 */
	@Test
	public void testGetInstance() {
		final String expected = "com.mysql.jdbc.JDBC4Connection@2077d4de";
		assertNotNull(expected, DBC.toString());
	}
	
}
