package View;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;

public class AdminView extends JPanel {
    private String action;

    //botones de navegacion
    private JButton exitButton;
    private JButton menuButton;
    private JButton salesButton;
    private JButton darkMode;

    //botones del menu
    private JButton employeeButton;
    private JButton moviesButton;

    //botones de empleados
    private JButton addEmployee;
    private JButton deleteEmployee;
    private JButton editEmployee;

    //botones de peliculas
    private JButton addMov;
    private JButton deleteMov;
    private JButton editMov;

    //paneles del menu
    private JPanel employeePanel;
    private JPanel moviesPanel;

    //paneles
    private JPanel mainPanel;
    private JPanel leftPanel;

    private JPanel menuPanel;
    private JPanel menuButtonsPanel;

    private JPanel salesPanel;

    /*------------Labels------------*/
    //Menu
    private JLabel title;
    private JLabel userTitle;
    private JLabel buttonTitleEmp;
    private JLabel managementTitle;
    private JLabel buttonTitleMov;
    private JLabel summaryLbl;
    private JLabel moviesLbl;
    private JLabel salesLbl;
    private JLabel numEmployees; //Label donde se mostrara la cantidad de empleados
    private JLabel numMovies; //numero de peliculas disponibles
    private JLabel totalSales; //ventas totales

    //Empleados
    private JLabel empTitle;

    //Peliculas


    /*----------Tablas----------*/
    //Empleados
    private JTable empTable;
    private DefaultTableModel tableModelEmp;
    private JTable movTable;
    private DefaultTableModel tmMovies;

    /*----------Colores de botones y texto----------*/
    //no muevan aqui paro
    private Color fgColor = new Color(0x2C3E50);
    private Color bgColor = new Color(0xEDF2FA);

