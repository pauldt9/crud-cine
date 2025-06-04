package View.Admin.ShowtimesManagement;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;

import static utils.CreateComponents.*;

public class ShowtimesMain extends JPanel {
    private JLabel titleLbl;

    private Color fgColor = new Color(0x2C3E50);
    private Color bgColButtons = new Color(245, 245, 245);

    private JButton addShowtime;
    private JButton deleteShowtime;
    private JButton editShowtime;

    private JTable showtimesTable;

    private int idShowtime; //primary key

    public ShowtimesMain(){
        setOpaque(false);
        setLayout(new BorderLayout());

        //titulo
        titleLbl = createJLabel("Funciones", 40, true);
        titleLbl.setForeground(fgColor);
        titleLbl.setBorder(BorderFactory.createEmptyBorder(60, 40, 0, 0));
        add(titleLbl, BorderLayout.NORTH);

        //Paneles vacios
        JPanel emptyEast = createEmptyPanel(40, 800);
        add(emptyEast, BorderLayout.EAST);

        JPanel emptyWest = createEmptyPanel(40, 800);
        add(emptyWest, BorderLayout.WEST);

        initMovTable(); //tabla

        //Aqui van los botones
        JPanel movButtonPanel = createButtonsPanel();
        add(movButtonPanel, BorderLayout.SOUTH);

        addShowtime = createButton("Agregar", 120, 40);
        addShowtime.setFont(new Font("Helvetica Neue", Font.BOLD, 15));
        addShowtime.setActionCommand("Agregar funcion");
        addShowtime.setBackground(bgColButtons);
        addShowtime.setForeground(fgColor);
        movButtonPanel.add(addShowtime);

        editShowtime = createButton("Editar", 120, 40);
        editShowtime.setFont(new Font("Helvetica Neue", Font.BOLD, 15));
        editShowtime.setActionCommand("Editar funcion");
        editShowtime.setBackground(bgColButtons);
        editShowtime.setForeground(fgColor);
        movButtonPanel.add(editShowtime);

        deleteShowtime = createButton("Eliminar", 120, 40);
        deleteShowtime.setFont(new Font("Helvetica Neue", Font.BOLD, 15));
        deleteShowtime.setActionCommand("Eliminar funcion");
        deleteShowtime.setBackground(bgColButtons);
        deleteShowtime.setForeground(fgColor);
        movButtonPanel.add(deleteShowtime);

        try {
            Image deleteIcon = ImageIO.read(getClass().getResource("/img/remove.png"));
            deleteIcon = deleteIcon.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            deleteShowtime.setIcon(new ImageIcon(deleteIcon));

            Image editIcon = ImageIO.read(getClass().getResource("/img/editDark.png"));
            editIcon = editIcon.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            editShowtime.setIcon(new ImageIcon(editIcon));

            Image addIcon = ImageIO.read(getClass().getResource("/img/add.png"));
            addIcon = addIcon.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            addShowtime.setIcon(new ImageIcon(addIcon));
        } catch (IOException e){
            System.out.println("error al cargar imagen: " + e.getMessage());
        }
    }

    public void initMovTable(){

    }

    public void setListeners(ActionListener listener){
        addShowtime.addActionListener(listener);
        editShowtime.addActionListener(listener);
        deleteShowtime.addActionListener(listener);
    }

    public int getIdShowtime() {
        return idShowtime;
    }

    public void setIdShowtime(int idShowtime) {
        this.idShowtime = idShowtime;
    }
}
