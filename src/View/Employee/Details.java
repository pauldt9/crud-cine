package View.Employee;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import static utils.CreateComponents.*;
import static utils.CreateComponents.createEmptyPanel;

public class Details extends JPanel {
    private Color fgCol = new Color(0x2C3E50);
    private Color bgColButtons = new Color(245, 245, 245);

    private JLabel title;

    private JButton backButton;
    private JButton endButton;

    private JPanel imgMoviePanel;
    private JPanel detailsPanel;

    public Details(){
        setOpaque(false);
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setOpaque(false);
        topPanel.setPreferredSize(new Dimension(1100, 140));
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        add(topPanel, BorderLayout.NORTH);

        backButton = createButton(null, 45, 45);
        backButton.setActionCommand("Regresar a seleccion de asientos");
        backButton.setAlignmentX(Box.LEFT_ALIGNMENT);
        backButton.setBackground(bgColButtons);
        topPanel.add(Box.createRigidArea(new Dimension(40, 60)));
        topPanel.add(backButton);

        title = createJLabel("Detalles de venta", 40, true);
        title.setForeground(fgCol);
        title.setAlignmentX(Component.LEFT_ALIGNMENT);
        topPanel.add(Box.createHorizontalStrut(15));
        topPanel.add(title);

        //Paneles vacios
        JPanel emptyWest = createEmptyPanel(40, Integer.MAX_VALUE);
        add(emptyWest, BorderLayout.WEST);

        JPanel emptyEast = createEmptyPanel(40, Integer.MAX_VALUE);
        add(emptyEast, BorderLayout.EAST);

        JPanel southPanel = createEmptyPanel(1100, 60);
        southPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        add(southPanel, BorderLayout.SOUTH);

        endButton = createButton("Finalizar", 120, 40);
        endButton.setFont(new Font("Helvetica Neue", Font.BOLD, 15));
        endButton.setForeground(Color.WHITE);
        endButton.setBackground(new Color(0x17C3B2));
        endButton.setActionCommand("Finalizar venta");
        southPanel.add(endButton);

        //Panel central
        JPanel mainPanel = new JPanel();
        mainPanel.setOpaque(false);
        mainPanel.setLayout(new GridLayout(1, 2));
        add(mainPanel, BorderLayout.CENTER);

        imgMoviePanel = new JPanel(); //aqui va a estar la imagen de la pelicula
        imgMoviePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        imgMoviePanel.setOpaque(false);
        mainPanel.add(imgMoviePanel);

        detailsPanel = new JPanel(); //aqui van los detalles
        detailsPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        detailsPanel.setOpaque(false);
        mainPanel.add(detailsPanel);

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
        endButton.addActionListener(listener);
    }

    public void updateMovieImage(String imgPath){
        imgMoviePanel.removeAll();

        try{
            Image movieImg = ImageIO.read(new File(imgPath));
            movieImg = movieImg.getScaledInstance(320, 500, Image.SCALE_SMOOTH);

            JLabel img = new JLabel(new ImageIcon(movieImg));
            imgMoviePanel.add(img);
        } catch (IOException e){
            System.out.println("Error al cargar imagen: " + e.getMessage());
        }

        imgMoviePanel.revalidate();
        imgMoviePanel.repaint();
    }
}