    public AdminView() {
        setLayout(new BorderLayout());

        /*-----------Panel izquierdo-----------*/
        leftPanel = new JPanel();
        leftPanel.setBackground(new Color(0xDCE9F9));
        leftPanel.setPreferredSize(new Dimension(200, getHeight()));
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        add(leftPanel, BorderLayout.WEST);

        userTitle = createJLabel("Admin", 30, true); //donde dice admin en el panel izquierdo
        userTitle.setForeground(fgColor);
        userTitle.setAlignmentX(CENTER_ALIGNMENT);
        leftPanel.add(Box.createVerticalStrut(70));
        leftPanel.add(userTitle);

        //botones de navegacion
        menuButton = createButton("Menu", 20, 150, 40);
        menuButton.setActionCommand("Menu");
        menuButton.setBackground(bgColor);
        menuButton.setForeground(fgColor);
        menuButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        leftPanel.add(Box.createVerticalStrut(50));
        leftPanel.add(menuButton);

        salesButton = createButton("Ventas", 20, 150, 40);
        salesButton.setActionCommand("Ventas");
        salesButton.setBackground(new Color(0xEDF2FA));
        salesButton.setForeground(fgColor);
        salesButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        leftPanel.add(Box.createVerticalStrut(30));
        leftPanel.add(salesButton);

        darkMode = createButton(null, 15, 150, 40);
        darkMode.setActionCommand("Modo Oscuro");
        darkMode.setBackground(new Color(0xEDF2FA));
        darkMode.setForeground(fgColor);
        darkMode.setAlignmentX(Component.CENTER_ALIGNMENT);
        leftPanel.add(Box.createVerticalStrut(350));
        leftPanel.add(darkMode);

        exitButton = createButton(null, 20, 150, 40);
        exitButton.setActionCommand("Salir");
        exitButton.setBackground(new Color(0x17C3B2));
        exitButton.setForeground(Color.WHITE);
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        leftPanel.add(Box.createVerticalStrut(30));
        leftPanel.add(exitButton);

        /*-----------Panel central-----------*/
        mainPanel = new JPanel();
        mainPanel.setLayout(new CardLayout());
        mainPanel.setBackground(Color.WHITE);
        add(mainPanel, BorderLayout.CENTER);

        menuPanel = new JPanel();
        salesPanel = new JPanel();
        employeePanel = new JPanel();
        moviesPanel = new JPanel();

        mainPanel.add(menuPanel, "menu");
        mainPanel.add(salesPanel, "ventas");
        mainPanel.add(employeePanel, "Empleados");
        mainPanel.add(moviesPanel, "peliculas");

        //Panel menu
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        menuPanel.setBackground(Color.WHITE);

        title = createJLabel("Inicio", 40, true); //titulo inicio
        title.setForeground(fgColor);
        title.setAlignmentX(Component.LEFT_ALIGNMENT);
        title.setBorder(BorderFactory.createEmptyBorder(60, 40, 0, 0));
        menuPanel.add(title);

        summaryLbl = createJLabel("Resumen", 20, true);
        summaryLbl.setForeground(fgColor);
        summaryLbl.setAlignmentX(Component.LEFT_ALIGNMENT);
        summaryLbl.setBorder(BorderFactory.createEmptyBorder(30, 40, 0, 0));
        menuPanel.add(summaryLbl);

        numEmployees = createJLabel("Cantidad de empleados: " + "#no. empleado", 15, true);
        numEmployees.setForeground(fgColor);
        numEmployees.setAlignmentX(Component.LEFT_ALIGNMENT);
        numEmployees.setBorder(BorderFactory.createEmptyBorder(30, 40, 0, 0));
        menuPanel.add(numEmployees);

        numMovies = createJLabel("Peliculas disponibles: " + "#no. pel", 15, true);
        numMovies.setForeground(fgColor);
        numMovies.setAlignmentX(Component.LEFT_ALIGNMENT);
        numMovies.setBorder(BorderFactory.createEmptyBorder(10, 40, 0, 0));
        menuPanel.add(numMovies);

        totalSales = createJLabel("Ventas: $" + "$$", 15, true);
        totalSales.setForeground(fgColor);
        totalSales.setAlignmentX(Component.LEFT_ALIGNMENT);
        totalSales.setBorder(BorderFactory.createEmptyBorder(10, 40, 0, 0));
        menuPanel.add(totalSales);

        managementTitle = createJLabel("Gestion", 20, true);
        managementTitle.setForeground(fgColor);
        managementTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        managementTitle.setBorder(BorderFactory.createEmptyBorder(40, 40, 0, 0));
        menuPanel.add(managementTitle);

        //Aqui estan otros botones del menu
        menuButtonsPanel = new JPanel();
        menuButtonsPanel.setLayout(new GridBagLayout());
        menuButtonsPanel.setBackground(Color.WHITE);
        menuButtonsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
                                                    //obtiene el mayor valor para el width del panel
        menuButtonsPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 300));
        menuPanel.add(menuButtonsPanel);

        GridBagConstraints c = new GridBagConstraints();
        c.gridy = 1;
        c.gridx = 0;
        c.weighty = 0;
        c.weightx = 0;
        c.insets = new Insets(0, 40, 80, 10);
        c.anchor = GridBagConstraints.NORTHWEST;
        c.fill = GridBagConstraints.NONE;

        employeeButton = createButton(null, 1, 100, 100);
        employeeButton.setBackground(new Color(245, 245, 245));
        employeeButton.setActionCommand("Empleados");

        menuButtonsPanel.add(employeeButton, c);

        buttonTitleEmp = createJLabel("Gestionar Empleados", 15, true);
        buttonTitleEmp.setForeground(fgColor);

        c.gridy = 1;
        c.gridx = 1;
        c.weightx = 1;
        c.weighty = 0;
        c.insets = new Insets(0, 10, 80, 10);
        c.anchor = GridBagConstraints.WEST;
        c.fill = GridBagConstraints.NONE;

        menuButtonsPanel.add(buttonTitleEmp, c);

        c.gridy = 1;
        c.gridx = 2;
        c.weightx = 2;
        c.weighty = 0;
        c.insets = new Insets(0, 30, 80, 10);
        c.anchor = GridBagConstraints.NORTHWEST;
        c.fill = GridBagConstraints.NONE;

        moviesButton = createButton(null, 1, 100, 100);
        moviesButton.setBackground(new Color(245, 245, 245));
        moviesButton.setActionCommand("Peliculas");
        menuButtonsPanel.add(moviesButton, c);

        buttonTitleMov = createJLabel("Gestionar Peliculas", 15, true);
        buttonTitleMov.setForeground(fgColor);

        c.gridy = 1;
        c.gridx = 2;
        c.weightx = 3;
        c.weighty = 0;
        c.insets = new Insets(0, 150, 80, 10);
        c.anchor = GridBagConstraints.WEST;
        c.fill = GridBagConstraints.NONE;

        menuButtonsPanel.add(buttonTitleMov, c);

        //----------Empleados
        employeePanel.setOpaque(false);
        employeePanel.setLayout(new BorderLayout());

        empTitle = createJLabel("Empleados", 40, true);
        empTitle.setForeground(fgColor);
        empTitle.setBorder(BorderFactory.createEmptyBorder(60, 40, 0, 0));
        employeePanel.add(empTitle, BorderLayout.NORTH);

        JPanel empButtonPanel = createButtonsPanel();
        employeePanel.add(empButtonPanel, BorderLayout.SOUTH);

        addEmployee = createButton("Agregar", 15, 120, 40);
        addEmployee.setActionCommand("Agregar empleado");
        addEmployee.setBackground(new Color(245, 245, 245));
        addEmployee.setForeground(fgColor);
        empButtonPanel.add(addEmployee);

        editEmployee = createButton("Editar", 15, 120, 40);
        editEmployee.setActionCommand("Editar empleado");
        editEmployee.setBackground(new Color(245, 245, 245));
        editEmployee.setForeground(fgColor);
        empButtonPanel.add(editEmployee);

        deleteEmployee = createButton("Eliminar", 15, 120, 40);
        deleteEmployee.setActionCommand("Eliminar empleado");
        deleteEmployee.setBackground(new Color(245, 245, 245));
        deleteEmployee.setForeground(fgColor);
        empButtonPanel.add(deleteEmployee);

        JPanel emptyWest = createEmptyPanel();
        employeePanel.add(emptyWest, BorderLayout.WEST);

        JPanel emptyEast = createEmptyPanel();
        employeePanel.add(emptyEast, BorderLayout.EAST);

        initEmpTable();

        //-------------Peliculas
        moviesPanel.setLayout(new BorderLayout());
        moviesPanel.setOpaque(false);

        moviesLbl = createJLabel("Peliculas", 40, true);
        moviesLbl.setForeground(fgColor);
        moviesLbl.setBorder(BorderFactory.createEmptyBorder(60, 40, 0, 0));
        moviesPanel.add(moviesLbl, BorderLayout.NORTH);

        JPanel emptyPanelMov = createEmptyPanel();
        moviesPanel.add(emptyPanelMov, BorderLayout.EAST);

        JPanel emptyPanelMov2 = createEmptyPanel();
        moviesPanel.add(emptyPanelMov2, BorderLayout.WEST);

        initMovTable();

        JPanel movButtonPanel = createButtonsPanel();
        moviesPanel.add(movButtonPanel, BorderLayout.SOUTH);

        addMov = createButton("Agregar", 15, 120, 40);
        addMov.setActionCommand("Agregar pelicula");
        addMov.setBackground(new Color(245, 245, 245));
        addMov.setForeground(fgColor);
        movButtonPanel.add(addMov);

        editMov = createButton("Editar", 15, 120, 40);
        editMov.setActionCommand("Editar pelicula");
        editMov.setBackground(new Color(245, 245, 245));
        editMov.setForeground(fgColor);
        movButtonPanel.add(editMov);

        deleteMov = createButton("Eliminar", 15 , 120, 40);
        deleteMov.setActionCommand("Eliminar pelicula");
        deleteMov.setBackground(new Color(245, 245, 245));
        deleteMov.setForeground(fgColor);
        movButtonPanel.add(deleteMov);

        //Panel ventas
        salesPanel.setLayout(new BorderLayout());

        salesLbl = createJLabel("Ventas", 40, true);
        salesLbl.setForeground(fgColor);
        salesLbl.setHorizontalAlignment(SwingConstants.LEFT);
        salesLbl.setBorder(BorderFactory.createEmptyBorder(60, 40, 0, 0));
        salesPanel.add(salesLbl, BorderLayout.NORTH);

        //Iconos en botones
        try {
            Image exitIcon = ImageIO.read(getClass().getResource("/img/exit.png"));
            exitIcon = exitIcon.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            exitButton.setIcon(new ImageIcon(exitIcon));

            Image darkIcon = ImageIO.read(getClass().getResource("/img/dark.png"));
            darkIcon = darkIcon.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            darkMode.setIcon(new ImageIcon(darkIcon));

            Image userIcon = ImageIO.read(getClass().getResource("/img/employee.png"));
            userIcon = userIcon.getScaledInstance(70, 70, Image.SCALE_SMOOTH);
            employeeButton.setIcon(new ImageIcon(userIcon));

            Image moviesIcon = ImageIO.read(getClass().getResource("/img/movies.png"));
            moviesIcon = moviesIcon.getScaledInstance(70, 70, Image.SCALE_SMOOTH);
            moviesButton.setIcon(new ImageIcon(moviesIcon));

            Image addIcon = ImageIO.read(getClass().getResource("/img/add.png"));
            addIcon = addIcon.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            addEmployee.setIcon(new ImageIcon(addIcon));
            addMov.setIcon(new ImageIcon(addIcon));

            Image editIcon = ImageIO.read(getClass().getResource("/img/editDark.png"));
            editIcon = editIcon.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            editEmployee.setIcon(new ImageIcon(editIcon));
            editMov.setIcon(new ImageIcon(editIcon));

            Image deleteIcon = ImageIO.read(getClass().getResource("/img/remove.png"));
            deleteIcon = deleteIcon.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            deleteEmployee.setIcon(new ImageIcon(deleteIcon));
            deleteMov.setIcon(new ImageIcon(deleteIcon));
        } catch (IOException e){
            System.out.println("error al cargar imagen: " + e.getMessage());
        }
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

    public void setListeners(ActionListener listener){
        exitButton.addActionListener(listener);
        menuButton.addActionListener(listener);
        salesButton.addActionListener(listener);
        darkMode.addActionListener(listener);
        employeeButton.addActionListener(listener);
        moviesButton.addActionListener(listener);
        addEmployee.addActionListener(listener);
        deleteEmployee.addActionListener(listener);
        editEmployee.addActionListener(listener);
        addMov.addActionListener(listener);
        editMov.addActionListener(listener);
        deleteMov.addActionListener(listener);
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

    public void initEmpTable(){
        String[] col = {"Nombre", "Apellido", "Tipo de Empleado", "Usuario"};
        tableModelEmp = new DefaultTableModel();
        tableModelEmp.setColumnIdentifiers(col);

        empTable = new JTable(tableModelEmp);
        JScrollPane scroll = new JScrollPane(empTable);
        employeePanel.add(scroll, BorderLayout.CENTER);
    }

    public void initMovTable(){
        String[] col = {"Titulo", "Duracion", "Genero", "Clasificacion"};
        tmMovies = new DefaultTableModel();
        tmMovies.setColumnIdentifiers(col);

        movTable = new JTable(tmMovies);
        JScrollPane scroll = new JScrollPane((movTable));
        moviesPanel.add(scroll, BorderLayout.CENTER);
    }

    //Cambiar modos (dark mode)
    public void setViewMode(String action){
        this.action = action;
        darkMode.setActionCommand(action);

        if (action.equals("Modo Oscuro")){
            fgColor = new Color(0x2C3E50);
            leftPanel.setBackground(new Color(0xDCE9F9));

            //Menu
            menuPanel.setBackground(Color.WHITE);
            mainPanel.setBackground(Color.WHITE);
            menuButtonsPanel.setBackground(Color.WHITE);
            employeeButton.setBackground(new Color(245, 245, 245));
            moviesButton.setBackground(new Color(245, 245, 245));
            buttonTitleMov.setForeground(fgColor);
            title.setForeground(fgColor);
            userTitle.setForeground(fgColor);
            summaryLbl.setForeground(fgColor);
            managementTitle.setForeground(fgColor);
            buttonTitleMov.setForeground(fgColor);
            buttonTitleEmp.setForeground(fgColor);

            //Empleados
            empTitle.setForeground(fgColor);

            editEmployee.setForeground(fgColor);
            editEmployee.setBackground(bgColor);
            deleteEmployee.setForeground(fgColor);
            deleteEmployee.setBackground(bgColor);
            addEmployee.setForeground(fgColor);
            addEmployee.setBackground(bgColor);

            //peliculas
            moviesLbl.setForeground(fgColor);
            addMov.setBackground(bgColor);
            addMov.setForeground(fgColor);
            deleteMov.setForeground(fgColor);
            deleteMov.setBackground(bgColor);
            editMov.setBackground(bgColor);
            editMov.setForeground(fgColor);

            salesPanel.setBackground(Color.WHITE);
            numEmployees.setForeground(fgColor);
            numMovies.setForeground(fgColor);
            totalSales.setForeground(fgColor);

            salesLbl.setForeground(fgColor);

            menuButton.setBackground(new Color(0xEDF2FA));
            menuButton.setForeground(fgColor);

            salesButton.setBackground(new Color(0xEDF2FA));
            salesButton.setForeground(fgColor);

            darkMode.setBackground(new Color(0xEDF2FA));
            darkMode.setForeground(fgColor);

            exitButton.setBackground(new Color(0x17C3B2));

            try {
                Image darkIcon = ImageIO.read(getClass().getResource("/img/dark.png"));
                darkIcon = darkIcon.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
                darkMode.setIcon(new ImageIcon(darkIcon));

                Image editIcon = ImageIO.read(getClass().getResource("/img/editDark.png"));
                editIcon = editIcon.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
                editEmployee.setIcon(new ImageIcon(editIcon));
                editMov.setIcon(new ImageIcon(editIcon));
            } catch (IOException e){
                System.out.println("error al cargar imagen: " + e.getMessage());
            }

        } else {
            fgColor = new Color(0xE0E0E0);
            leftPanel.setBackground(new Color(0x1E2A47));
            menuPanel.setBackground(new Color(0x1C1C2E));
            mainPanel.setBackground(new Color(0x1C1C2E));
            menuButtonsPanel.setBackground(new Color(0x1C1C2E));

            salesPanel.setBackground(new Color(0x1C1C2E));

            title.setForeground(fgColor);
            userTitle.setForeground(fgColor);

            //Menu
            summaryLbl.setForeground(fgColor);
            numEmployees.setForeground(fgColor);
            numMovies.setForeground(fgColor);
            totalSales.setForeground(fgColor);
            employeeButton.setBackground(new Color(0x2C2C3E));
            moviesButton.setBackground(new Color(0x2C2C3E));
            managementTitle.setForeground(fgColor);
            buttonTitleMov.setForeground(fgColor);
            buttonTitleEmp.setForeground(fgColor);

            //Empleados
            empTitle.setForeground(fgColor);

            editEmployee.setForeground(fgColor);
            editEmployee.setBackground(new Color(0x2C2C3E));
            deleteEmployee.setForeground(fgColor);
            deleteEmployee.setBackground(new Color(0x2C2C3E));
            addEmployee.setForeground(fgColor);
            addEmployee.setBackground(new Color(0x2C2C3E));

            //peliculas
            moviesLbl.setForeground(fgColor);
            addMov.setBackground(new Color(0x2C2C3E));
            addMov.setForeground(fgColor);
            deleteMov.setForeground(fgColor);
            deleteMov.setBackground(new Color(0x2C2C3E));
            editMov.setBackground(new Color(0x2C2C3E));
            editMov.setForeground(fgColor);

            salesLbl.setForeground(fgColor);

            menuButton.setBackground(new Color(0x3A4E84));
            menuButton.setForeground(fgColor);

            salesButton.setBackground(new Color(0x3A4E84));
            salesButton.setForeground(fgColor);

            darkMode.setBackground(new Color(0x3A4E84));
            darkMode.setForeground(fgColor);

            exitButton.setBackground(new Color(0x1ABC9C));

            try {
                Image darkIcon = ImageIO.read(getClass().getResource("/img/brightness.png"));
                darkIcon = darkIcon.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
                darkMode.setIcon(new ImageIcon(darkIcon));

                Image editIcon = ImageIO.read(getClass().getResource("/img/editWhite.png"));
                editIcon = editIcon.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
                editEmployee.setIcon(new ImageIcon(editIcon));
                editMov.setIcon(new ImageIcon(editIcon));
            } catch (IOException e){
                System.out.println("error al cargar imagen: " + e.getMessage());
            }
        }
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JTable getEmpTable(){
        return empTable;
    }

    public JTable getMovTable(){
        return movTable;
    }

    public void setMovTable(JTable movTable) {
        this.movTable = movTable;
    }

    public void setEmpTable(JTable empTable) {
        this.empTable = empTable;
    }

    public void setMainPanel(JPanel mainPanel) {
        this.mainPanel = mainPanel;
    }
}
