package View;

import com.formdev.flatlaf.FlatLightLaf;
import lib.TextPrompt;

import javax.swing.*;
import java.awt.*;

public class LoginView extends JPanel {
    private JButton verifyUser;
    private JButton adminButton;
    private JTextField userField;
    private JPasswordField passwordField;

    public LoginView(){
        FlatLightLaf.setup();
        UIManager.put("Button.arc", 25);
        UIManager.put("TextField.arc", 20);

        setLayout(new GridBagLayout());
        setBackground(new Color(0x4A66CA));

        //Logo?? no gibran no pongas mi foto


        //panel central del login (rectangulo blanco)
        JPanel centralPanel = new JPanel();
        centralPanel.setLayout(new BoxLayout(centralPanel, BoxLayout.Y_AXIS));
        centralPanel.setBackground(Color.WHITE);
        centralPanel.setPreferredSize(new Dimension(480, 580));
        add(centralPanel);

        JLabel loginLabel = createJLabel("Identifícate", 40);
        centralPanel.add(Box.createVerticalStrut(100));
        loginLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        centralPanel.add(loginLabel);

        JLabel user = createJLabel("Usuario", 15);
        centralPanel.add(Box.createVerticalStrut(40));
        user.setAlignmentX(Component.CENTER_ALIGNMENT);
        centralPanel.add(user);

        userField = createTextField("   Ingresar usuario", 350, 50);
        userField.setMaximumSize(new Dimension(350, 50));
        userField.setAlignmentX(Component.CENTER_ALIGNMENT);
        centralPanel.add(Box.createVerticalStrut(5)); //baja el textfield
        centralPanel.add(userField);

        JLabel password = createJLabel("Contraseña", 15);
        centralPanel.add(Box.createVerticalStrut(15));
        password.setAlignmentX(Component.CENTER_ALIGNMENT);
        centralPanel.add(password);

        passwordField = createPasswordField("   Ingresar contraseña");
        passwordField.setMaximumSize(new Dimension(350, 50));
        passwordField.setAlignmentX(Component.CENTER_ALIGNMENT);
        centralPanel.add(Box.createVerticalStrut(5));
        centralPanel.add(passwordField);

        verifyUser = createButton("Ingresar", 20,160, 40);
        verifyUser.setAlignmentX(Component.CENTER_ALIGNMENT);
        centralPanel.add(Box.createVerticalStrut(35));
        centralPanel.add(verifyUser);

    }

    public JLabel createJLabel(String title, int fontSize){
        JLabel label = new JLabel(title);
        label.setFont(new Font("Montserrat", Font.BOLD, fontSize));
        label.setForeground(new Color(30, 30, 30));
        return label;
    }

    //crea los botones
    public JButton createButton(String buttonName, int fontSize, int w, int h){
        JButton button = new JButton(buttonName);
        button.setFont(new Font("Montserrat", Font.BOLD, fontSize));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setMaximumSize(new Dimension(w, h));
        button.setBackground(new Color(0x17C3B2));
        button.setActionCommand(buttonName);

        return button;
    }

    public JTextField createTextField(String placeHolder, int w, int h){
        Font font = new Font("Montserrat", Font.PLAIN, 15);
        JTextField textField = new JTextField();

        textField.setFont(font);
        textField.setForeground(new Color(30, 30 , 30));
        textField.setBorder(BorderFactory.createLineBorder(new Color(20, 20, 20), 1, true));
        textField.setMaximumSize(new Dimension(w, h));

        TextPrompt ph = new TextPrompt(placeHolder, textField);
        ph.setForeground(new Color(150, 150, 150));
        ph.setFont(font);

        return textField;
    }

    public JPasswordField createPasswordField(String placeHolder){
        JPasswordField pf = new JPasswordField();

        pf.setForeground(new Color(30, 30, 30));
        pf.setBorder(BorderFactory.createLineBorder(new Color(20, 20, 20), 1, true));

        TextPrompt ph = new TextPrompt(placeHolder, pf);
        ph.setForeground(new Color(150, 150, 150));
        ph.setFont(new Font("Montserrat", Font.PLAIN, 15));

        return pf;
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); //suaviza las lineas

        g2.setColor(Color.WHITE);
        g2.fillRoundRect(442, 130, 500, 600, 20, 20);
    }
}
