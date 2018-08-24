/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arbi.engine;

import com.arbi.core.IMarketData;
import com.binance.api.client.domain.event.MiniMarketTickersEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BinanceMarketData implements IMarketData {
    Stack<MiniMarketTickersEvent> stack;
    private static final int stackSize = 100;

    public BinanceMarketData() {
        this.stack = new Stack<>();
    }
    
    public void addMarketData(List<MiniMarketTickersEvent> marketData) {
        stack.addAll(marketData);
        
        if (stack.size() > stackSize) {
            int k = stack.size();
            List<MiniMarketTickersEvent> tmp = new ArrayList<>();
            tmp.addAll(stack);
            do {
                tmp.remove(k-1);
                k = tmp.size();
            } while (k <= stackSize);
            
            stack.clear();
            stack.addAll(tmp);
            tmp.clear();
        }
    }
    
//    public List<MiniMarketTickersEvent> getMarketData() {
//        List<MiniMarketTickersEvent> marketData = new ArrayList<>();
//        marketData.addAll(stack);
//    }
    
}
