package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.JOptionPane;

import Exceptions.DockerComposeNotFoundException;

public class DockerValidation {
	//consultar a configuração
	private static final String DIRECTORY = "./";
	
	private static final String DOCKER_COMPOSE_FILE = "docker-compose.yaml";
	private static final String DOCKER_COMPOSE_URL = "https://raw.githubusercontent.com/lucaspereirasouza/GenericTypeOrderAndService/refs/heads/master/docker/docker-compose.yaml";
	
	private static final String DOCKER_INSTALL_SH_URL = "https://raw.githubusercontent.com/lucaspereirasouza/GenericTypeOrderAndService/refs/heads/master/docker/docker-install.sh";
	private static final String DOCKER_INSTALL_FILE = "docker-install.sh";
			
	public void InicializeDockerCompose() {
		try {
			if(!Files.exists(Paths.get(DIRECTORY, DOCKER_COMPOSE_FILE))) {
				downloadFile(DOCKER_COMPOSE_URL,DOCKER_COMPOSE_FILE);
			}
            String[] command = {"docker", "compose", "up", "-d"};
            executeCommand(command, "Docker compose executado com sucesso", "Houve um erro no docker compose");
		}
		catch(IOException | InterruptedException | URISyntaxException e){
			 System.err.println("Erro ao executar o comando Docker Compose: " + e.getMessage());
	           e.printStackTrace();
	           JOptionPane.showConfirmDialog(null, e);
		}
		
	}
	
	private String executeCommand(String[] command, String successMessage, String errorMessage) throws IOException, InterruptedException {
        ProcessBuilder processBuilder = new ProcessBuilder(command);
        processBuilder.redirectErrorStream(true);

        Process process = processBuilder.start();
        StringBuilder output = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append(System.lineSeparator());
            }
        }

        int exitCode = process.waitFor();
        if (exitCode == 0) {
            if (successMessage != null) {
                System.out.println(successMessage);
            }
            return output.toString();
        } else {
            if (errorMessage != null) {
                System.err.println(errorMessage + " Código de saída: " + exitCode);
                JOptionPane.showMessageDialog(null, errorMessage + " Código de saída: " + exitCode);
                return errorMessage + "Código de saida: " + exitCode;
            }
            return null;
        }
    }
	
	private void downloadFile(String URL,String Filetype) throws IOException, URISyntaxException {
        try (InputStream in = new URI(URL).toURL().openStream()) {
            Files.copy(in, Paths.get(Filetype));
            System.out.println("Arquivo docker-compose.yml baixado com sucesso.");
        } catch (IOException e) {
            throw new IOException("Erro ao baixar o arquivo : " + e.getMessage(), e);
        } catch (URISyntaxException eURI) {
			// TODO Auto-generated catch block
        	throw new URISyntaxException("Erro de sintaxe: " + eURI.getMessage(), eURI.toString());
		}
    }
	
	public boolean isWindows() {
        return System.getProperty("os.name").toLowerCase().contains("win");
    }
	
	public void InstallDockerEngine() throws IOException, URISyntaxException {
		downloadFile(DOCKER_INSTALL_SH_URL,DOCKER_INSTALL_FILE);
		
	}

	public boolean isDockerInstalled() throws DockerComposeNotFoundException {
		try {
			String[] command = {"docker","--version"};
			String output = executeCommand(command, null, null);

			if (output != null && !output.isBlank()) {
                System.out.println("Docker está instalado:\n" + output);
                return true;
            } else {
                throw new DockerComposeNotFoundException();
            }
		}catch(DockerComposeNotFoundException dockerErr) {
			JOptionPane.showMessageDialog(null, "Docker não está instalado ou não está no PATH."+ dockerErr.getMessage());
		} catch (Exception e) {
			System.out.println("Erro geral: " + e.getMessage());
		}
		return false;
	}
	
}
