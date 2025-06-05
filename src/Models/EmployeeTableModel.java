package Models;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

//idEmployee;
//firstName;
//lastName;
//employeeType;
//username;
//password;

public class EmployeeTableModel extends AbstractTableModel {
    private ArrayList<Employee> data = new ArrayList<Employee>();
    private String column[] = {"Nombre", "Apellido", "Tipo de empleado", "Usuario"};

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
        Employee employee = data.get(rowIndex);

        switch(columnIndex) {
            case 0:
                return employee.getFirstName();
            case 1:
                return employee.getLastName();
            case 2:
                return employee.getEmployeeType();
            case 3:
                return employee.getUsername();
        }
        return null;
    }

    //lo puse en false para evitar que puedan editar en la tabla misma
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        Employee employee = data.get(rowIndex);
        try {
            switch(columnIndex) {
                case 0:
                    employee.setFirstName(String.valueOf(value));
                    break;
                case 1:
                    employee.setLastName(String.valueOf(value));
                    break;
                case 2:
                    employee.setEmployeeType(String.valueOf(value));
                    break;
                case 3:
                    employee.setUsername(String.valueOf(value));
                    break;
            }
            fireTableCellUpdated(rowIndex, columnIndex);
        }catch(IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }

    public void addRow(Employee employee) {
        data.add(employee);
        fireTableRowsInserted(data.size() - 1, data.size() - 1);
    }

    public void cleanTable() {
        data.clear();
        fireTableDataChanged();
    }

    public Employee getRowData(int row) {
        return data.get(row);
    }

    public void setRowData(int id, Employee E) {
        int index = getRowById(id);
        if(index == -1) {
            return;
        }
        data.set(index, E);
        fireTableRowsUpdated(index, index);
    }

    public int getRowById(int id) {
        for(int i = 0; i < data.size(); i++) {
            if(data.get(i).getIdEmployee() == id) {
                return i;
            }
        }
        return -1;
    }
}
