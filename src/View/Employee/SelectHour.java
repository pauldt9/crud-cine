package View.Employee;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import static utils.CreateComponents.*;

public class SelectHour extends JPanel {
    private Color fgCol = new Color(0x2C3E50);
    private Color bgColButtons = new Color(245, 245, 245);

    private JLabel showtimesTitle;

    private JButton backButton;

    private JPanel imgMoviePanel;
    private JPanel showtimesPanel;

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

        imgMoviePanel = new JPanel(); //aqui va a estar la imagen de la pelicula
        imgMoviePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        imgMoviePanel.setOpaque(false);
//        imgMoviePanel.setBackground(Color.GREEN); //borrar esta linea
        mainPanel.add(imgMoviePanel);

        showtimesPanel = new JPanel(); //aqui van a estar las funciones
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

    public void setListeners(ActionListener listener){
        backButton.addActionListener(listener);
    }

    public void updateMovieImage(String imgPath){
        imgMoviePanel.removeAll();

        try{
            Image movieImg = ImageIO.read(new File(imgPath));
            movieImg = movieImg.getScaledInstance(300, 470, Image.SCALE_SMOOTH);

            JLabel img = new JLabel(new ImageIcon(movieImg));
            imgMoviePanel.add(img);
        } catch (IOException e){
            System.out.println("Error al cargar imagen: " + e.getMessage());
        }

        imgMoviePanel.revalidate();
        imgMoviePanel.repaint();
    }
}
