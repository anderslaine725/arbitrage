/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arbi.engine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ExchangeData {
    private List<ExchangeValue> exchangeData;
    private static final int LIMIT = 50;

    public ExchangeData() {
        exchangeData = new ArrayList<>();
    }
    
    public void addExchangeData(ExchangeValue value) {
        for (Iterator<ExchangeValue> iterator = exchangeData.iterator(); iterator.hasNext();) {
            ExchangeValue data = iterator.next();
            if (data.getSymbol().equals(value.getSymbol()))
                iterator.remove();
        }
        
        exchangeData.add(value);
        
        if (exchangeData.size() > LIMIT) {
            int k = exchangeData.size();
            List<ExchangeValue> tmp = new ArrayList<>();
            tmp.addAll(exchangeData);
            do {
                tmp.remove(0);
                k = tmp.size();
            } while (k <= LIMIT);
            exchangeData.clear();
            exchangeData.addAll(tmp);
            tmp.clear();
            tmp = null;
        }
    }
    
    public List<ExchangeValue> getExchangeData() {
        List<ExchangeValue> result = new ArrayList<>();
        result.addAll(exchangeData);
        Collections.reverse(result);
        
        return result;
    }
    
    public ExchangeValue getLastEvent() {
        return exchangeData.get(exchangeData.size() - 1);
    }
    
    public void clear() {
        exchangeData.clear();
    }
}
