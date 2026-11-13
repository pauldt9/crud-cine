package View;

import com.formdev.flatlaf.FlatLightLaf;
import lib.TextPrompt;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import static utils.Colors.*;
import static utils.CreateComponents.*;

public class LoginPanel extends JPanel {
    private JButton loginButton;
    private JTextField userField;
    private JPasswordField passwordField;

    private JPanel centralPanel;

    public LoginPanel(){

        setLayout(new GridBagLayout());
        setBackground(LOGIN_BG);

        //panel central del login (rectangulo blanco)
        centralPanel = new JPanel();
        centralPanel.setLayout(new BoxLayout(centralPanel, BoxLayout.Y_AXIS));
        centralPanel.setBackground(Color.WHITE);
        centralPanel.setOpaque(false);
        centralPanel.setPreferredSize(new Dimension(480, 580));
        add(centralPanel);

        JLabel loginLabel = createJLabel("Bienvenido", 40, true);
        centralPanel.add(Box.createVerticalStrut(100));
        loginLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        centralPanel.add(loginLabel);

        JLabel user = createJLabel("Usuario", 15, true);
        centralPanel.add(Box.createVerticalStrut(30));
        user.setAlignmentX(Component.CENTER_ALIGNMENT);
        centralPanel.add(user);

        userField = createTextField("Ingresar usuario", 350, 50);
        userField.setMaximumSize(new Dimension(325, 60));
        userField.setAlignmentX(Component.CENTER_ALIGNMENT);
        centralPanel.add(Box.createVerticalStrut(5));
        centralPanel.add(userField);

        JLabel password = createJLabel("Contraseña", 15, true);
        centralPanel.add(Box.createVerticalStrut(25));
        password.setAlignmentX(Component.CENTER_ALIGNMENT);
        centralPanel.add(password);

        passwordField = createPasswordField("Ingresar contraseña", 325, 60);
        passwordField.setAlignmentX(Component.CENTER_ALIGNMENT);
        centralPanel.add(Box.createVerticalStrut(5));
        centralPanel.add(passwordField);

        loginButton = createButton("Ingresar", 330, 40);
        loginButton.setFont(new Font("Helvetica Neue", Font.BOLD, 20));
        loginButton.setBackground(ACCENT);
        loginButton.setForeground(ACCENT_TEXT);
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        centralPanel.add(Box.createVerticalStrut(40));
        centralPanel.add(loginButton);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);//suaviza las lineas

        Composite original = g2.getComposite();
        g2.setColor(new Color(0, 0, 0, 50));
        g2.fillRoundRect(centralPanel.getX(), centralPanel.getY(), centralPanel.getWidth(), centralPanel.getHeight(), 20, 20);
        g2.setComposite(original);

        g2.setColor(Color.WHITE);
        g2.fillRoundRect(centralPanel.getX(), centralPanel.getY(), centralPanel.getWidth(), centralPanel.getHeight(), 20, 20);
    }

    public String getUserField(){
        return userField.getText();
    }

    public String getPasswordField(){
        return new String(passwordField.getPassword());
    }

    public void clearFields(){
        passwordField.setText("");
        userField.setText("");
    }

    public void setListeners(ActionListener listener){
        loginButton.addActionListener(listener);
    }
}
