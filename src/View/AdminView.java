package View;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;

public class AdminView extends JPanel {
    private String action;

    //botones
    private JButton exitButton;
    private JButton menuButton;
    private JButton moviesButton;
    private JButton salesButton;
    private JButton darkMode;

    //paneles
    private JPanel mainPanel;
    private JPanel leftPanel;
    private JPanel menuPanel;
    private JPanel moviesPanel;
    private JPanel salesPanel;

    //labels
    private JLabel title;
    private JLabel userTitle;
    private JLabel dashboardLbl;
    private JLabel moviesLbl;
    private JLabel salesLbl;

    //solo para los botones y labels
    private Color fgColor = new Color(0x2C3E50);
    private Color bgColor = new Color(0xEDF2FA);

    public AdminView() {
        setLayout(new BorderLayout());

        /*-----------Panel izquierdo-----------*/
        leftPanel = new JPanel();
        leftPanel.setBackground(new Color(0xDCE9F9));
        leftPanel.setPreferredSize(new Dimension(200, getHeight()));
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        add(leftPanel, BorderLayout.WEST);

        userTitle = createJLabel("Admin", 30, true); //donde dice admin en el panel izquierdo
        userTitle.setForeground(fgColor);
        userTitle.setAlignmentX(CENTER_ALIGNMENT);
        leftPanel.add(Box.createVerticalStrut(70));
        leftPanel.add(userTitle);

        //botones
        menuButton = createButton("Menu", 20, 150, 40);
        menuButton.setBackground(bgColor);
        menuButton.setForeground(fgColor);
        menuButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        leftPanel.add(Box.createVerticalStrut(50));
        leftPanel.add(menuButton);

        moviesButton = createButton("Peliculas", 20, 150, 40);
        moviesButton.setBackground(bgColor);
        moviesButton.setForeground(fgColor);
        moviesButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        leftPanel.add(Box.createVerticalStrut(30));
        leftPanel.add(moviesButton);

        salesButton = createButton("Ventas", 20, 150, 40);
        salesButton.setBackground(new Color(0xEDF2FA));
        salesButton.setForeground(fgColor);
        salesButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        leftPanel.add(Box.createVerticalStrut(30));
        leftPanel.add(salesButton);

        darkMode = createButton(null, 15, 150, 40);
        darkMode.setActionCommand("Modo Oscuro");
        darkMode.setBackground(new Color(0xEDF2FA));
        darkMode.setForeground(fgColor);
        darkMode.setAlignmentX(Component.CENTER_ALIGNMENT);
        leftPanel.add(Box.createVerticalStrut(350));
        leftPanel.add(darkMode);

        exitButton = createButton(null, 20, 150, 40);
        exitButton.setActionCommand("Salir");
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

        /*-----------Panel central-----------*/
        mainPanel = new JPanel();
        mainPanel.setLayout(new CardLayout());
        add(mainPanel, BorderLayout.CENTER);

        menuPanel = new JPanel();
        moviesPanel = new JPanel();
        salesPanel = new JPanel();
        mainPanel.add(menuPanel, "menu");
        mainPanel.add(moviesPanel, "peliculas");
        mainPanel.add(salesPanel, "ventas");

        //Panel menu
        menuPanel.setLayout(new BorderLayout());

        title = createJLabel("Inicio", 40, true); //titulo inicio
        title.setForeground(fgColor);
        title.setHorizontalAlignment(SwingConstants.LEFT);
        title.setBorder(BorderFactory.createEmptyBorder(60, 40, 0, 0));
        menuPanel.add(title, BorderLayout.NORTH);

        dashboardLbl = createJLabel("Dashboard", 20, true);
        dashboardLbl.setForeground(fgColor);
        dashboardLbl.setHorizontalAlignment(SwingConstants.LEFT);
        dashboardLbl.setBorder(BorderFactory.createEmptyBorder(0, 40, 550, 0));
        menuPanel.add(dashboardLbl, BorderLayout.WEST);

        //Panel peliculas
        moviesPanel.setLayout(new BorderLayout());

        moviesLbl = createJLabel("Peliculas", 40, true);
        moviesLbl.setForeground(fgColor);
        moviesLbl.setHorizontalAlignment(SwingConstants.LEFT);
        moviesLbl.setBorder(BorderFactory.createEmptyBorder(60, 40, 0, 0));
        moviesPanel.add(moviesLbl, BorderLayout.NORTH);

        //Panel ventas
        salesPanel.setLayout(new BorderLayout());

        salesLbl = createJLabel("Ventas", 40, true);
        salesLbl.setForeground(fgColor);
        salesLbl.setHorizontalAlignment(SwingConstants.LEFT);
        salesLbl.setBorder(BorderFactory.createEmptyBorder(60, 40, 0, 0));
        salesPanel.add(salesLbl, BorderLayout.NORTH);


    }

