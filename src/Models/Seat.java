package Models;

import utils.MySQLConnection;

import java.sql.*;
import java.util.ArrayList;

public class Seat {
    private int idSeat; //primary key
    private String seatName;
    private int seatNumber;
    private boolean isOccupied;
    private Room room; //Foreign Key
    private int idRoom = room.getIdRoom();


    public Seat(int idSeat, String seatName, int seatNumber, boolean isOccupied, Room room, int idRoom) {
        this.idSeat = idSeat;
        this.seatName = seatName;
        this.seatNumber = seatNumber;
        this.isOccupied = isOccupied;
        this.room = room;
        this.idRoom = idRoom;
    }

    public Seat(int idRoom, int idSeat, String seatName, int seatNumber, boolean isOccupied) {
        this.idRoom = idRoom;
        this.idSeat = idSeat;
        this.seatName = seatName;
        this.seatNumber = seatNumber;
        this.isOccupied = isOccupied;
    }

    public Seat() {

    }

    public int getIdSeat() {
        return idSeat;
    }

    public void setIdSeat(int idSeat) {
        this.idSeat = idSeat;
    }

    public String getSeatName() {
        return seatName;
    }

    public void setSeatName(String seatName) {
        this.seatName = seatName;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    public Room getRoom() {
        return room;
    }
    
    public void setRoom(Room room) {
        this.room = room;
    }

    public int getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(int idRoom) {
        this.idRoom = idRoom;
    }

    public static ArrayList<Seat> getSeats() {
        ArrayList<Seat> seats = new ArrayList<>();
        String query = "SELECT * FROM seats";

        try (
                Connection connection = MySQLConnection.connect();
                Statement st = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet rs = st.executeQuery(query)
        ) {

            while (rs.next()) {
                seats.add(new Seat(
                        rs.getInt("idSeat"),
                        rs.getInt("idRoom"),
                        rs.getString("seatName"),
                        rs.getInt("seatNumber"),
                        rs.getBoolean("isOccupied")
                ));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return seats;
    }

    public static Seat getSeat(int id) {
        Seat seat = null;
        String query = "SELECT * FROM seats WHERE idSeat = " + id;

        try (
                Connection connection = MySQLConnection.connect();
                Statement st = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet rs = st.executeQuery(query)
        ) {

            if (rs.next()) {
                seat = new Seat(
                        rs.getInt("idSeat"),
                        rs.getInt("idRoom"),
                        rs.getString("seatName"),
                        rs.getInt("seatNumber"),
                        rs.getBoolean("isOccupied")
                );
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return seat;
    }

    public static int addSeat(Seat seat) {

        int id = 0;
        String query = "INSERT INTO seats " + "(seatName,seatNumber,isOccupied))"
                + "VALUES (?,?,?)";
        int created = 0;

        try (Connection connection = MySQLConnection.connect();
             PreparedStatement pst = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        ) {
            pst.setString(1, seat.getSeatName());
            pst.setInt(2, seat.getSeatNumber());
            pst.setBoolean(3, seat.isOccupied());

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

    public static boolean updateSeat(Seat seat) {
        String query = "UPDATE seats SET seatName = ?,seatNumber = ?," +
                "isOccupied = ? WHERE idSeat = ?";
        int updated = 0;

        try (
                Connection connection = MySQLConnection.connect();
                PreparedStatement pst = connection.prepareStatement(query)
        ) {
            pst.setString(1, seat.getSeatName());
            pst.setInt(2, seat.getSeatNumber());
            pst.setBoolean(3, seat.isOccupied());
            pst.setInt(4, seat.getIdSeat());

            updated = pst.executeUpdate();
            return updated > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean deleteSeat(int id) {

        int deleted = 0;

        String query = "DELETE FROM seats WHERE idSeat = " + id;

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
