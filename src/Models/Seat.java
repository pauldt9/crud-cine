package Models;

public class Seat {
    private String seatName;
    private int seatNumber;
    private boolean isOccupied;

    public Seat(String seatName, int seatNumber, boolean isOccupied) {
        this.seatName = seatName;
        this.seatNumber = seatNumber;
        this.isOccupied = isOccupied;
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
}
