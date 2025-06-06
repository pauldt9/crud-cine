package View.Admin.EmployeeManagement;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;

import static utils.CreateComponents.*;

public class EmployeeForm extends JPanel {
    private String action;

    private JButton confirmEmp;
    private JButton backButton;

    private Color bgColButtons = new Color(245, 245, 245);
    private Color fgColor = new Color(0x2C3E50);

    private JLabel addEmpTitle;

    private JTextField addEmpName;
    private JTextField addEmpLastName;
    private JTextField addEmpUser;
    private JPasswordField addEmpPass;
    private JPasswordField addEmpConfirmPass;
    private JComboBox<String> empType;

    public EmployeeForm(){
        setLayout(new BorderLayout());
        setOpaque(false);

        JPanel topPanel = new JPanel();
        topPanel.setPreferredSize(new Dimension(Integer.MAX_VALUE, 170));
        topPanel.setOpaque(false);
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        add(topPanel, BorderLayout.NORTH);

        backButton = createButton(null, 45, 45);
        backButton.setActionCommand("Regresar empleado");
        backButton.setAlignmentX(Box.LEFT_ALIGNMENT);
        backButton.setBackground(bgColButtons);
        topPanel.add(Box.createRigidArea(new Dimension(40, 60)));
        topPanel.add(backButton);

        //titulo
        addEmpTitle = createJLabel("Agregar Empleado", 40, true);
        addEmpTitle.setAlignmentX(Box.LEFT_ALIGNMENT);
        addEmpTitle.setForeground(fgColor);
        topPanel.add(Box.createHorizontalStrut(15));
        topPanel.add(addEmpTitle);

        JPanel emptyEast = createEmptyPanel(40, 800);
        add(emptyEast, BorderLayout.EAST);

        JPanel emptyWest = createEmptyPanel(40, 800);
        add(emptyWest, BorderLayout.WEST);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(4, 2, 30, 30));
        formPanel.setOpaque(false);
        add(formPanel, BorderLayout.CENTER);

        addEmpName = createTextField("Ingresar nombre", 350, 50);
        addEmpName.setAlignmentX(Component.LEFT_ALIGNMENT);
        addEmpName.setBorder(BorderFactory.createCompoundBorder(addEmpName.getBorder(), new EmptyBorder(0, 10, 0, 0)));
        addEmpName.setBackground(bgColButtons);
        addEmpName.setForeground(fgColor);
        formPanel.add(addEmpName);

        addEmpLastName = createTextField("Ingresar Apellido", 350, 50);
        addEmpLastName.setAlignmentX(Component.LEFT_ALIGNMENT);
        addEmpLastName.setBackground(bgColButtons);
        addEmpLastName.setForeground(fgColor);
        addEmpLastName.setBorder(BorderFactory.createCompoundBorder(addEmpLastName.getBorder(), new EmptyBorder(0, 10, 0, 0)));
        formPanel.add(addEmpLastName);

        String[] types = {"-----Tipo de Empleado-----", "Taquillero", "Admin"};
        empType = new JComboBox<String>(types);
        empType.setAlignmentX(Component.LEFT_ALIGNMENT);
        empType.setBackground(bgColButtons);
        empType.setForeground(fgColor);
        empType.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
        empType.setMaximumSize(new Dimension(350, 50));

        formPanel.add(empType);

        addEmpUser = createTextField("Ingresar nombre de usuario", 350, 50);
        addEmpUser.setAlignmentX(Component.LEFT_ALIGNMENT);
        addEmpUser.setBackground(bgColButtons);
        addEmpUser.setForeground(fgColor);
        addEmpUser.setBorder(BorderFactory.createCompoundBorder(addEmpUser.getBorder(), new EmptyBorder(0, 10, 0, 0)));
        formPanel.add(addEmpUser);

        addEmpPass = createPasswordField("Ingresar Contraseña", 350, 50);
        addEmpPass.setAlignmentX(Component.LEFT_ALIGNMENT);
        addEmpPass.setBackground(bgColButtons);
        addEmpPass.setBorder(BorderFactory.createCompoundBorder(addEmpPass.getBorder(), new EmptyBorder(0, 10, 0, 0)));
        formPanel.add(addEmpPass);

        addEmpConfirmPass = createPasswordField("Confirmar Contraseña", 350, 50);
        addEmpConfirmPass.setAlignmentX(Component.LEFT_ALIGNMENT);
        addEmpConfirmPass.setBackground(bgColButtons);
        addEmpConfirmPass.setBorder(BorderFactory.createCompoundBorder(addEmpConfirmPass.getBorder(), new EmptyBorder(0, 10, 0, 0)));
        formPanel.add(addEmpConfirmPass);

        confirmEmp = createButton("Confirmar", 350, 50);
        confirmEmp.setFont(new Font("Helvetica Neue", Font.BOLD, 15));
        confirmEmp.setActionCommand("Confirmar empleado");
        confirmEmp.setForeground(fgColor);
        confirmEmp.setBackground(bgColButtons);
        confirmEmp.setAlignmentX(Component.LEFT_ALIGNMENT);
        formPanel.add(confirmEmp);

        JPanel emptyBottom = new JPanel();
        emptyBottom.setOpaque(false);
        emptyBottom.setPreferredSize(new Dimension(Integer.MAX_VALUE, 250));
        add(emptyBottom, BorderLayout.SOUTH);

        try {
            Image backIcon = ImageIO.read(getClass().getResource("/img/back.png"));
            backIcon = backIcon.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
            backButton.setIcon(new ImageIcon(backIcon));
        } catch (IOException e){
            System.out.println("error al cargar imagen: " + e.getMessage());
        }
    }

    public void setListeners(ActionListener listener){
        confirmEmp.addActionListener(listener);
        backButton.addActionListener(listener);
    }

    public void setAction(String action){
        this.action = action;
        if (action.equals("Registrar")){
            addEmpTitle.setText("Agregar Empleado");
            confirmEmp.setActionCommand("Confirmar empleado");
        } else {
            addEmpTitle.setText("Actualizar Empleado");
            confirmEmp.setActionCommand("Confirmar cambios de empleado");
        }
    }

    public void clearFields(){
        addEmpName.setText("");
        addEmpLastName.setText("");
        empType.setSelectedIndex(0);
        addEmpUser.setText("");
        addEmpPass.setText("");
        addEmpConfirmPass.setText("");
    }

    public void setAddEmpName(String name){
        addEmpName.setText(name);
    }

    public void setAddEmpLastName(String lastName){
        addEmpLastName.setText(lastName);
    }

    public void setEmpType(String type){
        empType.setSelectedItem(type);
    }

    public void setEmpUser(String user){
        addEmpUser.setText(user);
    }

    public void setAddEmpPass(String pass){
        addEmpPass.setText(pass);
    }

    public void setAddEmpConfirmPass(String pass){
        addEmpConfirmPass.setText(pass);
    }

    public String getEmpName(){
        return addEmpName.getText();
    }

    public String getEmpLastName(){
        return addEmpLastName.getText();
    }

    public JComboBox<String> getEmpType(){
        return empType;
    }

    public String getEmpUser(){
        return addEmpUser.getText();
    }

    public String getEmpPass(){
        return new String(addEmpPass.getPassword());
    }

    public String getEmpPassConfirm(){
        return new String(addEmpConfirmPass.getPassword());
    }

    public boolean validatePassField(){
        return getEmpPass().equals(getEmpPassConfirm());
    }
}
