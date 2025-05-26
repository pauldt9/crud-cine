package View;

import Controller.Controller;

import javax.swing.*;
import java.awt.*;

public class App {
    public static void main(String[] args) {
        JFrame login = new JFrame(); //ventana principal
        login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        login.setSize(1400, 900);
        login.setLocationRelativeTo(null);
        login.setResizable(false);
        login.setTitle("Ingresar credenciales");

        LoginView loginPanel = new LoginView();
        login.add(loginPanel);
        login.setVisible(true);

        Toolkit tk = Toolkit.getDefaultToolkit();
        Image icon = tk.getImage("src/img/logo.png");
        login.setIconImage(icon);


        new Controller(loginPanel, login);
    }
}