/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arbi.core;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import java.io.FileReader;
import java.util.List;
import javax.servlet.ServletContext;

public class Config {
    private String rootDir;
    private String configDir;
    private String configPath;
    private String userPath;    
    private String pluginPath;
    private String mappingPath;
    
    private List<Exchange> exchanges;
    private List<FiatCoin> fiat_coins;
    private List<Mapping> mappings;

    public String getPluginPath() {
        return pluginPath;
    }    
    
    public String getConfigPath() {
        return configPath;
    }

    public String getUserPath() {
        return userPath;
    }

    public List<Exchange> getExchanges() {
        return exchanges;
    }

    public List<FiatCoin> getFiatCoins() {
        return fiat_coins;
    }

    public String getMappingPath() {
        return mappingPath;
    }

    public List<Mapping> getMappings() {
        return mappings;
    }
    
    public Config(ServletContext ctxt) {
        rootDir = ctxt.getRealPath("/");
        configDir = rootDir + "/WEB-INF/config/";
        pluginPath = rootDir + "/WEB-INF/plugin/";
        configPath = configDir + "config.json";
        userPath = configDir + "users.json";
        mappingPath = configDir + "coin_mappings.json";
    }
    
    public boolean loadConfig() {
        //load config        
        Gson gson = new Gson();
        
        try {
            JsonReader reader = new JsonReader(new FileReader(configPath));
            ConfigModel config = gson.fromJson(reader, ConfigModel.class);
            exchanges = config.exchanges;
            fiat_coins = config.fiat_coins;            
            reader.close();
            
            reader = new JsonReader(new FileReader(mappingPath));
            MappingModel mapping = gson.fromJson(reader, MappingModel.class);
            mappings = mapping.mappings;
            
        } catch (Exception ex) { 
            System.out.println(String.format("load config failed: %s", ex.toString()));
            
            return false;
        }
        
        return true;
    }
    
    public static class ConfigModel {
        public List<Exchange> exchanges;
        public List<FiatCoin> fiat_coins;
    }
    
    public static class MappingModel {
        public List<Mapping> mappings;
    }
}
