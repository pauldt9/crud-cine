package Controller;

import Models.*;
import View.Employee.EmployeeView;
import View.LoginPanel;
import View.Admin.AdminView;
import View.Admin.MovieManagement.MoviesMain;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.DeviceGray;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
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
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class AdminController implements ActionListener {
    private LoginPanel loginPanel;
    private JFrame frame;
    private AdminView adminView;
    private EmployeeView employeeView;
    private MoviesMain moviesMain;

    private ArrayList<Employee> employees;
    private EmployeeTableModel empTable;

    private ArrayList<Movie> movies;
    private MoviesTableModel movTable;
    private String finalRoute = null;

    private ArrayList<Room> rooms;
    private RoomsTableModel roomsTable;

    private ArrayList<MovieShowtime> showtimes;
    private MovieShowtimeTableModel showtimeTable;

    private ArrayList<Ticket> tickets;
    private SalesTableModel salesTable;

    public AdminController(LoginPanel loginPanel, JFrame frame, AdminView adminView, EmployeeView employeeView){
        this.loginPanel = loginPanel;
        this.frame = frame;
        this.adminView = adminView;
        this.adminView.setListeners(this);
        this.employeeView = employeeView;

        empTable = adminView.getEmployeePanel().getTableModelEmployees();
        movTable = adminView.getMoviesViewAdminPanel().getMoviesTableModel();
        roomsTable =  adminView.getRoomsView().getRoomsTableModel();
        showtimeTable = adminView.getShowtimesPanel().getShowtimesTableModel();
        salesTable = adminView.getSalesMain().getSalesTableModel();

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

        adminView.getRoomsView().tableListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    adminView.getRoomsView().removeTableSelection();
                }
            }
        });

        employees = new ArrayList<>();
        movies = new ArrayList<>();
        rooms = new ArrayList<>();
        showtimes = new ArrayList<>();
        tickets = new ArrayList<>();
        loadEmployees();
        loadMovies();
        loadRooms();
        loadShowtimes();
        loadTickets();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command) {
            case "Salir":
                System.out.println("ha salido");
                exit();
                loginPanel.clearFields();
                break;
            case "Menu":
                System.out.println("menu");
                showAdminPanel("menu");
                break;
            case "Ventas":
                System.out.println("ventas");
                showAdminPanel("ventas");
                break;
//--------------------------Empleados
            case "Empleados":
                System.out.println("empleados");
                showAdminPanel("empleados");
                break;
            case "Agregar empleado":
                System.out.println("agregar empleado");
                adminView.getEmployeeFormPanel().clearFields();
                showAdminPanel("agregar/editar empleado");
                adminView.getEmployeeFormPanel().setAction("Registrar");
                adminView.updateNumEmployees();
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
                adminView.updateNumEmployees();
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
//---------------------------Peliculas
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
                adminView.getShowtimesFormPanel().updateMovieCombo();
                loadMovies();
                break;
            case "Editar pelicula":
                System.out.println("editar pelicula");
                if (adminView.getMoviesViewAdminPanel().getMoviesTable().getSelectedRow() == -1) {
                    JOptionPane.showMessageDialog(adminView, "Por favor selecciona una fila");
                    break;
                }
                adminView.getMovieForm().setAction("Editar");
                fillFieldsMovie();
                showAdminPanel("agregar/editar pelicula");

                break;
            case "Agregar pelicula":
                System.out.println("agregar pelicula");
                adminView.getMovieForm().setAction("Agregar");
                adminView.getMovieForm().clearFields();
                showAdminPanel("agregar/editar pelicula");
                adminView.updateNumMovies();
                break;
            case "Agregar imagen":
                System.out.println("se ha agregado una imagen");
                String route = chooseImage();

                if (route != null) {
                    finalRoute = route;
                    JOptionPane.showMessageDialog(frame, "Imagen agregada correctamente");
                }
                break;
            case "Confirmar pelicula":
                System.out.println("se ha agregado una pelicula");
                adminView.getMovieForm().setAction("Agregar");
                addMovie();
                adminView.getShowtimesFormPanel().updateMovieCombo();
                showAdminPanel("peliculas");
                break;
            case "Confirmar cambios de pelicula":
                System.out.println("se ha editado la pelicula");
                adminView.getMovieForm().setAction("Editar");
                saveChangesMovie();
                adminView.getShowtimesFormPanel().updateMovieCombo();
                showAdminPanel("peliculas");
                loadMovies();
                break;
//--------------------------------funciones
            case "Funciones":
                System.out.println("funciones..");
                showAdminPanel("funciones");
                break;
            case "Agregar funcion":
                System.out.println("agregar funcion");
                adminView.getShowtimesFormPanel().setAction("Agregar");
                adminView.getShowtimesFormPanel().resetComboBox();
                showAdminPanel("agregar/editar funcion");
                break;
            case "Editar funcion":
                System.out.println("editar funcion");
                adminView.getShowtimesFormPanel().setAction("Editar");
                if (adminView.getShowtimesPanel().getShowtimesTable().getSelectedRow() == -1) {
                    JOptionPane.showMessageDialog(adminView, "Por favor selecciona una fila");
                    break;
                }
                showAdminPanel("agregar/editar funcion");
                fillFieldsShowtime();
                break;
            case "Eliminar funcion":
                System.out.println("eliminar funcion");
                deleteShowtime();
                loadShowtimes();
                break;
            case "Regresar funcion":
                System.out.println("regresar a la pestaña funcion");
                adminView.getShowtimesFormPanel().resetComboBox();
                showAdminPanel("funciones");
                break;
            case "Confirmar funcion":
                System.out.println("funcion agregada");
                adminView.getShowtimesFormPanel().setAction("Agregar");
                addShowtime();
                showAdminPanel("funciones");

                break;
            case "Confirmar cambios de funcion":
                System.out.println("cambios guardados de funcion");
                adminView.getShowtimesFormPanel().setAction("Editar");
                saveChangesShowtime();
                loadShowtimes();
                showAdminPanel("funciones");
                break;
//------------------------------salas
            case "Salas":
                System.out.println("salas");
                showAdminPanel("salas");
                break;
            case "Agregar sala":
                adminView.getRoomsFormPanel().clearFields();
                System.out.println("agregando sala");
                adminView.getRoomsFormPanel().setAction("Agregar");
                showAdminPanel("agregar/editar sala");
                break;
            case "Editar sala":
                System.out.println("editar sala");
                if (adminView.getRoomsView().getRoomsTable().getSelectedRow() == -1) {
                    JOptionPane.showMessageDialog(adminView, "Por favor selecciona una fila");
                    break;
                }
                adminView.getRoomsFormPanel().setAction("Editar");
                showAdminPanel("agregar/editar sala");
                fillFieldsRoom();
                break;
            case "Eliminar sala":
                System.out.println("eliminar sala");
                deleteRooms();
                adminView.getShowtimesFormPanel().updateRoomCombo();
                loadRooms();
                break;
            case "Regresar sala":
                System.out.println("regresando a sala");
                showAdminPanel("salas");
                break;
            case "Confirmar sala":
                System.out.println("confirmando sala");
                adminView.getRoomsFormPanel().setAction("Agregar");
                showAdminPanel("salas");
                addRoom();
                adminView.getShowtimesFormPanel().updateRoomCombo();
                break;
            case "Confirmar cambios de sala":
                System.out.println("confirmando cambios de sala");
                adminView.getRoomsFormPanel().setAction("Editar");
                saveChangesRoom();
                adminView.getShowtimesFormPanel().updateRoomCombo();
                showAdminPanel("salas");
                loadRooms();
                break;
            case "Eliminar venta":
                System.out.println("venta eliminada");
                deleteSale();
                loadTickets();
                break;


            case "Generar pdf":
                System.out.println("generando pdf");
                generatePDF();
                break;
        }
    }

    public void deleteSale(){
        int selectedRow = adminView.getSalesMain().getSalesTable().getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(adminView, "Debes seleccionar una venta de la tabla");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
                adminView,
                "¿Esta seguro de que desea eliminar esta venta?",
                "Confirmar eliminacion",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            int id = tickets.get(selectedRow).getIdTicket();
            int idSeat = tickets.get(selectedRow).getIdSeat();
            int idFunction = tickets.get(selectedRow).getFunction().getIdShowtime();

            boolean deleted = Ticket.deleteTicket(id);
            boolean deleted2 = OccupiedSeats.cancelReservation(idSeat, idFunction);
            if (deleted && deleted2) {
                JOptionPane.showMessageDialog(adminView, "Venta eliminada correctamente");
                showShowtimes();
            } else {
                JOptionPane.showMessageDialog(adminView, "No se pudo eliminar la venta");
            }
        }
    }

    private void loadTickets(){
        tickets = Ticket.getTickets();
        showTickets();
    }

    private void showTickets(){
        salesTable.clearTable();

        for (Ticket ticket : tickets){
            salesTable.addRow(ticket);
        }
    }

    public void fillFieldsShowtime(){
        int selectedRow = adminView.getShowtimesPanel().getShowtimesTable().getSelectedRow();
        int idShowtime = showtimeTable.getRowData(selectedRow).getIdShowtime();

        MovieShowtime showtime = MovieShowtime.getShowtimes(idShowtime);

        Movie movie = Movie.getMovieById(showtime.getIdMovie());
        Room room = Room.getRoom(showtime.getIdRoom());

        adminView.getShowtimesPanel().setIdShowtime(showtime.getIdShowtime());
        adminView.getShowtimesFormPanel().setAddMovieTitle(movie.getTitle());
        adminView.getShowtimesFormPanel().setAddRoom(room.getRoomName());
        adminView.getShowtimesFormPanel().setAddShowtime(showtime.getShowTime());
    }

    public void addShowtime(){
        if (!validateFormShowtimes()) return;
        MovieShowtime showtime = createShowtime();

        if (!MovieShowtime.isShowtimeAvailable(showtime.getShowTime(), showtime.getIdRoom())){
            JOptionPane.showMessageDialog(frame, "No puedes repetir la misma sala con el mismo horario mas de 1 vez");
            return;
        }

        try {
            int idShowtime = MovieShowtime.addShowtimes(showtime);
            System.out.println(idShowtime);
            showtime.setIdShowtime(idShowtime);


            if (idShowtime > 0){
                showtimes.add(showtime);
                showtimeTable.addRow(showtime);
                loadMovies();
                JOptionPane.showMessageDialog(frame, "Se ha agregado una funcion");
            }
        } catch (Exception e){
            JOptionPane.showMessageDialog(frame,
                    "Error: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }

        adminView.getShowtimesFormPanel().resetComboBox();
    }

    public void saveChangesShowtime(){
        if (!validateFormShowtimes()) return;

        MovieShowtime showtime = createShowtime();

        if (!MovieShowtime.isShowtimeAvailable(showtime.getShowTime(), showtime.getIdRoom())){
            JOptionPane.showMessageDialog(frame, "No puedes repetir la misma sala con el mismo horario mas de 1 vez");
            return;
        }

        try {
            int rowIndex = showtimeTable.getRowById(showtime.getIdShowtime());

            if (rowIndex == -1) {
                JOptionPane.showMessageDialog(frame, "Funcion no encontrada", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (MovieShowtime.updateShowtime(showtime)) {
                showtimeTable.setRowData(rowIndex, showtime);
                adminView.getShowtimesFormPanel().resetComboBox();
                System.out.println("se han efectuado los cambios");
                JOptionPane.showMessageDialog(frame, "Se han efectuado los cambios");
            } else {
                JOptionPane.showMessageDialog(frame, "No se pudo actualizar el usuario");
            }

        }catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Error al guardar cambios: " + ex.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
            adminView.getShowtimesFormPanel().resetComboBox();
        }

        adminView.getShowtimesFormPanel().resetComboBox();
    }

    public void deleteShowtime(){
        int selectedRow = adminView.getShowtimesPanel().getShowtimesTable().getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(adminView, "Debes seleccionar una funcion de la tabla");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
                adminView,
                "¿Esta seguro de que desea eliminar esta funcion?",
                "Confirmar eliminacion",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            int id = showtimes.get(selectedRow).getIdShowtime();

            boolean deleted = MovieShowtime.deleteShowtime(id);
            if (deleted) {
                JOptionPane.showMessageDialog(adminView, "Funcion eliminado correctamente");
                showShowtimes();
            } else {
                JOptionPane.showMessageDialog(adminView, "No se pudo eliminar la funcion");
            }
        }
    }

    public MovieShowtime createShowtime(){
        String selectedMovieTitle = (String) adminView.getShowtimesFormPanel().getAddMovieTitle().getSelectedItem();
        String selectedRoomName = (String) adminView.getShowtimesFormPanel().getAddRoom().getSelectedItem();
        String showtime = (String) adminView.getShowtimesFormPanel().getAddShowtime().getSelectedItem();

        Movie selectedMovie = null;
        //recorre cada pelicula hasta encontrar el titulo
        for (Movie movie : Movie.getMovies()){
            if (movie.getTitle().equals(selectedMovieTitle)){
                selectedMovie = movie;
                break;
            }
        }

        Room selectedRoom = null;
        for (Room room : Room.getRooms()){
            if (room.getRoomName().equals(selectedRoomName)){
                selectedRoom = room;
                break;
            }
        }
        return new MovieShowtime(adminView.getShowtimesPanel().getIdShowtime(), selectedMovie, selectedRoom, showtime);
    }

    private void showShowtimes(){
        showtimeTable.clearTable();

        for (MovieShowtime ms : showtimes){
            showtimeTable.addRow(ms);
        }
    }

    private void loadShowtimes(){
        showtimes = MovieShowtime.getShowtimes();
        showShowtimes();
    }

    public void fillFieldsRoom() {
        int selectedRow = adminView.getRoomsView().getRoomsTable().getSelectedRow();
        int roomId = roomsTable.getRowData(selectedRow).getIdRoom();

        Room room = Room.getRoom(roomId);
        if (room != null) {
            adminView.getRoomsView().setIdRoom(room.getIdRoom());
            adminView.getRoomsFormPanel().setRoomName(room.getRoomName());
            adminView.getRoomsFormPanel().setRows(room.getRows());
            adminView.getRoomsFormPanel().setCol(room.getCols());
            adminView.getRoomsFormPanel().setRoomType(room.getRoomType());
        } else {
            System.out.println("No se encontró la sala en la base de datos.");
        }
    }

    public void addRoom() {
        if (!validateFormRoom()) return;

        Room room = createRoom();
        int rows = (Integer) adminView.getRoomsFormPanel().getRows().getSelectedItem();
        int cols = (Integer) adminView.getRoomsFormPanel().getCol().getSelectedItem();

        try {
            int idRoom = Room.addRoom(room);
            System.out.println(idRoom);
            room.setIdRoom(idRoom);

            room.generateSeats(rows,cols);

            if (idRoom > 0) {
                rooms.add(room);
                roomsTable.addRow(room);
                showAdminPanel("Salas");
                JOptionPane.showMessageDialog(frame, "Se ha agregado una nueva sala");
            } else {
                JOptionPane.showMessageDialog(frame, "No se pudo crear la sala");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame,
                    "El nombre de la sala ya existe. Intenta con otro.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void saveChangesRoom() {
        if (!validateFormRoom()) return;

        try {
            Room room = createRoom();
            int rowIndex = roomsTable.getRowById(room.getIdRoom());
            if (rowIndex == -1) {
                JOptionPane.showMessageDialog(frame, "Sala no encontrada", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Room existingRoom = rooms.get(rowIndex);
            room.setIdRoom(existingRoom.getIdRoom());

            if (Room.updateRoom(room)) {
                int newRows = (Integer) adminView.getRoomsFormPanel().getRows().getSelectedItem();
                int newCols = (Integer) adminView.getRoomsFormPanel().getCol().getSelectedItem();

                ArrayList<Seat> existingSeats = Seat.getSeatsByRoomId(room.getIdRoom());

                Seat[][] newSeats = new Seat[newRows][newCols];
                ArrayList<Seat> seatsToKeep = new ArrayList<>();

                char rowLetter = 'A';
                for (int i = 0; i < newRows; i++) {
                    for (int j = 0; j < newCols; j++) {
                        String seatName = rowLetter + String.valueOf(j + 1);
                        Seat seat = null;

                        for (Seat s : existingSeats) {
                            if (s.getSeatName().equals(seatName)) {
                                seat = s;
                                seatsToKeep.add(s);
                                break;
                            }
                        }
                        if (seat == null) {
                            seat = new Seat();
                            seat.setSeatName(seatName);
                            seat.setSeatNumber(j + 1);
                            seat.setIdRoom(room.getIdRoom());
                            int generatedId = Seat.addSeat(seat);
                            seat.setIdSeat(generatedId);
                        }
                        newSeats[i][j] = seat;
                    }
                    rowLetter++;
                }

                for (Seat s : existingSeats) {
                    boolean found = false;
                    for (Seat keep : seatsToKeep) {
                        if (s.getIdSeat() == keep.getIdSeat()) {
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        Seat.deleteSeat(s.getIdSeat());
                    }
                }

                room.setSeats(newSeats);
                roomsTable.setRowData(rowIndex, room);
                rooms.set(rowIndex, room);

                adminView.getRoomsFormPanel().clearFields();
                JOptionPane.showMessageDialog(frame, "Se han efectuado los cambios");
            } else {
                JOptionPane.showMessageDialog(frame, "No se pudo actualizar la sala");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame,
                    "Error al guardar cambios: La sala ya existe. Intenta con otra.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    private void deleteRooms() {
        int selectedRow = adminView.getRoomsView().getRoomsTable().getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(adminView, "Debes seleccionar una sala de la tabla");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
                adminView,
                "¿Esta seguro de que desea eliminar esta sala?",
                "Confirmar eliminacion",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            int id = rooms.get(selectedRow).getIdRoom();

            boolean deletedSeats = Room.deleteRoomSeats(id);
            boolean deleted = Room.deleteRoom(id);
            if (deleted && deletedSeats) {
                JOptionPane.showMessageDialog(adminView, "Sala eliminada correctamente");
                showRooms();
            } else {
                JOptionPane.showMessageDialog(adminView, "No se pudo eliminar la sala");
            }
        }
    }

    public Room createRoom() {
        String roomName = adminView.getRoomsFormPanel().getRoomName();

        int rows = (Integer) adminView.getRoomsFormPanel().getRows().getSelectedItem();
        int cols = (Integer) adminView.getRoomsFormPanel().getCol().getSelectedItem();

        int capacity = rows * cols;
        String roomType = (String)adminView.getRoomsFormPanel().getRoomType().getSelectedItem();

        return new Room(adminView.getRoomsView().getIdRoom(),roomName,capacity,roomType,rows,cols);
    }

    private void showRooms() {
        roomsTable.cleanTable();
        for (Room r : rooms) {
            roomsTable.addRow(r);
        }
    }

    private void loadRooms() {
        rooms = Room.getRooms();
        showRooms();
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
            finalRoute = movie.getImgRoute();
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
            finalRoute = null;
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
        String imgRoute = finalRoute;

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
            finalRoute = null;
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
            finalRoute == null ) {

            JOptionPane.showMessageDialog(frame, "Los campos no pueden estar vacíos.",
                    "Campos vacíos", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;

    }

    public boolean validateFormRoom(){
        if (adminView.getRoomsFormPanel().getRoomName().isBlank() ||
                adminView.getRoomsFormPanel().getCol().getSelectedIndex() == 0||
                adminView.getRoomsFormPanel().getRows().getSelectedIndex() == 0 ||
                adminView.getRoomsFormPanel().getRoomType().getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(frame, "Los campos no pueden estar vacíos.",
                    "Campos vacíos", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    public boolean validateFormShowtimes(){
        if (adminView.getShowtimesFormPanel().getAddMovieTitle().getSelectedIndex() == 0 ||
        adminView.getShowtimesFormPanel().getAddRoom().getSelectedIndex() == 0){
            JOptionPane.showMessageDialog(frame, "Los campos no pueden estar vacios", "Campos vacios",
                    JOptionPane.ERROR_MESSAGE);
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


    public void generatePDF() {

        UIManager.put("FileChooser.cancelButtonText", "Cancelar");


        JFileChooser fileChooser = new JFileChooser("src/PDF_Folder");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter pdf = new FileNameExtensionFilter("Documentos PDF","pdf");
        fileChooser.addChoosableFileFilter(pdf);
        fileChooser.setFileFilter(pdf);

        int message = fileChooser.showDialog(frame, "Generar PDF");

        if(message == JFileChooser.CANCEL_OPTION) {
            JOptionPane.showMessageDialog(frame, "Se canceló la exportación");
            return;
        }


        try (
                PdfDocument pdfDoc = new PdfDocument(new PdfWriter(fileChooser.getSelectedFile()));
                Document doc = new Document(pdfDoc, PageSize.LETTER.rotate());
        ){

            InputStream is = getClass().getClassLoader().getResourceAsStream("img/logo.png");
            System.out.println(is);
            if (is != null) {
                ImageData data = ImageDataFactory.create(is.readAllBytes());
                Image img = new Image(data).scaleAbsolute(50, 50);

                float height = PageSize.LETTER.rotate().getHeight();
                float border = 40;
                img.setFixedPosition(border, height - border - 50);

                doc.add(img);
            }

            doc.add(new Paragraph("Reporte de ventas generadas en Cine").setBold().setFontSize(12).setTextAlignment(TextAlignment.CENTER));

            doc.add(new Paragraph("").setMarginTop(30));



            float[] cols = {2,2,2,2,2};
            Table table = new Table(UnitValue.createPercentArray(cols)).useAllAvailableWidth();

            PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA);


            Cell cell = new Cell(1, 6)
                    .add(new Paragraph("Ventas Cine"))
                    .setFont(font)
                    .setFontSize(14)
                    .setFontColor(DeviceGray.BLACK)
                    .setBackgroundColor(new DeviceRgb(160, 203, 231))
                    .setTextAlignment(TextAlignment.CENTER);
            table.addHeaderCell(cell);


            for(int i = 0; i < 2; i++) {
                Cell[] header = new Cell[] {
                        new Cell().setTextAlignment(TextAlignment.CENTER).setBorderTop(new SolidBorder(1f)).setBackgroundColor(new DeviceRgb(191, 244, 250)).add(new Paragraph("Pelicula")),
                        new Cell().setTextAlignment(TextAlignment.CENTER).setBorderTop(new SolidBorder(1f)).setBackgroundColor(new DeviceRgb(191, 244, 250)).add(new Paragraph("Sala")),
                        new Cell().setTextAlignment(TextAlignment.CENTER).setBorderTop(new SolidBorder(1f)).setBackgroundColor(new DeviceRgb(191, 244, 250)).add(new Paragraph("Asiento")),
                        new Cell().setTextAlignment(TextAlignment.CENTER).setBorderTop(new SolidBorder(1f)).setBackgroundColor(new DeviceRgb(191, 244, 250)).add(new Paragraph("Horario")),
                        new Cell().setTextAlignment(TextAlignment.CENTER).setBorderTop(new SolidBorder(1f)).setBackgroundColor(new DeviceRgb(191, 244, 250)).add(new Paragraph("Precio")),
                };

                for(Cell cellss : header) {
                    if(i == 0) {
                        table.addHeaderCell(cellss);
                    }
                }
            }


            int i = 1;
            for(Ticket t : tickets) {
                table.addCell(new Cell().setTextAlignment(TextAlignment.LEFT).add(new Paragraph(t.getFunction().getMovie().getTitle())));
                table.addCell(new Cell().setTextAlignment(TextAlignment.LEFT).add(new Paragraph(t.getFunction().getRoom().getRoomName())));
                table.addCell(new Cell().setTextAlignment(TextAlignment.LEFT).add(new Paragraph(t.getSeat().getSeatName())));
                table.addCell(new Cell().setTextAlignment(TextAlignment.LEFT).add(new Paragraph(t.getFunction().getShowTime())));
                table.addCell(new Cell().setTextAlignment(TextAlignment.LEFT).add(new Paragraph(String.valueOf(t.getPrice()))));
                i++;
            }

            doc.add(table);


            if(Desktop.isDesktopSupported()) {
                try {
                    Desktop.getDesktop().open(fileChooser.getSelectedFile());
                }catch(IOException ex) {
                    JOptionPane.showMessageDialog(frame, "No se pudo abrir el archivo");
                }
            }

        }catch(IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "No se pudo exportar el PDF");
        }
    }

}
