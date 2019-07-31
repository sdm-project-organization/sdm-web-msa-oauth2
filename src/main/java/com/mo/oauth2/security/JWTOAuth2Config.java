package com.mo.oauth2.security;

import com.mo.oauth2.service.AccountUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.Arrays;

@Configuration
public class JWTOAuth2Config extends AuthorizationServerConfigurerAdapter {

    static final String CLIENT_ID = "eagleeye";
    static final String CLIENT_SECRET = "thisissecret";

    @Autowired
    private AuthenticationManager authenticationManager;

//    @Qualifier("userDetailsServiceBean")
//    @Autowired
//    private UserDetailsService userDetailsService;

    @Autowired
    private AccountUserDetailsService accountUserDetailsService;

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private DefaultTokenServices tokenServices;

    @Autowired
    private TokenEnhancer jwtTokenEnhancer;

    @Autowired
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // 서비스에 등록될 클라이언트를 정의
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient(CLIENT_ID)
                .secret(passwordEncoder.encode(CLIENT_SECRET))
                .authorizedGrantTypes("refresh_token",
                        "password",
                        "client_credentials")
                .scopes("webclient",
                        "mobileclient")
                /*.accessTokenValiditySeconds(600)*/
                /*.refreshTokenValiditySeconds(3600)*/
        ;
    }

    // 스프링의 기본 인증 관리자와 스프링과 함께 제공되는 사용자 상세 서비스를 이용한다고 알려줌
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        // 스프링 OAuth TokenEnhancerChain 클래스를 등록하면 여러 `TokenEnhancer`를 후킹 가능
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(
                Arrays.asList(jwtTokenEnhancer, jwtAccessTokenConverter)
        );

        endpoints
                .tokenStore(tokenStore)
                .accessTokenConverter(jwtAccessTokenConverter)
                // configure()를 호출할 때 전달된 endpoints 매개변수에 `tokenEnhancerChain` 연결
                // .tokenEnhancer(tokenEnhancerChain)
                .authenticationManager(authenticationManager)
                .userDetailsService(accountUserDetailsService);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                /*.tokenKeyAccess("permitAll()")*/
                .allowFormAuthenticationForClients()
                /*.passwordEncoder(this.passwordEncoder)*/
        ;
    }

}
