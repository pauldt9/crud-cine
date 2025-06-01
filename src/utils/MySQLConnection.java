package utils;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MySQLConnection {
    private static final Logger logger = AppLogger.getLogger();
    private static final String driver = "com.mysql.cj.jdbc.Driver";

    public static Connection connect() {
        DBConfig config = new DBConfig(View.App.configPath);
        String url = "jdbc:mysql://" + config.getHost() + ":" + config.getPort() + "/" + config.getDatabase()
                + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=true";
        Connection conn = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, config.getUser(), config.getPassword());
            logger.info("Conexión exitosa a la base de datos");
            System.out.println("Se realizó la conexión");
        } catch (Exception ex) {
            String msg = "No se pudo realizar la conexión: " + ex.getMessage();
            JOptionPane.showMessageDialog(null, msg);
            logger.log(Level.SEVERE, msg, ex);
        }
        return conn;
    }
}
