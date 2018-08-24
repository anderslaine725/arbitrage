/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arbi.database;

import java.util.ArrayList;
import java.util.List;

public class DataSet {
    private List<DataRow> rowSet = new ArrayList();

    public DataRow getRow(int index) {
        return (DataRow)this.rowSet.get(index);
    }

    public int getCount() {
        return this.rowSet.size();
    }

    public boolean isEmpty() {
        return this.rowSet.isEmpty();
    }

    public List<DataRow> getList() {
        return this.rowSet;
    }

    protected void add(DataRow rowMapper) {
        this.rowSet.add(rowMapper);
    }
}
