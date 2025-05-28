package Models;

public class Ticket {

    private int idTicket; //Primary Key
    private Function function; //Foreign Key
    private Seat seat; //Foreign Key
    private int price;

    public Ticket(int idTicket, Function function, Seat seat, int price) {
        this.idTicket = idTicket;
        this.function = function;
        this.seat = seat;
        this.price = price;
    }

    public int getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(int idTicket) {
        this.idTicket = idTicket;
    }

    public Function getFunction() {
        return function;
    }

    public void setFunction(Function function) {
        this.function = function;
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
}
