package View;

import lib.TextPrompt;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;

public class AdminView extends JPanel {
    private String action;
    private MoviesViewAdmin moviesPanel;

    /*------------Botones------------*/
    //botones de navegacion
    private JButton exitButton;
    private JButton menuButton;
    private JButton salesButton;
    private JButton darkMode;

    //botones del menu
    private JButton employeeButton;
    private JButton moviesButton;

    //paneles del menu
    private EmployeeManagement employeePanel;
    private AddEmployeeForm addEmployeePanel;

    //paneles
    private JPanel mainPanel;
    private JPanel leftPanel;

    private JPanel menuPanel;
    private JPanel menuButtonsPanel;

    private SalesPanel salesPanel;

    /*------------Labels------------*/
    //Menu
    private JLabel title;
    private JLabel userTitle;
    private JLabel buttonTitleEmp;
    private JLabel managementTitle;
    private JLabel buttonTitleMov;
    private JLabel summaryLbl;
    private JLabel numEmployees; //Label donde se mostrara la cantidad de empleados
    private JLabel numMovies; //numero de peliculas disponibles
    private JLabel totalSales; //ventas totales

    /*----------Colores de botones y texto----------*/
    //no muevan aqui paro
    private Color fgColor = new Color(0x2C3E50);
    private Color bgColor = new Color(0xEDF2FA);

    //para los botones
    private Color bgColButtons = new Color(245, 245, 245);

    //---Peliculas


    public AdminView() {
        setLayout(new BorderLayout());
        //aqui wa iniciar las tablas w luego le mueves, shi?, es que me perdi en todo el codigo w es que esta bien grande jajaja we ya bajale a las lineas we no ma-

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
        salesPanel = new SalesPanel();
        employeePanel = new EmployeeManagement();
        moviesPanel = new MoviesViewAdmin();
        addEmployeePanel = new AddEmployeeForm();

        mainPanel.add(menuPanel, "menu");
        mainPanel.add(salesPanel, "ventas");
        mainPanel.add(employeePanel, "empleados");
        mainPanel.add(moviesPanel, "peliculas");
        mainPanel.add(addEmployeePanel, "agregar/editar empleado");

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
        employeeButton.setBackground(bgColButtons);
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

    public void setListeners(ActionListener listener){
        exitButton.addActionListener(listener);
        menuButton.addActionListener(listener);
        salesButton.addActionListener(listener);
        darkMode.addActionListener(listener);
        employeeButton.addActionListener(listener);
        moviesButton.addActionListener(listener);

        moviesPanel.setListeners(listener);
        employeePanel.setListeners(listener);
        addEmployeePanel.setListeners(listener);
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

    //Cambiar modos (dark mode)
    public void setViewMode(String action){
        this.action = action;
        darkMode.setActionCommand(action);

        if (action.equals("Modo Oscuro")){
            fgColor = new Color(0x2C3E50);
            leftPanel.setBackground(new Color(0xDCE9F9));
            bgColButtons = new Color(245, 245, 245);

            //Menu
            menuPanel.setBackground(Color.WHITE);
            mainPanel.setBackground(Color.WHITE);
            menuButtonsPanel.setBackground(Color.WHITE);
            employeeButton.setBackground(bgColButtons);
            moviesButton.setBackground(bgColButtons);
            buttonTitleMov.setForeground(fgColor);
            title.setForeground(fgColor);
            userTitle.setForeground(fgColor);
            summaryLbl.setForeground(fgColor);
            managementTitle.setForeground(fgColor);
            buttonTitleMov.setForeground(fgColor);
            buttonTitleEmp.setForeground(fgColor);

            //peliculas

            salesPanel.setBackground(Color.WHITE);
            numEmployees.setForeground(fgColor);
            numMovies.setForeground(fgColor);
            totalSales.setForeground(fgColor);

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
            } catch (IOException e){
                System.out.println("error al cargar imagen: " + e.getMessage());
            }

        } else {
            fgColor = new Color(0xE0E0E0);
            bgColButtons = new Color(0x2C2C3E);

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
            employeeButton.setBackground(bgColButtons);
            moviesButton.setBackground(bgColButtons);
            managementTitle.setForeground(fgColor);
            buttonTitleMov.setForeground(fgColor);
            buttonTitleEmp.setForeground(fgColor);

            //peliculas

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
            } catch (IOException e){
                System.out.println("error al cargar imagen: " + e.getMessage());
            }
        }
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public AddEmployeeForm getAddEmployeePanel(){
        return addEmployeePanel;
    }

    public EmployeeManagement getEmployeePanel(){
        return employeePanel;
    }

    public void setMainPanel(JPanel mainPanel) {
        this.mainPanel = mainPanel;
    }
}
