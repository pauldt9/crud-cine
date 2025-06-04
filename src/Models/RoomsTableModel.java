package Models;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class RoomsTableModel extends AbstractTableModel {
    private ArrayList<Room> data = new ArrayList<Room>();
    private String column[] = {"Sala", "Capacidad", "Tipo de sala"};

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
        Room room = data.get(rowIndex);

        switch(columnIndex) {
            case 0:
                return room.getRoomName();
            case 1:
                return room.getCapacity();
            case 2:
                return room.getRoomType();
        }
        return null;
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        Room room = data.get(rowIndex);
        try {
            switch(columnIndex) {
            }
            fireTableCellUpdated(rowIndex, columnIndex);
        }catch(IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }

    public void addRow(Room room) {
        data.add(room);
        fireTableRowsInserted(data.size() - 1, data.size() - 1);
    }

    public void cleanTable() {
        data.clear();
        fireTableDataChanged();
    }

    public Room getRowData(int row) {
        return data.get(row);
    }

    public void setRowData(int id, Room M) {
        int index = getRowById(id);
        if(index == -1) {
            return;
        }
        data.set(index, M);
        fireTableRowsUpdated(index, index);
    }

    public int getRowById(int id) {
        for(int i = 0; i < data.size(); i++) {
            if(data.get(i).getIdRoom() == id) {
                return i;
            }
        }
        return -1;
    }
}
