package com.yang.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

import javax.servlet.http.HttpServletResponse;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    private static final String RESOURCE_ID = "*";

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId(RESOURCE_ID).stateless(true);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        //http.csrf().disable();
//                .exceptionHandling()
//                .authenticationEntryPoint((request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED))
//                .and()
//                .authorizeRequests()
//                .anyRequest().authenticated()
//                .and()
//                .httpBasic();
        http.authorizeRequests()
                    .antMatchers("/oauth/*").permitAll()
                    .antMatchers("/user").permitAll()
                    .anyRequest().authenticated()
                .and()
                    .csrf().disable();
        //http.csrf().disable();
    }
}
