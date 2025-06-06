package View.Admin;

import Models.Employee;
import Models.Movie;
import Models.Ticket;
import View.Admin.EmployeeManagement.EmployeeForm;
import View.Admin.EmployeeManagement.EmployeeMain;
import View.Admin.MovieManagement.MovieForm;
import View.Admin.MovieManagement.MoviesMain;
import View.Admin.RoomManagement.RoomsForm;
import View.Admin.RoomManagement.RoomsMain;
import View.Admin.SalesManagement.SalesMain;
import View.Admin.ShowtimesManagement.ShowtimesForm;
import View.Admin.ShowtimesManagement.ShowtimesMain;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;

import static utils.CreateComponents.createButton;
import static utils.CreateComponents.createJLabel;

public class AdminView extends JPanel {
    private String action;

    /*------------Botones------------*/
    //botones de navegacion
    private JButton exitButton;
    private JButton menuButton;
    private JButton salesButton;

    //botones del menu
    private JButton employeeButton;
    private JButton moviesButton;
    private JButton showtimesButton;
    private JButton roomsButton;

    //Vistas
    private EmployeeMain employeePanel;
    private EmployeeForm employeeFormPanel;
    private MoviesMain moviesPanel;
    private MovieForm movieForm;
    private ShowtimesMain showtimesPanel;
    private SalesMain salesMain;
    private ShowtimesForm showtimesFormPanel;
    private RoomsMain roomsPanel;
    private RoomsForm roomsFormPanel;

    //paneles
    private JPanel mainPanel;
    private JPanel leftPanel;

    private JPanel menuPanel;
    private JPanel menuButtonsPanel;

    /*------------Labels------------*/
    //Menu
    private JLabel title;
    private JLabel userTitle;
    private JLabel buttonTitleEmp;
    private JLabel managementTitle;
    private JLabel buttonTitleMov;
    private JLabel buttonTitleShowtime;
    private JLabel buttonTitleRooms;
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

        /*-----------Panel central-----------*/
        mainPanel = new JPanel();
        mainPanel.setLayout(new CardLayout());
        mainPanel.setBackground(Color.WHITE);
        add(mainPanel, BorderLayout.CENTER);

        menuPanel = new JPanel();
        salesMain = new SalesMain();
        employeePanel = new EmployeeMain();
        moviesPanel = new MoviesMain();
        employeeFormPanel = new EmployeeForm();
        movieForm = new MovieForm();
        showtimesPanel = new ShowtimesMain();
        showtimesFormPanel = new ShowtimesForm();
        roomsPanel = new RoomsMain();
        roomsFormPanel = new RoomsForm();

        mainPanel.add(menuPanel, "menu");
        mainPanel.add(salesMain, "ventas");
        mainPanel.add(employeePanel, "empleados");
        mainPanel.add(moviesPanel, "peliculas");
        mainPanel.add(employeeFormPanel, "agregar/editar empleado");
        mainPanel.add(movieForm, "agregar/editar pelicula");
        mainPanel.add(showtimesPanel, "funciones");
        mainPanel.add(showtimesFormPanel, "agregar/editar funcion");
        mainPanel.add(roomsPanel, "salas");
        mainPanel.add(roomsFormPanel, "agregar/editar sala");

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

        numEmployees = createJLabel("Cantidad de empleados: " + Employee.getNumEmployees(), 15, true);
        numEmployees.setForeground(fgColor);
        numEmployees.setAlignmentX(Component.LEFT_ALIGNMENT);
        numEmployees.setBorder(BorderFactory.createEmptyBorder(30, 40, 0, 0));
        menuPanel.add(numEmployees);

        numMovies = createJLabel("Peliculas disponibles: " + Movie.getNumMovies(), 15, true);
        numMovies.setForeground(fgColor);
        numMovies.setAlignmentX(Component.LEFT_ALIGNMENT);
        numMovies.setBorder(BorderFactory.createEmptyBorder(10, 40, 0, 0));
        menuPanel.add(numMovies);

