package View.Admin.SalesManagement;

import Models.SalesTableModel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;

import static utils.CreateComponents.*;

public class SalesMain extends JPanel {
    private JLabel salesLbl;

    private Color fgCol = new Color(0x2C3E50);
    private Color bgColButtons = new Color(245, 245, 245);

    private JButton deleteButton;
    private JButton generatePdf;

    private JTable salesTable;
    private SalesTableModel salesTableModel;

    public SalesMain(){
        setLayout(new BorderLayout());
        setOpaque(false);

        salesLbl = createJLabel("Ventas", 40, true);
        salesLbl.setForeground(fgCol);
        salesLbl.setHorizontalAlignment(SwingConstants.LEFT);
        salesLbl.setBorder(BorderFactory.createEmptyBorder(60, 40, 0, 0));
        add(salesLbl, BorderLayout.NORTH);

        initSalesTable(); //este metodo inicializa la tabla

        //Paneles vacios
        JPanel emptyEast = createEmptyPanel(40, 800);
        add(emptyEast, BorderLayout.EAST);

        JPanel emptyWest = createEmptyPanel(40, 800);
        add(emptyWest, BorderLayout.WEST);

        //Aqui van los botones
        JPanel buttonPanel = createButtonsPanel();
        add(buttonPanel, BorderLayout.SOUTH);

        deleteButton = createButton("Eliminar", 120, 40);
        deleteButton.setFont(new Font("Helvetica Neue", Font.BOLD, 15));
        deleteButton.setActionCommand("Eliminar venta");
        deleteButton.setBackground(bgColButtons);
        deleteButton.setForeground(fgCol);
        buttonPanel.add(deleteButton);

        generatePdf = createButton("Generar PDF", 140, 40);
        generatePdf.setFont(new Font("Helvetica Neue", Font.BOLD, 15));
        generatePdf.setActionCommand("Generar pdf");
        generatePdf.setBackground(bgColButtons);
        generatePdf.setForeground(fgCol);
        buttonPanel.add(generatePdf);

        try {
            Image deleteIcon = ImageIO.read(getClass().getResource("/img/remove.png"));
            deleteIcon = deleteIcon.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            deleteButton.setIcon(new ImageIcon(deleteIcon));
        } catch (IOException e){
            System.out.println("Error al cargar la imagen: " + e.getMessage());
        }
    }

    public void initSalesTable(){
        salesTableModel = new SalesTableModel();
        salesTable = new JTable(salesTableModel);

        JScrollPane scroll = new JScrollPane(salesTable);
        add(scroll, BorderLayout.CENTER);
    }

    public void setListeners(ActionListener listener){
        deleteButton.addActionListener(listener);
        generatePdf.addActionListener(listener);
    }
}
