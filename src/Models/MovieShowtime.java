package Models;

import utils.MySQLConnection;

import java.sql.*;
import java.util.ArrayList;

public class MovieShowtime {
    private int idShowtime; //Primary Key
    private Movie movie; //Foreign Key
    private int idMovie;
    private Room room; //Foreign Key
    private int idRoom;
    private String showTime;

    public MovieShowtime(int id, Movie movie, Room room, String showTime) {
        this.idShowtime = id;
        this.movie = movie;
        this.room = room;
        this.showTime = showTime;
        this.idMovie = (movie != null) ? movie.getIdMovie() : 0;
        this.idRoom = (room != null) ? room.getIdRoom() : 0;
    }

    public MovieShowtime(int idShowtime, int idMovie, int idRoom, String showTime) {
        this.idShowtime = idShowtime;
        this.idMovie = idMovie;
        this.idRoom = idRoom;
        this.showTime = showTime;
    }

    public int getIdShowtime() {
        return idShowtime;
    }

    public void setIdShowtime(int id) {
        this.idShowtime = id;
    }

    public int getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(int idMovie) {
        this.idMovie = idMovie;
    }

    public int getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(int idRoom) {
        this.idRoom = idRoom;
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

    public static ArrayList<MovieShowtime> getShowtimes() {
        ArrayList<MovieShowtime> movieShowtimes = new ArrayList<>();
        String query = "SELECT * FROM functions";
        try (
                Connection connection = MySQLConnection.connect();
                Statement st = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet rs = st.executeQuery(query)
        ) {
            while (rs.next()) {
                int idMovie = rs.getInt("idMovie");
                int idRoom = rs.getInt("idRoom");
                Movie movie = Movie.getMovieById(idMovie);
                Room room = Room.getRoom(idRoom);
                movieShowtimes.add(new MovieShowtime(
                        rs.getInt("idFunction"),
                        movie,
                        room,
                        rs.getString("showTime")
                ));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return movieShowtimes;
    }

    public static MovieShowtime getShowtimes(int id) {
        MovieShowtime movieShowtime = null;
        String query = "SELECT * FROM functions WHERE idFunction = " + id;

        try (
                Connection connection = MySQLConnection.connect();
                Statement st = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet rs = st.executeQuery(query)
        ) {

            if (rs.next()) {
                movieShowtime = new MovieShowtime(
                        rs.getInt("idFunction"),
                        rs.getInt("idMovie"),
                        rs.getInt("idRoom"),
                        rs.getString("showTime")
                );
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return movieShowtime;
    }

    public static int addShowtimes(MovieShowtime movieShowtime) {

        int id = 0;
        String query = "INSERT INTO functions " + "(idMovie,idRoom,showTime)"
                + "VALUES (?,?,?)";
        int created = 0;

        try (Connection connection = MySQLConnection.connect();
             PreparedStatement pst = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        ) {
            pst.setInt(1, movieShowtime.getIdMovie());
            pst.setInt(2, movieShowtime.getIdRoom());
            pst.setString(3, movieShowtime.getShowTime());

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

    public static boolean updateShowtime(MovieShowtime movieShowtime) {
        String query = "UPDATE functions SET idMovie = ?, idRoom = ?, showTime = ? WHERE idFunction = ?";
        int updated = 0;

        try (
                Connection connection = MySQLConnection.connect();
                PreparedStatement pst = connection.prepareStatement(query)
        ) {

            pst.setInt(1, movieShowtime.getIdMovie());
            pst.setInt(2, movieShowtime.getIdRoom());
            pst.setString(3, movieShowtime.getShowTime());
            pst.setInt(4, movieShowtime.getIdShowtime());

            updated = pst.executeUpdate();
            return updated > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean deleteShowtime(int id) {

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

    public static boolean isShowtimeAvailable(String showtime, int idMovie, int idRoom) {
        String query = "SELECT COUNT(*) FROM functions WHERE showTime = ? AND idMovie = ? AND idRoom = ?";

        try (
                Connection connection = MySQLConnection.connect();
                PreparedStatement stmt = connection.prepareStatement(query);
        ) {
            stmt.setString(1, showtime);
            stmt.setInt(2, idMovie);
            stmt.setInt(3, idRoom);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return count == 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
