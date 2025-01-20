package model.configuration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;



public class ConfigurationModel {
	
	private String driver;
	private String ip;
	private String user;
	private String password;
	
	public ConfigurationModel() {
		
	}
	public ConfigurationModel(ConfigurationModel conf) {
		driver = conf.getDriver();
		ip = conf.getIp();
		user = conf.getUser();
		password = conf.getPassword();
	}
	public ConfigurationModel(String driver, String ip, String user, String password) {
		this.driver = driver;
		this.ip = ip;
		this.user = user;
		this.password = password;
	}

	/**
	 * @param Write Configuration File with called object
	 */
	
	

	public String getDriver() {
		return driver;
	}


	

	public void setDriver(String driver) {
		this.driver = driver;
	}


	public String getIp() {
		return ip;
	}


	public void setIp(String ip) {
		this.ip = ip;
	}


	public String getUser() {
		return user;
	}


	public void setUser(String user) {
		this.user = user;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
