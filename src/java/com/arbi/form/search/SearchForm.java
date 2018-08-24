/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arbi.form.search;

public class SearchForm {
    private String exchange;    
    private String searchFrom;
    private String searchTo;    
    private String fiatCoin;

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getSearchFrom() {
        return searchFrom;
    }

    public void setSearchFrom(String searchFrom) {
        this.searchFrom = searchFrom;
    }

    public String getSearchTo() {
        return searchTo;
    }

    public void setSearchTo(String searchTo) {
        this.searchTo = searchTo;
    }

    public String getFiatCoin() {
        return fiatCoin;
    }

    public void setFiatCoin(String fiatCoin) {
        this.fiatCoin = fiatCoin;
    }
    
    
}
