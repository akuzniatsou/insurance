package com.dww.insurance.model;

import com.dww.insurance.domain.SearchResult;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class SearchResultTableModel extends AbstractTableModel {

    private String[] columns = {"User ID", "Surname", "Name", "Body ID"};
    private List<SearchResult> list;

    public SearchResultTableModel(List<SearchResult> list) {
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
            temp = list.get(rowIndex).getPersonId();
        } else if (columnIndex == 1) {
            temp = list.get(rowIndex).getLastName();
        } else if (columnIndex == 2) {
            temp = list.get(rowIndex).getName();
        } else if (columnIndex == 3) {
            temp = list.get(rowIndex).getBodyNumber();
        }
        return temp;
    }

    @Override
    public String getColumnName(int col) {
        return columns[col];
    }
}
