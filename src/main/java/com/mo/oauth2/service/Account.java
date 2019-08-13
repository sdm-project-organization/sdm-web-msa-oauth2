package com.mo.oauth2.service;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Data
public class Account {

    private static final String ROLE_NAME = "displayName";

    String username;
    String password;
    List<Map<String, Object>> roles;
    Collection<GrantedAuthority> authorities;

    public Collection<GrantedAuthority> getAuthorities() {
        String[] authorities = roles.stream()
                .map(role -> role.get(ROLE_NAME))
                .toArray(String[]::new);
        return AuthorityUtils.createAuthorityList(authorities);
    }

}
