package Models;

import utils.MySQLConnection;

import java.sql.*;
import java.util.ArrayList;

public class Room {
    private int idRoom; //primary key
    private String roomName;
    private int capacity;
    private String roomType;


    //Por lo pronto y por conveniencia, empezamos los asientos como una matriz
    private Seat[][] seats;

    public Room(int idRoom, int capacity, String roomName, Seat[][] seats, String roomType) {
        this.idRoom = idRoom;
        this.capacity = capacity;
        this.roomName = roomName;
        this.seats = seats;
        this.roomType = roomType;
    }

    public Room(int idRoom, String roomName, int capacity, String roomType) {
        this.idRoom = idRoom;
        this.roomName = roomName;
        this.capacity = capacity;
        this.roomType = roomType;
    }

    public int getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(int idRoom) {
        this.idRoom = idRoom;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Seat[][] getSeats() {
        return seats;
    }

    public void setSeats(Seat[][] seats) {
        this.seats = seats;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public static ArrayList<Room> getRooms() {
        ArrayList<Room> rooms = new ArrayList<>();
        String query = "SELECT * FROM rooms";

        try (
                Connection connection = MySQLConnection.connect();
                Statement st = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet rs = st.executeQuery(query)
        ) {

            while (rs.next()) {
                rooms.add(new Room(
                        rs.getInt("idRoom"),
                        rs.getString("roomName"),
                        rs.getInt("capacity"),
                        rs.getString("roomType")
                ));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return rooms;
    }

    public static Room getRoom(int id) {
        Room room = null;
        String query = "SELECT * FROM rooms WHERE idRoom = " + id;

        try (
                Connection connection = MySQLConnection.connect();
                Statement st = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet rs = st.executeQuery(query)
        ) {

            if (rs.next()) {
                room = new Room(
                        rs.getInt("idRoom"),
                        rs.getString("roomName"),
                        rs.getInt("capacity"),
                        rs.getString("roomType")
                );
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return room;
    }

    public static int addRoom(Room room) {

        int id = 0;
        String query = "INSERT INTO rooms " + "(roomName,capacity,roomType) ))"
                + "VALUES (?,?,?)";
        int created = 0;

        try (Connection connection = MySQLConnection.connect();
             PreparedStatement pst = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        ) {
            pst.setString(1, room.getRoomName());
            pst.setInt(2, room.getCapacity());
            pst.setString(3, room.getRoomType());

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

    public static boolean updateRoom(Room room) {
        String query = "UPDATE rooms SET roomName = ?,capacity = ?,roomType = ? WHERE idRoom = ?";
        int updated = 0;

        try (
                Connection connection = MySQLConnection.connect();
                PreparedStatement pst = connection.prepareStatement(query)
        ) {
            pst.setString(1, room.getRoomName());
            pst.setInt(2, room.getCapacity());
            pst.setString(3, room.getRoomType());
            pst.setInt(4, room.getIdRoom());

            updated = pst.executeUpdate();
            return updated > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveSeats() {
        if (seats == null) return;

        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[i].length; j++) {
                Seat seat = seats[i][j];
                seat.setIdRoom(this.idRoom);
                Seat.addSeat(seat);
            }
        }
    }

    public void updateRoomSize(Seat[][] oldSeats) {
        ArrayList<Integer> existingIds = new ArrayList<>();

        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[i].length; j++) {
                Seat newSeat = seats[i][j];
                if (oldSeats != null && i < oldSeats.length && j < oldSeats[i].length && oldSeats[i][j] != null) {
                    newSeat.setIdSeat(oldSeats[i][j].getIdSeat());
                    existingIds.add(newSeat.getIdSeat());
                    Seat.updateSeat(newSeat);
                } else {
                    newSeat.setIdRoom(this.idRoom);
                    int newId = Seat.addSeat(newSeat);
                    newSeat.setIdSeat(newId);
                }
            }
        }

        if (oldSeats != null) {
            for (int i = 0; i < oldSeats.length; i++) {
                for (int j = 0; j < oldSeats[i].length; j++) {
                    Seat oldSeat = oldSeats[i][j];
                    if (oldSeat != null && !existingIds.contains(oldSeat.getIdSeat())) {
                        Seat.deleteSeat(oldSeat.getIdSeat());
                    }
                }
            }
        }
    }





}
