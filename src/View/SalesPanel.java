package View;

import javax.swing.*;
import java.awt.*;

public class SalesPanel extends JPanel {
    private JLabel salesLbl;
    private Color fgCol = new Color(0x2C3E50);

    public SalesPanel(){
        setLayout(new BorderLayout());
        setOpaque(false);

        salesLbl = createJLabel("Ventas", 40, true);
        salesLbl.setForeground(fgCol);
        salesLbl.setHorizontalAlignment(SwingConstants.LEFT);
        salesLbl.setBorder(BorderFactory.createEmptyBorder(60, 40, 0, 0));
        add(salesLbl, BorderLayout.NORTH);


    }

    public JLabel createJLabel(String title, int fontSize, boolean bold){
        JLabel label = new JLabel(title);
        if (bold){
            label.setFont(new Font("Helvetica Neue", Font.BOLD, fontSize));
        } else {
            label.setFont(new Font("Helvetica Neue", Font.PLAIN, fontSize));
        }
        label.setForeground(fgCol);
        return label;
    }
}
