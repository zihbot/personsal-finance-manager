package com.zihbot.pfm.security;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

public interface HttpSecurityConfig {
    public void configure(HttpSecurity security) throws Exception;
    public void configure(AuthenticationManagerBuilder auth) throws Exception;
}
