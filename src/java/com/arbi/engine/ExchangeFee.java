/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arbi.engine;

import java.util.HashMap;
import java.util.Map;

public class ExchangeFee {
    Map<String, String> map;

    public ExchangeFee() {
        map = new HashMap<>();
    }
    
    public void clear() {
        map.clear();
    }
    
    public void putFee(String symbol, String fee) {
        String key = symbol.toLowerCase();
        map.put(key, fee);
    }
    
    public String getFee(String symbol) {
        String key = symbol.toLowerCase();
        if (map.containsKey(key))
            return map.get(key);
        else
            return "0";
    }
}
