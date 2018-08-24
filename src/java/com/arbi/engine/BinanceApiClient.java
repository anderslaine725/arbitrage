/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arbi.engine;
import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiWebSocketClient;

import com.arbi.core.IExchangeApiClientPlugin;

public class BinanceApiClient implements IExchangeApiClientPlugin {
    private BinanceApiWebSocketClient client;

    public BinanceApiClient() {
        client = BinanceApiClientFactory.newInstance().newWebSocketClient();
    }    
    
    public BinanceApiWebSocketClient getClient() {
        return client;
    }
    
    @Override
    public String getName() {
        return "BinanceApiClient";
    }

    @Override
    public void close() {
        if (client != null)
            client.close();
    }

    @Override
    public void initialize() {
        
    }
    
}
