package Controller;

import View.Catalog;
import View.EmployeeView;
import View.LoginPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmployeeController implements ActionListener {
    private JFrame frame;
    private EmployeeView employeeView;
    private LoginPanel loginPanel;
    private Catalog catalog;

    public EmployeeController(JFrame frame, EmployeeView employeeView, LoginPanel loginPanel, Catalog catalog){
        this.employeeView = employeeView;
        this.loginPanel = loginPanel;
        this.frame = frame;
        this.catalog = catalog;
        this.employeeView.setListeners(this);
        this.catalog.initMoviesCatalog(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command){
            case "Salir":
                exit();
                break;
            case "Regresar al catalogo":
            case "Inicio empleado":
                showEmployeePanel("catalogo");
                break;
            case "Funciones":
                showEmployeePanel("seleccionar hora");
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
        System.out.println("ID pelicula: " + idMovie);
    }
}
