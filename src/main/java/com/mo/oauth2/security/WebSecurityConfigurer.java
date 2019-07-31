package com.mo.oauth2.security;

import com.mo.oauth2.service.AccountUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Autowired
    AccountUserDetailsService accountUserDetailsService;

    /**
     * authenticationManagerBean 스프링 시큐리티가 인증을 처리하는데 사용
     *
     * */
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * userDetailsServiceBean 스프링 시큐리티에서 반환될 사용자 정보를 저장하는데 사용
     *
     * */
    @Override
    @Bean
    public UserDetailsService userDetailsServiceBean() throws Exception {
        return super.userDetailsServiceBean();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        /*return PasswordEncoderFactories.createDelegatingPasswordEncoder();*/
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(accountUserDetailsService)
                .passwordEncoder(passwordEncoder());

        /*auth
                .inMemoryAuthentication()
                .passwordEncoder(encoder)
                .withUser("john.carnell")
                .password(encoder.encode("password1")).roles("USER")
                .and()
                .withUser("william.woodward")
                .password(encoder.encode("password2")).roles("USER", "ADMIN");*/
    }

}
