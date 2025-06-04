package View;

import lib.TextPrompt;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;

import static utils.CreateComponents.*;

public class RoomsForm extends JPanel {
    private String action;
    private JLabel panelTitle;

    private Color fgColor = new Color(0x2C3E50);
    private Color bgColButtons = new Color(245, 245, 245);

    private JButton confirmButton;
    private JButton backButton;

    /*----------Campos del formulario----------*/
    private JTextField roomName;
    private JComboBox<Integer> rows;
    private JComboBox<Integer> col;
    private JComboBox<String> roomType;

    public RoomsForm(){
        setLayout(new BorderLayout());
        setOpaque(false);

        JPanel topPanel = new JPanel();
        topPanel.setPreferredSize(new Dimension(Integer.MAX_VALUE, 170));
        topPanel.setOpaque(false);
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        add(topPanel, BorderLayout.NORTH);

        backButton = createButton("", 45, 45);
        backButton.setActionCommand("Regresar sala");
        backButton.setAlignmentX(Box.LEFT_ALIGNMENT);
        backButton.setBackground(bgColButtons);
        topPanel.add(Box.createRigidArea(new Dimension(40, 60)));
        topPanel.add(backButton);

        //titulo
        panelTitle = createJLabel("Agregar sala", 40, true);
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
        formPanel.setLayout(new GridLayout(3, 2, 30, 30));
        formPanel.setOpaque(false);
        add(formPanel, BorderLayout.CENTER);

        roomName = createTextField("Ingresar nombre de la sala", 350, 50);
        roomName.setAlignmentX(Component.LEFT_ALIGNMENT);
        roomName.setBorder(BorderFactory.createCompoundBorder(roomName.getBorder(), new EmptyBorder(0, 10, 0, 0)));
        roomName.setBackground(bgColButtons);
        roomName.setForeground(fgColor);
        formPanel.add(roomName);

        String[] roomTypes = {"Seleccionar tipo de sala", "IMAX", "3D", "4D", "MacroXE", "Tradicional"};
        roomType = new JComboBox<String>(roomTypes);
        roomType.setAlignmentX(Component.LEFT_ALIGNMENT);
        roomType.setBackground(bgColButtons);
        roomType.setForeground(fgColor);
        roomType.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
        roomType.setMaximumSize(new Dimension(350, 50));
        formPanel.add(roomType);

        Integer[] rowsArray = {1, 2, 3, 4, 5, 6, 7, 8 ,9, 10};
        rows = new JComboBox<Integer>(rowsArray);
        rows.setAlignmentX(Component.LEFT_ALIGNMENT);
        rows.setBackground(bgColButtons);
        rows.setForeground(fgColor);
        rows.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
        rows.setMaximumSize(new Dimension(350, 50));
        formPanel.add(rows);

        Integer[] columns = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        col = new JComboBox<Integer>(columns);
        col.setAlignmentX(Component.LEFT_ALIGNMENT);
        col.setBackground(bgColButtons);
        col.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
        col.setMaximumSize(new Dimension(350, 50));
        formPanel.add(col);

        confirmButton = createButton("Confirmar", 350, 50);
        confirmButton.setFont(new Font("Helvetica Neue", Font.BOLD, 15));
        confirmButton.setActionCommand("Confirmar sala");
        confirmButton.setForeground(fgColor);
        confirmButton.setBackground(bgColButtons);
        confirmButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        formPanel.add(confirmButton);

        try {
            Image backIcon = ImageIO.read(getClass().getResource("/img/back.png"));
            backIcon = backIcon.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
            backButton.setIcon(new ImageIcon(backIcon));
        } catch (IOException e){
            System.out.println("error al cargar imagen: " + e.getMessage());
        }
    }

    public void setListeners(ActionListener listener){
        confirmButton.addActionListener(listener);
        backButton.addActionListener(listener);
    }

    public void setAction(String action){
        this.action = action;
        if (action.equals("Agregar")){
            panelTitle.setText("Agregar Sala");
            confirmButton.setActionCommand("Confirmar sala");
        } else {
            panelTitle.setText("Actualizar Sala");
            confirmButton.setActionCommand("Confirmar cambios de sala");
        }
    }

    public void clearFields(){
        roomName.setText("");
        rows.setSelectedIndex(0);
        col.setSelectedIndex(0);
        roomType.setSelectedIndex(0);
    }

    public void setRoomName(String room){
        roomName.setText(room);
    }

    public JComboBox<Integer> getRows() {
        return rows;
    }

    public JComboBox<Integer> getCol() {
        return col;
    }

    public JComboBox<String> getRoomType() {
        return roomType;
    }
}
