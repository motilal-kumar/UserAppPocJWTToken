package com.neosoft.userapppocjwttoken.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * AppConfig.
 *
 * @author Motilal Kumar.
 * version  1.0
 *
 */
@Configuration
public class AppConfig {

    /**
     * encoder.
     *
     * @param
     * @return
     *
     */
    @Bean
    public BCryptPasswordEncoder   encoder(){
        return new BCryptPasswordEncoder();
    }
}
