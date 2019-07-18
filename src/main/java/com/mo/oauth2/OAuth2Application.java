package com.mo.oauth2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestController
@EnableResourceServer
@EnableAuthorizationServer // Oauth2 서비스 명시
public class OAuth2Application extends SpringBootServletInitializer {

    @Value("${signing.key}")
    String signingKey;

    @PostConstruct
    public void init() {
        System.out.println("signingKey " + signingKey);
    }

    public static void main(String[] args) {
        System.setProperty("server.servlet.context-path", "/auth");
        SpringApplication.run(OAuth2Application.class, args);
    }

    @RequestMapping(value = { "/user" }, produces = "application/json")
    public Map<String, Object> user(OAuth2Authentication user) {
        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("user", user.getUserAuthentication().getPrincipal());
        userInfo.put("authorities", AuthorityUtils.authorityListToSet(user.getUserAuthentication().getAuthorities()));
        return userInfo;
    }

    /**
     * Using generated security password: aab9983e-ca06-48f4-9961-f4231d8ef965
     * security.oauth2.client.client-id = 78fbd923-eed4-49af-b1ea-179166a21b6d
     * security.oauth2.client.client-secret = 1ca21b11-2d75-4a33-a68c-1280cd73271c
     * */

}
