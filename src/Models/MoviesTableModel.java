package Models;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class MoviesTableModel extends AbstractTableModel {
    private ArrayList<Movie> data = new ArrayList<Movie>();
    private String column[] = {"Titulo", "Duracion", "Genero", "Clasificacion"};

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return column.length;
    }

    @Override
    public String getColumnName (int columnIndex) {
        return column[columnIndex];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Movie movie = data.get(rowIndex);

        switch(columnIndex) {
            case 0:
                return movie.getTitle();
            case 1:
                return movie.getDuration();
            case 2:
                return movie.getGenre();
            case 3:
                return movie.getClassification();
        }
        return null;
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        Movie movie = data.get(rowIndex);
        try {
            switch(columnIndex) {
            }
            fireTableCellUpdated(rowIndex, columnIndex);
        }catch(IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }

    public void addRow(Movie movie) {
        data.add(movie);
        fireTableRowsInserted(data.size() - 1, data.size() - 1);
    }

    public void cleanTable() {
        data.clear();
        fireTableDataChanged();
    }

    public Movie getRowData(int row) {
        return data.get(row);
    }

    public void setRowData(int id, Movie M) {
        int index = getRowById(id);
        if(index == -1) {
            return;
        }
        data.set(index, M);
        fireTableRowsUpdated(index, index);
    }

    public int getRowById(int id) {
        for(int i = 0; i < data.size(); i++) {
            if(data.get(i).getIdMovie() == id) {
                return i;
            }
        }
        return -1;
    }
}
