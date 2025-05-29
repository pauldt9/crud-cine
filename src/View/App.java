package View;

import Controller.Controller;
import utils.MySQLConnection;

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

        LoginPanel loginPanel = new LoginPanel();
        login.add(loginPanel);
        login.setVisible(true);

        Toolkit tk = Toolkit.getDefaultToolkit();
        Image icon = tk.getImage("src/img/logo.png");
        login.setIconImage(icon);

        MySQLConnection.connect();


        new Controller(loginPanel, login, new AdminView(), new EmployeeView());
    }
}