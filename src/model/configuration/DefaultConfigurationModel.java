package model.configuration;

import java.util.HashMap;
import java.util.Map;

public class DefaultConfigurationModel{

	private static String driver = "com.mysql.cj.jdbc.Driver";
	private static String ip = "jdbc:mysql://127.0.0.1:3306/databaseos";
	private static String user = "root";
	private static String password = "root@passwd@changeme";
	private Map<String,String> keyString = new HashMap<String, String>();
	
	public DefaultConfigurationModel() {
		super();
	}
	
	public Map<String,String> getEntry(){
		keyString.put("driver", driver);
		keyString.put("ip", ip);
		keyString.put("user", user);
		keyString.put("password", password);
		
		return keyString;
	}
	
	
	
}
