package Models;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class SalesTableModel extends AbstractTableModel {
    private ArrayList<Ticket> data = new ArrayList<Ticket>();
    private String[] column = {"Pelicula", "Sala", "Asiento", "Horario","Precio"};

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
        Ticket ticket = data.get(rowIndex);

        switch (columnIndex){
            case 0:
                return ticket.getFunction().getMovie().getTitle();
            case 1:
                return ticket.getFunction().getRoom().getRoomName();
            case 2:
                return ticket.getSeat().getSeatName();
            case 3:
                return ticket.getFunction().getShowTime();
            case 4:
                return ticket.getPrice();
        }
        return null;
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex){
        Ticket ticket = data.get(rowIndex);

        try{
            switch (columnIndex){
                case 0:
                    ticket.getFunction().getMovie().setTitle(String.valueOf(value));
                    break;
                case 1:
                    ticket.getFunction().getRoom().setRoomName(String.valueOf(value));
                    break;
                case 2:
                    ticket.getSeat().setSeatName(String.valueOf(value));
                    break;
                case 3:
                    ticket.getFunction().setShowTime(String.valueOf(value));
                    break;
                case 4:
                    ticket.setPrice(Integer.parseInt(String.valueOf(value)));
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

    public void addRow(Ticket ticket){
        data.add(ticket);
        fireTableRowsInserted(data.size() - 1, data.size() - 1);
    }

    public void clearTable(){
        data.clear();
        fireTableDataChanged();
    }

    public Ticket getRowData(int row){
        return data.get(row);
    }

    public int getRowById(int id){
        for (int i = 0; i < data.size(); i++){
            if (data.get(i).getIdTicket() == id){
                return i;
            }
        }

        return -1;
    }

    public void setRowData(int id, Ticket ticket){
        int index = getRowById(id);

        if (index == -1){
            return;
        }
        data.set(index, ticket);
        fireTableRowsUpdated(index, index);
    }
}
