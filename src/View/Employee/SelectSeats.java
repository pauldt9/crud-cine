package View.Employee;

import Models.MovieShowtime;
import Models.OccupiedSeats;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static utils.CreateComponents.*;

public class SelectSeats extends JPanel {
    private Color fgCol = new Color(0x2C3E50);
    private Color bgColButtons = new Color(245, 245, 245);

    private JLabel title;

    private JButton backButton;
    private JButton confirmButton;

    private JPanel imgMoviePanel;
    private JPanel rightPanel;
    private JPanel screen;
    private JPanel seatsPanel;

    private ArrayList<String> selectedSeats = new ArrayList<>();

    public SelectSeats(){
        setLayout(new BorderLayout());
        setOpaque(false);

        JPanel topPanel = new JPanel();
        topPanel.setOpaque(false);
        topPanel.setPreferredSize(new Dimension(1100, 140));
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        add(topPanel, BorderLayout.NORTH);

        backButton = createButton(null, 45, 45);
        backButton.setActionCommand("Regresar a funciones");
        backButton.setAlignmentX(Box.LEFT_ALIGNMENT);
        backButton.setBackground(bgColButtons);
        topPanel.add(Box.createRigidArea(new Dimension(40, 60)));
        topPanel.add(backButton);

        title = createJLabel("Seleccionar asiento", 40, true);
        title.setForeground(fgCol);
        title.setAlignmentX(Component.LEFT_ALIGNMENT);
        topPanel.add(Box.createHorizontalStrut(15));
        topPanel.add(title);

        //Paneles vacios
        JPanel emptyWest = createEmptyPanel(40, 800);
        add(emptyWest, BorderLayout.WEST);

        JPanel emptyEast = createEmptyPanel(40, 800);
        add(emptyEast, BorderLayout.EAST);

        JPanel southPanel = createEmptyPanel(1100, 60);
        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.Y_AXIS));
        add(southPanel, BorderLayout.SOUTH);

        confirmButton = createButton("Confirmar Asientos", 200, 40);
        confirmButton.setFont(new Font("Helvetica Neue", Font.BOLD, 15));
        confirmButton.setForeground(Color.WHITE);
        confirmButton.setBackground(new Color(0x17C3B2));
        confirmButton.setActionCommand("Confirmar asientos");
        confirmButton.setAlignmentX(Box.RIGHT_ALIGNMENT);
        southPanel.add(Box.createVerticalStrut(10));
        southPanel.add(confirmButton);

        //Panel central
        JPanel mainPanel = new JPanel();
        mainPanel.setOpaque(false);
        mainPanel.setLayout(new GridLayout(1, 2));
        add(mainPanel, BorderLayout.CENTER);

        imgMoviePanel = new JPanel(); //aqui va a estar la imagen de la pelicula
        imgMoviePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        imgMoviePanel.setOpaque(false);
        mainPanel.add(imgMoviePanel);

        rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());
        rightPanel.setOpaque(false);
        mainPanel.add(rightPanel);

        //"Mostrar" la pantalla
        screen = new JPanel();
        screen.setBackground(new Color(0x6B6D76));
        screen.setLayout(new FlowLayout(FlowLayout.CENTER));
        rightPanel.add(screen, BorderLayout.NORTH);

        JLabel screenText = createJLabel("P A N T A L L A", 15, true);
        screenText.setForeground(Color.WHITE);
        screen.add(screenText);

        //Aca deben de ir los "botones" de asientos
        seatsPanel = new JPanel();
        seatsPanel.setOpaque(false);
        seatsPanel.setLayout(new GridLayout(0, 4, 10, 10));
        rightPanel.add(seatsPanel, BorderLayout.CENTER);

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
        confirmButton.addActionListener(listener);
    }

    public void updateSeats(MovieShowtime showtime){
        seatsPanel.removeAll();
        selectedSeats.clear();

        int idRoom = showtime.getRoom().getIdRoom();
        ArrayList<String> seatName = MovieShowtime.getSeatsNames(idRoom);

        int rows = showtime.getRoom().getRows();
        int cols = showtime.getRoom().getCols();

        seatsPanel.setLayout(new GridLayout(rows, cols, 10, 10));

        int index = 0;
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){
                JButton btn;
                if (index < seatName.size()){
                    String name = seatName.get(index);

                    btn = createButton(seatName.get(index), 50, 50);
                    btn.setFont(new Font("Helvetica Neue", Font.PLAIN, 12));
                    btn.setBackground(bgColButtons);
                    btn.setForeground(fgCol);

                    btn.addActionListener(e -> {
                        if (selectedSeats.contains(name)){
                            selectedSeats.remove(name);
                            btn.setBackground(bgColButtons);
                        } else {
                            selectedSeats.add(name);
                            btn.setBackground(new Color(200, 200, 200));
                        }
                    });

                    index++;
                    seatsPanel.add(btn);
                }
            }
        }

        seatsPanel.revalidate();
        seatsPanel.repaint();
    }

    public void updateMovieImage(String imgPath) {
        imgMoviePanel.removeAll();

        try {
            Image movieImg = ImageIO.read(new File(imgPath));
            movieImg = movieImg.getScaledInstance(320, 500, Image.SCALE_SMOOTH);

            JLabel img = new JLabel(new ImageIcon(movieImg));
            imgMoviePanel.add(img);
        } catch (IOException e) {
            System.out.println("Error al cargar imagen: " + e.getMessage());
        }

        imgMoviePanel.revalidate();
        imgMoviePanel.repaint();
    }

    public ArrayList<String> getSelectedSeats() {
        return selectedSeats;
    }

    public void setSelectedSeats(ArrayList<String> selectedSeats) {
        this.selectedSeats = selectedSeats;
    }
}
