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

public class LoginView extends JPanel {
    private JButton loginButton;
    private JTextField userField;
    private JPasswordField passwordField;
    private Image logo;

    public LoginView(){
        FlatLightLaf.setup();
        UIManager.put("Button.arc", 25);

        setLayout(new GridBagLayout());
        setBackground(new Color(0x4A66CA));

        //Logo?? no gibran no pongas mi foto
        try {
            logo = ImageIO.read(new File("src/img/logo.png"));
            logo = logo.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        } catch (IOException e){
            System.out.println("Error al cargar la imagen: " + e.getMessage());
        }

        //panel central del login (rectangulo blanco)
        JPanel centralPanel = new JPanel();
        centralPanel.setLayout(new BoxLayout(centralPanel, BoxLayout.Y_AXIS));
        centralPanel.setBackground(Color.WHITE);
        centralPanel.setOpaque(false);
        centralPanel.setPreferredSize(new Dimension(480, 580));
        add(centralPanel);

        JLabel loginLabel = createJLabel("Bienvenido", 40);
        centralPanel.add(Box.createVerticalStrut(140));
        loginLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        centralPanel.add(loginLabel);

        JLabel user = createJLabel("Usuario", 15);
        centralPanel.add(Box.createVerticalStrut(30));
        user.setAlignmentX(Component.CENTER_ALIGNMENT);
        centralPanel.add(user);

        userField = createTextField("Ingresar usuario", 350, 50);
        userField.setMaximumSize(new Dimension(325, 50));
        userField.setAlignmentX(Component.CENTER_ALIGNMENT);
        userField.setBorder(BorderFactory.createCompoundBorder(userField.getBorder(), new EmptyBorder(0, 10, 0, 0))); //agrega un espacio al campo
        centralPanel.add(Box.createVerticalStrut(5)); //baja el textfield
        centralPanel.add(userField);

        JLabel password = createJLabel("Contraseña", 15);
        centralPanel.add(Box.createVerticalStrut(25));
        password.setAlignmentX(Component.CENTER_ALIGNMENT);
        centralPanel.add(password);

        passwordField = createPasswordField("Ingresar contraseña");
        passwordField.setMaximumSize(new Dimension(325, 50));
        passwordField.setAlignmentX(Component.CENTER_ALIGNMENT);
        passwordField.setBorder(BorderFactory.createCompoundBorder(passwordField.getBorder(), new EmptyBorder(0, 10, 0, 0)));
        centralPanel.add(Box.createVerticalStrut(5));
        centralPanel.add(passwordField);

        loginButton = createButton("Ingresar", 20,160, 40);
        loginButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        centralPanel.add(Box.createVerticalStrut(40));
        centralPanel.add(loginButton);


    }

    public JLabel createJLabel(String title, int fontSize){
        JLabel label = new JLabel(title);
        label.setFont(new Font("Helvetica Neue", Font.BOLD, fontSize));
        label.setForeground(new Color(30, 30, 30));
        return label;
    }

    //crea los botones
    public JButton createButton(String buttonName, int fontSize, int w, int h){
        JButton button = new JButton(buttonName);
        button.setFont(new Font("Helvetica Neue", Font.BOLD, fontSize));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setMaximumSize(new Dimension(w, h));
        button.setBackground(new Color(0x17C3B2));
        button.setActionCommand(buttonName);

        return button;
    }

    public JTextField createTextField(String placeHolder, int w, int h){
        Font font = new Font("Helvetica Neue", Font.PLAIN, 16);
        JTextField textField = new JTextField();

        textField.setFont(font);
        textField.setForeground(new Color(30, 30 , 30));
        textField.setBorder(BorderFactory.createLineBorder(new Color(20, 20, 20), 1, true));
        textField.setMaximumSize(new Dimension(w, h));

        TextPrompt ph = new TextPrompt(placeHolder, textField);
        ph.setForeground(new Color(100, 100, 100));
        ph.setFont(font);

        return textField;
    }

    public JPasswordField createPasswordField(String placeHolder){
        JPasswordField pf = new JPasswordField();
        Font font = new Font("Helvetica Neue", Font.PLAIN, 16);

        pf.setForeground(new Color(30, 30, 30));
        pf.setBorder(BorderFactory.createLineBorder(new Color(20, 20, 20), 1, true));
        pf.setFont(font);

        TextPrompt ph = new TextPrompt(placeHolder, pf);
        ph.setForeground(new Color(100, 100, 100));
        ph.setFont(font);

        return pf;
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);//suaviza las lineas

        Composite original = g2.getComposite();
        g2.setColor(new Color(0, 0, 0, 50));
        g2.fillRoundRect(452, 140, 500, 600, 20, 20);
        g2.setComposite(original);

        g2.setColor(Color.WHITE);
        g2.fillRoundRect(442, 130, 500, 600, 20, 20);


        g2.drawImage(logo, 643, 180, this);
    }

    public String getUserField(){
        return userField.getText();
    }

    public String getPasswordField(){
        return new String(passwordField.getPassword());
    }

    public void setListeners(ActionListener listener){
        loginButton.addActionListener(listener);
    }
}
