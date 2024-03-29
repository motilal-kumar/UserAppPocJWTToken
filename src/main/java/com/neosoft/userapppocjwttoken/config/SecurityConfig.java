package com.neosoft.userapppocjwttoken.config;


import com.neosoft.userapppocjwttoken.filter.SecurityFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * SecurityConfig.
 *
 * @author Motilal Kumar.
 * version 1.0
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private BCryptPasswordEncoder bcryptPasswordEncoder;
    @Autowired
    private InvalidUserAuthenticationEntryPoint authenticationEntryPoint;
    @Autowired
    private SecurityFilter securityFilter;

    /**
     * authenticationManager.
     *
     * @return
     * @throws Exception
     *
     */
    @Override
    @Bean
    protected AuthenticationManager authenticationManager()
            throws Exception
    {
        return super.authenticationManager();
    }

    /**
     * configure.
     * @param auth the {@link AuthenticationManagerBuilder} to use
     * @throws Exception
     *
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(bcryptPasswordEncoder);
    }

    /**
     * configure.
     *
     * @param http the {@link HttpSecurity} to modify
     * @throws Exception
     *
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/user/save","/user/fetch","/user/login").permitAll()
                //.antMatchers("/user/fetch","/user/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                //Register filters for 2nd request onwards...
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)

        //TODO: verify user for 2nd req onwards..


        ;
    }
}
