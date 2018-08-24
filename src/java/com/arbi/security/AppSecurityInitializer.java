/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arbi.security;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

public class AppSecurityInitializer extends AbstractSecurityWebApplicationInitializer {

    public AppSecurityInitializer() {
        super(SecurityConfig.class);
        
        System.out.println("AppSecurityInitializer inited....");
    }
    
}
