package Models;

import utils.MySQLConnection;

import java.sql.*;
import java.util.ArrayList;

public class Room {
    private int idRoom; //primary key
    private String roomName;
    private int capacity;
    private String roomType;
    private int roomRows;
    private int roomCols;


    //Por lo pronto y por conveniencia, empezamos los asientos como una matriz
    private Seat[][] seats;

    public Room(int idRoom, int capacity, String roomName, Seat[][] seats, String roomType, int roomRows, int roomCols) {
        this.idRoom = idRoom;
        this.capacity = capacity;
        this.roomName = roomName;
        this.seats = seats;
        this.roomType = roomType;
        this.roomRows = roomRows;
        this.roomCols = roomCols;
    }

    public Room(int idRoom, String roomName, int capacity, String roomType, int roomRows, int roomCols) {
        this.idRoom = idRoom;
        this.roomName = roomName;
        this.capacity = capacity;
        this.roomType = roomType;
        this.roomRows = roomRows;
        this.roomCols = roomCols;
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

    public int getRows() {
        return roomRows;
    }

    public void setRows(int roomRows) {
        this.roomRows = roomRows;
    }

    public int getCols() {
        return roomCols;
    }

    public void setCols(int roomCols) {
        this.roomCols = roomCols;
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
                        rs.getString("roomType"),
                        rs.getInt("roomRows"),
                        rs.getInt("roomCols")
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
                        rs.getString("roomType"),
                        rs.getInt("roomRows"),
                        rs.getInt("roomCols")
                );
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return room;
    }

    public static int addRoom(Room room) {

        int id = 0;
        String query = "INSERT INTO rooms " + "(roomName,capacity,roomType,roomRows,roomCols)"
                + "VALUES (?,?,?,?,?)";
        int created = 0;

        try (Connection connection = MySQLConnection.connect();
             PreparedStatement pst = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        ) {
            pst.setString(1, room.getRoomName());
            pst.setInt(2, room.getCapacity());
            pst.setString(3, room.getRoomType());
            pst.setInt(4, room.getRows());
            pst.setInt(5, room.getCols());

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
        String query = "UPDATE rooms SET roomName = ?,capacity = ?,roomType = ?,roomRows = ?, roomCols = ? WHERE idRoom = ?";
        int updated = 0;

        try (
                Connection connection = MySQLConnection.connect();
                PreparedStatement pst = connection.prepareStatement(query)
        ) {
            pst.setString(1, room.getRoomName());
            pst.setInt(2, room.getCapacity());
            pst.setString(3, room.getRoomType());
            pst.setInt(4, room.getRows());
            pst.setInt(5, room.getCols());
            pst.setInt(6, room.getIdRoom());


            updated = pst.executeUpdate();
            return updated > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean deleteRoom(int id) {

        int deleted = 0;

        String query = "DELETE FROM rooms WHERE idRoom = " + id;

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

    public static boolean deleteRoomSeats(int id) {

        int deleted = 0;

        String query = "DELETE FROM seats WHERE idRoom = " + id;

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

    public void generateSeats(int rows, int cols) {
        Seat[][] generatedSeats = new Seat[rows][cols];
        char rowLetter = 'A';

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                String seatName = rowLetter + String.valueOf(j + 1);
                int seatNumber = j + 1;

                Seat seat = new Seat();
                seat.setSeatName(seatName);
                seat.setSeatNumber(seatNumber);
                seat.setIdRoom(this.idRoom);


                int generatedId = Seat.addSeat(seat);
                seat.setIdSeat(generatedId);

                generatedSeats[i][j] = seat;
            }
            rowLetter++;
        }

        this.seats = generatedSeats;
    }



    public void updateRoomSize(Seat[][] oldSeats) {
        ArrayList<Integer> existingIds = new ArrayList<>();

        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[i].length; j++) {
                Seat newSeat = seats[i][j];

                if (newSeat.getIdSeat() > 0) {
                    // Si ya tiene ID, solo actualizar
                    Seat.updateSeat(newSeat);
                    existingIds.add(newSeat.getIdSeat());
                } else {
                    // Nuevo asiento
                    newSeat.setIdRoom(this.idRoom);
                    int newId = Seat.addSeat(newSeat);
                    newSeat.setIdSeat(newId);
                    existingIds.add(newId);
                }
            }
        }

        // Eliminar asientos que ya no existen
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
