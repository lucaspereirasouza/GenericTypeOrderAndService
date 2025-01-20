package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import util.Reader;


public class Configuration {
	private File file = new File("./configuration.conf");
//	private FileReader read;
	
	private String driver;
	private String ip;
	private String user;
	private String password;
	
	public Configuration() {
	}
	public Configuration(Configuration conf) {
		driver = conf.getDriver();
		ip = conf.getIp();
		user = conf.getUser();
		password = conf.getPassword();
	}
	public Configuration(String driver, String ip, String user, String password) {
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


	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
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
