package Controller;

import Models.Movie;
import Models.MovieShowtime;
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
    private LoginPanel loginPanel;
    private Catalog catalog;
    private ArrayList<MovieShowtime> allShowtimes = new ArrayList<MovieShowtime>();

    public EmployeeController(JFrame frame, EmployeeView employeeView, LoginPanel loginPanel, Catalog catalog){
        this.employeeView = employeeView;
        this.loginPanel = loginPanel;
        this.frame = frame;
        this.catalog = catalog;
        this.employeeView.setListeners(this);
        this.catalog.initMoviesCatalog(this);
        this.allShowtimes = MovieShowtime.getShowtimes();
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
                /*logica para concretar la venta y subirla a la base de datos...*/
                showEmployeePanel("catalogo");
                break;
            case "Confirmar asientos":
                if (employeeView.getSelectSeatsPanel().getSelectedSeats().isEmpty()){
                    JOptionPane.showMessageDialog(frame, "Debes seleccionar al menos 1 asiento");
                } else {
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
            employeeView.getSelectSeatsPanel().updateSeats(showtime);
            showEmployeePanel("seleccionar asientos");
            employeeView.getSelectSeatsPanel().updateMovieImage(showtime.getMovie().getImgRoute());
        }
    }
}
