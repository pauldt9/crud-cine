package View;

import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.*;
import java.awt.*;

public class LoginView extends JPanel {
    private JButton verifyUser;
    private JButton adminButton;
    private JTextField userField;
    private JTextField passwordField;

    public LoginView(){
        FlatLightLaf.setup();
        UIManager.put("Button.arc", 999);
        UIManager.put("TextField.arc", 20);

        setLayout(new GridBagLayout());
        setBackground(new Color(0x4A66CA));

        //Logo?? no gibran no pongas mi foto


        //panel central del login (rectangulo blanco)
        JPanel centralPanel = new JPanel();
        centralPanel.setBackground(Color.WHITE);
        centralPanel.setPreferredSize(new Dimension(480, 580));
        add(centralPanel);


    }

    //crea los botones
    public JButton createButton(String buttonName,int w, int h){
        JButton button = new JButton(buttonName);
        button.setFont(new Font("Montserrat", Font.BOLD, 15));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setPreferredSize(new Dimension(w, h));
        button.setBackground(new Color(0x5C7AF4));
        button.setActionCommand(buttonName);

        return button;
    }

    public JTextField createTextField(String placeHolder, int w, int h){
        JTextField textField = new JTextField();
        textField.setFont(new Font("Montserrat", Font.PLAIN, 15));
        textField.setForeground(new Color(30, 30 , 30));
        return textField;
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(Color.WHITE);
        //g2.setColor(new Color(0x4A66CA));
        g2.fillRoundRect(442, 130, 500, 600, 20, 20);
    }
}
