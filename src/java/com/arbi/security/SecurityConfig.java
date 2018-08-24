package com.arbi.security;

import com.arbi.core.Global;
import com.arbi.core.LoginUser;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer.AuthorizedUrl;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
//    @Autowired
//    private AuthenticationEntryPoint authEntryPoint;
//    
//    @Autowired
//    private UserDetailsService userDetailsService;
    
    @Bean
    public AuthenticationEntryPoint getAuthenticationEntryPoint() {
        return new AuthenticationEntryPoint();
    }
    
    @Bean
    public UserDetailsService getUserDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                LoginUser user = Global.getUsers().getUserByName(username);
//                Set<GrantedAuthority> grantedAuthorities = new HashSet();
//                grantedAuthorities.add(new SimpleGrantedAuthority("USER"));
//                return new User(user.getUsername(), user.getPassword(), grantedAuthorities);
                user.setPassword(String.format("{noop}%s", user.getPassword()));
                return User.withUsername(user.getUsername()).password(user.getPassword()).roles("USER").build();
            }
        };
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        MCEventSource[] eventSources = MCGlobal.getConfig().getEventSources();
//        List<String> unAuthUrls = new ArrayList();
//        if (eventSources != null) {
//            MCEventSource[] var4 = eventSources;
//            int var5 = eventSources.length;
//
//            for(int var6 = 0; var6 < var5; ++var6) {
//                MCEventSource eventSource = var4[var6];
//                if (!eventSource.isNeedAuthentication()) {
//                    unAuthUrls.add("/api/" + eventSource.getName());
//                }
//            }
//        }

//        ((HttpSecurity) ((AuthorizedUrl) ((AuthorizedUrl) ((HttpSecurity) http.csrf().disable()).authorizeRequests().antMatchers(new String[] {"wbls/"})).permitAll().anyRequest()).authenticated().and()).httpBasic().authenticationEntryPoint(getAuthenticationEntryPoint());

        http.authorizeRequests().anyRequest().authenticated().and().csrf().disable().httpBasic().authenticationEntryPoint(getAuthenticationEntryPoint());

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER);
    } 

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(getUserDetailsService());
    }
}
