package com.typhoon0678.board_practice.social;

import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
public class NaverUserInfo implements SocialUserInfo {

    private final Map<String, Object> attributes;

    @Override
    public String getEmail() {
        return getResponse().get("email");
    }

    @Override
    public String getName() {
        return getResponse().get("nickname");
    }

    @Override
    public String getProvider() {
        return "naver";
    }

    @Override
    public String getProviderId() {
        return getProvider() + "_" + getResponse().get("id");
    }

    private Map<String, String> getResponse() {
        return (Map<String, String>) attributes.get("response");
    }
}
