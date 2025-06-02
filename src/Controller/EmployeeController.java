package Controller;

import View.EmployeeView;
import View.LoginPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmployeeController implements ActionListener {
    private JFrame frame;
    private EmployeeView employeeView;
    private LoginPanel loginPanel;

    public EmployeeController(JFrame frame, EmployeeView employeeView, LoginPanel loginPanel){
        this.employeeView = employeeView;
        this.loginPanel = loginPanel;
        this.frame = frame;
        this.employeeView.setListeners(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command){
            case "Salir":
                exit();
                break;
            case "Regresar al catalogo", "Inicio empleado":
                showEmployeePanel("catalogo");
                break;
            case "Funciones":
                showEmployeePanel("seleccionar hora");
                break;
        }
    }

    public void exit(){
        int answer = JOptionPane.showConfirmDialog(frame, "Estas seguro de cerrar sesion?", "Salir", JOptionPane.YES_NO_OPTION);

        if (answer == JOptionPane.YES_OPTION){
            frame.remove(employeeView);
            frame.add(loginPanel);
            frame.repaint();
            frame.revalidate();
            frame.setTitle("Ingresar credenciales");
        }
    }

    //cambiar de pestaña
    public void showEmployeePanel(String namePanel){
        CardLayout card = (CardLayout) (employeeView.getMainPanel().getLayout());
        card.show(employeeView.getMainPanel(), namePanel);
    }


}
