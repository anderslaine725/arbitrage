package com.arbi.util;

import com.github.ccob.bittrex4j.BittrexExchange;
import com.github.ccob.bittrex4j.dao.Fill;
import com.github.ccob.bittrex4j.dao.Market;
import com.github.ccob.bittrex4j.dao.OrderType;
import com.github.ccob.bittrex4j.dao.Response;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

public class ShowRealTimeFills {

    public static void main(String[] args) throws IOException {

        System.out.println("Press any key to quit");
        
        BittrexExchange bittrexExchange2 = new BittrexExchange();
        Response<Market[]> result = bittrexExchange2.getMarkets();
        
        
        for (Market market: result.getResult()) {
            BittrexExchange bittrexExchange = new BittrexExchange();
        
        bittrexExchange.onUpdateExchangeState(updateExchangeState -> {            
                double volume = Arrays.stream(updateExchangeState.getFills())
                        .mapToDouble(Fill::getQuantity)
                        .sum();
                
                double price = Arrays.stream(updateExchangeState.getFills())
                        .mapToDouble(Fill::getPrice).sum();

                if(updateExchangeState.getFills().length > 0) {
                    System.out.println(String.format("N: %d, %02f volume %02f price for %s", updateExchangeState.getNounce(),
                            volume, price, updateExchangeState.getMarketName()));
                }
            });
        
            bittrexExchange.connectToWebSocket(() -> {
                bittrexExchange.queryExchangeState(market.getMarketName(),exchangeState -> {
                    System.out.println(String.format("%s order book has %d open buy orders and %d open sell orders (500 return limit)",market.getMarketName(),exchangeState.getBuys().length, exchangeState.getSells().length));

                });
                bittrexExchange.subscribeToExchangeDeltas(market.getMarketName(), null);
                bittrexExchange.subscribeToMarketSummaries(null);
            });
        }
        
        
            

            System.in.read();
//        }

        System.out.println("Closing websocket and exiting");
    }
}