package View;

import Controller.AdminController;
import Controller.EmployeeController;
import Controller.LoginController;
import View.Admin.AdminView;
import View.Employee.Catalog;
import View.Employee.EmployeeView;

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

        Catalog movieCatalog = new Catalog();
        AdminView adminView = new AdminView();
        EmployeeView employeeView = new EmployeeView(movieCatalog);

        new LoginController(loginPanel, frame, adminView, employeeView);
        new AdminController(loginPanel, frame, adminView);
        new EmployeeController(frame, employeeView, loginPanel, movieCatalog);
    }
}