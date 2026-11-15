package Models;

import utils.MySQLConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

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

    // Obtiene y devuelve el valor de showtimes
    public static ArrayList<MovieShowtime> getShowtimes() {
        ArrayList<MovieShowtime> movieShowtimes = new ArrayList<>();
        String query = "SELECT f.idFunction, f.showTime, "
                + "m.idMovie, m.title, m.duration, m.genre, m.classification, m.imgRoute, "
                + "r.idRoom, r.roomName, r.capacity, r.roomType, r.roomRows, r.roomCols "
                + "FROM functions f "
                + "JOIN movies m ON m.idMovie = f.idMovie "
                + "JOIN rooms r ON r.idRoom = f.idRoom "
                + "ORDER BY f.idFunction";
        try (
                Connection connection = MySQLConnection.connect();
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(query)
        ) {
            while (rs.next()) {
                Movie movie = new Movie(
                        rs.getInt("idMovie"),
                        rs.getString("title"),
                        rs.getInt("duration"),
                        rs.getString("genre"),
                        rs.getString("classification"),
                        rs.getString("imgRoute")
                );
                Room room = new Room(
                        rs.getInt("idRoom"),
                        rs.getString("roomName"),
                        rs.getInt("capacity"),
                        rs.getString("roomType"),
                        rs.getInt("roomRows"),
                        rs.getInt("roomCols")
                );
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

    // Obtiene y devuelve el valor de un showtime especifico
    public static MovieShowtime getShowtimes(int id) {
        MovieShowtime movieShowtime = null;
        String query = "SELECT * FROM functions WHERE idFunction = " + id;

        try (
                Connection connection = MySQLConnection.connect();
                Statement st = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet rs = st.executeQuery(query)
        ) {

            if(rs.next()){
                int idMovie = rs.getInt("idMovie");
                int idRoom = rs.getInt("idRoom");
                String showTime = rs.getString("showTime");

                Movie movie = Movie.getMovieById(idMovie);
                Room room = Room.getRoom(idRoom);

                return new MovieShowtime(id, movie, room, showTime);
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
        String query = "DELETE FROM functions WHERE idFunction = ?";
        int deleted = 0;
        try (
                Connection connection = MySQLConnection.connect();
                PreparedStatement pst = connection.prepareStatement(query)
        ) {
            pst.setInt(1, id);
            deleted = pst.executeUpdate();
            System.out.println("Funciones eliminadas: " + deleted);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        return deleted > 0;
    }

    public static boolean isShowtimeAvailable(String showtime, int idRoom) {
        String query = "SELECT COUNT(*) FROM functions WHERE showtime = ? AND idRoom = ?";

        try (
                Connection connection = MySQLConnection.connect();
                PreparedStatement stmt = connection.prepareStatement(query);
        ) {
            stmt.setString(1, showtime);
            stmt.setInt(2, idRoom);
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

    public static ArrayList<String> getSeatsNames(int idRoom){
        ArrayList<String> seatNames = new ArrayList<>();
        String query = "SELECT seatName FROM seats WHERE idRoom = ?";

        try (
                Connection connection = MySQLConnection.connect();
                PreparedStatement pst = connection.prepareStatement(query);
        ) {

            pst.setInt(1, idRoom);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                seatNames.add(rs.getString("seatName"));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return seatNames;
    }

    // Obtiene y devuelve el valor de los estados de los asientos (ocupados o no ocupados)
    public static Map<String, Boolean> getSeatsStatus(int idRoom, int idFunction) {
        Map<String, Boolean> seats = new LinkedHashMap<>();
        String query = "SELECT s.seatName, COALESCE(o.isOccupied, 0) AS occupied "
                + "FROM seats s "
                + "LEFT JOIN occupiedseats o ON o.idSeat = s.idSeat AND o.idFunction = ? "
                + "WHERE s.idRoom = ? "
                + "ORDER BY s.idSeat";

        try (
                Connection connection = MySQLConnection.connect();
                PreparedStatement pst = connection.prepareStatement(query)
        ) {
            pst.setInt(1, idFunction);
            pst.setInt(2, idRoom);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    seats.put(rs.getString("seatName"), rs.getBoolean("occupied"));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return seats;
    }
}
