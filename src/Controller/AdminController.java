package Controller;

import Models.Employee;
import Models.EmployeeTableModel;
import View.EmployeeView;
import View.LoginPanel;
import View.AdminView;
import utils.PasswordUtils;
import View.*;

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

        empTable = adminView.getTableModelEmp();

        adminView.tableListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    adminView.removeTableSelection();
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
            case "Peliculas":
                System.out.println("peliculas");
                showAdminPanel("peliculas");
                break;
            case "Empleados":
                System.out.println("empleados");
                showAdminPanel("Empleados");
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
            case "Agregar empleado":
                System.out.println("agregar empleado");
                adminView.clearFields();
                showAdminPanel("agregar/editar empleado");
                adminView.setNewAction("Registrar");

                break;
            case "Editar empleado":
                System.out.println("editar empleado");
                if (adminView.getEmpTable().getSelectedRow() == -1) {
                    JOptionPane.showMessageDialog(adminView, "Por favor selecciona una fila");
                    break;
                }

                showAdminPanel("agregar/editar empleado");
                adminView.setNewAction("Editar");
                fillFieldsEmp();
                break;
            case "Eliminar empleado":
                System.out.println("borrar empleado");

                deleteEmployee();
                loadEmployees();

                break;
            case "Eliminar pelicula":
                System.out.println("eliminar pelicula");
                break;
            case "Editar pelicula":
                System.out.println("editar pelicula");
                break;
            case "Agregar pelicula":
                System.out.println("agregar pelicula");
                break;
            case "Confirmar empleado":
                System.out.println("se ha agregado un usuario nuevo");
                JOptionPane.showMessageDialog(frame, "Se ha agregado un empleado nuevo");

                addEmployee();

                showAdminPanel("Empleados");
                adminView.clearFields();
                break;
            case "Confirmar cambios de empleado":
                saveChanges();
                loadEmployees();
                break;
            case "Regresar empleado":
                System.out.println("El usuario se ha regresado al apartado empleado");
                showAdminPanel("Empleados");
                break;
        }
    }

    private void deleteEmployee() {
        int selectedRow = adminView.getEmpTable().getSelectedRow();
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

            String plainPassword = adminView.getEmpPass();

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
                    adminView.clearFields();
                    System.out.println("se han efectuado los cambios");
                    JOptionPane.showMessageDialog(frame, "Se han efectuado los cambios");
                } else {
                    JOptionPane.showMessageDialog(frame, "No se pudo actualizar el usuario");
                }
            }else{
                JOptionPane.showMessageDialog(frame, "Contraseña incorrecta","Error",JOptionPane.ERROR_MESSAGE);
            }
        }catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Error al guardar cambios: " + ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }

    }

    public void fillFieldsEmp(){
        Employee employee = empTable.getRowData(adminView.getEmpTable().getSelectedRow());
        adminView.setIdEmployee(employee.getIdEmployee());
        adminView.setAddEmpName(employee.getFirstName());
        adminView.setAddEmpLastName(employee.getLastName());
        adminView.setEmpType(employee.getEmployeeType());
        adminView.setEmpUser(employee.getUsername());
//        adminView.setAddEmpPass(employee.getPassword());
//        adminView.setAddEmpConfirmPass(employee.getPassword());
    }

    // Crea el objeto empleado que se usara en el metodo addEmployee
    public Employee createEmployee() {
        String name = adminView.getEmpName();
        String lastName = adminView.getEmpLastName();
        String employeeType = (String)adminView.getEmpType().getSelectedItem();
        String empUsername = adminView.getEmpUser();

        if (!Employee.isUsernameAvailable(empUsername)) {
            JOptionPane.showMessageDialog(frame, "El nombre de usuario ya existe. Intenta con otro.");
            adminView.setEmpUser("");
            return null;
        }

        String empPassword = adminView.getEmpPass();

        return new Employee(adminView.getIdEmployee(),name,lastName,employeeType,empUsername,empPassword);
    }

    // Crea un empleado sin validar el nombre de usuario (para edición)
    public Employee createEmployeeForEdit() {
        String name = adminView.getEmpName();
        String lastName = adminView.getEmpLastName();
        String employeeType = (String)adminView.getEmpType().getSelectedItem();
        String empUsername = adminView.getEmpUser();
        String empPassword = adminView.getEmpPass();

        return new Employee(adminView.getIdEmployee(), name, lastName, employeeType, empUsername, empPassword);
    }


    //agrega al empleado a la base de datos y a la tabla
    public void addEmployee(){
        if(!validateForm()) return;
        Employee emp = createEmployee();
        emp.setPassword(PasswordUtils.hashPassword(emp.getPassword()));

        int idUser = Employee.addEmployee(emp);

        if(idUser > 0) {
            emp.setIdEmployee(idUser);
            employees.add(emp);
            empTable.addRow(emp);
            showAdminPanel("Empleados");
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
        if (adminView.getEmpName().isBlank() ||
            adminView.getEmpLastName().isBlank()||
            adminView.getEmpType().getSelectedIndex() == 0 ||
            adminView.getEmpUser().isBlank() ||
            adminView.getEmpPass().isBlank() ||
            adminView.getEmpPassConfirm().isBlank()) {
            JOptionPane.showMessageDialog(frame, "Los campos no pueden estar vacíos.",
                    "Campos vacíos", JOptionPane.ERROR_MESSAGE);
            return false;
        } else if (!adminView.validatePassField()) {
            JOptionPane.showMessageDialog(frame, "Los campos de contraseña no coinciden.");
            return false;
        }
        return true;
    }

    public void exit(){
        int answer = JOptionPane.showConfirmDialog(frame, "Estas seguro de cerrar sesion?", "Salir", JOptionPane.YES_NO_OPTION);

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
