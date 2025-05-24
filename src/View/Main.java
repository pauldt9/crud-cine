package View;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        JFrame login = new JFrame(); //ventana principal
        login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        login.setSize(1400, 900);
        login.setLocationRelativeTo(null);
        login.setTitle("Ingresar credenciales");

        LoginView loginPanel = new LoginView();
        login.add(loginPanel);
        login.setVisible(true);

        Toolkit tk = Toolkit.getDefaultToolkit();
        Image icon = tk.getImage("src/img/logo.png");
        login.setIconImage(icon);
    }
}