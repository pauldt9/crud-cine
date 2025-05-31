package View;

import Controller.Controller;
import Models.Employee;
import utils.MySQLConnection;

import javax.swing.*;
import java.awt.*;

public class App {
    public static void main(String[] args) {
        JFrame frame = new JFrame(); //ventana principal
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1300, 800);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setTitle("Ingresar credenciales");

        LoginPanel loginPanel = new LoginPanel();
        frame.add(loginPanel);
        frame.setVisible(true);

        Toolkit tk = Toolkit.getDefaultToolkit();
        Image icon = tk.getImage("src/img/logo.png");
        frame.setIconImage(icon);

        MySQLConnection.connect();

        new Controller(loginPanel, frame, new AdminView(), new EmployeeView(), new Employee());
    }
}