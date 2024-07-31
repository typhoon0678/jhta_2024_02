package com.typhoon0678.board_practice.service;


import com.typhoon0678.board_practice.dto.CustomUserDetails;
import com.typhoon0678.board_practice.entity.Member;
import com.typhoon0678.board_practice.social.GoogleUserInfo;
import com.typhoon0678.board_practice.social.KakaoUserInfo;
import com.typhoon0678.board_practice.social.NaverUserInfo;
import com.typhoon0678.board_practice.social.SocialUserInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;


@Service
@Slf4j
@RequiredArgsConstructor
public class OAuth2DetailsService extends DefaultOAuth2UserService {

    private final MemberService memberService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        Member member;
        SocialUserInfo socialUserInfo;

        String provider = userRequest.getClientRegistration().getRegistrationId();
        Map<String, Object> oauth2UserInfo = oAuth2User.getAttributes();

        log.info(oauth2UserInfo.toString());

        switch (provider) {
            case "google" -> {
                socialUserInfo = new GoogleUserInfo(oauth2UserInfo);

                member = Member.builder()
                        .username(socialUserInfo.getEmail())
                        .password(bCryptPasswordEncoder.encode(UUID.randomUUID().toString()))
                        .build();
            }
            case "kakao" -> {
                socialUserInfo = new KakaoUserInfo(oauth2UserInfo);

                member = Member.builder()
                        .username(socialUserInfo.getName())
                        .password(bCryptPasswordEncoder.encode(UUID.randomUUID().toString()))
                        .build();
            }
            case "naver" -> {
                socialUserInfo = new NaverUserInfo(oauth2UserInfo);

                member = Member.builder()
                        .username(socialUserInfo.getEmail())
                        .password(bCryptPasswordEncoder.encode(UUID.randomUUID().toString()))
                        .build();
            }
            default -> throw new OAuth2AuthenticationException("Unsupported provider: " + provider);
        }

        memberService.saveMemberIfNotExist(member);

        return new CustomUserDetails(member, oAuth2User.getAttributes());
    }
}
