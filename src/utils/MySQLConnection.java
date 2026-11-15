package utils;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MySQLConnection {
    private static final Logger logger = AppLogger.getLogger();
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final Deque<Connection> pool = new ArrayDeque<>();

    public static Connection connect() {
        Connection real = null;
        synchronized (pool) {
            while (!pool.isEmpty() && real == null) {
                Connection c = pool.pop();
                try {
                    if (c.isValid(2)) real = c;
                } catch (SQLException ignored) { }
            }
        }
        if (real == null) real = openNew();
        if (real == null) return null;

        final Connection base = real;
        final boolean[] closed = {false};
        return (Connection) Proxy.newProxyInstance(
                Connection.class.getClassLoader(),
                new Class[]{Connection.class},
                (proxy, method, args) -> {
                    if (method.getName().equals("close")) {
                        if (!closed[0]) {
                            closed[0] = true;
                            synchronized (pool) { pool.push(base); }
                        }
                        return null;
                    }
                    try {
                        return method.invoke(base, args);
                    } catch (InvocationTargetException e) {
                        throw e.getCause();
                    }
                });
    }

    private static Connection openNew() {
        DBConfig config = new DBConfig(View.App.configPath);
        String url = "jdbc:mysql://" + config.getHost() + ":" + config.getPort() + "/" + config.getDatabase()
                + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyD   atetimeCode=false&serverTimezone=UTC&useSSL=true";
        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, config.getUser(), config.getPassword());
            logger.info("Conexión exitosa a la base de datos");
            return conn;
        } catch (Exception ex) {
            String msg = "No se pudo realizar la conexión: " + ex.getMessage();
            JOptionPane.showMessageDialog(null, msg);
            logger.log(Level.SEVERE, msg, ex);
            return null;
        }
    }
}