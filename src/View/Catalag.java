package View;

import javax.swing.*;
import java.awt.*;

public class Catalag extends JPanel {
    private Color fgCol = new Color(0x2C3E50);

    private JLabel catalogTitle;

    public Catalag(){
        setLayout(new BorderLayout());

        //Aqui esta solo el titulo
        JPanel topPanel = new JPanel();
        topPanel.setOpaque(false);
        topPanel.setPreferredSize(new Dimension(1100, 140));
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        add(topPanel, BorderLayout.NORTH);

        catalogTitle = createJLabel("Catalogo", 40, true);
        catalogTitle.setForeground(fgCol);
        catalogTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        catalogTitle.setBorder(BorderFactory.createEmptyBorder(30, 40, 0, 0));
        topPanel.add(catalogTitle);

        //Paneles vacios
        JPanel emptyWest = createEmptyPanel(40, Integer.MAX_VALUE);
        add(emptyWest, BorderLayout.WEST);

        JPanel emptyEast = createEmptyPanel(40, Integer.MAX_VALUE);
        add(emptyEast, BorderLayout.EAST);

        JPanel emptySouth = createEmptyPanel(Integer.MAX_VALUE, 40);
        add(emptySouth, BorderLayout.SOUTH);

        //Panel donde se mostraran las peliculas
        JPanel catalogPanel = new JPanel();
        catalogPanel.setOpaque(false);
//        catalogPanel.setBackground(Color.GREEN);
        catalogPanel.setLayout(new GridLayout());
        add(catalogPanel, BorderLayout.CENTER);


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

    public JButton createButton(String buttonName, int w, int h){
        JButton button = new JButton(buttonName);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setPreferredSize(new Dimension(w, h));
        button.setMinimumSize(new Dimension(w, h));
        button.setMaximumSize(new Dimension(w, h));
        return button;
    }

    public JPanel createEmptyPanel(int w, int h){
        JPanel empty = new JPanel();
        empty.setOpaque(false);
        empty.setPreferredSize(new Dimension(w, h));
        return empty;
    }
}
