package model.DAO;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Jean marie
 *
 */
public class DBConnection {

	/**
	 * L'URL de la base de donnée
	 */
	private static String URL = "jdbc:mysql://localhost/jpublankproject?autoReconnect=true&useSSL=false";
	/**
	 * Le nom d'utilisateur de la base de donnée
	 */
	private static String USER = "root";
	/**
	 * Le mot de passe de la base de donnée
	 */
	private static String PASSWD = "";

	private Connection connection = null;

	private DBConnection INSTANCE;

	/**
	 * Récupère l'Instance
	 */
	public DBConnection getInstance() {
		if (INSTANCE != null) {
			INSTANCE = new DBConnection();
		}
		return INSTANCE;
	}

	/**
	 * On se connecte à la base de donnée
	 */
	public void connect() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		connection=DriverManager.getConnection(URL, USER, PASSWD);
	}
	
	/**
	 * Récupère la connection
	 */
	public Connection getConnection() {
		return connection;
	}
	
	/**
	 * Récupère l'URL
	 */
	public String getURL() {
		return URL;
	}

	/**
	 * Récupère le nom d'utilisateur
	 */
	public  String getUSER() {
		return USER;
	}

	/**
	 * Récupère le mot de passe
	 * @return
	 */
	public  String getPASSWD() {
		return PASSWD;
	}
}