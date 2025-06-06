package View.Employee;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;

import static utils.CreateComponents.createButton;
import static utils.CreateComponents.createJLabel;

public class LeftPanel extends JPanel {
    private Color westPanelCol = new Color(0xDCE9F9);
    private Color fgCol = new Color(0x2C3E50);
    private Color buttonColor = new Color(0xEDF2FA);

    private JLabel userTitle;

    //Botones
    private JButton homeButton;
    private JButton exitButton;

    public LeftPanel(){
        setBackground(westPanelCol);
        setPreferredSize(new Dimension(200, 800));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        userTitle = createJLabel("Taquilla", 30, true);
        userTitle.setAlignmentX(CENTER_ALIGNMENT);

        add(Box.createVerticalStrut(70));
        add(userTitle);

        homeButton = createButton("Inicio", 150, 40);
        homeButton.setFont(new Font("Helvetica Neue", Font.BOLD, 20));
        homeButton.setActionCommand("Inicio empleado");
        homeButton.setBackground(buttonColor);
        homeButton.setForeground(fgCol);
        homeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(Box.createVerticalStrut(50));
        add(homeButton);

        exitButton = createButton("", 150, 40);
        exitButton.setActionCommand("Salir");
        exitButton.setBackground(new Color(0x17C3B2));
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(Box.createVerticalStrut(500));
        add(exitButton);

        //Iconos
        try {
            Image exitIcon = ImageIO.read(getClass().getResource("/img/exit.png"));
            exitIcon = exitIcon.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            exitButton.setIcon(new ImageIcon(exitIcon));
        } catch (IOException e){
            System.out.println("hubo un error al cargar las imagenes, error: " + e.getMessage());
        }
    }

    public void setListeners(ActionListener listener){
        homeButton.addActionListener(listener);
        exitButton.addActionListener(listener);
    }
}
