/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arbi.service;

import com.arbi.core.Global;
import com.arbi.core.LoginUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    public UserDetailsServiceImpl() {
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LoginUser user = Global.getUsers().getUserByName(username);
//        Set<GrantedAuthority> grantedAuthorities = new HashSet();
//        grantedAuthorities.add(new SimpleGrantedAuthority("USER"));
//        return new User(user.getUsername(), user.getPassword(), grantedAuthorities);
        user.setPassword(String.format("{noop}%s", user.getPassword()));
        return User.withUsername(user.getUsername()).password(user.getPassword()).roles("USER").build();
    }
}
