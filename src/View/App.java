package View;

import Controller.AdminController;
import Controller.LoginController;
import utils.MySQLConnection;

import javax.swing.*;
import java.awt.*;

public class App {

    public static String configPath;

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

        configPath = "config.properties";

        AdminView adminView = new AdminView();
        EmployeeView employeeView = new EmployeeView();

        new LoginController(loginPanel, frame, adminView, employeeView);
        new AdminController(loginPanel, frame, adminView);
    }
}