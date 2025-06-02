package View;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MoviesViewAdmin extends JPanel {
    private JLabel moviesLbl;

    private Color fgColor = new Color(0x2C3E50);
    private Color bgColButtons = new Color(245, 245, 245);

    private JButton addMov;
    private JButton deleteMov;
    private JButton editMov;

    public MoviesViewAdmin(){
        setLayout(new BorderLayout());
        setOpaque(false);

        moviesLbl = createJLabel("Peliculas", 40, true);
        moviesLbl.setForeground(fgColor);
        moviesLbl.setBorder(BorderFactory.createEmptyBorder(60, 40, 0, 0));
        add(moviesLbl, BorderLayout.NORTH);

        //Paneles vacios
        JPanel emptyEast = createEmptyPanel();
        add(emptyEast, BorderLayout.EAST);

        JPanel emptyWest = createEmptyPanel();
        add(emptyWest, BorderLayout.WEST);

        initMovTable(); //tabla

        //Aqui van los botones
        JPanel movButtonPanel = createButtonsPanel();
        add(movButtonPanel, BorderLayout.SOUTH);

        addMov = createButton("Agregar", 15, 120, 40);
        addMov.setActionCommand("Agregar pelicula");
        addMov.setBackground(bgColButtons);
        addMov.setForeground(fgColor);
        movButtonPanel.add(addMov);

        editMov = createButton("Editar", 15, 120, 40);
        editMov.setActionCommand("Editar pelicula");
        editMov.setBackground(bgColButtons);
        editMov.setForeground(fgColor);
        movButtonPanel.add(editMov);

        deleteMov = createButton("Eliminar", 15 , 120, 40);
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

    public JLabel createJLabel(String title, int fontSize, boolean bold){
        JLabel label = new JLabel(title);
        if (bold){
            label.setFont(new Font("Helvetica Neue", Font.BOLD, fontSize));
        } else {
            label.setFont(new Font("Helvetica Neue", Font.PLAIN, fontSize));
        }

        return label;
    }

    public JPanel createButtonsPanel(){
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        panel.setOpaque(false);
        panel.setPreferredSize(new Dimension(Integer.MAX_VALUE, 130));
        return panel;
    }

    public JPanel createEmptyPanel(){
        JPanel empty = new JPanel();
        empty.setOpaque(false);
        empty.setPreferredSize(new Dimension(40, Integer.MAX_VALUE));
        return empty;
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

    public void initMovTable(){

    }

    public void setListeners(ActionListener listener){
        addMov.addActionListener(listener);
        deleteMov.addActionListener(listener);
        editMov.addActionListener(listener);
    }
}