        totalSales = createJLabel("Ventas: $" + Ticket.getTotalSales(), 15, true);
        totalSales.setForeground(fgColor);
        totalSales.setAlignmentX(Component.LEFT_ALIGNMENT);
        totalSales.setBorder(BorderFactory.createEmptyBorder(10, 40, 0, 0));
        menuPanel.add(totalSales);

        managementTitle = createJLabel("Gestion", 20, true);
        managementTitle.setForeground(fgColor);
        managementTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        managementTitle.setBorder(BorderFactory.createEmptyBorder(50, 40, 0, 0));
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

        employeeButton = createButton("", 100, 100);
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

        moviesButton = createButton("", 100, 100);
        moviesButton.setBackground(bgColButtons);
        moviesButton.setActionCommand("Peliculas");
        menuButtonsPanel.add(moviesButton, c);

        buttonTitleMov = createJLabel("Agregar Peliculas", 15, true);
        buttonTitleMov.setForeground(fgColor);

        c.gridy = 1;
        c.gridx = 2;
        c.weightx = 3;
        c.weighty = 0;
        c.insets = new Insets(0, 150, 80, 10);
        c.anchor = GridBagConstraints.WEST;
        c.fill = GridBagConstraints.NONE;

        menuButtonsPanel.add(buttonTitleMov, c);

        showtimesButton = createButton("", 100, 100);
        showtimesButton.setBackground(bgColButtons);
        showtimesButton.setActionCommand("Funciones");

        c.gridy = 2;
        c.gridx = 0;
        c.weightx = 0;
        c.weighty = 0;
        c.insets = new Insets(0, 40, 0, 10);
        c.anchor = GridBagConstraints.NORTHWEST;
        c.fill = GridBagConstraints.NONE;

        menuButtonsPanel.add(showtimesButton, c);

        buttonTitleShowtime = createJLabel("Agendar Funciones", 15, true);
        buttonTitleShowtime.setForeground(fgColor);

        c.gridy = 2;
        c.gridx = 1;
        c.weightx = 1;
        c.weighty = 0;
        c.insets = new Insets(0, 10, 0, 10);
        c.anchor = GridBagConstraints.WEST;
        c.fill = GridBagConstraints.NONE;

        menuButtonsPanel.add(buttonTitleShowtime, c);

        roomsButton = createButton("", 100, 100);
        roomsButton.setBackground(bgColButtons);
        roomsButton.setActionCommand("Salas");

        c.gridy = 2;
        c.gridx = 2;
        c.weightx = 0;
        c.weighty = 0;
        c.insets = new Insets(0, 30, 0, 10);
        c.anchor = GridBagConstraints.NORTHWEST;
        c.fill = GridBagConstraints.NONE;

        menuButtonsPanel.add(roomsButton, c);

        buttonTitleRooms = createJLabel("Gestionar Salas", 15, true);
        buttonTitleRooms.setForeground(fgColor);

        c.gridy = 2;
        c.gridx = 3;
        c.weightx = 1;
        c.weighty = 0;
        c.insets = new Insets(0, -420, 0, 10);
        c.anchor = GridBagConstraints.WEST;
        c.fill = GridBagConstraints.NONE;

        menuButtonsPanel.add(buttonTitleRooms, c);

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
        menuButton = createButton("Menu", 150, 40);
        menuButton.setFont(new Font("Helvetica Neue", Font.BOLD, 20));
        menuButton.setActionCommand("Menu");
        menuButton.setBackground(bgColor);
        menuButton.setForeground(fgColor);
        menuButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        leftPanel.add(Box.createVerticalStrut(50));
        leftPanel.add(menuButton);