    public JLabel createJLabel(String title, int fontSize, boolean bold){
        JLabel label = new JLabel(title);
        if (bold){
            label.setFont(new Font("Helvetica Neue", Font.BOLD, fontSize));
        } else {
            label.setFont(new Font("Helvetica Neue", Font.PLAIN, fontSize));
        }

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
        darkMode.setActionCommand(action);

        if (action.equals("Modo Oscuro")){
            fgColor = new Color(0x2C3E50);

            leftPanel.setBackground(new Color(0xDCE9F9));
            menuPanel.setBackground(Color.WHITE);
            moviesPanel.setBackground(Color.WHITE);
            salesPanel.setBackground(Color.WHITE);

            title.setForeground(fgColor);
            userTitle.setForeground(fgColor);
            dashboardLbl.setForeground(fgColor);

            moviesLbl.setForeground(fgColor);

            salesLbl.setForeground(fgColor);

            menuButton.setBackground(new Color(0xEDF2FA));
            menuButton.setForeground(fgColor);

            moviesButton.setBackground(new Color(0xEDF2FA));
            moviesButton.setForeground(fgColor);

            salesButton.setBackground(new Color(0xEDF2FA));
            salesButton.setForeground(fgColor);

            darkMode.setBackground(new Color(0xEDF2FA));
            darkMode.setForeground(fgColor);

            exitButton.setBackground(new Color(0x17C3B2));

            try {
                Image darkIcon = ImageIO.read(getClass().getResource("/img/dark.png"));
                darkIcon = darkIcon.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
                darkMode.setIcon(new ImageIcon(darkIcon));
            } catch (IOException e){
                System.out.println("error al cargar imagen: " + e.getMessage());
            }

        } else {
            fgColor = new Color(0xE0E0E0);

            leftPanel.setBackground(new Color(0x1E2A47));
            menuPanel.setBackground(new Color(0x1C1C2E));
            moviesPanel.setBackground(new Color(0x1C1C2E));
            salesPanel.setBackground(new Color(0x1C1C2E));

            title.setForeground(fgColor);
            userTitle.setForeground(fgColor);
            dashboardLbl.setForeground(fgColor);

            moviesLbl.setForeground(fgColor);

            salesLbl.setForeground(fgColor);

            menuButton.setBackground(new Color(0x3A4E84));
            menuButton.setForeground(fgColor);

            moviesButton.setBackground(new Color(0x3A4E84));
            moviesButton.setForeground(fgColor);

            salesButton.setBackground(new Color(0x3A4E84));
            salesButton.setForeground(fgColor);

            darkMode.setBackground(new Color(0x3A4E84));
            darkMode.setForeground(fgColor);

            exitButton.setBackground(new Color(0x1ABC9C));

            try {
                Image darkIcon = ImageIO.read(getClass().getResource("/img/brightness.png"));
                darkIcon = darkIcon.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
                darkMode.setIcon(new ImageIcon(darkIcon));
            } catch (IOException e){
                System.out.println("error al cargar imagen: " + e.getMessage());
            }
        }
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void setMainPanel(JPanel mainPanel) {
        this.mainPanel = mainPanel;
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
