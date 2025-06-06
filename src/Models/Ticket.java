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
        String query = "SELECT * FROM tickets";

        try (
                Connection connection = MySQLConnection.connect();
                Statement st = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet rs = st.executeQuery(query)
        ) {

            while (rs.next()) {
                tickets.add(new Ticket(
                        rs.getInt("idTicket"),
                        rs.getInt("price")
                ));
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

    public static int addTicket(Ticket ticket) {

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

        } catch (SQLException e) {
            throw new RuntimeException(e);
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
}
