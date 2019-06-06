package com.dww.insurance.model;

import com.dww.insurance.domain.Credentials;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class UserTableModel extends AbstractTableModel {

    private String[] columns = {"Login", "Pass", "Role"};
    private List<Credentials> list;

    public UserTableModel(List<Credentials> list) {
        this.list = list;
    }

    @Override
    public int getRowCount() {
        int size;
        if (list == null) {
            size = 0;
        } else {
            size = list.size();
        }
        return size;
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object temp = null;
        if (columnIndex == 0) {
            temp = list.get(rowIndex).getLogin();
        } else if (columnIndex == 1) {
            temp = list.get(rowIndex).getPassword();
        } else if (columnIndex == 2) {
            temp = list.get(rowIndex).getRole();
        }
        return temp;
    }

    public Credentials getValue(int rowIndex) {
        return list.get(rowIndex);
    }

    @Override
    public String getColumnName(int col) {
        return columns[col];
    }
}
