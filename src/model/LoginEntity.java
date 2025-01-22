package model;

import java.io.Serializable;
import java.util.Objects;

public class LoginEntity implements Serializable {

	String name;
	String password;
	String priviledge;
	
	
	
	public LoginEntity(String name, String pasword, String priviledge) {
		super();
		this.name = name;
		this.password = pasword;
		this.priviledge = priviledge;
	}

	public LoginEntity() {
	
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}



	public String getPasword() {
		return password;
	}



	public void setPasword(String pasword) {
		this.password = pasword;
	}



	public String getPriviledge() {
		return priviledge;
	}



	public void setPriviledge(String priviledge) {
		this.priviledge = priviledge;
	}



	@Override
	public String toString() {
		return "LoginEntity [name=" + name + ", pasword=" + password + ", priviledge=" + priviledge + "]";
	}



	@Override
	public int hashCode() {
		return Objects.hash(name, password, priviledge);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LoginEntity other = (LoginEntity) obj;
		return Objects.equals(name, other.name) && Objects.equals(password, other.password)
				&& Objects.equals(priviledge, other.priviledge);
	}
	
	
}
