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

// Clase de utilidades que centraliza funciones de apoyo utilizadas en distintas partes del proyecto
public class MySQLConnection {
    private static final Logger logger = AppLogger.getLogger();
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final long VALIDATE_AFTER_MS = 20_000;

    private static final class Idle {
        final Connection conn;
        final long since = System.currentTimeMillis();
        Idle(Connection c) { conn = c; }
    }

    private static final Deque<Idle> pool = new ArrayDeque<>();

    // Obtiene una conexión con la base de datos para ejecutar operaciones SQL
    public static Connection connect() {
        Connection real = null;
        synchronized (pool) {
            while (!pool.isEmpty() && real == null) {
                Idle idle = pool.pop();
                try {
                    boolean fresh = System.currentTimeMillis() - idle.since < VALIDATE_AFTER_MS;
                    if (fresh || idle.conn.isValid(2)) real = idle.conn;
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
                            synchronized (pool) { pool.push(new Idle(base)); }
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

    // Abre una nueva conexión física con la base de datos utilizando la configuración del proyecto
    private static Connection openNew() {
        DBConfig config = new DBConfig(View.App.configPath);
        String url = "jdbc:mysql://" + config.getHost() + ":" + config.getPort() + "/" + config.getDatabase()
                + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=true";
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