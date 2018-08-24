/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arbi.engine;

import com.binance.api.client.domain.event.AggTradeEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BinanceAggTradeData {
    private List<AggTradeEvent> eventData;
    private static final int LIMIT = 50;

    public BinanceAggTradeData() {
        this.eventData = new ArrayList<>();
    }
    
    public void addBinanceAggTradeData(AggTradeEvent event) {
        eventData.add(event);
        
        if (eventData.size() > LIMIT) {
            int k = eventData.size();
            List<AggTradeEvent> tmp = new ArrayList<>();
            tmp.addAll(eventData);
            do {
                tmp.remove(0);
                k = tmp.size();
            } while (k <= LIMIT);
            eventData.clear();
            eventData.addAll(tmp);
            tmp.clear();
            tmp = null;
        }
    }
    
    public List<AggTradeEvent> getEventData() {
        List<AggTradeEvent> result = new ArrayList<>();
        result.addAll(eventData);
        Collections.reverse(result);
        
        return result;
    }
}
