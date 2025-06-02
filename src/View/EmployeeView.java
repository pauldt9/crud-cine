package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class EmployeeView extends JPanel {
    //colores principales
    private Color fgCol = new Color(0x2C3E50);
    private Color panelColor = Color.WHITE;

    private JPanel mainPanel;
    private Catalog catalogView;
    private LeftPanelEmployee leftPanelEmployee;
    private SelectHour selectHourPanel;

    public EmployeeView(){
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        leftPanelEmployee = new LeftPanelEmployee(); //panel izquierdo

        add(leftPanelEmployee, BorderLayout.WEST);

        //Aqui se encuentran todos los paneles
        mainPanel = new JPanel();
        mainPanel.setLayout(new CardLayout());
        mainPanel.setBackground(panelColor);

        catalogView = new Catalog();
        selectHourPanel = new SelectHour();

        mainPanel.add(catalogView, "catalogo");
        mainPanel.add(selectHourPanel, "seleccionar hora");

        add(mainPanel, BorderLayout.CENTER);

    }

    public void setListeners(ActionListener listener){
        leftPanelEmployee.setListeners(listener);
        selectHourPanel.setListeners(listener);
        catalogView.setListeners(listener);
    }

    public JPanel getMainPanel(){
        return mainPanel;
    }

}
