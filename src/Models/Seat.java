package Models;

public class Seat {
    private int idSeat; //primary key
    private String seatName;
    private int seatNumber;
    private boolean isOccupied;
    private Room room; //Foreign Key


    public Seat(int idSeat, String seatName, int seatNumber, boolean isOccupied, Room room) {
        this.idSeat = idSeat;
        this.seatName = seatName;
        this.seatNumber = seatNumber;
        this.isOccupied = isOccupied;
        this.room = room;
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
}
