package model;

import java.sql.Connection;
import java.sql.DriverManager;

import model.configuration.ConfigurationModel;

public class DAO {
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://127.0.0.1:3306/databaseos";
	private String user = "root";
	private String password = "root@passwd@changeme";
	// Criação de um objeto para uso da classe Connection(JDBC)
	private Connection con;
	
	private ConfigurationModel conf;
	// Insert all configuration inside DAO
	
	/**
	 *
	 * Método responsavel pela conexão jdbc no sistema
	 * @return con
	 */
	public Connection conectar() {
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user,password);
			return con;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public DAO(String driver, String url, String user, String password, Connection con) {
		super();
		this.driver = driver;
		this.url = url;
		this.user = user;
		this.password = password;
		this.con = con;
	}
	
	public DAO(boolean b) {
		super();
		this.driver = conf.getDriver();
		this.url = conf.getIp();
		this.user = conf.getUser();
		this.password = conf.getPassword();
	}
	
	public DAO() {
	}
}
