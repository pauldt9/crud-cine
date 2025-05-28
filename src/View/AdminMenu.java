package View;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;

public class AdminMenu extends JPanel {
    private JButton exitButton;
    private JButton menuButton;
    private JButton moviesButton;
    private JButton salesButton;
    private JButton darkMode;
    private JPanel centerPanel;
    private String action;
    private JLabel title;
    private JPanel leftPanel;
    private JLabel userTitle;
//    private Color titleCol = new Color(0x2A3D66);

    public AdminMenu() {
        setLayout(new BorderLayout());

        /*Panel izquierdo*/
        leftPanel = new JPanel();
        leftPanel.setBackground(new Color(0xDCE9F9));
        leftPanel.setPreferredSize(new Dimension(200, getHeight()));
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        add(leftPanel, BorderLayout.WEST);

        userTitle = createJLabel("Admin", 30);
        userTitle.setForeground(new Color(0x2C3E50));
        userTitle.setAlignmentX(CENTER_ALIGNMENT);
        leftPanel.add(Box.createVerticalStrut(100));
        leftPanel.add(userTitle);

        menuButton = createButton("Menu", 20, 150, 40);
        menuButton.setBackground(new Color(0xEDF2FA));
        menuButton.setForeground(new Color(0x2C3E50));
        menuButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        leftPanel.add(Box.createVerticalStrut(50));
        leftPanel.add(menuButton);

        moviesButton = createButton("Peliculas", 20, 150, 40);
        moviesButton.setBackground(new Color(0xEDF2FA));
        moviesButton.setForeground(new Color(0x2C3E50));
        moviesButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        leftPanel.add(Box.createVerticalStrut(30));
        leftPanel.add(moviesButton);

        salesButton = createButton("Ventas", 20, 150, 40);
        salesButton.setBackground(new Color(0xEDF2FA));
        salesButton.setForeground(new Color(0x2C3E50));
        salesButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        leftPanel.add(Box.createVerticalStrut(30));
        leftPanel.add(salesButton);

        darkMode = createButton("Modo Oscuro", 15, 150, 40);
        darkMode.setBackground(new Color(0xEDF2FA));
        darkMode.setForeground(new Color(0x2C3E50));
        darkMode.setAlignmentX(Component.CENTER_ALIGNMENT);
        leftPanel.add(Box.createVerticalStrut(350));
        leftPanel.add(darkMode);

        exitButton = createButton("Salir", 20, 150, 40);
        exitButton.setBackground(new Color(0x17C3B2));
        exitButton.setForeground(Color.WHITE);
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        leftPanel.add(Box.createVerticalStrut(30));
        leftPanel.add(exitButton);

        try {
            Image exitIcon = ImageIO.read(getClass().getResource("/img/exit.png"));
            exitIcon = exitIcon.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            exitButton.setIcon(new ImageIcon(exitIcon));

            Image darkIcon = ImageIO.read(getClass().getResource("/img/dark.png"));
            darkIcon = darkIcon.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            darkMode.setIcon(new ImageIcon(darkIcon));
        } catch (IOException e){
            System.out.println("error al cargar imagen: " + e.getMessage());
        }

        /*panel central*/
        centerPanel = new JPanel();
        centerPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        add(centerPanel, BorderLayout.CENTER);

        title = createJLabel("Inicio", 40);
        title.setBorder(BorderFactory.createEmptyBorder(75, 55, 5, 5));
        centerPanel.add(title);

    }

    public JLabel createJLabel(String title, int fontSize){
        JLabel label = new JLabel(title);
        label.setFont(new Font("Helvetica Neue", Font.BOLD, fontSize));

        return label;
    }

    public void setListeners(ActionListener listener){
        exitButton.addActionListener(listener);
        menuButton.addActionListener(listener);
        moviesButton.addActionListener(listener);
        salesButton.addActionListener(listener);
        darkMode.addActionListener(listener);
    }


    public JButton createButton(String buttonName, int fontSize, int w, int h){
        JButton button = new JButton(buttonName);
        button.setFont(new Font("Helvetica Neue", Font.BOLD, fontSize));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setMaximumSize(new Dimension(w, h));
        button.setActionCommand(buttonName);
        return button;
    }

    //Cambiar modos (dark mode)
    public void setViewMode(String action){
        this.action = action;
        darkMode.setText(action);
        darkMode.setActionCommand(action);

        if (action.equals("Modo Oscuro")){
            centerPanel.setBackground(Color.WHITE);
            leftPanel.setBackground(new Color(0xDCE9F9));

            title.setForeground(new Color(0x2C3E50));
            userTitle.setForeground(new Color(0x2C3E50));

            menuButton.setBackground(new Color(0xEDF2FA));
            menuButton.setForeground(new Color(0x2C3E50));

            moviesButton.setBackground(new Color(0xEDF2FA));
            moviesButton.setForeground(new Color(0x2C3E50));

            salesButton.setBackground(new Color(0xEDF2FA));
            salesButton.setForeground(new Color(0x2C3E50));

            darkMode.setBackground(new Color(0xEDF2FA));
            darkMode.setForeground(new Color(0x2C3E50));

            exitButton.setBackground(new Color(0x17C3B2));
        } else {
            leftPanel.setBackground(new Color(0x1E2A47));
            centerPanel.setBackground(new Color(0x1C1C2E));

            title.setForeground(new Color(0xF0F4FF));
            userTitle.setForeground(new Color(0xE0E0E0));

            menuButton.setBackground(new Color(0x3A4E84));
            menuButton.setForeground(new Color(0xF5F9FF));

            moviesButton.setBackground(new Color(0x3A4E84));
            moviesButton.setForeground(new Color(0xF5F9FF));

            salesButton.setBackground(new Color(0x3A4E84));
            salesButton.setForeground(new Color(0xF5F9FF));

            darkMode.setBackground(new Color(0x3A4E84));
            darkMode.setForeground(new Color(0xF5F9FF));

            exitButton.setBackground(new Color(0x1ABC9C));
        }
    }

    public JPanel getCenterPanel() {
        return centerPanel;
    }

    public void setCenterPanel(JPanel centerPanel) {
        this.centerPanel = centerPanel;
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
//        g.drawImage(logo, 20, 5, this);
//
//        Graphics2D g2 = (Graphics2D) g;
//        Composite original = g2.getComposite(); //obtener la composicion original
//
//        g2.setColor(new Color(0, 0, 0, 50));
//        g2.fillRoundRect(1078, 10, 300, 110, 15, 15);
//
//        g2.setComposite(original); //restablecer
    }
}
