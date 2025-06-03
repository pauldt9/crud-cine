package View;

import lib.TextPrompt;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;

public class AddMovieForm extends JPanel {
    private String action;

    private JButton confirmEmp;
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
    private JTextField addShowtime;

    public AddMovieForm(){
        setLayout(new BorderLayout());
        setOpaque(false);

        JPanel topPanel = new JPanel();
        topPanel.setPreferredSize(new Dimension(Integer.MAX_VALUE, 170));
        topPanel.setOpaque(false);
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        add(topPanel, BorderLayout.NORTH);

        backButton = createButton(null, 1, 45, 45);
        backButton.setActionCommand("Regresar pelicula");
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

        JPanel emptyWest = createEmptyPanel();
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

        addShowtime = createTextField("Ingresar Horario", 350, 50);
        addShowtime.setAlignmentX(Component.LEFT_ALIGNMENT);
        addShowtime.setBorder(BorderFactory.createCompoundBorder(addShowtime.getBorder(), new EmptyBorder(0, 10, 0, 0)));
        addShowtime.setBackground(bgColButtons);
        addShowtime.setForeground(fgColor);
        formPanel.add(addShowtime);

        addImage = createButton("Agregar Imagen", 15, 350, 50);
        addImage.setActionCommand("Agregar imagen");
        addImage.setForeground(fgColor);
        addImage.setBackground(bgColButtons);
        addImage.setAlignmentX(Component.LEFT_ALIGNMENT);
        formPanel.add(addImage);

        confirmEmp = createButton("Confirmar", 15, 350, 50);
        confirmEmp.setActionCommand("Confirmar funcion");
        confirmEmp.setForeground(fgColor);
        confirmEmp.setBackground(bgColButtons);
        confirmEmp.setAlignmentX(Component.LEFT_ALIGNMENT);
        formPanel.add(confirmEmp);

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

    public JButton createButton(String buttonName, int fontSize, int w, int h){
        JButton button = new JButton(buttonName);
        button.setFont(new Font("Helvetica Neue", Font.BOLD, fontSize));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setPreferredSize(new Dimension(w, h));
        button.setMinimumSize(new Dimension(w, h));
        button.setMaximumSize(new Dimension(w, h));
        return button;
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

    public JPanel createEmptyPanel(){
        JPanel empty = new JPanel();
        empty.setOpaque(false);
        empty.setPreferredSize(new Dimension(40, Integer.MAX_VALUE));
        return empty;
    }

    public void setListeners(ActionListener listener){
        confirmEmp.addActionListener(listener);
        addImage.addActionListener(listener);
        backButton.addActionListener(listener);
    }

    public void setAction(String action){
        this.action = action;
        if (action.equals("Agregar")){
            panelTitle.setText("Agregar Funcion");
            confirmEmp.setActionCommand("Confirmar funcion");
        } else {
            panelTitle.setText("Actualizar Funcion");
            confirmEmp.setActionCommand("Confirmar cambios de funcion");
        }
    }
}