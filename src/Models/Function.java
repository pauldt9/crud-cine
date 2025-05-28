package Models;

public class Function {
    private int idFunction; //Primary Key
    private Movie movie; //Foreign Key
    private Room room; //Foreign Key
    private String showTime;

    public Function(int id, Movie movie, Room room, String showTime) {
        this.idFunction = id;
        this.movie = movie;
        this.room = room;
        this.showTime = showTime;
    }

    public int getId() {
        return idFunction;
    }

    public void setId(int id) {
        this.idFunction = id;
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
}
