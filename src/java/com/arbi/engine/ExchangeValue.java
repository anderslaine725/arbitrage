/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arbi.engine;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class ExchangeValue {
    private String symbol;
    private String price;
    private String volume;
    private String gap;
    private String exchangeName;
    
    private List<ExchangeValue> valueList;

    public ExchangeValue() {
        this.symbol = "";
        this.price = "0";
        this.volume = "0";
        this.gap = "0";
        this.exchangeName = "";
        this.valueList = new ArrayList<>();
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getGap() {
        return gap;
    }

    public void setGap(String gap) {
        this.gap = gap;
    }

    public String getExchangeName() {
        return exchangeName;
    }

    public void setExchangeName(String exchangeName) {
        this.exchangeName = exchangeName;
    }

    public List<ExchangeValue> getValueList() {
        return valueList;
    }

    public void setValueList(List<ExchangeValue> valueList) {
        this.valueList = valueList;
    }
    
    public void addExchangeValue(ExchangeValue value) {
        this.valueList.add(value);
    }
}
