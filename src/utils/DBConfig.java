package utils;

import java.io.*;
import java.util.Properties;

import javax.swing.JOptionPane;

public class DBConfig {
    private Properties props;

    public DBConfig(String filePath) {
        props = new Properties();
        try (FileInputStream inputStream = new FileInputStream(filePath)) {
            props.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Hubo un error al importar la configuración" + e.getMessage());
            System.exit(0);
        }
    }

    public String getHost() {
        return props.getProperty("db.host");
    }

    public String getUser() {
        return props.getProperty("db.user");
    }

    public String getPassword() {
        return props.getProperty("db.password");
    }
    
    public String getPort() {
    	return props.getProperty("db.port");
    }
    
    public String getDatabase() {
    	return props.getProperty("db.database");
    }
}
