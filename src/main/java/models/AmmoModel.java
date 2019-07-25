package models;

import javax.swing.table.AbstractTableModel;

public class AmmoModel extends AbstractTableModel {
    private String[] columnNames = {
            "Caliber", "Name", "Damage", "Penetration Value", "Armour Damage %",
            "Fragmentation Chance", "Vs Class 1", "Vs Class 2", "Vs Class 3", "Vs Class 4", "Vs Class 5",
            "Vs Class 6"
    };
    private Object[][] rowData = new Object[14][columnNames.length];

    @Override
    public int getRowCount() {
        return 14;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public boolean isCellEditable(int i, int i1) {
        return false;
    }

    @Override
    public Object getValueAt(int row, int col) {
        return rowData[row][col];
    }

    @Override
    public void setValueAt(Object o, int row, int col) {
        rowData[row][col] = o;
        fireTableCellUpdated(row, col);
    }
}
