package Models;

import utils.MySQLConnection;

import java.sql.*;
import java.util.ArrayList;

public class Function {
    private int idFunction; //Primary Key
    private Movie movie; //Foreign Key
    private int idMovie = movie.getIdMovie();
    private Room room; //Foreign Key
    private int idRoom = room.getIdRoom();
    private String showTime;

    public Function(int id, Movie movie, Room room, String showTime) {
        this.idFunction = id;
        this.movie = movie;
        this.room = room;
        this.showTime = showTime;
    }

    public Function(int idFunction, int idMovie, int idRoom, String showTime) {
        this.idFunction = idFunction;
        this.idMovie = idMovie;
        this.idRoom = idRoom;
        this.showTime = showTime;
    }

    public int getIdFunction() {
        return idFunction;
    }

    public void setIdFunction(int id) {
        this.idFunction = id;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public String getShowTime() {
        return showTime;
    }

    public void setShowTime(String showTime) {
        this.showTime = showTime;
    }

    public static ArrayList<Function> getFunctions() {
        ArrayList<Function> functions = new ArrayList<>();
        String query = "SELECT * FROM functions";

        try (
                Connection connection = MySQLConnection.connect();
                Statement st = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet rs = st.executeQuery(query)
        ) {

            while (rs.next()) {
                functions.add(new Function(
                        rs.getInt("idFunction"),
                        rs.getInt("idMovie"),
                        rs.getInt("idRoom"),
                        rs.getString("showTime")
                ));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return functions;
    }

    public static Function getFunction(int id) {
        Function function = null;
        String query = "SELECT * FROM functions WHERE idFunction = " + id;

        try (
                Connection connection = MySQLConnection.connect();
                Statement st = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet rs = st.executeQuery(query)
        ) {

            if (rs.next()) {
                function = new Function(
                        rs.getInt("idFunction"),
                        rs.getInt("idMovie"),
                        rs.getInt("idRoom"),
                        rs.getString("showTime")
                );
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return function;
    }

    public static int addFunction(Function function) {

        int id = 0;
        String query = "INSERT INTO functions " + "(showTime))"
                + "VALUES (?)";
        int created = 0;

        try (Connection connection = MySQLConnection.connect();
             PreparedStatement pst = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        ) {
            pst.setString(1, function.getShowTime());

            created = pst.executeUpdate();

            ResultSet rs = pst.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return id;
    }

    public static boolean updateFunction(Function function) {
        String query = "UPDATE functions SET showTime = ? WHERE idFunction = ?";
        int updated = 0;

        try (
                Connection connection = MySQLConnection.connect();
                PreparedStatement pst = connection.prepareStatement(query)
        ) {
            pst.setString(1, function.getShowTime());
            pst.setInt(2, function.getIdFunction());

            updated = pst.executeUpdate();
            return updated > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean deleteFunction(int id) {

        int deleted = 0;

        String query = "DELETE FROM functions WHERE idFunction = " + id;

        try (
                Connection connection = MySQLConnection.connect();
                Statement st = (Statement) connection.createStatement();
        ) {
            deleted = st.executeUpdate(query);
            System.out.println(deleted);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return deleted > 0;

    }
}
