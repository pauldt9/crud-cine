package View;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;

public class SelectHour extends JPanel {
    private Color fgCol = new Color(0x2C3E50);
    private Color bgColButtons = new Color(245, 245, 245);

    private JLabel showtimesTitle;

    private JButton backButton;

    public SelectHour(){
        setOpaque(false);
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setOpaque(false);
        topPanel.setPreferredSize(new Dimension(1100, 140));
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        add(topPanel, BorderLayout.NORTH);

        backButton = createButton(null, 45, 45);
        backButton.setActionCommand("Regresar al catalogo");
        backButton.setAlignmentX(Box.LEFT_ALIGNMENT);
        backButton.setBackground(bgColButtons);
        topPanel.add(Box.createRigidArea(new Dimension(40, 60)));
        topPanel.add(backButton);

        showtimesTitle = createJLabel("Funciones", 40, true);
        showtimesTitle.setForeground(fgCol);
        showtimesTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        topPanel.add(Box.createHorizontalStrut(15));
        topPanel.add(showtimesTitle);

        //Paneles vacios
        JPanel emptyWest = createEmptyPanel(40, Integer.MAX_VALUE);
        add(emptyWest, BorderLayout.WEST);

        JPanel emptyEast = createEmptyPanel(40, Integer.MAX_VALUE);
        add(emptyEast, BorderLayout.EAST);

        JPanel emptySouth = createEmptyPanel(1100, 40);
        add(emptySouth, BorderLayout.SOUTH);

        //Panel central
        JPanel mainPanel = new JPanel();
        mainPanel.setOpaque(false);
        mainPanel.setLayout(new GridLayout(1, 2));
        add(mainPanel, BorderLayout.CENTER);

        JPanel imgMoviePanel = new JPanel(); //aqui va a estar la imagen de la pelicula
        imgMoviePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
//        imgMoviePanel.setOpaque(false);
        imgMoviePanel.setBackground(Color.GREEN); //borrar esta linea
        mainPanel.add(imgMoviePanel);

        JPanel showtimesPanel = new JPanel(); //aqui van a estar las funciones
        showtimesPanel.setLayout(new GridLayout(0, 3));
        showtimesPanel.setBackground(Color.BLUE); //borrar esta linea
//        showtimesPanel.setOpaque(false);
        mainPanel.add(showtimesPanel);

        try {
            Image backIcon = ImageIO.read(getClass().getResource("/img/back.png"));
            backIcon = backIcon.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
            backButton.setIcon(new ImageIcon(backIcon));
        } catch (IOException e){
            System.out.println("No se pudo cargar la imagen, error: " + e.getMessage());
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

    public JButton createButton(String buttonName, int w, int h){
        JButton button = new JButton(buttonName);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setPreferredSize(new Dimension(w, h));
        button.setMinimumSize(new Dimension(w, h));
        button.setMaximumSize(new Dimension(w, h));
        return button;
    }

    public JPanel createEmptyPanel(int w, int h){
        JPanel empty = new JPanel();
        empty.setOpaque(false);
        empty.setPreferredSize(new Dimension(w, h));
        return empty;
    }

    public void setListeners(ActionListener listener){
        backButton.addActionListener(listener);
    }
}
