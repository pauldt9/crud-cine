package Controller;

import View.LoginView;
import View.AdminMenu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener {
    private LoginView loginView;
    private JFrame frame;
    private AdminMenu adminMenu;

    public Controller(LoginView loginView, JFrame frame, AdminMenu adminMenu) {
        this.loginView = loginView;
        this.frame = frame;
        this.adminMenu = adminMenu;
        this.loginView.setListeners(this);
        this.adminMenu.setListeners(this);
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command) {
            case "Ingresar":
                System.out.println("el dinosaurio ha hecho click en ingresar:v");
                if (validateLogin()){
                    openMainView();
                }
                break;
            case "Salir":
                System.out.println("el usuario ha hecho click en salir");
                exit();
                break;
            case "Menu":
                System.out.println("el usuario quiere ingresar al menu");
                break;
            case "Peliculas":
                System.out.println("el usuario accedio a peliculas");
                break;
            case "Ventas":
                System.out.println("el usuario accedio a ventas");
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

        if (loginView.getUserField().trim().isEmpty()) {
            faltantes.append("Usuario, ");
        }
        if (loginView.getPasswordField().trim().isEmpty()) {
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
            frame.remove(adminMenu);
            frame.add(loginView);
            frame.repaint();
            frame.revalidate();
            frame.setTitle("Ingresar credenciales");
        }
    }


    public void openMainView(){
        frame.remove(loginView);
        frame.add(adminMenu);

        frame.repaint();
        frame.revalidate();
        frame.setTitle("Inicio");
    }
}
