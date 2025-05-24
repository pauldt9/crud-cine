package View;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame login = new JFrame();
        login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        login.setSize(1400, 900);
        login.setLocationRelativeTo(null);
        login.setTitle("Identifícate");

        LoginView loginPanel = new LoginView();
        login.add(loginPanel);
        login.setVisible(true);
    }
}