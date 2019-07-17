package com.mo.oauth2.security;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

public class JWTTokenEnhancer implements TokenEnhancer {

    // DB Connection 생략
    // @Autowired
    // private OrgUserRepository orgUserRepository;

    private String getOrgId(String userName) {
        // UserOrganization orgUser = orgUserRepository.findByUserName(userName);
        return "orgId";
    }

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {
        Map<String, Object> additionalInfo = new HashMap<>();
        String orgId = getOrgId(oAuth2Authentication.getName());
        additionalInfo.put("organizationId", orgId);
        // 모든 추가 속성은 `HashMap`에 추가하고 메서드에 전달된 accessToken 변수에 설정
        ((DefaultOAuth2AccessToken) oAuth2AccessToken).setAdditionalInformation(additionalInfo);
        return oAuth2AccessToken;
    }
}
