/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arbi.listener;

import com.arbi.core.Coin;
import com.arbi.core.Config;
import com.arbi.core.Constant;
import com.arbi.core.Global;
import com.arbi.core.IExchangeApiClientPlugin;
import com.arbi.core.Mapping;
import com.arbi.database.Db;
import com.arbi.engine.BinanceAggTradeData;
import com.arbi.engine.EventData;
import com.arbi.engine.ExchangeData;
import com.arbi.engine.ExchangeFee;
import com.arbi.engine.ExchangeSymbol;
import com.arbi.engine.ExchangeValue;
import com.arbi.engine.MarketData;
import com.arbi.util.NumberUtil;
import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiRestClient;
import com.binance.api.client.BinanceApiWebSocketClient;
import com.binance.api.client.domain.account.TradeFee;
import com.binance.api.client.domain.account.TradeFeeResult;
import com.binance.api.client.domain.general.ExchangeInfo;
import com.binance.api.client.domain.general.SymbolInfo;
import com.github.jnidzwetzki.bitfinex.v2.BitfinexApiBroker;
import com.github.jnidzwetzki.bitfinex.v2.entity.APIException;
import com.github.jnidzwetzki.bitfinex.v2.entity.BitfinexCurrencyPair;
import com.github.jnidzwetzki.bitfinex.v2.entity.BitfinexTick;
import com.github.jnidzwetzki.bitfinex.v2.entity.symbol.BitfinexTickerSymbol;
import com.github.jnidzwetzki.bitfinex.v2.manager.QuoteManager;
import java.io.Closeable;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.BiConsumer;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.bitfinex.v1.BitfinexExchange;
import org.knowm.xchange.bitfinex.v1.service.BitfinexMarketDataServiceRaw;

public class ArbiContextListener implements ServletContextListener {
    private static IExchangeApiClientPlugin apiClientPlugin;
    private BinanceApiWebSocketClient binanceApiWSClient;    
    private ExchangeSymbol exchangeSymbol;
    private List<Closeable> wss;
    private BinanceAggTradeData aggTradeData;
    private MarketData binanceMarketData;
    private MarketData bitfinexMarketData;
    private ExchangeData exchangeData;
    private ExchangeFee exchangeFee;
    private List<BitfinexApiBroker> brokers; 
    private String prevEx = "";
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        
        Config config = new Config(sce.getServletContext());
        config.loadConfig();
        
        Global.setConfig(config);
        
        Db db = new Db();
        Global.setDb(db);
        Global.setUsers(db);
        Global.setCtxt(this);
                
        if (exchangeSymbol == null)
            exchangeSymbol = new ExchangeSymbol();
        
        if (exchangeFee == null)
            exchangeFee = new ExchangeFee();
        
        if (exchangeData == null)
            exchangeData = new ExchangeData();
                
//        BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance("AHV0roe8BRIELORQltr3jl3JP4NVtVXunC7s23caVehBo8QCwsmPnTRDmcY8azBT", "sybpu7VynPpsEaogZ2LmJsGxz2Hzc5J0hPXK9RrT0lPDylE7hjsNHKpj1ie2ZR8B");
//        BinanceApiRestClient client = factory.newRestClient();
//        ExchangeInfo exchangeInfo = client.getExchangeInfo();
//
//        List<SymbolInfo> symbols = exchangeInfo.getSymbols();
//        
//        for (SymbolInfo info: symbols)
//            exchangeSymbol.addSymbol(info.getSymbol());
//        
//        TradeFeeResult result = client.tradeFee(null);
//        
//        if (result != null) {
//            for (TradeFee tradeFee: result.getTradeFee()) {
//                fee.putFee(tradeFee.getSymbol(), tradeFee.getTaker());
//            }
//        }
        
        wss = new ArrayList<>();
        
