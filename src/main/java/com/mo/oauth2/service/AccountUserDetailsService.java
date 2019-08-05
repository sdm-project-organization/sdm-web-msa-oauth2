package com.mo.oauth2.service;

import com.mo.oauth2.client.GuardDiscoveryClient;
import com.mo.oauth2.model.AccountUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountUserDetailsService implements UserDetailsService {

    @Autowired
    GuardDiscoveryClient guardDiscoveryClient;

    /**
     * Account - Database 객체
     * AccountUserDetails - Database 객체를 `Spring Security`로 인증하기 위해서 Wrapping 한 객체
     *
     * */
    @Override
    /*@Transactional(readOnly = true)*/
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = Optional
                .ofNullable(guardDiscoveryClient.getAccount(username))
                .orElseThrow(()-> new UsernameNotFoundException("user not found")) ;
        return new AccountUserDetails(account, account.getAuthorities());
    }

}
