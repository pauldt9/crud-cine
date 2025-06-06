package Controller;

import Models.*;
import View.Admin.AdminView;
import View.Employee.Catalog;
import View.Employee.EmployeeView;
import View.LoginPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class EmployeeController implements ActionListener {
    private JFrame frame;
    private EmployeeView employeeView;
    private AdminView adminView;
    private LoginPanel loginPanel;
    private Catalog catalog;

    private ArrayList<MovieShowtime> allShowtimes = new ArrayList<MovieShowtime>(); //aqui se guardan todas las funciones disponibles
    private ArrayList<Seat> seatsSelected = new ArrayList<Seat>();
    private MovieShowtime currentShowtime;
    private SalesTableModel salesTableModel;

    public EmployeeController(JFrame frame, EmployeeView employeeView, LoginPanel loginPanel, Catalog catalog, AdminView adminView) {
        this.employeeView = employeeView;
        this.loginPanel = loginPanel;
        this.frame = frame;
        this.catalog = catalog;
        this.employeeView.setListeners(this);
        this.catalog.initMoviesCatalog(this);
        this.allShowtimes = MovieShowtime.getShowtimes();
        salesTableModel = adminView.getSalesMain().getSalesTableModel();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.startsWith("funcion ")){
            int idShowtime = Integer.parseInt(command.substring("funcion ".length()));
            showSeatsSelection(idShowtime);
            return;
        }

        switch (command){
            case "Salir":
                exit();
                loginPanel.clearFields();
                break;
            case "Regresar al catalogo":
            case "Inicio empleado":
                showEmployeePanel("catalogo");
                break;
            case "Funciones":
            case "Regresar a funciones":
                showEmployeePanel("seleccionar hora");
                break;
            case "Finalizar venta":
                completeSale();
                break;
            case "Confirmar asientos":
                //trae todos los nombres de los asientos seleccionados
                ArrayList<String> selectedSeatNames = employeeView.getSelectSeatsPanel().getSelectedSeats();
                if (selectedSeatNames.isEmpty()){
                    JOptionPane.showMessageDialog(frame, "Debes seleccionar al menos 1 asiento");
                } else {
                    seatsSelected = getSeatsFromNames(selectedSeatNames, currentShowtime.getRoom().getIdRoom());
                    employeeView.getDetailsPanel().updateMovieImage(currentShowtime.getMovie().getImgRoute());
                    employeeView.getDetailsPanel().updateDetails(currentShowtime, seatsSelected);
                    showEmployeePanel("detalles de venta");
                }
                break;
            case "Regresar a seleccion de asientos":
                showEmployeePanel("seleccionar asientos");
                break;
            default:
                int idMovie = Integer.parseInt(command);
                movieSelected(idMovie);
        }
    }

    public void exit(){
        int answer = JOptionPane.showConfirmDialog(frame, "Estas seguro de cerrar sesion?", "Salir", JOptionPane.YES_NO_OPTION);

        if (answer == JOptionPane.YES_OPTION){
            frame.remove(employeeView);
            frame.add(loginPanel);
            frame.repaint();
            frame.revalidate();
            frame.setTitle("Ingresar credenciales");
        }
    }

    //cambiar de pestaña
    public void showEmployeePanel(String namePanel){
        CardLayout card = (CardLayout) (employeeView.getMainPanel().getLayout());
        card.show(employeeView.getMainPanel(), namePanel);
    }

    public void movieSelected(int idMovie){
        Movie movie = Movie.getMovieById(idMovie);
        ArrayList<MovieShowtime> showtimes = new ArrayList<MovieShowtime>();

        for (MovieShowtime ms : allShowtimes){
            if (ms.getMovie().getIdMovie() == idMovie){
                showtimes.add(ms);
            }
        }

        employeeView.getSelectHourPanel().updateMovieImage(movie.getImgRoute());
        employeeView.getSelectHourPanel().updateShowtimes(showtimes, this);
        showEmployeePanel("seleccionar hora");
    }

    public void showSeatsSelection(int idShowtime){
        MovieShowtime showtime = null;

        for (MovieShowtime ms : allShowtimes){
            if (ms.getIdShowtime() == idShowtime){
                showtime = ms;
                break;
            }
        }

        if (showtime != null){
            currentShowtime = showtime;
            employeeView.getSelectSeatsPanel().updateSeats(showtime);
            showEmployeePanel("seleccionar asientos");
            employeeView.getSelectSeatsPanel().updateMovieImage(showtime.getMovie().getImgRoute());
        }
    }

    private ArrayList<Seat> getSeatsFromNames(ArrayList<String> names, int idRoom) {
        ArrayList<Seat> allSeats = Seat.getSeatsByRoomId(idRoom);
        ArrayList<Seat> selectedSeats = new ArrayList<>();

        for (String name : names) {
            for (Seat seat : allSeats) {
                if (seat.getSeatName().equals(name)) {
                    selectedSeats.add(seat);
                    break;
                }
            }
        }
        return selectedSeats;
    }

    public void completeSale(){
        int roomPrice = switch (currentShowtime.getRoom().getRoomType()){
            case "IMAX" -> 150;
            case "4D" -> 120;
            case "3D" -> 100;
            case "MacroXE" -> 90;
            default -> 60;
        };

        for (Seat seat : seatsSelected){
            Ticket ticket = new Ticket(0, currentShowtime, seat, roomPrice,
                    currentShowtime.getIdShowtime(), seat.getIdSeat());

            Ticket.addTicket(ticket);
            salesTableModel.addRow(ticket);

            OccupiedSeats os = new OccupiedSeats(0, seat.getIdSeat(),
                    currentShowtime.getIdShowtime(), true);

            OccupiedSeats.addReservation(os);
        }

        JOptionPane.showMessageDialog(frame, "Venta realizada con exito");
        seatsSelected.clear();
        showEmployeePanel("catalogo");
    }
}
