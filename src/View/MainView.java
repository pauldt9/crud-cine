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

        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(0x4A66CA));
        topPanel.setLayout(new FlowLayout());
        

        try {
            logo = ImageIO.read(new File("src/img/logo.png"));
            logo = logo.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        } catch (IOException e){
            System.out.println("Hubo un error al cargar la imagen: " + e.getMessage());
        }

    }

    public JLabel createJLabel(String title, int fontSize){
        JLabel label = new JLabel(title);
        label.setFont(new Font("Helvetica Neue", Font.BOLD, fontSize));
        label.setForeground(new Color(30, 30, 30));
        return label;
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        /*Rectangulo superior*/

        
        
    }
}
