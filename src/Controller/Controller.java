package Controller;

import View.LoginView;
import View.AdminMenu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener {
    private LoginView loginView;
    private JFrame loginFrame;
    private AdminMenu adminMenu;

    public Controller(LoginView loginView, JFrame loginFrame, AdminMenu adminMenu) {
        this.loginView = loginView;
        this.loginFrame = loginFrame;
        this.adminMenu = adminMenu;
        this.loginView.setListeners(this);
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command) {
            case "Ingresar":
                System.out.println("el ususario ha hecho click en ingresar");
                if (validateLogin()){
                    openMainView();
                } else {
                    JOptionPane.showMessageDialog(loginFrame, "Credenciales incorrectas", "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;
        }
    }

    public boolean validateLogin(){
        /*Aqui agregar validaciones del login*/
        return true;
    }

    public void openMainView(){
        AdminMenu adminMenu = new AdminMenu();
        loginFrame.remove(loginView);
        loginFrame.add(adminMenu);

        loginFrame.repaint();
        loginFrame.revalidate();
        loginFrame.setTitle("Inicio");
    }
}
