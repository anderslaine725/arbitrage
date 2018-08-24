/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arbi.core;

import com.arbi.engine.ExchangeFee;
import com.arbi.listener.ArbiContextListener;
import java.util.List;

public class Global {
    private static Config _config ;
    private static IUsers _users;
    private static IDb _db;
    private static ArbiContextListener _ctxt;
    private static List<ExchangeFee> _fee;
    
    public static void setConfig(Config config) {
        _config = config;
    }
    
    public static Config getConfig() {
        return _config;
    }
    
    public static void setUsers(IUsers users) {
        _users = users;
    }
    
    public static IUsers getUsers() {
        return _users;
    }

    public static IDb getDb() {
        return _db;
    }

    public static void setDb(IDb db) {
        _db = db;
    }
    
    public static void setCtxt(ArbiContextListener ctxt) {
        _ctxt = ctxt;
    }
    
    public static ArbiContextListener getCtxt() {
        return _ctxt;
    }

    public static List<ExchangeFee> getExchangeFee() {
        return _fee;
    }

    public static void setExchangeFee(List<ExchangeFee> fee) {
        Global._fee = fee;
    }
    
    
}
