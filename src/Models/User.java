package Models;

import utils.MySQLConnection;

import java.sql.*;
import java.util.ArrayList;

public class User {

//    private int idUser;
//    private String userName;
//    private String password;
//
//    public User(int idUser, String userName, String password) {
//        this.idUser = idUser;
//        this.userName = userName;
//        this.password = password;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public int getIdUser() {
//        return idUser;
//    }
//
//    public void setIdUser(int idUser) {
//        this.idUser = idUser;
//    }
//
//    public String getUsername() {
//        return userName;
//    }
//
//    public void setUsername(String userName) {
//        this.userName = userName;
//    }
//
//    public static ArrayList<User> getUsers() {
//        ArrayList<User> users = new ArrayList<>();
//        String query = "SELECT * FROM users";
//
//        try (
//                Connection connection = MySQLConnection.connect();
//                Statement st = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
//                ResultSet rs = st.executeQuery(query)
//        ) {
//            while (rs.next()) {
//                users.add(new User(
//                        rs.getInt("idUser"),
//                        rs.getString("username"),
//                        rs.getString("password")
//                ));
//            }
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//
//        return users;
//    }
//
//    public static User getUser(int id) {
//        User user = null;
//        String query = "SELECT * FROM users WHERE idUser = " + id;
//
//        try (
//                Connection connection = MySQLConnection.connect();
//                Statement st = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
//                ResultSet rs = st.executeQuery(query)
//        ) {
//
//            if (rs.next()) {
//                user = new User(
//                        rs.getInt("idUser"),
//                        rs.getString("username"),
//                        rs.getString("password")
//                );
//            }
//
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//
//        return user;
//    }
//
//    public static int addUser(User user) {
//
//        int id = 0;
//        String query = "INSERT INTO users " + "(username, password))"
//                + "VALUES (?,?)";
//        int created = 0;
//
//        try (Connection connection = MySQLConnection.connect();
//             PreparedStatement pst = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
//        ) {
//            pst.setString(1, user.getUsername());
//            pst.setString(2, user.getPassword());
//
//            created = pst.executeUpdate();
//
//            ResultSet rs = pst.getGeneratedKeys();
//            if (rs.next()) {
//                id = rs.getInt(1);
//            }
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return id;
//    }
//
//    public static boolean updateUser(User user) {
//        String query = "UPDATE users SET username = ?,password = ? WHERE idUser = ?";
//        int updated = 0;
//
//        try (
//                Connection connection = MySQLConnection.connect();
//                PreparedStatement pst = connection.prepareStatement(query)
//        ) {
//            pst.setString(1, user.getUsername());
//            pst.setString(2, user.getPassword());
//
//            updated = pst.executeUpdate();
//            return updated > 0;
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public static boolean deleteUser(int id) {
//
//        int deleted = 0;
//
//        String query = "DELETE FROM users WHERE idUser = " + id;
//
//        try (
//                Connection connection = MySQLConnection.connect();
//                Statement st = (Statement) connection.createStatement();
//        ) {
//            deleted = st.executeUpdate(query);
//            System.out.println(deleted);
//
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//
//        return deleted > 0;
//
//    }
    
}
