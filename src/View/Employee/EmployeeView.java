package View.Employee;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class EmployeeView extends JPanel {
    //colores principales
    private Color fgCol = new Color(0x2C3E50);
    private Color panelColor = Color.WHITE;

    private JPanel mainPanel;
    private Catalog catalogView;
    private LeftPanel leftPanel;
    private SelectHour selectHourPanel;
    private SelectSeats selectSeatsPanel;
    private Details detailsPanel;

    public EmployeeView(Catalog catalog) {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        leftPanel = new LeftPanel();

        add(leftPanel, BorderLayout.WEST);

        mainPanel = new JPanel();
        mainPanel.setLayout(new CardLayout());
        mainPanel.setBackground(panelColor);

        catalogView = catalog;
        selectHourPanel = new SelectHour();
        selectSeatsPanel = new SelectSeats();
        detailsPanel = new Details();

        mainPanel.add(catalogView, "catalogo");
        mainPanel.add(selectHourPanel, "seleccionar hora");
        mainPanel.add(selectSeatsPanel, "seleccionar asientos");
        mainPanel.add(detailsPanel, "detalles de venta");

        add(mainPanel, BorderLayout.CENTER);
    }

    public void setListeners(ActionListener listener){
        leftPanel.setListeners(listener);
        selectHourPanel.setListeners(listener);
        selectSeatsPanel.setListeners(listener);
        detailsPanel.setListeners(listener);
    }

    public JPanel getMainPanel(){
        return mainPanel;
    }

    public SelectHour getSelectHourPanel(){
        return selectHourPanel;
    }

    public SelectSeats getSelectSeatsPanel(){
        return selectSeatsPanel;
    }

    public Details getDetailsPanel(){
        return detailsPanel;
    }

    public Catalog getCatalogView() {
        return catalogView;
    }

    public void updateCatalog(ActionListener listener) {
        catalogView.initMoviesCatalog(listener);
    }


}
