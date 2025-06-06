package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.mindrot.jbcrypt.BCrypt;

import utils.MySQLConnection;

public class EmployeeDAO {
    public boolean authentifyEmployee(String username, String password) {
        String sql = "SELECT password FROM employees WHERE username = ?";
        try (Connection connection = MySQLConnection.connect();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String hash = rs.getString("password");
                //Aquí se usa la librería JBCrypt para verificar que la contraseña coincida
                return BCrypt.checkpw(password, hash);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}

