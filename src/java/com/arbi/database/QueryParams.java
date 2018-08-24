/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arbi.database;

import java.util.ArrayList;
import java.util.List;

public class QueryParams {
    private List<Object> conditionParams = new ArrayList();

    public int size() {
        return this.conditionParams.size();
    }

    public Object get(int index) {
        return this.conditionParams.get(index);
    }

    public void add(Object obj) {
        this.conditionParams.add(obj);
    }

    public boolean isEmpty() {
        return this.conditionParams.isEmpty();
    }

    public void clear() {
        if (conditionParams != null)
            conditionParams.clear();
    }
}
