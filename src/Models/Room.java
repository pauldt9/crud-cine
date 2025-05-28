package Models;

public class Room {
    private int idRoom;
    private String roomName;
    private int capacity;

    //Por lo pronto y por conveniencia, empezamos los asientos como una matriz
    private Seat[][] seats;

    public Room(int idRoom, int capacity, String roomName, Seat[][] seats) {
        this.idRoom = idRoom;
        this.capacity = capacity;
        this.roomName = roomName;
        this.seats = seats;
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
}
