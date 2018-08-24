/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arbi.engine;

import com.arbi.core.Coin;
import com.arbi.core.Global;
import com.arbi.core.Mapping;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang.StringUtils;

public class MarketData {
    private List<EventData> eventData;
    private static final int LIMIT = 90;

    public MarketData() {
        eventData = new ArrayList<>();
    }
    
    public void addMarketData(EventData event) {
        for (Iterator<EventData> iterator = eventData.iterator(); iterator.hasNext();) {
            EventData data = iterator.next();
            if (data.getSymbol().equals(event.getSymbol()))
                iterator.remove();
        }
        
        eventData.add(event);
        
        if (eventData.size() > LIMIT) {
            int k = eventData.size();
            List<EventData> tmp = new ArrayList<>();
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
    
    public List<EventData> getEventData() {
        List<EventData> result = new ArrayList<>();
        result.addAll(eventData);
        Collections.reverse(result);
        
        return result;
    }
    
    public EventData getLastEvent() {
        return eventData.get(eventData.size() - 1);
    }
    
    public EventData findMaketData(String exchangeName, String findExchangeName, String symbol) {
//        if (symbol.toUpperCase().startsWith("TUSD")) {
//            symbol = symbol.substring(1);
//        } else if (symbol.toUpperCase().endsWith("TUSD")) {
//            String[] strs = StringUtils.split(symbol.toUpperCase(), "TUSD");
//            symbol = strs[0] + "TUSD".substring(1);
//        } else if (symbol.toUpperCase().startsWith("USDT")) {
//            String[] strs = StringUtils.split(symbol.toUpperCase(), "USDT");
//            symbol = "USDT".substring(0, 3) + strs[1];
//        } else if (symbol.toUpperCase().endsWith("USDT")) {
//            String[] strs = StringUtils.split(symbol.toUpperCase(), "USDT");
//            symbol = strs[0] + "USDT".substring(0, 3);
//        }

        List<Mapping> mappings = Global.getConfig().getMappings();
        
        if (mappings == null)
            return null;
        
        Mapping own = null, find = null;
        for (Mapping mapping: mappings) {
            if (mapping.getId().equals(exchangeName)) {
                own = mapping;
            }
            
            if (mapping.getId().equals(findExchangeName)) {
                find = mapping;
            }
        }
        
        if (own == null || find == null)
            return null;
        
        List<Coin> coins = own.getCoins();
        String alias = null, findSymbol = null;
        for (Coin coin: coins) {
            if (coin.getSymbol().equalsIgnoreCase(symbol)) {
                alias = coin.getAlias();
                break;
            }
        }
        
        if (alias == null)
            return null;
        
        coins = find.getCoins();
        for (Coin coin: coins) {
            if (coin.getAlias().equalsIgnoreCase(alias)) {
                findSymbol = coin.getSymbol();
                break;
            }
        }
        
        if (findSymbol == null)
            return null;
        
        List<EventData> reverse = getEventData();
        for (EventData event: reverse) {
            if (event.getSymbol().equalsIgnoreCase(findSymbol))
                return event;
        }
        
        return null;
    }
}
