package com.zihbot.pfm.security;

import com.zihbot.pfm.configuration.ApplicationConstants;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration
@Profile(ApplicationConstants.PROFILE_DEV)
public class DevHttpSecurityConfig implements HttpSecurityConfig {

    @Override
    public void configure(HttpSecurity security) throws Exception {
        security.httpBasic();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {

    }
}