        brokers = new ArrayList<>();
    }
    
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        if (apiClientPlugin != null) 
            apiClientPlugin.close();
        
        if (wss != null) {
            for (Closeable ws: wss) {
                try {
                    ws.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
        
        if (brokers != null) {
            for (BitfinexApiBroker broker: brokers) {
                broker.close();
            }
        }
        
        if (binanceApiWSClient != null)
            binanceApiWSClient.close();
    }

    public static IExchangeApiClientPlugin getApiClientPlugin() {
        return apiClientPlugin;
    }
    
    public String calcGap(String selectedPrice, String comparedPrice, String transactionFee) {
        if (selectedPrice == null || selectedPrice.isEmpty() || (Double.valueOf(selectedPrice)) == 0)
            return "0.00";
        
        BigDecimal A = new BigDecimal(selectedPrice);
        BigDecimal B = new BigDecimal(comparedPrice);
        BigDecimal fee = new BigDecimal(transactionFee);
        
        BigDecimal A1 = A.subtract(fee);
        BigDecimal B1 = B.subtract(fee);
        
//        BigDecimal gap = B1.subtract(A1).divide(A);
//        gap = gap.multiply(new BigDecimal(100));
        double gap = (B1.doubleValue() - A1.doubleValue()) / A.doubleValue() ;
        gap *= 100;
        return NumberUtil.format(gap, "#.00");
    }
    
    public MarketData getBinanceMarketData() {
        return binanceMarketData;
    }

    public MarketData getBitfinexMarketData() {
        return bitfinexMarketData;
    }

    public ExchangeData getExchangeData() {
        return exchangeData;
    }
       
    public String findSymbol(String exchangeName, String findExchangeName, String symbol) {
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
        
        return findSymbol;
    }
    
    public void getAllMarketData(String exchangeName, String key) throws IOException {        
        if (wss != null) {
            for (Closeable ws: wss) {
                try {
                    ws.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
        
        wss.clear();
        
        if (brokers != null) {
            for (BitfinexApiBroker broker: brokers) {
                broker.close();
            }
        }
        
        brokers.clear();
        
        if (binanceApiWSClient != null)
            binanceApiWSClient.close();
        
//        if (bitfinexApiBroker != null)
//            bitfinexApiBroker.close();
        
        exchangeSymbol.clear();
        exchangeFee.clear();
        exchangeData.clear();
        
        if (exchangeName.equals("Binance")) {
            BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance("AHV0roe8BRIELORQltr3jl3JP4NVtVXunC7s23caVehBo8QCwsmPnTRDmcY8azBT", "sybpu7VynPpsEaogZ2LmJsGxz2Hzc5J0hPXK9RrT0lPDylE7hjsNHKpj1ie2ZR8B");
            BinanceApiRestClient client = factory.newRestClient();
            ExchangeInfo exchangeInfo = client.getExchangeInfo();
            List<SymbolInfo> symbols = exchangeInfo.getSymbols();
            
            if (symbols != null) {
                for (SymbolInfo info: symbols)
                    exchangeSymbol.addSymbol(info.getSymbol());
            }
            
            TradeFeeResult result = client.tradeFee(null);
        
            if (result != null) {
                for (TradeFee tradeFee: result.getTradeFee())
                    exchangeFee.putFee(tradeFee.getSymbol(), tradeFee.getTaker());
            }
            
        } else if (exchangeName.equals("Bitfinex")) {
            Exchange exchange = ExchangeFactory.INSTANCE.createExchange(BitfinexExchange.class.getName());
            BitfinexMarketDataServiceRaw dataService = (BitfinexMarketDataServiceRaw) exchange.getMarketDataService();
            Collection<String> symbols = dataService.getBitfinexSymbols();
            if (symbols != null) {
                for (String str: symbols) {
                    String sym = str;
                    if (str.substring(0, 1).equalsIgnoreCase("t") ) {
                        sym = str.substring(1);
                    }
                    exchangeSymbol.addSymbol(sym);
                }    
            }
        }
        
        
        List<String> symbols;
        if (key.equalsIgnoreCase("TUSD") || key.equalsIgnoreCase("USDT")) {
            if (exchangeName.equals("Bitfinex"))
                symbols = exchangeSymbol.getSymbols("usd");
            else
                symbols = exchangeSymbol.getSymbols(key);
        } else {
            symbols = exchangeSymbol.getCoinSymbol();
        }
        
        binanceApiWSClient = BinanceApiClientFactory.newInstance().newWebSocketClient();
                
        for (String symbol: symbols) {
            String binanceSym = null, bitfinexSym = null;
            
            if (exchangeName.equals(Constant.BINANCE)) {
                binanceSym = symbol;
                bitfinexSym = findSymbol(Constant.BINANCE, Constant.BITFINEX, symbol);
            } else if (exchangeName.equals(Constant.BITFINEX)) {
                binanceSym = findSymbol(Constant.BITFINEX, Constant.BINANCE, symbol);
                bitfinexSym = symbol;
            }
            
            if (binanceSym == null || bitfinexSym == null)
                continue;
            
            Closeable ws = binanceApiWSClient.onAggTradeEvent(binanceSym.toLowerCase(), event -> {
                if (binanceMarketData == null)
                    binanceMarketData = new MarketData();
                
                System.out.println(event);

                EventData eventData = new EventData();
                eventData.setSymbol(event.getSymbol());
                eventData.setPrice(event.getPrice());
                eventData.setQuantity(event.getQuantity());

                binanceMarketData.addMarketData(eventData);
                
                if (exchangeName.equals(Constant.BINANCE)) {
                    ExchangeValue value = new ExchangeValue();
                    value.setSymbol(event.getSymbol());
                    value.setPrice(event.getPrice());
                    value.setVolume(event.getQuantity());
                    
                    if (bitfinexMarketData != null) {
                        EventData bitfinex = bitfinexMarketData.findMaketData(Constant.BINANCE, Constant.BITFINEX, value.getSymbol());
                        if (bitfinex != null) {
                            value.setPrice1(bitfinex.getPrice());
                            value.setVolume1(bitfinex.getQuantity());
                            String fees = this.exchangeFee.getFee(value.getSymbol());
                            value.setGap1(calcGap(value.getPrice(), bitfinex.getPrice(), fees));
                        }
                    }
                    exchangeData.addExchangeData(value);
                }
            });
            wss.add(ws);
            
//            if (symbol.toUpperCase().startsWith("TUSD")) {
//                symbol = symbol.substring(1);
//            } else if (symbol.toUpperCase().endsWith("TUSD")) {
//                String[] strs = StringUtils.split(symbol.toUpperCase(), "TUSD");
//                symbol = strs[0] + "TUSD".substring(1);
//            } else if (symbol.toUpperCase().startsWith("USDT")) {
//                String[] strs = StringUtils.split(symbol.toUpperCase(), "USDT");
//                symbol = "USDT".substring(0, 3) + strs[1];
//            } else if (symbol.toUpperCase().endsWith("USDT")) {
//                String[] strs = StringUtils.split(symbol.toUpperCase(), "USDT");
//                symbol = strs[0] + "USDT".substring(0, 3);
//            }
            
//            System.out.println(symbol);
                
            int mid = bitfinexSym.length() / 2;
            String s1 = bitfinexSym.substring(0, mid);
            String s2 = bitfinexSym.substring(mid);
            
//            Collection<BitfinexCurrencyPair> pairs = BitfinexCurrencyPair.values();
//            boolean found = false;
//            for (BitfinexCurrencyPair pair: pairs) {
//                if (pair.toBitfinexString().toLowerCase().equals(("t" + s1 + s2).toLowerCase())) {
//                    found = true;
//                    break;
//                }
//            }
//            
//            if (!found)
//                continue;
            
//            Runnable runnable = new Runnable() {
//                @Override
//                public void run() {
                    BitfinexCurrencyPair bitfinexCurrencyPair = BitfinexCurrencyPair.of(s1.toUpperCase(),s2.toUpperCase());
                        
                    BitfinexApiBroker bitfinexApiBroker = new BitfinexApiBroker();

                    try {
                        bitfinexApiBroker.connect();
                        Thread.sleep(1500);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                    final BiConsumer<BitfinexTickerSymbol, BitfinexTick> callback = (s, tick) -> {
                        System.out.format("symbol (%s) Got BitfinexTick (%s)\n", s.getBitfinexCurrencyPair().toBitfinexString(), tick);
                        
                        if (bitfinexMarketData == null)
                            bitfinexMarketData = new MarketData();
                        EventData event = new EventData();
                        event.setSymbol(s.getBitfinexCurrencyPair().toBitfinexString().substring(1));
                        event.setPrice(tick.getLastPrice().toString());
                        event.setQuantity(tick.getVolume().toString());
                        bitfinexMarketData.addMarketData(event);

                        if (exchangeName.equals(Constant.BITFINEX)) {
                            ExchangeValue value = new ExchangeValue();
                            value.setSymbol(event.getSymbol());
                            value.setPrice(event.getPrice());
                            value.setVolume(event.getQuantity());

                            if (binanceMarketData != null) {
                            EventData binance = binanceMarketData.findMaketData(Constant.BITFINEX, Constant.BINANCE, value.getSymbol());
                                if (binance != null) {
                                    value.setPrice1(binance.getPrice());
                                    value.setVolume1(binance.getQuantity());
                                    String fees = exchangeFee.getFee(value.getSymbol());
                                    value.setGap1(calcGap(value.getPrice(), binance.getPrice(), fees));
                                }
                            }
                            exchangeData.addExchangeData(value);
                        }
                    };

                    final BitfinexTickerSymbol bitfinexSymbol = new BitfinexTickerSymbol(bitfinexCurrencyPair);

                    final QuoteManager quoteManager = bitfinexApiBroker.getQuoteManager();
                    try {
                        quoteManager.registerTickCallback(bitfinexSymbol, callback);
                    } catch (APIException ex) {
                        ex.printStackTrace();
                    }
                    quoteManager.subscribeTicker(bitfinexSymbol);
                    brokers.add(bitfinexApiBroker);
//                }
//            };
            
//            new Thread(runnable).start();
        }
           
    }
    
    
}
