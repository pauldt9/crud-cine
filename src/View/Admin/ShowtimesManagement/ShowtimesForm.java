package View.Admin.ShowtimesManagement;

import Models.Movie;
import Models.Room;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import static utils.CreateComponents.*;

public class ShowtimesForm extends JPanel {
    private String action;
    private JLabel panelTitle;

    private Color fgColor = new Color(0x2C3E50);
    private Color bgColButtons = new Color(245, 245, 245);

//    private JButton confirmButton;
    private JButton backButton;

    /*----------Campos del formulario----------*/
    private JComboBox<String> addMovieTitle;
    private JComboBox<String> addShowtime;
    private JComboBox<String> addRoom;

    public ShowtimesForm(){
        setLayout(new BorderLayout());
        setOpaque(false);

        JPanel topPanel = new JPanel();
        topPanel.setPreferredSize(new Dimension(Integer.MAX_VALUE, 170));
        topPanel.setOpaque(false);
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        add(topPanel, BorderLayout.NORTH);

        backButton = createButton("", 45, 45);
        backButton.setActionCommand("Regresar funcion");
        backButton.setAlignmentX(Box.LEFT_ALIGNMENT);
        backButton.setBackground(bgColButtons);
        topPanel.add(Box.createRigidArea(new Dimension(40, 60)));
        topPanel.add(backButton);

        //titulo
        panelTitle = createJLabel("Agregar Funcion", 40, true);
        panelTitle.setAlignmentX(Box.LEFT_ALIGNMENT);
        panelTitle.setForeground(fgColor);
        topPanel.add(Box.createHorizontalStrut(15));
        topPanel.add(panelTitle);

        //Paneles vacios
        JPanel emptyEast = new JPanel();
        emptyEast.setOpaque(false);
        emptyEast.setPreferredSize(new Dimension(300, Integer.MAX_VALUE));
        add(emptyEast, BorderLayout.EAST);

        JPanel emptyWest = createEmptyPanel(40, 800);
        add(emptyWest, BorderLayout.WEST);

        JPanel emptySouth = new JPanel();
        emptySouth.setOpaque(false);
        emptySouth.setPreferredSize(new Dimension(Integer.MAX_VALUE, 250));
        add(emptySouth, BorderLayout.SOUTH);

        //aqui van los campos
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(2, 2, 30, 30));
        formPanel.setOpaque(false);
        add(formPanel, BorderLayout.CENTER);


        addMovieTitle = new JComboBox<String>();
        addMovieTitle.addItem("Seleccione una película");
        addMovieTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        addMovieTitle.setBackground(bgColButtons);
        addMovieTitle.setForeground(fgColor);
        addMovieTitle.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
        addMovieTitle.setMaximumSize(new Dimension(350, 50));
        formPanel.add(addMovieTitle);
        updateMovieCombo();

        String[] schedules = {"12:00", "14:00", "16:00", "18:00", "20:00"};
        addShowtime = new JComboBox<String>(schedules);
        addShowtime.setAlignmentX(Component.LEFT_ALIGNMENT);
        addShowtime.setBackground(bgColButtons);
        addShowtime.setForeground(fgColor);
        addShowtime.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
        addShowtime.setMaximumSize(new Dimension(350, 50));
        formPanel.add(addShowtime);

        addRoom = new JComboBox<String>();
        addRoom.addItem("Seleccione una sala");
        addRoom.setAlignmentX(Component.LEFT_ALIGNMENT);
        addRoom.setBackground(bgColButtons);
        addRoom.setForeground(fgColor);
        addRoom.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
        addRoom.setMaximumSize(new Dimension(350, 50));
        formPanel.add(addRoom);
        updateRoomCombo();

        try {
            Image backIcon = ImageIO.read(getClass().getResource("/img/back.png"));
            backIcon = backIcon.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
            backButton.setIcon(new ImageIcon(backIcon));
        } catch (IOException e){
            System.out.println("error al cargar imagen: " + e.getMessage());
        }
    }

    public void setListeners(ActionListener listener){
//        confirmButton.addActionListener(listener);
        backButton.addActionListener(listener);
    }

    public void setAction(String action){
        this.action = action;
        if (action.equals("Agregar")){
            panelTitle.setText("Agregar Funcion");
//            confirmButton.setActionCommand("Confirmar funcion");
        } else {
            panelTitle.setText("Actualizar Funcion");
//            confirmButton.setActionCommand("Confirmar cambios de funcion");
        }
    }

    public void updateMovieCombo() {
        if (addMovieTitle == null) return;
        addMovieTitle.removeAllItems();
        addMovieTitle.addItem("Seleccione una película");

        ArrayList<Movie> movieList = Movie.getMovies();
        for (Movie movie : movieList) {
            addMovieTitle.addItem(movie.getTitle());
        }
    }

    public void updateRoomCombo() {
        if (addRoom == null) return;
        addRoom.removeAllItems();
        addRoom.addItem("Seleccione una sala");

        ArrayList<Room> roomList = Room.getRooms();
        for (Room room : roomList) {
            addRoom.addItem(room.getRoomName());
        }
    }
//
//    public JComboBox<String> getAddMovieTitle() {
//        return addMovieTitle;
//    }
//
//    public JComboBox<String> getAddRoom() {
//        return addRoom;
//    }
}
