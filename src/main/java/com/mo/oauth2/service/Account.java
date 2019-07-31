package com.mo.oauth2.service;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Data
public class Account {

    String username;
    String password;
    Collection<GrantedAuthority> authorities;

}
