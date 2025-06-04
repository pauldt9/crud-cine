package Controller;

import Models.Employee;
import Models.EmployeeTableModel;
import Models.Movie;
import Models.MoviesTableModel;
import View.LoginPanel;
import View.AdminView;
import View.MoviesViewAdmin;
import utils.ImageService;
import utils.PasswordUtils;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;

public class AdminController implements ActionListener {
    private LoginPanel loginPanel;
    private JFrame frame;
    private AdminView adminView;
    private MoviesViewAdmin moviesViewAdmin;

    private ArrayList<Employee> employees;
    private EmployeeTableModel empTable;

    private ArrayList<Movie> movies;
    private MoviesTableModel movTable;
    private String finalPath = null;

    public AdminController(LoginPanel loginPanel, JFrame frame, AdminView adminView){
        this.loginPanel = loginPanel;
        this.frame = frame;
        this.adminView = adminView;
        this.adminView.setListeners(this);

        empTable = adminView.getEmployeePanel().getTableModelEmployees();
        movTable = adminView.getMoviesViewAdminPanel().getMoviesTableModel();

        adminView.getEmployeePanel().tableListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    adminView.getEmployeePanel().removeTableSelection();
                }
            }
        });

        adminView.getMoviesViewAdminPanel().tableListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    adminView.getMoviesViewAdminPanel().removeTableSelection();
                }
            }
        });

        employees = new ArrayList<>();
        movies = new ArrayList<>();
        loadEmployees();
        loadMovies();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command) {
            case "Salir":
                System.out.println("ha salido");
                exit();
                break;
            case "Menu":
                System.out.println("menu");
                showAdminPanel("menu");
                break;
            case "Ventas":
                System.out.println("ventas");
                showAdminPanel("ventas");
                break;
            case "Modo Oscuro":
                System.out.println("cambiar a oscuro");
                adminView.setViewMode("Modo Claro");
                break;
            case "Modo Claro":
                System.out.println("cambiar a claro");
                adminView.setViewMode("Modo Oscuro");
                break;
            //Acciones Empleados
            case "Empleados":
                System.out.println("empleados");
                showAdminPanel("empleados");
                break;
            case "Agregar empleado":
                System.out.println("agregar empleado");
                adminView.getEmployeeFormPanel().clearFields();
                showAdminPanel("agregar/editar empleado");
                adminView.getEmployeeFormPanel().setAction("Registrar");
                break;
            case "Editar empleado":
                System.out.println("editar empleado");
                if (adminView.getEmployeePanel().getEmployeesTable().getSelectedRow() == -1) {
                    JOptionPane.showMessageDialog(adminView, "Por favor selecciona una fila");
                    break;
                }

                showAdminPanel("agregar/editar empleado");
                adminView.getEmployeeFormPanel().setAction("Editar");
                fillFieldsEmp();
                break;
            case "Eliminar empleado":
                System.out.println("borrar empleado");

                deleteEmployee();
                loadEmployees();

                break;
            case "Confirmar empleado":
                System.out.println("se ha agregado un usuario nuevo");
                adminView.getEmployeeFormPanel().setAction("Registrar");
                addEmployee();
                adminView.getEmployeeFormPanel().clearFields();
                break;
            case "Confirmar cambios de empleado":
                adminView.getEmployeeFormPanel().setAction("Editar");
                saveChangesEmployee();
                loadEmployees();
                showAdminPanel("empleados");
                break;
            case "Regresar empleado":
                System.out.println("El usuario se ha regresado al apartado empleado");
                showAdminPanel("empleados");
                break;
            //Acciones Peliculas
            case "Peliculas":
                System.out.println("peliculas");
                showAdminPanel("peliculas");
                break;
            case "Regresar pelicula":
                System.out.println("se ha regresado a pelicula");
                showAdminPanel("peliculas");
                break;
            case "Eliminar pelicula":
                System.out.println("eliminar pelicula");
                deleteMovie();
                loadMovies();
                break;
            case "Editar pelicula":
                System.out.println("editar pelicula");
                if (adminView.getMoviesViewAdminPanel().getMoviesTable().getSelectedRow() == -1) {
                    JOptionPane.showMessageDialog(adminView, "Por favor selecciona una fila");
                    break;
                }
                adminView.getMovieForm().setAction("Editar");
                showAdminPanel("agregar/editar pelicula");
                fillFieldsMovie();

                break;
            case "Agregar pelicula":
                System.out.println("agregar pelicula");
                adminView.getMovieForm().setAction("Agregar");
                showAdminPanel("agregar/editar pelicula");


                break;
            case "Agregar imagen":
                System.out.println("se ha agregado una imagen");
                String route = chooseImage();

                if (route != null) {
                    finalPath = route;
                    JOptionPane.showMessageDialog(frame, "Imagen agregada correctamente");
                }
                break;
            case "Confirmar pelicula":
                System.out.println("se ha agregado una pelicula");
                adminView.getMovieForm().setAction("Agregar");
                addMovie();
                showAdminPanel("peliculas");
                break;
            case "Confirmar cambios de pelicula":
                System.out.println("se ha editado la pelicula");
                adminView.getMovieForm().setAction("Editar");
                saveChangesMovie();
                showAdminPanel("peliculas");
                loadMovies();
                break;
            case "Funciones":
                System.out.println("funciones..");
                showAdminPanel("funciones");
                break;
            case "Agregar funcion":
                System.out.println("agregar funcion");
                adminView.getShowtimesFormPanel().setAction("Agregar");
                showAdminPanel("agregar/editar funcion");
                break;
            case "Editar funcion":
                System.out.println("editar funcion");
                adminView.getShowtimesFormPanel().setAction("Editar");
                showAdminPanel("agregar/editar funcion");
                break;
            case "Eliminar funcion":
                System.out.println("eliminar funcion");
                break;
            case "Regresar funcion":
                System.out.println("regresar a la pestaña funcion");
                showAdminPanel("funciones");
                break;
            case "Confirmar funcion":
                System.out.println("funcion agregada");
                adminView.getShowtimesFormPanel().setAction("Agregar");
                showAdminPanel("funciones");

                break;
            case "Confirmar cambios de funcion":
                System.out.println("cambios guardados de funcion");
                adminView.getShowtimesFormPanel().setAction("Editar");
                showAdminPanel("funciones");
                break;
            case "Salas":
                System.out.println("salas");
                showAdminPanel("salas");
                break;
            case "Agregar sala":
                System.out.println("agregando sala");
                adminView.getRoomsFormPanel().setAction("Agregar");
                showAdminPanel("agregar/editar sala");
                break;
            case "Editar sala":
                System.out.println("editar sala");
                adminView.getRoomsFormPanel().setAction("Editar");
                showAdminPanel("agregar/editar sala");
                break;
            case "Eliminar sala":
                System.out.println("eliminar sala");
                break;
            case "Regresar sala":
                System.out.println("regresando a sala");
                showAdminPanel("salas");
                break;
            case "Confirmar sala":
                System.out.println("confirmando sala");
                adminView.getRoomsFormPanel().setAction("Agregar");
                showAdminPanel("salas");
                break;
            case "Confirmar cambios de sala":
                System.out.println("confirmando cambios de sala");
                adminView.getRoomsFormPanel().setAction("Editar");
                showAdminPanel("salas");
                break;
        }
    }

    private String chooseImage() {
        JFileChooser chooser = new JFileChooser();
        chooser.setAcceptAllFileFilterUsed(false);
        chooser.addChoosableFileFilter(new FileNameExtensionFilter("Imagenes", "png", "jpg", "JPEG"));
        int result = chooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            String fileName = file.getName();

            String route = ImageService.saveImage(file, fileName);

            if (route != null) {
                return route;
            } else {
                JOptionPane.showMessageDialog(null, "Error al guardar la imagen.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Debes seleccionar una imagen.");
        }

        return null;
    }


    public void fillFieldsMovie() {
        int selectedRow = adminView.getMoviesViewAdminPanel().getMoviesTable().getSelectedRow();
        int movieId = movTable.getRowData(selectedRow).getIdMovie();

        Movie movie = Movie.getMovieById(movieId);
        if (movie != null) {
            adminView.getMoviesViewAdminPanel().setIdMovie(movie.getIdMovie());
            adminView.getMovieForm().setAddMovieTitle(movie.getTitle());
            adminView.getMovieForm().setAddDuration(String.valueOf(movie.getDuration()));
            adminView.getMovieForm().setAddGenre(movie.getGenre());
            adminView.getMovieForm().setAddClassification(movie.getClassification());
            finalPath = movie.getImgRoute();
        } else {
            System.out.println("No se encontró la película en la base de datos.");
        }
    }

    private void deleteMovie() {
        int selectedRow = adminView.getMoviesViewAdminPanel().getMoviesTable().getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(adminView, "Debes seleccionar una pelicula de la tabla");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
                adminView,
                "¿Esta seguro de que desea eliminar esta pelicula?",
                "Confirmar eliminacion",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            int id = movies.get(selectedRow).getIdMovie();

            boolean deleted = Movie.deleteMovie(id);
            if (deleted) {
                JOptionPane.showMessageDialog(adminView, "Pelicula eliminada correctamente");
                showMovies();
            } else {
                JOptionPane.showMessageDialog(adminView, "No se pudo eliminar la pelicula");
            }
        }
    }

    private void saveChangesMovie() {
        if (!validateFormMovie()) return;

        try {
            Movie movie = createMovie();

            int rowIndex = movTable.getRowById(movie.getIdMovie());
            System.out.println("ID: " + movie.getIdMovie());
            if (rowIndex == -1) {
                JOptionPane.showMessageDialog(frame, "pelicula no encontrada", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (Movie.updateMovie(movie)) {
                movTable.setRowData(rowIndex, movie);
                adminView.getMovieForm().clearFields();
                System.out.println("se han efectuado los cambios");
                JOptionPane.showMessageDialog(frame, "Se han efectuado los cambios");
            } else {
                JOptionPane.showMessageDialog(frame, "No se pudo actualizar la pelicula");
            }

        }catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Error al guardar cambios: " + "La pelicula ya existe. Intenta con otra.","Error", JOptionPane.ERROR_MESSAGE);
        }finally {
            finalPath = null;
        }
    }

    private void showMovies() {
        movTable.cleanTable();
        for (Movie m : movies) {
            movTable.addRow(m);
        }
    }


    private void loadMovies() {
        movies = Movie.getMovies();
        showMovies();
    }


    public Movie createMovie() {
        String title = adminView.getMovieForm().getAddMovieTitle().getText();
        int duration = Integer.parseInt(adminView.getMovieForm().getAddDuration().getText());
        String genre = (String)adminView.getMovieForm().getAddGenre().getSelectedItem();
        String classification = (String)adminView.getMovieForm().getAddClassification().getSelectedItem();
        String imgRoute = finalPath;

        return new Movie(adminView.getMoviesViewAdminPanel().getIdMovie(),title,duration,genre,classification, imgRoute);
    }

    public void addMovie() {
        if (!validateFormMovie()) return;

        Movie movie = createMovie();
        try {
            int idMovie = Movie.addMovie(movie);

            if (idMovie > 0) {
                movie.setIdMovie(idMovie);
                movies.add(movie);
                movTable.addRow(movie);
                showAdminPanel("Peliculas");
                JOptionPane.showMessageDialog(frame, "Se ha agregado una pelicula nueva");
            } else {
                JOptionPane.showMessageDialog(frame, "No se pudo crear la pelicula");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame,
                    "La pelicula ya existe. Intenta con otra.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }finally {
            finalPath = null;
        }
    }

    private void deleteEmployee() {
        int selectedRow = adminView.getEmployeePanel().getEmployeesTable().getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(adminView, "Debes seleccionar un usuario de la tabla");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
                adminView,
                "¿Esta seguro de que desea eliminar este usuario?",
                "Confirmar eliminacion",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            int id = employees.get(selectedRow).getIdEmployee();

            boolean deleted = Employee.deleteEmployee(id);
            if (deleted) {
                JOptionPane.showMessageDialog(adminView, "Usuario eliminado correctamente");
                showEmployees();
            } else {
                JOptionPane.showMessageDialog(adminView, "No se pudo eliminar el usuario");
            }
        }
    }

    private void saveChangesEmployee() {
        if (!validateFormEmployee()) return;

        try {
            Employee employee = createEmployee();

            String plainPassword = adminView.getEmployeeFormPanel().getEmpPass();

            int rowIndex = empTable.getRowById(employee.getIdEmployee());
            if (rowIndex == -1) {
                JOptionPane.showMessageDialog(frame, "Empleado no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Employee original = empTable.getRowData(rowIndex);
            String currentHashedPassword = original.getPassword();

            System.out.println("ID: "+ employee.getIdEmployee());
            if (PasswordUtils.checkPassword(plainPassword,currentHashedPassword )) {

                employee.setPassword(PasswordUtils.hashPassword(plainPassword));

                if (Employee.updateEmployee(employee)) {
                    empTable.setRowData(rowIndex, employee);
                    adminView.getEmployeeFormPanel().clearFields();
                    System.out.println("se han efectuado los cambios");
                    JOptionPane.showMessageDialog(frame, "Se han efectuado los cambios");
                } else {
                    JOptionPane.showMessageDialog(frame, "No se pudo actualizar el usuario");
                }
            }else{
                JOptionPane.showMessageDialog(frame, "Contraseña incorrecta","Error", JOptionPane.ERROR_MESSAGE);
                adminView.getEmployeeFormPanel().setAddEmpPass("");
                adminView.getEmployeeFormPanel().setAddEmpConfirmPass("");
            }
        }catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Error al guardar cambios: " + "El nombre de usuario ya existe. Intenta con otro.","Error", JOptionPane.ERROR_MESSAGE);
            adminView.getEmployeeFormPanel().setAddEmpPass("");
            adminView.getEmployeeFormPanel().setAddEmpConfirmPass("");
        }
    }

    public void fillFieldsEmp(){
        Employee employee = empTable.getRowData(adminView.getEmployeePanel().getEmployeesTable().getSelectedRow());
        adminView.getEmployeePanel().setIdEmployee(employee.getIdEmployee());
        adminView.getEmployeeFormPanel().setAddEmpName(employee.getFirstName());
        adminView.getEmployeeFormPanel().setAddEmpLastName(employee.getLastName());
        adminView.getEmployeeFormPanel().setEmpType(employee.getEmployeeType());
        adminView.getEmployeeFormPanel().setEmpUser(employee.getUsername());
    }

    // Crea el objeto empleado que se usara en el metodo addEmployee
    public Employee createEmployee() {
        String name = adminView.getEmployeeFormPanel().getEmpName();
        String lastName = adminView.getEmployeeFormPanel().getEmpLastName();
        String employeeType = (String)adminView.getEmployeeFormPanel().getEmpType().getSelectedItem();
        String empUsername = adminView.getEmployeeFormPanel().getEmpUser();
        String empPassword = adminView.getEmployeeFormPanel().getEmpPass();

        return new Employee(adminView.getEmployeePanel().getIdEmployee(),name,lastName,employeeType,empUsername,empPassword);
    }


    //agrega al empleado a la base de datos y a la tabla
    public void addEmployee() {
        if (!validateFormEmployee()) return;

        Employee emp = createEmployee();
        try {
            int idUser = Employee.addEmployee(emp);

            if (idUser > 0) {
                emp.setIdEmployee(idUser);
                employees.add(emp);
                empTable.addRow(emp);
                showAdminPanel("Empleados");
                JOptionPane.showMessageDialog(frame, "Se ha agregado un empleado nuevo");
            } else {
                JOptionPane.showMessageDialog(frame, "No se pudo crear el empleado");
            }

        } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame,
                        "El nombre de usuario ya existe. Intenta con otro.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }

    //obtiene los empleados
    private void showEmployees() {
        empTable.cleanTable();
        for (Employee e : employees) {
            empTable.addRow(e);
        }
    }

    //carga los empleados en la tabla
    private void loadEmployees() {
        employees = Employee.getEmployees();
        showEmployees();
    }

    //valida que los campos esten llenos
    public boolean validateFormEmployee(){
        if (adminView.getEmployeeFormPanel().getEmpName().isBlank() ||
            adminView.getEmployeeFormPanel().getEmpLastName().isBlank()||
            adminView.getEmployeeFormPanel().getEmpType().getSelectedIndex() == 0 ||
            adminView.getEmployeeFormPanel().getEmpUser().isBlank() ||
            adminView.getEmployeeFormPanel().getEmpPass().isBlank() ||
            adminView.getEmployeeFormPanel().getEmpPassConfirm().isBlank()) {
            JOptionPane.showMessageDialog(frame, "Los campos no pueden estar vacíos.",
                    "Campos vacíos", JOptionPane.ERROR_MESSAGE);
            return false;
        } else if (!adminView.getEmployeeFormPanel().validatePassField()) {
            JOptionPane.showMessageDialog(frame, "Los campos de contraseña no coinciden.");
            return false;
        }
        return true;
    }

    public boolean validateFormMovie() {

        String durationField = adminView.getMovieForm().getAddDuration().getText();
        try {
            int duration = Integer.parseInt(durationField);
            if (duration <= 0) {
                JOptionPane.showMessageDialog(frame, "La duración debe ser mayor a cero", "Error", JOptionPane.ERROR_MESSAGE);
                System.out.println("Se llego");
                return false;

            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "El campo duración debe ser numérico", "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("Se llego");
            return false;
        }


        if (adminView.getMovieForm().getMovieTitle().isBlank() ||
            adminView.getMovieForm().getDuration() == 0 ||
            adminView.getMovieForm().getGenre().getSelectedIndex() == 0 ||
            adminView.getMovieForm().getClassification().getSelectedIndex() == 0 ||
            finalPath == null ) {

            JOptionPane.showMessageDialog(frame, "Los campos no pueden estar vacíos.",
                    "Campos vacíos", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;

    }


    public void exit(){
        int answer = JOptionPane.showConfirmDialog(frame, "¿Estas seguro de cerrar sesion?", "Salir", JOptionPane.YES_NO_OPTION);

        if (answer == JOptionPane.YES_OPTION){
            frame.remove(adminView);
            frame.add(loginPanel);
            frame.repaint();
            frame.revalidate();
            frame.setTitle("Ingresar credenciales");
        }
    }

    //con este metodo se cambia las pestañas del admin
    public void showAdminPanel(String namePanel){
        CardLayout card = (CardLayout) (adminView.getMainPanel().getLayout());
        card.show(adminView.getMainPanel(), namePanel);
    }




}
