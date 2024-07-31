package com.typhoon0678.board_practice.social;

import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
public class KakaoUserInfo implements SocialUserInfo {

    private final Map<String, Object> attributes;

    @Override
    public String getEmail() {
        return "Please Write Your Email Address";
    }

    @Override
    public String getName() {
        Map<String, String> properties = (Map<String, String>) attributes.get("properties");
        return properties.get("nickname");
    }

    @Override
    public String getProvider() {
        return "kakao";
    }

    @Override
    public String getProviderId() {
        return getProvider() + "_" + attributes.get("id");
    }
}
