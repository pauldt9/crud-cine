package View;

import Models.MoviesTableModel;
import Models.RoomsTableModel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;

import static utils.CreateComponents.*;

public class RoomsView extends JPanel {
    private JLabel titleLbl;

    private Color fgColor = new Color(0x2C3E50);
    private Color bgColButtons = new Color(245, 245, 245);

    private JButton addRoom;
    private JButton deleteRoom;
    private JButton editRoom;

    private JTable roomsTable;
    private RoomsTableModel roomsTableModel;

    private int idRoom; //primary key

    public RoomsView(){
        setOpaque(false);
        setLayout(new BorderLayout());

        //titulo
        titleLbl = createJLabel("Salas", 40, true);
        titleLbl.setForeground(fgColor);
        titleLbl.setBorder(BorderFactory.createEmptyBorder(60, 40, 0, 0));
        add(titleLbl, BorderLayout.NORTH);

        //Paneles vacios
        JPanel emptyEast = createEmptyPanel(40, 800);
        add(emptyEast, BorderLayout.EAST);

        JPanel emptyWest = createEmptyPanel(40, 800);
        add(emptyWest, BorderLayout.WEST);

        initRoomsTable(); //tabla

        //Aqui van los botones
        JPanel movButtonPanel = createButtonsPanel();
        add(movButtonPanel, BorderLayout.SOUTH);

        addRoom = createButton("Agregar",120, 40);
        addRoom.setFont(new Font("Helvetica Neue", Font.BOLD, 15));
        addRoom.setActionCommand("Agregar sala");
        addRoom.setBackground(bgColButtons);
        addRoom.setForeground(fgColor);
        movButtonPanel.add(addRoom);

        editRoom = createButton("Editar", 120, 40);
        editRoom.setFont(new Font("Helvetica Neue", Font.BOLD, 15));
        editRoom.setActionCommand("Editar sala");
        editRoom.setBackground(bgColButtons);
        editRoom.setForeground(fgColor);
        movButtonPanel.add(editRoom);

        deleteRoom = createButton("Eliminar", 120, 40);
        deleteRoom.setFont(new Font("Helvetica Neue", Font.BOLD, 15));
        deleteRoom.setActionCommand("Eliminar sala");
        deleteRoom.setBackground(bgColButtons);
        deleteRoom.setForeground(fgColor);
        movButtonPanel.add(deleteRoom);

        try {
            Image deleteIcon = ImageIO.read(getClass().getResource("/img/remove.png"));
            deleteIcon = deleteIcon.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            deleteRoom.setIcon(new ImageIcon(deleteIcon));

            Image editIcon = ImageIO.read(getClass().getResource("/img/editDark.png"));
            editIcon = editIcon.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            editRoom.setIcon(new ImageIcon(editIcon));

            Image addIcon = ImageIO.read(getClass().getResource("/img/add.png"));
            addIcon = addIcon.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            addRoom.setIcon(new ImageIcon(addIcon));
        } catch (IOException e){
            System.out.println("error al cargar imagen: " + e.getMessage());
        }
    }

    public void initRoomsTable(){
        roomsTableModel = new RoomsTableModel();
        roomsTable = new JTable(roomsTableModel);

        JScrollPane scroll = new JScrollPane(roomsTable);
        add(scroll, BorderLayout.CENTER);

    }

    public void setListeners(ActionListener listener){
        addRoom.addActionListener(listener);
        editRoom.addActionListener(listener);
        deleteRoom.addActionListener(listener);
    }

    public int getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(int idRoom) {
        this.idRoom = idRoom;
    }
}
