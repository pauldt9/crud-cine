package Models;

import utils.MySQLConnection;

import java.sql.*;
import java.util.List;

public class OccupiedSeats {
    private int idOccupied;
    private int idSeat;
    private int idFunction;
    private Boolean isOccupied;

    public OccupiedSeats(int idOccupied, int idSeat, int idFunction, Boolean isOccupied) {
        this.idOccupied = idOccupied;
        this.idSeat = idSeat;
        this.idFunction = idFunction;
        this.isOccupied = isOccupied;
    }

    public int getIdOccupied() {
        return idOccupied;
    }

    public void setIdOccupied(int idOccupied) {
        this.idOccupied = idOccupied;
    }

    public int getIdSeat() {
        return idSeat;
    }

    public void setIdSeat(int idSeat) {
        this.idSeat = idSeat;
    }

    public int getidFunction() {
        return idFunction;
    }

    public void setidFunction(int idFunction) {
        this.idFunction = idFunction;
    }

    public Boolean getOccupied() {
        return isOccupied;
    }

    public void setOccupied(Boolean occupied) {
        isOccupied = occupied;
    }

    public static int addReservation(OccupiedSeats reservation) {
        int id = 0;
        String query = "INSERT INTO occupiedseats (idSeat, idFunction, isOccupied) VALUES (?, ?, ?)";

        try (Connection connection = MySQLConnection.connect();
             PreparedStatement pst = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            pst.setInt(1, reservation.getIdSeat());
            pst.setInt(2, reservation.getidFunction());
            pst.setBoolean(3, reservation.getOccupied());

            pst.executeUpdate();

            ResultSet rs = pst.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

//    public static boolean isSeatOccupied(int idSeat, int idSchedule) {
//        String query = "SELECT isOccupied FROM occupiedseats WHERE idSeat = ? AND idFunction = ?";
//        try (Connection connection = MySQLConnection.connect();
//             PreparedStatement pst = connection.prepareStatement(query)) {
//
//            pst.setInt(1, idSeat);
//            pst.setInt(2, idSchedule);
//            ResultSet rs = pst.executeQuery();
//
//            return rs.next() && rs.getBoolean("isOccupied");
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//    public static boolean cancelReservation(int idSeat, int idSchedule) {
//        String query = "DELETE FROM occupiedseats WHERE idSeat = ? AND idSchedule = ?";
//        try (Connection connection = MySQLConnection.connect();
//             PreparedStatement pst = connection.prepareStatement(query)) {
//
//            pst.setInt(1, idSeat);
//            pst.setInt(2, idSchedule);
//            int deleted = pst.executeUpdate();
//
//            return deleted > 0;
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return false;
//        }
//    }

//    public static boolean reserveSeats(List<OccupiedSeats> reservations) {
//        String query = "INSERT INTO occupiedseats (idSeat, idSchedule, isOccupied) VALUES (?, ?, ?)";
//
//        try (Connection connection = MySQLConnection.connect();
//             PreparedStatement pst = connection.prepareStatement(query)) {
//
//            for (OccupiedSeats r : reservations) {
//                pst.setInt(1, r.getIdSeat());
//                pst.setInt(2, r.getIdSchedule());
//                pst.setBoolean(3, r.getOccupied());
//                pst.addBatch();
//            }
//
//            int[] results = pst.executeBatch();
//            for (int r : results) {
//                if (r == Statement.EXECUTE_FAILED) return false;
//            }
//
//            return true;
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
}
