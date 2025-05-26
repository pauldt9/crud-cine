package Controller;

import View.App;
import View.LoginView;
import View.MainView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener {
    private LoginView loginView;
    private JFrame loginFrame;

    public Controller(LoginView loginView, JFrame loginFrame) {
        this.loginView = loginView;
        this.loginFrame = loginFrame;
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
        /*Agregar aqui las validar el usuario y contraseña*/
        return true;
    }

    public void openMainView(){
        MainView mainView = new MainView();
        loginFrame.remove(loginView);
        loginFrame.add(mainView);

        loginFrame.repaint();
        loginFrame.revalidate();
        loginFrame.setTitle("Inicio");
    }
}
