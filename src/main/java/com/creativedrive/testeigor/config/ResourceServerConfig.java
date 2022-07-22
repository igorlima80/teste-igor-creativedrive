package com.creativedrive.testeigor.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/oauth/**").permitAll()
                .antMatchers("/api/usuarios/**").authenticated()
                .antMatchers(HttpMethod.POST, "/api/usuarios").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/usuarios").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/usuarios").hasRole("ADMIN")
                .anyRequest().denyAll()
                .and().cors()
                .and().csrf().disable();
    }



}
