package View;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;

public class LeftPanelEmployee extends JPanel {
    private Color westPanelCol = new Color(0xDCE9F9);
    private Color fgCol = new Color(0x2C3E50);
    private Color buttonColor = new Color(0xEDF2FA);

    private JLabel userTitle;

    //Botones
    private JButton homeButton;
    private JButton darkMode;
    private JButton exitButton;

    public LeftPanelEmployee(){
        setBackground(westPanelCol);
        setPreferredSize(new Dimension(200, 800));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        userTitle = createJLabel("Taquilla", 30, true);
        userTitle.setAlignmentX(CENTER_ALIGNMENT);

        add(Box.createVerticalStrut(70));
        add(userTitle);

        homeButton = createButton("Inicio", 20, 150, 40);
        homeButton.setActionCommand("Inicio Empleado");
        homeButton.setBackground(buttonColor);
        homeButton.setForeground(fgCol);
        homeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(Box.createVerticalStrut(50));
        add(homeButton);

        darkMode = createButton(null, 1, 150, 40);
        darkMode.setActionCommand("Modo Oscuro");
        darkMode.setBackground(buttonColor);
        darkMode.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(Box.createVerticalStrut(420));
        add(darkMode);

        exitButton = createButton(null, 1, 150, 40);
        exitButton.setActionCommand("Salir");
        exitButton.setBackground(new Color(0x17C3B2));
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(Box.createVerticalStrut(30));
        add(exitButton);

        //Iconos
        try {
            Image darkIcon = ImageIO.read(getClass().getResource("/img/dark.png"));
            darkIcon = darkIcon.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            darkMode.setIcon(new ImageIcon(darkIcon));

            Image exitIcon = ImageIO.read(getClass().getResource("/img/exit.png"));
            exitIcon = exitIcon.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            exitButton.setIcon(new ImageIcon(exitIcon));
        } catch (IOException e){
            System.out.println("hubo un error al cargar las imagenes, error: " + e.getMessage());
        }
    }

    public JLabel createJLabel(String title, int fontSize, boolean bold){
        JLabel label = new JLabel(title);
        if (bold){
            label.setFont(new Font("Helvetica Neue", Font.BOLD, fontSize));
        } else {
            label.setFont(new Font("Helvetica Neue", Font.PLAIN, fontSize));
        }
        label.setForeground(fgCol);
        return label;
    }

    public JButton createButton(String buttonName, int fontSize, int w, int h){
        JButton button = new JButton(buttonName);
        button.setFont(new Font("Helvetica Neue", Font.BOLD, fontSize));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setPreferredSize(new Dimension(w, h));
        button.setMinimumSize(new Dimension(w, h));
        button.setMaximumSize(new Dimension(w, h));
        return button;
    }

    public void setListeners(ActionListener listener){
        homeButton.addActionListener(listener);
        darkMode.addActionListener(listener);
        exitButton.addActionListener(listener);
    }
}
