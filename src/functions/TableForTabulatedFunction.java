package functions;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;

/**
 * Created by gijoe on 6/25/2015.
 */
public class TableForTabulatedFunction extends DefaultTableModel implements TableModel {

    private TabulatedFunction tabulatedFunction;
    private Component component;
    private int rowCount;
    private int columnCount;

    public TableForTabulatedFunction(TabulatedFunction tabulatedFunction, Component component) {
        this.tabulatedFunction = tabulatedFunction;
        this.component = component;
    }

    @Override
    public int getRowCount() {
        if (tabulatedFunction != null) {
            return tabulatedFunction.getPointsCount();
        } else {
            return 3;
        }

    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public String getColumnName(int index) {
        if (index == 0) {
            return "x";
        }
        if (index == 1) {
            return "y";
        }
        return null;
    }

    public Class getColumnClass() {
        return Double.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (rowIndex > tabulatedFunction.getPointsCount() || columnIndex > 1) {
            return false;
        }

        return true;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (columnIndex == 0) {
            return tabulatedFunction.getPointX(rowIndex);
        }
        if (columnIndex == 1) {
            return tabulatedFunction.getPointY(rowIndex);
        }
        return null;
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        try {
            if (columnIndex == 0) {
                tabulatedFunction.setPointX(rowIndex, Double.parseDouble((String) value));
            }
            if (columnIndex == 1) {
                tabulatedFunction.setPointY(rowIndex, Double.parseDouble((String) value));
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(component, ex);
        }
    }
}
