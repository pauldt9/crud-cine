package Models;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class MovieShowtimeTableModel extends AbstractTableModel {
    private ArrayList<MovieShowtime> data = new ArrayList<MovieShowtime>();
    private String[] column = {"Pelicula", "Sala", "Horario"};

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return column.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        MovieShowtime showtime = data.get(rowIndex);

        switch (columnIndex){
            case 0:
                return showtime.getMovie().getTitle();
            case 1:
                return showtime.getRoom().getRoomName();
            case 2:
                return showtime.getShowTime();
        }
        return null;
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex){
        MovieShowtime showtime = data.get(rowIndex);

        try{
            switch (columnIndex){
                case 0:
                    showtime.getMovie().setTitle(String.valueOf(value));
                    break;
                case 1:
                    showtime.getRoom().setRoomName(String.valueOf(value));
                    break;
                case 2:
                    showtime.setShowTime(String.valueOf(value));
                    break;
            }

            fireTableCellUpdated(rowIndex, columnIndex);
        } catch (IllegalArgumentException e){
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

    @Override
    public String getColumnName(int columnIndex) {
        return column[columnIndex];
    }

    public void addRow(MovieShowtime showtime){
        data.add(showtime);
        fireTableRowsInserted(data.size() - 1, data.size() - 1);
    }

    public void clearTable(){
        data.clear();
        fireTableDataChanged();
    }

    public MovieShowtime getRowData(int row){
        return data.get(row);
    }

    public int getRowById(int id){
        for (int i = 0; i < data.size(); i++){
            if (data.get(i).getIdShowtime() == id){
                return i;
            }
        }

        return -1;
    }

    public void setRowData(int id, MovieShowtime showtime){
        int index = getRowById(id);

        if (index == -1){
            return;
        }
        data.set(index, showtime);
        fireTableRowsUpdated(index, index);
    }
}
