package com.study.Model;

import javax.swing.table.AbstractTableModel;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class DataTableModel extends AbstractTableModel {
    private final List<?> data;
    private final List<String> columnNames;

    public DataTableModel(List<?> data, List<String> columnNames) {
        this.data = data;
        this.columnNames = columnNames;
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        var variable = data.get(rowIndex);
        Field[] fields = variable.getClass().getDeclaredFields();
        Method[] methods = variable.getClass().getDeclaredMethods();

        String fieldName = "get" + fields[columnIndex + 1].getName().substring(0, 1).toUpperCase() +
                fields[columnIndex + 1].getName().substring(1);

        for (Method method : methods) {
            if (method.getName().equals(fieldName)) {
                try {
                    return method.invoke(variable);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        return null;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames.get(column);
    }
}