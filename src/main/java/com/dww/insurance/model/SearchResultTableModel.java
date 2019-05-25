package com.dww.insurance.model;

import com.dww.insurance.domain.SearchResult;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * //TODO: [before commit] class description.
 * <p/>
 * Copyright (C) 2019 copyright.com
 * <p/>
 * Date: 05/19/2019
 *
 * @author Andrei Kuzniatsou
 */
public class SearchResultTableModel extends AbstractTableModel {

    private String[] colums = {"User ID", "Surname", "Name", "Body ID"};
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
        return colums.length;
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
        return colums[col];
    }
}
