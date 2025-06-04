package View;

import Models.EmployeeTableModel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.io.IOException;

import static utils.CreateComponents.*;

public class EmployeeManagement extends JPanel {
    private Color bgColButtons = new Color(245, 245, 245);
    private Color fgColor = new Color(0x2C3E50);

    private int idEmployee; //Primary Key

    private JButton addEmployee;
    private JButton deleteEmployee;
    private JButton editEmployee;

    private JLabel empTitle;

    private JTable employeesTable;
    private EmployeeTableModel tableModelEmployees;

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

        addEmployee = createButton("Agregar", 120, 40);
        addEmployee.setFont(new Font("Helvetica Neue", Font.BOLD, 15));
        addEmployee.setActionCommand("Agregar empleado");
        addEmployee.setBackground(bgColButtons);
        addEmployee.setForeground(fgColor);
        empButtonPanel.add(addEmployee);

        editEmployee = createButton("Editar", 120, 40);
        editEmployee.setFont(new Font("Helvetica Neue", Font.BOLD, 15));
        editEmployee.setActionCommand("Editar empleado");
        editEmployee.setBackground(bgColButtons);
        editEmployee.setForeground(fgColor);
        empButtonPanel.add(editEmployee);

        deleteEmployee = createButton("Eliminar", 120, 40);
        deleteEmployee.setFont(new Font("Helvetica Neue", Font.BOLD, 15));
        deleteEmployee.setActionCommand("Eliminar empleado");
        deleteEmployee.setBackground(bgColButtons);
        deleteEmployee.setForeground(fgColor);
        empButtonPanel.add(deleteEmployee);

        JPanel emptyWest = createEmptyPanel(40, 800);
        add(emptyWest, BorderLayout.WEST);

        JPanel emptyEast = createEmptyPanel(40, 800);
        add(emptyEast, BorderLayout.EAST);

        initEmpTable();
        initIcons();
    }

    public void initEmpTable(){
        tableModelEmployees = new EmployeeTableModel();
        employeesTable = new JTable(tableModelEmployees);

        JScrollPane scroll = new JScrollPane(employeesTable);
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

    public JTable getEmployeesTable(){
        return employeesTable;
    }

    public EmployeeTableModel getTableModelEmployees(){
        return tableModelEmployees;
    }

    public void tableListener(KeyListener listener) {
        employeesTable.addKeyListener(listener);
    }

    public void removeTableSelection() {
        employeesTable.clearSelection();
    }

    public void setEmployeesTable(JTable employeesTable) {
        this.employeesTable = employeesTable;
    }

    public int getIdEmployee(){
        return this.idEmployee;
    }

    public void setIdEmployee(int id){
        this.idEmployee = id;
    }
}
