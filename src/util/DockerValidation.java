package util;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import javax.swing.JOptionPane;

public class DockerValidation {
	public void InicializeDockerCompose() {
		// docker compose command
	}
	public boolean isWindows() {
		if (System.getProperty("os.name") == "linux") {
			return false;
		} else {
			return true;
		}
	}
	public void InstallDockerEngine() {
	}

	public boolean isDockerInstalled() {
		boolean bol = false;
		try {

			ProcessBuilder processBuilder = new ProcessBuilder("docker", "--version");
			processBuilder.redirectErrorStream(true);

			Process process = processBuilder.start();

			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			StringBuilder output = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				output.append(line).append("\n");
			}

			// Espera o processo terminar
			int exitCode = process.waitFor();

			// Analisa o resultado
			if (exitCode == 0) {
				System.out.println("Docker está instalado:");
				System.out.println(output.toString());
//                JOptionPane.showMessageDialog(null, "Docker está instalado!");
				return true;
			} else {
				System.out.println("Docker não está instalado ou não está no PATH.");
				JOptionPane.showMessageDialog(null, "Docker não está instalado ou não está no PATH.");
				return false;
			}
		} catch (Exception e) {
			System.out.println("Erro ao verificar o Docker: " + e.getMessage());
		}

		return bol;
	}
	
}
