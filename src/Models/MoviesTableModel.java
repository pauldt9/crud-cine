package Models;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class MoviesTableModel extends AbstractTableModel {
    private ArrayList<MovieShowtime> data = new ArrayList<MovieShowtime>();
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
        MovieShowtime showtime = data.get(rowIndex);
        Movie movie = data.get(rowIndex).getMovie();
        Room room = data.get(rowIndex).getRoom();

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
        MovieShowtime showtime = data.get(rowIndex);
        try {
            switch(columnIndex) {
                case 4:
                    showtime.setShowTime(String.valueOf(value));
                    break;
            }
            fireTableCellUpdated(rowIndex, columnIndex);
        }catch(IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }

    public void addRow(MovieShowtime movieShowtime) {
        data.add(movieShowtime);
        fireTableRowsInserted(data.size() - 1, data.size() - 1);
    }

    public void cleanTable() {
        data.clear();
        fireTableDataChanged();
    }

    public MovieShowtime getRowData(int row) {
        return data.get(row);
    }

    public void setRowData(int id, MovieShowtime MS) {
        int index = getRowById(id);
        if(index == -1) {
            return;
        }
        data.set(index, MS);
        fireTableRowsUpdated(index, index);
    }

    public int getRowById(int id) {
        for(int i = 0; i < data.size(); i++) {
            if(data.get(i).getIdShowtime() == id) {
                return i;
            }
        }
        return -1;
    }
}
