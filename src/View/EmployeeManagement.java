package View;

import Models.EmployeeTableModel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.io.IOException;

public class EmployeeManagement extends JPanel {
    private Color bgColButtons = new Color(245, 245, 245);
    private Color fgColor = new Color(0x2C3E50);

    private int idEmployee;

    private JButton addEmployee;
    private JButton deleteEmployee;
    private JButton editEmployee;

    private JLabel empTitle;


    private JTable empTable;
    private EmployeeTableModel tableModelEmp;

    public EmployeeManagement(){
        setLayout(new BorderLayout());
        setOpaque(false);
//        setBackground(Color.BLUE);

        empTitle = createJLabel("Empleados", 40, true);
        empTitle.setForeground(fgColor);
        empTitle.setBorder(BorderFactory.createEmptyBorder(60, 40, 0, 0));
        add(empTitle, BorderLayout.NORTH);

        //aqui van los botones
        JPanel empButtonPanel = createButtonsPanel();
        add(empButtonPanel, BorderLayout.SOUTH);

        addEmployee = createButton("Agregar", 15, 120, 40);
        addEmployee.setActionCommand("Agregar empleado");
        addEmployee.setBackground(bgColButtons);
        addEmployee.setForeground(fgColor);
        empButtonPanel.add(addEmployee);

        editEmployee = createButton("Editar", 15, 120, 40);
        editEmployee.setActionCommand("Editar empleado");
        editEmployee.setBackground(bgColButtons);
        editEmployee.setForeground(fgColor);
        empButtonPanel.add(editEmployee);

        deleteEmployee = createButton("Eliminar", 15, 120, 40);
        deleteEmployee.setActionCommand("Eliminar empleado");
        deleteEmployee.setBackground(bgColButtons);
        deleteEmployee.setForeground(fgColor);
        empButtonPanel.add(deleteEmployee);

        JPanel emptyWest = createEmptyPanel();
        add(emptyWest, BorderLayout.WEST);

        JPanel emptyEast = createEmptyPanel();
        add(emptyEast, BorderLayout.EAST);

        initEmpTable();
        initIcons();
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

    public JPanel createEmptyPanel(){
        JPanel empty = new JPanel();
        empty.setOpaque(false);
        empty.setPreferredSize(new Dimension(40, Integer.MAX_VALUE));
        return empty;
    }

    public JPanel createButtonsPanel(){
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        panel.setOpaque(false);
        panel.setPreferredSize(new Dimension(Integer.MAX_VALUE, 130));
        return panel;
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

    public void initEmpTable(){
        tableModelEmp = new EmployeeTableModel();
        empTable = new JTable(tableModelEmp);

        JScrollPane scroll = new JScrollPane(empTable);
        add(scroll, BorderLayout.CENTER);
    }

    public void initIcons(){
        try {
            Image addIcon = ImageIO.read(getClass().getResource("/img/add.png"));
            addIcon = addIcon.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            addEmployee.setIcon(new ImageIcon(addIcon));

            Image editIcon = ImageIO.read(getClass().getResource("/img/editDark.png"));
            editIcon = editIcon.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            editEmployee.setIcon(new ImageIcon(editIcon));

            Image deleteIcon = ImageIO.read(getClass().getResource("/img/remove.png"));
            deleteIcon = deleteIcon.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            deleteEmployee.setIcon(new ImageIcon(deleteIcon));
        } catch (IOException e){
            System.out.println("error al cargar imagen: " + e.getMessage());
        }
    }

    public void setListeners(ActionListener listener){
        addEmployee.addActionListener(listener);
        deleteEmployee.addActionListener(listener);
        editEmployee.addActionListener(listener);
    }

    public JTable getEmpTable(){
        return empTable;
    }

    public EmployeeTableModel getTableModelEmp(){
        return tableModelEmp;
    }

    public void tableListener(KeyListener listener) {
        empTable.addKeyListener(listener);
    }

    public void removeTableSelection() {
        empTable.clearSelection();
    }

    public void setEmpTable(JTable empTable) {
        this.empTable = empTable;
    }

    public int getIdEmployee(){
        return this.idEmployee;
    }

    public void setIdEmployee(int id){
        this.idEmployee = id;
    }
}
