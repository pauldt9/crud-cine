package Controller;

import View.EmployeeView;
import View.LoginPanel;
import View.AdminView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener {
    private LoginPanel loginPanel;
    private JFrame frame;
    private AdminView adminView;
    private EmployeeView empView;

    public Controller(LoginPanel loginPanel, JFrame frame, AdminView adminView, EmployeeView empView) {
        this.loginPanel = loginPanel;
        this.frame = frame;
        this.adminView = adminView;
        this.empView = empView;
        this.loginPanel.setListeners(this);
        this.adminView.setListeners(this);
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command) {
            case "Ingresar":
                System.out.println("el dinosaurio ha hecho click en ingresar:v");
                if (validateLogin()){
                    if (isAdmin()){
                        openAdminView();

                    } else {
                        openEmployeeView();
                    }
                }
                break;
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
                break;
            case "Agregar empleado":
                System.out.println("agregar empleado");
                break;
        }
    }

    public boolean validateLogin(){
        /*Aqui agregar validaciones del login*/
        String mensajeCamposVacios = camposVacios();

        if (!mensajeCamposVacios.isBlank()) {
            JOptionPane.showMessageDialog(null, mensajeCamposVacios, "Campos vacíos", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    public String camposVacios() {
        StringBuilder faltantes = new StringBuilder();

        if (loginPanel.getUserField().trim().isEmpty()) {
            faltantes.append("Usuario, ");
        }
        if (loginPanel.getPasswordField().trim().isEmpty()) {
            faltantes.append("Contraseña, ");
        }

        if (faltantes.length() == 0) {
            return "";
        }

        return "Los siguientes parámetros están vacíos: " + faltantes.substring(0, faltantes.length() - 2);
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

    public boolean isAdmin(){
        /*alguna validacion si el usuario es admin, pero algo para determinar que ventana abrir*/
        return true;
    }

    //con este metodo se cambia las pestañas del admin
    public void showAdminPanel(String namePanel){
        CardLayout card = (CardLayout) (adminView.getMainPanel().getLayout());
        card.show(adminView.getMainPanel(), namePanel);
    }

    public void openAdminView(){
        frame.remove(loginPanel);
        frame.add(adminView);

        frame.repaint();
        frame.revalidate();
        frame.setTitle("Inicio");
    }

    public void openEmployeeView(){
        frame.remove(loginPanel);
        frame.add(empView);

        frame.repaint();
        frame.revalidate();
        frame.setTitle("Inicio");
    }
}
