package View;

import DAO.MoviesDAO;
import Models.Movie;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Catalog extends JPanel {
    private Color fgCol = new Color(0x2C3E50);
    private Color bgColButtons = new Color(245, 245, 245);

    private JLabel catalogTitle;

    private JPanel catalogPanel;

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
        JPanel emptyWest = createEmptyPanel(40, 800);
        add(emptyWest, BorderLayout.WEST);

        JPanel emptyEast = createEmptyPanel(40, 800);
        add(emptyEast, BorderLayout.EAST);

        JPanel emptySouth = createEmptyPanel(1100, 40);
        add(emptySouth, BorderLayout.SOUTH);


        //Panel donde se mostraran las peliculas
        catalogPanel = new JPanel();
        catalogPanel.setOpaque(false);
//        catalogPanel.setBackground(Color.GREEN);
        catalogPanel.setLayout(new GridLayout(0, 3, 20, 20));
        add(catalogPanel, BorderLayout.CENTER);
    }

    //aqui muestra las imagenes (botones) de las peliculas
    public void initMoviesCatalog(ActionListener listener){
        MoviesDAO moviesDAO = new MoviesDAO();
        ArrayList<Movie> moviesList = moviesDAO.getImgMovies();

        catalogPanel.removeAll();
        System.out.println("Peliculas encontradas: " + moviesList.size());

        for (Movie m : moviesList){
            JButton btn = new JButton();
            btn.setActionCommand(String.valueOf(m.getIdMovie()));
            btn.setBorder(null);
            btn.setContentAreaFilled(false);
            btn.setFocusPainted(false);
            btn.setPreferredSize(new Dimension(180, 270));

            File imageFile = new File(m.getImgRoute());

            try {
                Image movieIcon = ImageIO.read(imageFile);
                movieIcon = movieIcon.getScaledInstance(180, 270, Image.SCALE_SMOOTH);
                btn.setIcon(new ImageIcon(movieIcon));
            } catch (IOException e){
                System.out.println("Error al cargar la imagen: " + e.getMessage());
            }

            btn.addActionListener(listener);
            catalogPanel.add(btn);
        }

        catalogPanel.revalidate();
        catalogPanel.repaint();
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

    public int getNumMovies() {
        MoviesDAO moviesDAO = new MoviesDAO();
        ArrayList<Movie> moviesList = moviesDAO.getImgMovies();
        return moviesList.size();
    }
}
