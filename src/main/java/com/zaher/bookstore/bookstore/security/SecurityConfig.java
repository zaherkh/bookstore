package com.zaher.bookstore.bookstore.security;

import com.zaher.bookstore.bookstore.filter.CustomAuthenticatingFilter;
import com.zaher.bookstore.bookstore.filter.CustomAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CustomAuthenticatingFilter customAuthenticatingFilter = new CustomAuthenticatingFilter(authenticationManagerBean());
        customAuthenticatingFilter.setFilterProcessesUrl("/api/v1/login");
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests().antMatchers("/api/v1/login/**").permitAll();
        http.authorizeRequests().antMatchers("/api/v1/user/register").permitAll();

        http.authorizeRequests().antMatchers(GET, "/api/v1/user/orders/**").hasAnyAuthority("manager", "customer");
        http.authorizeRequests().antMatchers(POST, "/api/v1/user/order/add").hasAnyAuthority("manager", "customer");
        http.authorizeRequests().antMatchers(GET, "/api/v1/user/orders/**").hasAnyAuthority("manager", "customer");
        http.authorizeRequests().antMatchers(GET, "/api/v1/user/orders/**").hasAnyAuthority("manager", "customer");

        http.authorizeRequests().antMatchers(GET, "/api/v1/book/add").hasAnyAuthority("manager");
        http.authorizeRequests().antMatchers(GET, "/api/v1/statistics/get").hasAnyAuthority("manager", "customer");
        http.authorizeRequests().anyRequest().authenticated();
        http.addFilter(customAuthenticatingFilter);
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
