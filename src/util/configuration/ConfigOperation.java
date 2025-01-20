package util.configuration;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

import model.configuration.ConfigurationModel;
import model.configuration.DefaultConfigurationModel;

public class ConfigOperation{

    private String filePath=".conf";
    private Map<String, String> config;
    
    public ConfigOperation(String filePath) throws IOException{
        this.filePath = filePath;
        this.config = readConfigFile();
    }
    
    public ConfigOperation(){
        this.config = readConfigFile();
    }

    // READ: Ler o arquivo e carregar as configurações no Map
    private Map<String, String> readConfigFile() {
        Map<String, String> config = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith("#")) {
                    continue; // Ignorar linhas em branco e comentários
                }
                String[] parts = line.split("=", 2);
                if (parts.length == 2) {
                    config.put(parts[0].trim(), parts[1].trim());
                }
            }
        }
        
        catch(FileNotFoundException err){
        	System.out.println(err+"... Creating new file");
        	File file = new File(".conf");
        	 try {
        	     
        	      if (file.createNewFile()) {
        	        System.out.println("File created: " + file.getName());
        	      } else {
        	        System.out.println("File already exists.");
        	      }
        	    } catch (IOException e) {
        	      System.out.println("An error occurred.");
        	      e.printStackTrace();
        	    }
        	
        }
        catch(IOException err){
        	System.out.println(err);
        }
        
        return config;
    }

    // CREATE: Adicionar uma nova configuração
    public void create(String key, String value) throws IOException {
        if (config.containsKey(key)) {
            throw new IllegalArgumentException("A chave já existe: " + key);
        }
        config.put(key, value);
        saveConfigFile();
    }

    // READ: Obter o valor de uma chave
    public String read(String key) {
        return config.get(key);
    }

    // UPDATE: Atualizar o valor de uma chave existente
    public void update(String key, String newValue) throws IOException {
        if (!config.containsKey(key)) {
            throw new IllegalArgumentException("Chave não encontrada: " + key);
        }
        config.put(key, newValue);
        saveConfigFile();
    }

    // DELETE: Remover uma chave
    public void restoreDefault() {
    	DefaultConfigurationModel dfModel = new DefaultConfigurationModel();
      	config.putAll(dfModel.getEntry());
    	try {
			saveConfigFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void delete(String key) throws IOException {
        if (!config.containsKey(key)) {
            throw new IllegalArgumentException("Chave não encontrada: " + key);
        }
        config.remove(key);
        saveConfigFile();
    }

    // Persistir as configurações no arquivo
    private void saveConfigFile() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Map.Entry<String, String> entry : config.entrySet()) {
                writer.write(entry.getKey() + "=" + entry.getValue());
                writer.newLine();
            }
        }
    }
}

