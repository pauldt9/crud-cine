package Controller;

import Models.Employee;
import View.Admin.AdminView;
import View.Employee.EmployeeView;
import View.LoginPanel;
import utils.PasswordUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController implements ActionListener {
    private LoginPanel loginPanel;
    private AdminView adminView;
    private JFrame frame;
    private EmployeeView employeeView;
    private EmployeeController employeeController;
    private Employee employee;

    public LoginController(LoginPanel loginPanel, JFrame frame, AdminView adminView, EmployeeView employeeView, EmployeeController employeeController) {
        this.loginPanel = loginPanel;
        this.frame = frame;
        this.adminView = adminView;
        this.employeeView = employeeView;
        this.employeeController = employeeController;
        this.loginPanel.setListeners(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.equals("Ingresar")) {
            if (validateLogin()) {
                try {

                    employee = Employee.getEmployeeByUsername(loginPanel.getUserField());

                    //Valida contraseña
                    if(!PasswordUtils.checkPassword(loginPanel.getPasswordField(), employee.getPassword())){
                        JOptionPane.showMessageDialog(frame,"Contraseña incorrecta", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    //Valida tipo de usuario
                    if (isAdmin(employee)) {
                        openAdminView();
                    } else{
                        openEmployeeView();
                    }
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(frame, "Usuario no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    public boolean isAdmin(Employee employee){
//        alguna validacion si el usuario es admin, pero algo para determinar que ventana abrir

        if(employee.getEmployeeType().equals("Admin")){
            return true;
        }
        return false;
    }

    public boolean validateLogin(){
        /*Aqui agregar validaciones del login*/
        String message = emptyFields();

        if (!message.isBlank()) {
            JOptionPane.showMessageDialog(null, message, "Campos vacíos", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    public String emptyFields() {
        StringBuilder missingFields = new StringBuilder();

        if (loginPanel.getUserField().trim().isEmpty()) {
            missingFields.append("Usuario, ");
        }
        if (loginPanel.getPasswordField().trim().isEmpty()) {
            missingFields.append("Contraseña, ");
        }

        if (missingFields.length() == 0) {
            return "";
        }

        return "Los siguientes parámetros están vacíos: " + missingFields.substring(0, missingFields.length() - 2);
    }

    public void openAdminView(){
        frame.remove(loginPanel);
        frame.add(adminView);

        frame.repaint();
        frame.revalidate();
        frame.setTitle("Inicio");
    }

    public void openEmployeeView(){
        frame.remove(loginPanel);
        frame.add(employeeView);

        frame.repaint();
        frame.revalidate();
        frame.setTitle("Inicio");

        employeeController.reloadShowtimes();
        employeeController.showEmployeePanel("catalogo");
    }

}
