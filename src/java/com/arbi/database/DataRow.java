/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arbi.database;

import com.arbi.util.DateUtil;
import com.arbi.util.NumberUtil;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

public class DataRow {
    private LinkedHashMap<String, Object> row = new LinkedHashMap();

    public Object get(String columnName) {
        return this.row.containsValue(columnName) ? this.row.get(columnName) : null;
    }

    public int getInt(String columnName) {
        Object obj = this.row.get(columnName);
        return obj != null ? Integer.valueOf(obj.toString()) : 0;
    }

    public long getLong(String columnName) {
        Object obj = this.row.get(columnName);
        return obj != null ? Long.valueOf(obj.toString()) : 0L;
    }

    public String getString(String columnName) {
        Object obj = this.row.get(columnName);
        return obj != null ? obj.toString() : "";
    }

    public boolean getBoolean(String columnName) {
        Object obj = this.row.get(columnName);
        if (obj != null && !StringUtils.isNumeric(obj.toString())) {
            return Boolean.valueOf(obj.toString());
        } else if (obj != null && StringUtils.isNumeric(obj.toString())) {
            return NumberUtil.strToInt(obj.toString(), 0) > 0;
        } else {
            return false;
        }
    }

    public double getDouble(String columnName) {
        Object obj = this.row.get(columnName);
        return obj != null ? Double.valueOf(obj.toString()) : 0.0D;
    }

    public BigDecimal getBigDecimal(String columnName) {
        Object obj = this.row.get(columnName);
        return obj != null ? new BigDecimal(obj.toString()) : null;
    }

    public Date getDateTime(String columnName) {
        Object obj = this.row.get(columnName);
        return obj != null ? DateUtil.parseDate(obj.toString(), "yyyy-MM-dd HH:mm:ss.SSS") : null;
    }

    public Object get(int columnIndex) {
        List<Object> list = new ArrayList(this.row.values());
        return list.get(columnIndex);
    }

    public int getInt(int columnIndex) {
        List<Object> list = new ArrayList(this.row.values());
        Object obj = list.get(columnIndex);
        return obj != null ? Integer.valueOf(obj.toString()) : 0;
    }

    public long getLong(int columnIndex) {
        List<Object> list = new ArrayList(this.row.values());
        Object obj = list.get(columnIndex);
        return obj != null ? Long.valueOf(obj.toString()) : 0L;
    }

    public String getString(int columnIndex) {
        List<Object> list = new ArrayList(this.row.values());
        Object obj = list.get(columnIndex);
        return obj != null ? obj.toString() : "";
    }

    public boolean getBoolean(int columnIndex) {
        List<Object> list = new ArrayList(this.row.values());
        Object obj = list.get(columnIndex);
        if (obj != null && !StringUtils.isNumeric(obj.toString())) {
            return Boolean.valueOf(obj.toString());
        } else if (obj != null && StringUtils.isNumeric(obj.toString())) {
            return NumberUtil.strToInt(obj.toString(), 0) > 0;
        } else {
            return false;
        }
    }

    public double getDouble(int columnIndex) {
        List<Object> list = new ArrayList(this.row.values());
        Object obj = list.get(columnIndex);
        return obj != null ? Double.valueOf(obj.toString()) : 0.0D;
    }

    public BigDecimal getBigDecimal(int columnIndex) {
        List<Object> list = new ArrayList(this.row.values());
        Object obj = list.get(columnIndex);
        return obj != null ? new BigDecimal(obj.toString()) : null;
    }

    public Date getDateTime(int columnIndex) {
        List<Object> list = new ArrayList(this.row.values());
        Object obj = list.get(columnIndex);
        return obj != null ? DateUtil.parseDate(obj.toString(), "yyyy-MM-dd HH:mm:ss.SSS") : null;
    }

    protected void put(String columnName, Object value) {
        this.row.put(columnName, value);
    }
}
