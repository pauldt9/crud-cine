package Models;

import utils.MySQLConnection;

import java.sql.*;
import java.util.ArrayList;

public class Ticket {

    private int idTicket; //Primary Key
    private MovieShowtime movieShowtime; //Foreign Key
    private int idFunction;
    private Seat seat; //Foreign Key
    private int idSeat;
    private int price;

    public Ticket(int idTicket, MovieShowtime movieShowtime, Seat seat, int price, int idFunction, int idSeat) {
        this.idTicket = idTicket;
        this.movieShowtime = movieShowtime;
        this.idFunction = idFunction;
        this.seat = seat;
        this.idSeat = idSeat;
        this.price = price;
    }

    public Ticket(int idTicket, int price) {
        this.idTicket = idTicket;
        this.price = price;
    }

    public int getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(int idTicket) {
        this.idTicket = idTicket;
    }

    public MovieShowtime getFunction() {
        return movieShowtime;
    }

    public void setFunction(MovieShowtime movieShowtime) {
        this.movieShowtime = movieShowtime;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getIdFunction() {
        return idFunction;
    }

    public void setIdFunction(int idFunction) {
        this.idFunction = idFunction;
    }

    public int getIdSeat() {
        return idSeat;
    }

    public void setIdSeat(int idSeat) {
        this.idSeat = idSeat;
    }

    public static ArrayList<Ticket> getTickets() {
        ArrayList<Ticket> tickets = new ArrayList<>();
        String query = "SELECT t.idTicket, t.idFunction, t.idSeat, t.price, f.showTime, "
                + "m.idMovie, m.title, m.duration, m.genre, m.classification, m.imgRoute, "
                + "r.idRoom, r.roomName, r.capacity, r.roomType, r.roomRows, r.roomCols, "
                + "s.idRoom AS seatRoom, s.seatName, s.seatNumber "
                + "FROM tickets t "
                + "JOIN functions f ON f.idFunction = t.idFunction "
                + "JOIN movies m ON m.idMovie = f.idMovie "
                + "JOIN rooms r ON r.idRoom = f.idRoom "
                + "JOIN seats s ON s.idSeat = t.idSeat "
                + "ORDER BY t.idTicket";

        try (
                Connection connection = MySQLConnection.connect();
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(query)
        ) {
            while (rs.next()) {
                int idFunction = rs.getInt("idFunction");
                int idSeat = rs.getInt("idSeat");

                Movie movie = new Movie(rs.getInt("idMovie"), rs.getString("title"), rs.getInt("duration"),
                        rs.getString("genre"), rs.getString("classification"), rs.getString("imgRoute"));
                Room room = new Room(rs.getInt("idRoom"), rs.getString("roomName"), rs.getInt("capacity"),
                        rs.getString("roomType"), rs.getInt("roomRows"), rs.getInt("roomCols"));
                MovieShowtime ms = new MovieShowtime(idFunction, movie, room, rs.getString("showTime"));
                Seat seat = new Seat(idSeat, rs.getInt("seatRoom"), rs.getString("seatName"), rs.getInt("seatNumber"));

                tickets.add(new Ticket(rs.getInt("idTicket"), ms, seat, rs.getInt("price"), idFunction, idSeat));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return tickets;
    }

    public static Ticket getTicket(int id) {
        Ticket ticket = null;
        String query = "SELECT * FROM tickets WHERE idTicket = " + id;

        try (
                Connection connection = MySQLConnection.connect();
                Statement st = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet rs = st.executeQuery(query)
        ) {

            if (rs.next()) {
                ticket = new Ticket(
                        rs.getInt("idTicket"),
                        rs.getInt("price")
                );
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return ticket;
    }

    public static int addTicket(Ticket ticket) throws SQLException{

        int id = 0;
        String query = "INSERT INTO tickets " + "(idFunction,idSeat,price)"
                + "VALUES (?,?,?)";
        int created = 0;

        try (Connection connection = MySQLConnection.connect();
             PreparedStatement pst = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        ) {
            pst.setInt(1, ticket.getIdFunction());
            pst.setInt(2, ticket.getIdSeat());
            pst.setInt(3, ticket.getPrice());

            created = pst.executeUpdate();

            ResultSet rs = pst.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
            }

        }
        return id;
    }

    public static boolean updateTicket(Ticket ticket) {
        String query = "UPDATE tickets SET price = ? WHERE idTicket = ?";
        int updated = 0;

        try (
                Connection connection = MySQLConnection.connect();
                PreparedStatement pst = connection.prepareStatement(query)
        ) {
            pst.setInt(1, ticket.getPrice());
            pst.setInt(2, ticket.getIdTicket());

            updated = pst.executeUpdate();
            return updated > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean deleteTicket(int id) {

        int deleted = 0;

        String query = "DELETE FROM tickets WHERE idTicket = " + id;

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

    public static int getTotalSales() {
        int total = 0;
        String query = "SELECT SUM(price) FROM tickets";

        try (
                Connection connection = MySQLConnection.connect();
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(query);
        ) {
            if (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return total;
    }
}
