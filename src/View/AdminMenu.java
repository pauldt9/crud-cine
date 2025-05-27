package View;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class AdminMenu extends JPanel {
    private Image logo;
    private JButton exitButton;
    private JButton menuButton;
    private JButton moviesButton;
    private JButton salesButton;
    private JPanel centerPanel;

    public AdminMenu() {
        setLayout(new BorderLayout());

        /*Panel izquierdo*/
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(new Color(0x4A66CA));
        leftPanel.setPreferredSize(new Dimension(200, getHeight()));
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        add(leftPanel, BorderLayout.WEST);


        JLabel userTitle = createJLabel("Admin", 30, 2);
        userTitle.setAlignmentX(CENTER_ALIGNMENT);
        leftPanel.add(Box.createVerticalStrut(100));
        leftPanel.add(userTitle);

        menuButton = createButton("Menu", 20, 150, 40, 1);
        menuButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        leftPanel.add(Box.createVerticalStrut(50));
        leftPanel.add(menuButton);

        moviesButton = createButton("Peliculas", 20, 150, 40, 1);
        moviesButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        leftPanel.add(Box.createVerticalStrut(30));
        leftPanel.add(moviesButton);

        salesButton = createButton("Ventas", 20, 150, 40, 1);
        salesButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        leftPanel.add(Box.createVerticalStrut(30));
        leftPanel.add(salesButton);

        exitButton = createButton("Salir", 20, 150, 40, 2);
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        try {
            Image exitIcon = ImageIO.read(getClass().getResource("/img/exit.png"));
            exitIcon = exitIcon.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            exitButton.setIcon(new ImageIcon(exitIcon));

            Image homeIcon = ImageIO.read(getClass().getResource("/img/home.png"));
            homeIcon = homeIcon.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            menuButton.setIcon(new ImageIcon(homeIcon));

            Image movieIcon = ImageIO.read(getClass().getResource("/img/movie.png"));
            movieIcon = movieIcon.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
            moviesButton.setIcon(new ImageIcon(movieIcon));

            Image salesIcon = ImageIO.read(getClass().getResource("/img/sales.png"));
            salesIcon = salesIcon.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            salesButton.setIcon(new ImageIcon(salesIcon));
        } catch (IOException e){
            System.out.println("error al cargar imagen: " + e.getMessage());
        }

        leftPanel.add(Box.createVerticalStrut(400));
        leftPanel.add(exitButton);

        /*panel central*/
        centerPanel = new JPanel();
        centerPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        add(centerPanel, BorderLayout.CENTER);

        JLabel title = createJLabel("Inicio", 40, 2);
        title.setBorder(BorderFactory.createEmptyBorder(75, 55, 5, 5));
        centerPanel.add(title);



//        try {
//            logo = ImageIO.read(new File("src/img/logo.png"));
//            logo = logo.getScaledInstance(120, 120, Image.SCALE_SMOOTH);
//        } catch (IOException e){
//            System.out.println("Hubo un error al cargar la imagen: " + e.getMessage());
//        }

//        JLabel title = createJLabel("INICIO", 40, 1);
//        title.setBorder(BorderFactory.createEmptyBorder(5, 150, 5, 5));
//        topPanel.add(title, BorderLayout.WEST);

        /*Panel del menu*/
//        JPanel centerPanel = new JPanel();
//        centerPanel.setBackground(Color.WHITE);
//        centerPanel.setLayout(new GridBagLayout());
//        centerPanel.setPreferredSize(new Dimension(getWidth(), getHeight()));
//        add(centerPanel, BorderLayout.CENTER);



    }

    public JLabel createJLabel(String title, int fontSize, int color){
        JLabel label = new JLabel(title);
        label.setFont(new Font("Helvetica Neue", Font.BOLD, fontSize));

        if (color == 1){
            label.setForeground(Color.WHITE);
        } else if (color == 2){
            label.setForeground(new Color(0xE0F2FF));
        } else {
            label.setForeground(new Color(0x2A3D66));
        }

        return label;
    }

    public void setListeners(ActionListener listener){
        exitButton.addActionListener(listener);
        menuButton.addActionListener(listener);
        moviesButton.addActionListener(listener);
        salesButton.addActionListener(listener);
    }


    public JButton createButton(String buttonName, int fontSize, int w, int h, int color){
        JButton button = new JButton(buttonName);
        button.setFont(new Font("Helvetica Neue", Font.BOLD, fontSize));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setMaximumSize(new Dimension(w, h));
        button.setActionCommand(buttonName);

        if (color == 1){
            button.setBackground(new Color(0x3E5CC7));
        } else {
            button.setBackground(new Color(0x17C3B2));
        }

        return button;
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
