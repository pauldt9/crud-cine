package View.Employee;

import javax.swing.*;
import java.awt.*;

import static utils.CreateComponents.createEmptyPanel;
import static utils.CreateComponents.createJLabel;

public class SelectSeats extends JPanel {
    private Color fgCol = new Color(0x2C3E50);

    private JLabel title;

    public SelectSeats(){
        setLayout(new BorderLayout());
        setOpaque(false);

        JPanel topPanel = new JPanel();
        topPanel.setOpaque(false);
        topPanel.setPreferredSize(new Dimension(1100, 140));
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        add(topPanel, BorderLayout.NORTH);

        title = createJLabel("Seleccionar asiento", 40, true);
        title.setForeground(fgCol);
        title.setAlignmentX(Component.LEFT_ALIGNMENT);
        title.setBorder(BorderFactory.createEmptyBorder(30, 40, 0, 0));
        topPanel.add(title);

        //Paneles vacios
        JPanel emptyWest = createEmptyPanel(40, 800);
        add(emptyWest, BorderLayout.WEST);

        JPanel emptyEast = createEmptyPanel(40, 800);
        add(emptyEast, BorderLayout.EAST);

        JPanel emptySouth = createEmptyPanel(1100, 40);
        add(emptySouth, BorderLayout.SOUTH);

    }


}
