package com.mo.oauth2.service;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.Collection;
import java.util.List;

@Data
public class Account {

    String username;
    String password;
    List<String> roles;
    Collection<GrantedAuthority> authorities;

    public Collection<GrantedAuthority> getAuthorities() {
        String[] authorities = roles.toArray(new String[roles.size()]);
        return AuthorityUtils.createAuthorityList(authorities);
    }

}
