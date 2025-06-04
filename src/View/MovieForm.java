package View;

import lib.TextPrompt;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;

import static utils.CreateComponents.*;

public class MovieForm extends JPanel {
    private String action;

    private JButton confirmButton;
    private JButton backButton;
    private JButton addImage;

    private Color bgColButtons = new Color(245, 245, 245);
    private Color fgColor = new Color(0x2C3E50);

    private JLabel panelTitle;

    /*----------Campos del formulario----------*/
    private JTextField addMovieTitle;
    private JTextField addDuration;
    private JComboBox<String> addGenre;
    private JComboBox<String> addClassification;
//    private JTextField addShowtime;

    public MovieForm(){
        setLayout(new BorderLayout());
        setOpaque(false);

        JPanel topPanel = new JPanel();
        topPanel.setPreferredSize(new Dimension(Integer.MAX_VALUE, 170));
        topPanel.setOpaque(false);
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        add(topPanel, BorderLayout.NORTH);

        backButton = createButton("", 45, 45);
        backButton.setActionCommand("Regresar pelicula");
        backButton.setAlignmentX(Box.LEFT_ALIGNMENT);
        backButton.setBackground(bgColButtons);
        topPanel.add(Box.createRigidArea(new Dimension(40, 60)));
        topPanel.add(backButton);

        //titulo
        panelTitle = createJLabel("Agregar Pelicula", 40, true);
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
        formPanel.setLayout(new GridLayout(4, 2, 30, 30));
        formPanel.setOpaque(false);
        add(formPanel, BorderLayout.CENTER);

        addMovieTitle = createTextField("Ingresar titulo", 350, 50);
        addMovieTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        addMovieTitle.setBorder(BorderFactory.createCompoundBorder(addMovieTitle.getBorder(), new EmptyBorder(0, 10, 0, 0)));
        addMovieTitle.setBackground(bgColButtons);
        addMovieTitle.setForeground(fgColor);
        formPanel.add(addMovieTitle);

        addDuration = createTextField("Ingresar duracion", 350, 50);
        addDuration.setAlignmentX(Component.LEFT_ALIGNMENT);
        addDuration.setBorder(BorderFactory.createCompoundBorder(addDuration.getBorder(), new EmptyBorder(0, 10, 0, 0)));
        addDuration.setBackground(bgColButtons);
        addDuration.setForeground(fgColor);
        formPanel.add(addDuration);

        String[] genres = {"-----Genero-----", "Accion", "Aventura", "Comedia", "Drama", "Terror", "Ciencia ficción",
        "Fantasia", "Suspenso", "Animacion", "Romance", "Musical", "Documental"};
        addGenre = new JComboBox<String>(genres);
        addGenre.setAlignmentX(Component.LEFT_ALIGNMENT);
        addGenre.setBackground(bgColButtons);
        addGenre.setForeground(fgColor);
        addGenre.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
        addGenre.setMaximumSize(new Dimension(350, 50));
        formPanel.add(addGenre);

        String[] classifications = {"-----Clasificacion-----", "AA", "A", "B", "B15", "C"};
        addClassification = new JComboBox<String>(classifications);
        addClassification.setAlignmentX(Component.LEFT_ALIGNMENT);
        addClassification.setBackground(bgColButtons);
        addClassification.setForeground(fgColor);
        addClassification.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
        addClassification.setMaximumSize(new Dimension(350, 50));
        formPanel.add(addClassification);

//        addShowtime = createTextField("Ingresar Horario", 350, 50);
//        addShowtime.setAlignmentX(Component.LEFT_ALIGNMENT);
//        addShowtime.setBorder(BorderFactory.createCompoundBorder(addShowtime.getBorder(), new EmptyBorder(0, 10, 0, 0)));
//        addShowtime.setBackground(bgColButtons);
//        addShowtime.setForeground(fgColor);
//        formPanel.add(addShowtime);

        addImage = createButton("Agregar Imagen", 350, 50);
        addImage.setFont(new Font("Helvetica Neue", Font.BOLD, 15));
        addImage.setActionCommand("Agregar imagen");
        addImage.setForeground(fgColor);
        addImage.setBackground(bgColButtons);
        addImage.setAlignmentX(Component.LEFT_ALIGNMENT);
        formPanel.add(addImage);

        confirmButton = createButton("Confirmar", 350, 50);
        confirmButton.setFont(new Font("Helvetica Neue", Font.BOLD, 15));
        confirmButton.setActionCommand("Confirmar pelicula");
        confirmButton.setForeground(fgColor);
        confirmButton.setBackground(bgColButtons);
        confirmButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        formPanel.add(confirmButton);

        formPanel.add(new JLabel());

        try {
            Image backIcon = ImageIO.read(getClass().getResource("/img/back.png"));
            backIcon = backIcon.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
            backButton.setIcon(new ImageIcon(backIcon));
        } catch (IOException e){
            System.out.println("error al cargar imagen: " + e.getMessage());
        }
    }

    public JTextField createTextField(String placeHolder, int w, int h){
        Font font = new Font("Helvetica Neue", Font.PLAIN, 16);
        JTextField textField = new JTextField();

        textField.setFont(font);
        textField.setForeground(new Color(30, 30 , 30));
        textField.setBorder(BorderFactory.createLineBorder(new Color(20, 20, 20), 1, true));
        textField.setMaximumSize(new Dimension(w, h));

        TextPrompt ph = new TextPrompt(placeHolder, textField);
        ph.setForeground(new Color(0x999999));
        ph.setFont(font);

        return textField;
    }

    public void setListeners(ActionListener listener){
        confirmButton.addActionListener(listener);
        addImage.addActionListener(listener);
        backButton.addActionListener(listener);
    }

    public void setAction(String action){
        this.action = action;
        if (action.equals("Agregar")){
            panelTitle.setText("Agregar Pelicula");
            confirmButton.setActionCommand("Confirmar pelicula");
        } else {
            panelTitle.setText("Actualizar Pelicula");
            confirmButton.setActionCommand("Confirmar cambios de pelicula");
        }
    }

    public void clearFields(){
        addMovieTitle.setText("");
        addDuration.setText("");
        addGenre.setSelectedIndex(0);
        addClassification.setSelectedIndex(0);
    }

    public void setAddMovieTitle(String title) {
        addMovieTitle.setText(title);
    }

    public void setAddDuration(String duration) {
        addDuration.setText(duration);
    }

    public void setAddGenre(String genre) {
        addGenre.setSelectedItem(genre);
    }

    public void setAddClassification(String classification) {
        addClassification.setSelectedItem(classification);
    }

    public JTextField getAddMovieTitle() {
        return addMovieTitle;
    }

    public JTextField getAddDuration() {
        return addDuration;
    }

    public JComboBox<String> getAddGenre() {
        return addGenre;
    }

    public JComboBox<String> getAddClassification() {
        return addClassification;
    }

    public String getMovieTitle() {
        return addMovieTitle.getText();
    }

    public int getDuration() {
        return Integer.parseInt(addDuration.getText());
    }

    public JComboBox getGenre() {
        return addGenre;
    }

    public JComboBox getClassification() {
        return addClassification;
    }
}