package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Catalog extends JPanel {
    private Color fgCol = new Color(0x2C3E50);
    private Color bgColButtons = new Color(245, 245, 245);

    private JLabel catalogTitle;
    private JButton testButton; //boton de prueba

    public Catalog(){
        setLayout(new BorderLayout());
        setOpaque(false);

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

        JPanel emptySouth = createEmptyPanel(1100, 40);
        add(emptySouth, BorderLayout.SOUTH);

        /*Este boton es de prueba, se va a borrar al final*/
        testButton = createButton("Funciones", 100, 40);
        testButton.setActionCommand("Funciones");
        testButton.setBackground(bgColButtons);
        emptySouth.add(testButton);

        //Panel donde se mostraran las peliculas
        JPanel catalogPanel = new JPanel();
        catalogPanel.setOpaque(false);
//        catalogPanel.setBackground(Color.GREEN);
        catalogPanel.setLayout(new GridLayout(0, 3));
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

    public void setListeners(ActionListener listener){
        testButton.addActionListener(listener);
    }
}