        salesButton = createButton("Ventas", 150, 40);
        salesButton.setFont(new Font("Helvetica Neue", Font.BOLD, 20));
        salesButton.setActionCommand("Ventas");
        salesButton.setBackground(new Color(0xEDF2FA));
        salesButton.setForeground(fgColor);
        salesButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        leftPanel.add(Box.createVerticalStrut(30));
        leftPanel.add(salesButton);

        exitButton = createButton("", 150, 40);
        exitButton.setActionCommand("Salir");
        exitButton.setBackground(new Color(0x17C3B2));
        exitButton.setForeground(Color.WHITE);
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        leftPanel.add(Box.createVerticalStrut(430));
        leftPanel.add(exitButton);

        //Iconos en botones
        try {
            Image exitIcon = ImageIO.read(getClass().getResource("/img/exit.png"));
            exitIcon = exitIcon.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            exitButton.setIcon(new ImageIcon(exitIcon));

            Image userIcon = ImageIO.read(getClass().getResource("/img/employee.png"));
            userIcon = userIcon.getScaledInstance(70, 70, Image.SCALE_SMOOTH);
            employeeButton.setIcon(new ImageIcon(userIcon));

            Image moviesIcon = ImageIO.read(getClass().getResource("/img/movies.png"));
            moviesIcon = moviesIcon.getScaledInstance(70, 70, Image.SCALE_SMOOTH);
            moviesButton.setIcon(new ImageIcon(moviesIcon));

            Image showtimesIcon = ImageIO.read(getClass().getResource("/img/showtimes.png"));
            showtimesIcon = showtimesIcon.getScaledInstance(70, 70, Image.SCALE_SMOOTH);
            showtimesButton.setIcon(new ImageIcon(showtimesIcon));

            Image roomsIcon = ImageIO.read(getClass().getResource("/img/rooms.png"));
            roomsIcon = roomsIcon.getScaledInstance(70, 70, Image.SCALE_SMOOTH);
            roomsButton.setIcon(new ImageIcon(roomsIcon));
        } catch (IOException e){
            System.out.println("error al cargar imagen: " + e.getMessage());
        }
    }

    public void setListeners(ActionListener listener){
        exitButton.addActionListener(listener);
        menuButton.addActionListener(listener);
        salesButton.addActionListener(listener);
        employeeButton.addActionListener(listener);
        moviesButton.addActionListener(listener);
        showtimesButton.addActionListener(listener);
        roomsButton.addActionListener(listener);

        moviesPanel.setListeners(listener);
        employeePanel.setListeners(listener);
        employeeFormPanel.setListeners(listener);
        movieForm.setListeners(listener);
        showtimesPanel.setListeners(listener);
        showtimesFormPanel.setListeners(listener);
        roomsPanel.setListeners(listener);
        roomsFormPanel.setListeners(listener);
        salesMain.setListeners(listener);
    }

    public void updateNumEmployees(){
        numEmployees.setText("Cantidad de empleados: " + Employee.getNumEmployees());
    }
    public void updateNumMovies(){numMovies.setText("Peliculas disponibles: " + Movie.getNumMovies());}
    public void updateTotalSales(){totalSales.setText("Ventas: $" + Ticket.getTotalSales());}

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public EmployeeForm getEmployeeFormPanel(){
        return employeeFormPanel;
    }

    public EmployeeMain getEmployeePanel(){
        return employeePanel;
    }

    public MovieForm getMovieForm(){
        return movieForm;
    }

    public ShowtimesForm getShowtimesFormPanel(){
        return showtimesFormPanel;
    }

    public ShowtimesMain getShowtimesPanel(){
        return showtimesPanel;
    }

    public SalesMain getSalesMain(){
        return salesMain;
    }

    public RoomsForm getRoomsFormPanel(){
        return roomsFormPanel;
    }

    public void setMainPanel(JPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    public MoviesMain getMoviesViewAdminPanel(){
        return moviesPanel;
    }

    public RoomsMain getRoomsView(){
        return roomsPanel;
    }

}
