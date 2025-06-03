package Controller;

import Models.Employee;
import Models.EmployeeTableModel;
import View.LoginPanel;
import View.AdminView;
import utils.PasswordUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class AdminController implements ActionListener {
    private LoginPanel loginPanel;
    private JFrame frame;
    private AdminView adminView;
    private ArrayList<Employee> employees;
    private EmployeeTableModel empTable;

    public AdminController(LoginPanel loginPanel, JFrame frame, AdminView adminView){
        this.loginPanel = loginPanel;
        this.frame = frame;
        this.adminView = adminView;
        this.adminView.setListeners(this);

        empTable = adminView.getEmployeePanel().getTableModelEmployees();

        adminView.getEmployeePanel().tableListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    adminView.getEmployeePanel().removeTableSelection();
                }
            }
        });

        employees = new ArrayList<>();
        loadEmployees();
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
                adminView.getAddEmployeePanel().clearFields();
                showAdminPanel("agregar/editar empleado");
                adminView.getAddEmployeePanel().setAction("Registrar");
                break;
            case "Editar empleado":
                System.out.println("editar empleado");
                if (adminView.getEmployeePanel().getEmployeesTable().getSelectedRow() == -1) {
                    JOptionPane.showMessageDialog(adminView, "Por favor selecciona una fila");
                    break;
                }

                showAdminPanel("agregar/editar empleado");
                adminView.getAddEmployeePanel().setAction("Editar");
                fillFieldsEmp();
                break;
            case "Eliminar empleado":
                System.out.println("borrar empleado");

                deleteEmployee();
                loadEmployees();

                break;
            case "Confirmar empleado":
                System.out.println("se ha agregado un usuario nuevo");
                adminView.getAddEmployeePanel().setAction("Registrar");
                addEmployee();
                adminView.getAddEmployeePanel().clearFields();
                break;
            case "Confirmar cambios de empleado":
                adminView.getAddEmployeePanel().setAction("Editar");
                saveChanges();
                loadEmployees();
                showAdminPanel("Empleados");
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
                break;
            case "Editar pelicula":
                System.out.println("editar pelicula");
                adminView.getMovieForm().setAction("Editar");
                showAdminPanel("agregar/editar pelicula");

                break;
            case "Agregar pelicula":
                System.out.println("agregar pelicula");
                adminView.getMovieForm().setAction("Agregar");
                showAdminPanel("agregar/editar pelicula");


                break;
            case "Agregar imagen":
                System.out.println("se ha agregado una imagen");
                break;
            case "Confirmar pelicula":
                System.out.println("se ha agregado una pelicula");
                adminView.getMovieForm().setAction("Agregar");

                showAdminPanel("peliculas");
                break;
            case "Confirmar cambios de pelicula":
                System.out.println("se ha editado la pelicula");
                adminView.getMovieForm().setAction("Editar");

                showAdminPanel("peliculas");
                break;
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

    private void saveChanges() {
        if (!validateForm()) return;

        try {
            Employee employee = createEmployeeForEdit();

            String plainPassword = adminView.getAddEmployeePanel().getEmpPass();

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
                    adminView.getAddEmployeePanel().clearFields();
                    System.out.println("se han efectuado los cambios");
                    JOptionPane.showMessageDialog(frame, "Se han efectuado los cambios");
                } else {
                    JOptionPane.showMessageDialog(frame, "No se pudo actualizar el usuario");
                }
            }else{
                JOptionPane.showMessageDialog(frame, "Contraseña incorrecta","Error", JOptionPane.ERROR_MESSAGE);
            }
        }catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Error al guardar cambios: " + ex.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void fillFieldsEmp(){
        Employee employee = empTable.getRowData(adminView.getEmployeePanel().getEmployeesTable().getSelectedRow());
        adminView.getEmployeePanel().setIdEmployee(employee.getIdEmployee());
        adminView.getAddEmployeePanel().setAddEmpName(employee.getFirstName());
        adminView.getAddEmployeePanel().setAddEmpLastName(employee.getLastName());
        adminView.getAddEmployeePanel().setEmpType(employee.getEmployeeType());
        adminView.getAddEmployeePanel().setEmpUser(employee.getUsername());
    }

    // Crea el objeto empleado que se usara en el metodo addEmployee
    public Employee createEmployee() {
        String name = adminView.getAddEmployeePanel().getEmpName();
        String lastName = adminView.getAddEmployeePanel().getEmpLastName();
        String employeeType = (String)adminView.getAddEmployeePanel().getEmpType().getSelectedItem();
        String empUsername = adminView.getAddEmployeePanel().getEmpUser();

        if (!Employee.isUsernameAvailable(empUsername)) {
            JOptionPane.showMessageDialog(frame, "El nombre de usuario ya existe. Intenta con otro.");
            adminView.getAddEmployeePanel().setEmpUser("");
            return null;
        }

        String empPassword = adminView.getAddEmployeePanel().getEmpPass();

        return new Employee(adminView.getEmployeePanel().getIdEmployee(),name,lastName,employeeType,empUsername,empPassword);
    }

    // Crea un empleado sin validar el nombre de usuario (para edición)
    public Employee createEmployeeForEdit() {
        String name = adminView.getAddEmployeePanel().getEmpName();
        String lastName = adminView.getAddEmployeePanel().getEmpLastName();
        String employeeType = (String)adminView.getAddEmployeePanel().getEmpType().getSelectedItem();
        String empUsername = adminView.getAddEmployeePanel().getEmpUser();
        String empPassword = adminView.getAddEmployeePanel().getEmpPass();

        return new Employee(adminView.getEmployeePanel().getIdEmployee(), name, lastName, employeeType, empUsername, empPassword);
    }


    //agrega al empleado a la base de datos y a la tabla
    public void addEmployee(){
        if(!validateForm()) return;
        Employee emp = createEmployee();

        int idUser = Employee.addEmployee(emp);

        if(idUser > 0) {
            emp.setIdEmployee(idUser);
            employees.add(emp);
            empTable.addRow(emp);
            showAdminPanel("Empleados");
            JOptionPane.showMessageDialog(frame, "Se ha agregado un empleado nuevo");
        }else{
            JOptionPane.showMessageDialog(frame, "No se pudo crear el empleado");
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
    public boolean validateForm(){
        if (adminView.getAddEmployeePanel().getEmpName().isBlank() ||
            adminView.getAddEmployeePanel().getEmpLastName().isBlank()||
            adminView.getAddEmployeePanel().getEmpType().getSelectedIndex() == 0 ||
            adminView.getAddEmployeePanel().getEmpUser().isBlank() ||
            adminView.getAddEmployeePanel().getEmpPass().isBlank() ||
            adminView.getAddEmployeePanel().getEmpPassConfirm().isBlank()) {
            JOptionPane.showMessageDialog(frame, "Los campos no pueden estar vacíos.",
                    "Campos vacíos", JOptionPane.ERROR_MESSAGE);
            return false;
        } else if (!adminView.getAddEmployeePanel().validatePassField()) {
            JOptionPane.showMessageDialog(frame, "Los campos de contraseña no coinciden.");
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
