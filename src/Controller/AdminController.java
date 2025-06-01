package Controller;

import View.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminController implements ActionListener {
    private LoginPanel loginPanel;
    private JFrame frame;
    private AdminView adminView;

    public AdminController(LoginPanel loginPanel, JFrame frame, AdminView adminView){
        this.loginPanel = loginPanel;
        this.frame = frame;
        this.adminView = adminView;
        this.adminView.setListeners(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command) {
            case "Salir":
                System.out.println("ha salido");
                exit();
                break;
            case "Menu":
                System.out.println("menu");
                showAdminPanel("menu");
                break;
            case "Peliculas":
                System.out.println("peliculas");
                showAdminPanel("peliculas");
                break;
            case "Empleados":
                System.out.println("empleados");
                showAdminPanel("Empleados");
                break;
            case "Ventas":
                System.out.println("ventas");
                showAdminPanel("ventas");
                break;
            case "Modo Oscuro":
                System.out.println("cambiar a oscuro");
                adminView.setViewMode("Modo Claro");
                break;
            case "Modo Claro":
                System.out.println("cambiar a claro");
                adminView.setViewMode("Modo Oscuro");
                break;
            case "Eliminar empleado":
                System.out.println("borrar empleado");
                break;
            case "Editar empleado":
                System.out.println("editar empleado");
                showAdminPanel("agregar/editar empleado");
                adminView.setNewAction("Editar");
                break;
            case "Agregar empleado":
                System.out.println("agregar empleado");
                showAdminPanel("agregar/editar empleado");
                adminView.setNewAction("Registrar");
                break;
            case "Eliminar pelicula":
                System.out.println("eliminar pelicula");
                break;
            case "Editar pelicula":
                System.out.println("editar pelicula");
                break;
            case "Agregar pelicula":
                System.out.println("agregar pelicula");
                break;
            case "Confirmar empleado":
                System.out.println("se ha agregado un usuario nuevo");
                JOptionPane.showMessageDialog(frame, "Se ha agregado un empleado nuevo");

                //Insertar la logica para agregar los datos del empleado....

                showAdminPanel("Empleados");
                adminView.clearFields();
                break;
            case "Regresar empleado":
                System.out.println("El usuario se ha regresado al apartado empleado");
                showAdminPanel("Empleados");
                break;
        }
    }

    public void exit(){
        int answer = JOptionPane.showConfirmDialog(frame, "Estas seguro de cerrar sesion?", "Salir", JOptionPane.YES_NO_OPTION);

        if (answer == JOptionPane.YES_OPTION){
            frame.remove(adminView);
            frame.add(loginPanel);
            frame.repaint();
            frame.revalidate();
            frame.setTitle("Ingresar credenciales");
        }
    }

    //con este metodo se cambia las pestañas del admin
    public void showAdminPanel(String namePanel){
        CardLayout card = (CardLayout) (adminView.getMainPanel().getLayout());
        card.show(adminView.getMainPanel(), namePanel);
    }
}
