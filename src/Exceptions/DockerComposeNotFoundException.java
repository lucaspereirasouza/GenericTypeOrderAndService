package Exceptions;

public class DockerComposeNotFoundException extends Exception{
	 public DockerComposeNotFoundException() {
	        super("Docker compose file not found");
	    }
}
