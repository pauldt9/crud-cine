package View;

import Models.MoviesTableModel;
import Models.MoviesTableModel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.io.IOException;

import static utils.CreateComponents.*;

public class MoviesViewAdmin extends JPanel {
    private JLabel moviesLbl;

    private Color fgColor = new Color(0x2C3E50);
    private Color bgColButtons = new Color(245, 245, 245);

    private JButton addMov;
    private JButton deleteMov;
    private JButton editMov;

    private JTable moviesTable;
    private MoviesTableModel moviesTableModel;

    private int idMovie; //Primary Key

    public MoviesViewAdmin(){
        setLayout(new BorderLayout());
        setOpaque(false);

        moviesLbl = createJLabel("Peliculas", 40, true);
        moviesLbl.setForeground(fgColor);
        moviesLbl.setBorder(BorderFactory.createEmptyBorder(60, 40, 0, 0));
        add(moviesLbl, BorderLayout.NORTH);

        //Paneles vacios
        JPanel emptyEast = createEmptyPanel(40, 800);
        add(emptyEast, BorderLayout.EAST);

        JPanel emptyWest = createEmptyPanel(40, 800);
        add(emptyWest, BorderLayout.WEST);

        initMovTable(); //tabla

        //Aqui van los botones
        JPanel movButtonPanel = createButtonsPanel();
        add(movButtonPanel, BorderLayout.SOUTH);

        addMov = createButton("Agregar", 120, 40);
        addMov.setFont(new Font("Helvetica Neue", Font.BOLD, 15));
        addMov.setActionCommand("Agregar pelicula");
        addMov.setBackground(bgColButtons);
        addMov.setForeground(fgColor);
        movButtonPanel.add(addMov);

        editMov = createButton("Editar",120, 40);
        editMov.setFont(new Font("Helvetica Neue", Font.BOLD, 15));
        editMov.setActionCommand("Editar pelicula");
        editMov.setBackground(bgColButtons);
        editMov.setForeground(fgColor);
        movButtonPanel.add(editMov);

        deleteMov = createButton("Eliminar", 120, 40);
        deleteMov.setFont(new Font("Helvetica Neue", Font.BOLD, 15));
        deleteMov.setActionCommand("Eliminar pelicula");
        deleteMov.setBackground(bgColButtons);
        deleteMov.setForeground(fgColor);
        movButtonPanel.add(deleteMov);

        try {
            Image deleteIcon = ImageIO.read(getClass().getResource("/img/remove.png"));
            deleteIcon = deleteIcon.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            deleteMov.setIcon(new ImageIcon(deleteIcon));

            Image editIcon = ImageIO.read(getClass().getResource("/img/editDark.png"));
            editIcon = editIcon.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            editMov.setIcon(new ImageIcon(editIcon));

            Image addIcon = ImageIO.read(getClass().getResource("/img/add.png"));
            addIcon = addIcon.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            addMov.setIcon(new ImageIcon(addIcon));
        } catch (IOException e){
            System.out.println("error al cargar imagen: " + e.getMessage());
        }
    }

    public void initMovTable(){
        moviesTableModel = new MoviesTableModel();
        moviesTable = new JTable(moviesTableModel);

        JScrollPane scroll = new JScrollPane(moviesTable);
        add(scroll, BorderLayout.CENTER);
    }

    public void setListeners(ActionListener listener){
        addMov.addActionListener(listener);
        deleteMov.addActionListener(listener);
        editMov.addActionListener(listener);
    }

    public JTable getMoviesTable(){
        return moviesTable;
    }

    public void setMoviesTable(JTable moviesTable) {
        this.moviesTable = moviesTable;
    }

    public MoviesTableModel getMoviesTableModel() {
        return moviesTableModel;
    }

    public void setMoviesTableModel(MoviesTableModel moviesTableModel) {
        this.moviesTableModel = moviesTableModel;
    }

    public int getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(int idMovie) {
        this.idMovie = idMovie;
    }

    public void tableListener(KeyListener listener){
        moviesTable.addKeyListener(listener);
    }

    public void removeTableSelection(){
        moviesTable.clearSelection();
    }
}
