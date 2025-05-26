package View;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MainView extends JPanel {
    private Image logo;
    public MainView() {
        setBackground(new Color(0x4A66CA));
        setLayout(new BorderLayout());

        /*Panel superior*/
        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(0x4A66CA));
        topPanel.setLayout(new BorderLayout());
        topPanel.setOpaque(false);
        topPanel.setPreferredSize(new Dimension(getWidth(), 130));
        add(topPanel, BorderLayout.NORTH);

        try {
            logo = ImageIO.read(new File("src/img/logo.png"));
            logo = logo.getScaledInstance(120, 120, Image.SCALE_SMOOTH);
        } catch (IOException e){
            System.out.println("Hubo un error al cargar la imagen: " + e.getMessage());
        }

        JLabel title = createJLabel("INICIO", 40, 1);
        title.setBorder(BorderFactory.createEmptyBorder(5, 150, 5, 5));
        topPanel.add(title, BorderLayout.WEST);

        /*Panel del menu*/
        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(Color.WHITE);
        centerPanel.setLayout(new GridBagLayout());
        centerPanel.setPreferredSize(new Dimension(getWidth(), getHeight()));
        add(centerPanel, BorderLayout.CENTER);



    }

    public JLabel createJLabel(String title, int fontSize, int color){
        JLabel label = new JLabel(title);
        label.setFont(new Font("Helvetica Neue", Font.BOLD, fontSize));

        if (color == 1){
            label.setForeground(Color.WHITE);
        } else {
            label.setForeground(new Color(30, 30, 30));
        }

        return label;
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(logo, 20, 5, this);

        Graphics2D g2 = (Graphics2D) g;
        Composite original = g2.getComposite(); //obtener la composicion original

        g2.setColor(new Color(0, 0, 0, 50));
        g2.fillRoundRect(1078, 10, 300, 110, 15, 15);

        g2.setComposite(original); //restablecer
    }
}
