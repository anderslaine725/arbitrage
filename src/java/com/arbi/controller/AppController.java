/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arbi.controller;

import com.arbi.core.Exchange;
import com.arbi.core.FiatCoin;
import com.arbi.core.Global;
import com.arbi.engine.ExchangeData;
import com.arbi.engine.ExchangeValue;
import com.arbi.form.search.SearchForm;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/app")
public class AppController {
    
    @RequestMapping(value = "/index")
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response, SearchForm searchForm) throws IOException {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("homeLayout");
        request.setAttribute("navTitle", "index");
        
        List<Exchange> exchanges = Global.getConfig().getExchanges();
        List<FiatCoin> fiat_coins = Global.getConfig().getFiatCoins();
        
        if (request.getMethod().equals("GET")) {            
            if (exchanges != null && !exchanges.isEmpty())
                searchForm.setExchange(exchanges.get(0).getId());
            if (fiat_coins != null && !fiat_coins.isEmpty())
                searchForm.setFiatCoin(fiat_coins.get(0).getId());
        }
        
        if (searchForm.getExchange() != null && searchForm.getFiatCoin() != null)
            Global.getCtxt().getAllMarketData(searchForm.getExchange(), searchForm.getFiatCoin());
        
        mav.addObject("exchanges", exchanges);
        mav.addObject("_exchanges", exchanges);
        mav.addObject("fiat_coins", fiat_coins);
        mav.addObject("searchForm", searchForm);
        
        return mav;
    }
    
    @RequestMapping(value = "/getMarketData")
    public ModelAndView getMarketData(HttpServletRequest request, HttpServletResponse response) {
        ExchangeData exchangeData = Global.getCtxt().getExchangeData();
        List<ExchangeValue> eventDataList = null;
        
        if (exchangeData != null)
            eventDataList = exchangeData.getExchangeData();
        
        ModelAndView mav = new ModelAndView();
        mav.setViewName("eventDataLayout");
        mav.addObject("eventDataList", eventDataList);
        
        return mav;
    }
    
    @RequestMapping(value = "/getBiggestGap")
    public void getBiggestGap(HttpServletRequest request, HttpServletResponse response) {
        JSONObject json = new JSONObject();
        ExchangeData exchangeData = Global.getCtxt().getExchangeData();
        List<ExchangeValue> eventDataList = null;
        
        if (exchangeData != null)
            eventDataList = exchangeData.getExchangeData();
        
        if (eventDataList != null && !eventDataList.isEmpty()) {
            Collections.sort(eventDataList, new Comparator<ExchangeValue>() {
                @Override
                public int compare(ExchangeValue o1, ExchangeValue o2) {
                    return (int)(Double.valueOf(o2.getGap1()) - Double.valueOf(o1.getGap1()));
                }
                
            });
            String exchangeName = "";    
            ExchangeValue event = eventDataList.get(0);
            
            json.put("exchange", "Binance");
            json.put("symbol", event.getSymbol());
            json.put("gap", event.getGap1());
            json.put("volume", event.getVolume1());
        }
        
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        
        try {
            json.writeJSONString(response.getWriter());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
    }
    
    @RequestMapping(value = "/gap")
    public ModelAndView gap(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("gapLayout");
        request.setAttribute("navTitle", "gap");
        return mav;
    }
    
    @RequestMapping(value = "/coin")
    public ModelAndView coin(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("coinLayout");
        request.setAttribute("navTitle", "coin");
        return mav;
    }
    
    
}
