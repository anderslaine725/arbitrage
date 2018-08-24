/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arbi.core;

public interface IExchangeApiClientPlugin {
    void initialize();
    
    String getName();
    void close();
}
