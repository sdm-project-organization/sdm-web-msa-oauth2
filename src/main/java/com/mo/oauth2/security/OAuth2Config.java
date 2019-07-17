//package com.example.oauth2.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.factory.PasswordEncoderFactories;
//import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
//
//@Configuration
//public class OAuth2Config extends AuthorizationServerConfigurerAdapter /* 스프링시큐리티 - 인증 및 인가 수행 매커니즘 */ {
//
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Qualifier("userDetailsServiceBean")
//    @Autowired
//    private UserDetailsService userDetailsService;
//
//    /* 인증된 서비스에 등록된 클라이언트 APP을 정의 */
//    @Override
//    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        clients.inMemory()
//                .withClient("eagleeye")
//                .secret(PasswordEncoderFactories
//                        .createDelegatingPasswordEncoder()
//                        .encode("thisissecret"))
//                // OAuth2 서비스에서 지원하는 인가 그랜트타입을 구분
//                .authorizedGrantTypes("refresh_token", "password", "client_credentials")
//                // 호출 APP이 Oauth2 서버에 AccessToken을 요청할 때 APP 수행 경계를 정의
//                .scopes("webclient", "mobileclient");
//    }
//
//    @Override
//    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//        endpoints
//                .authenticationManager(authenticationManager)
//                .userDetailsService(userDetailsService);
//    }
//
//    /*@Override
//    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
//        security.allowFormAuthenticationForClients();
//    }*/
//}