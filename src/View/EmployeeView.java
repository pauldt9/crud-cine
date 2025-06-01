package View;

import javax.swing.*;
import java.awt.*;

public class EmployeeView extends JPanel {
    //colores principales
    private Color fgCol = new Color(0x2C3E50);
    private Color panelColor = Color.WHITE;

    private JPanel mainPanel;
    private Catalag catalagView;

    public EmployeeView(){
        setLayout(new BorderLayout());
        add(new LeftPanelEmployee(), BorderLayout.WEST);

        //Aqui se encuentran todos los paneles
        mainPanel = new JPanel();
        mainPanel.setLayout(new CardLayout());
        mainPanel.setBackground(panelColor);

        catalagView = new Catalag();

        mainPanel.add(catalagView, "catalogo");

        add(mainPanel, BorderLayout.CENTER);


    }

}
