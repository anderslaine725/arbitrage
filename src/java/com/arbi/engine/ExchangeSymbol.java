/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arbi.engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class ExchangeSymbol {
    List<String> symbols;

    public ExchangeSymbol() {
        this.symbols = new ArrayList<>();
    }
    
    public void addSymbol(String symbol) {
        if (!symbols.contains(symbol))
            symbols.add(symbol);
    }
    
    public List<String> getSymbols(String key) {
        List<String> result = new ArrayList<>();
        for (String symbol: symbols) {
           if (symbol.toLowerCase().startsWith(key.toLowerCase()) || symbol.toLowerCase().endsWith(key.toLowerCase()))
               result.add(symbol);
        }
        return result;
    }
    
    public List<String> getCoinSymbol() {
        List<String> result = new ArrayList<>();
        for (String symbol: symbols) {
           if (symbol.toLowerCase().startsWith("tusd") || symbol.toLowerCase().endsWith("tusd") 
               || symbol.toLowerCase().startsWith("usdt") || symbol.toLowerCase().endsWith("usdt")
               || symbol.toLowerCase().startsWith("usd") || symbol.toLowerCase().endsWith("usd"))
               continue;
           else
               result.add(symbol);
        }
        return result;
    }
    
    public void clear() {
        symbols.clear();
    }
}
