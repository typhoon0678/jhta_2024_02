package com.typhoon0678.jspmodel2.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CookieManager {

    public static void createCookie(HttpServletResponse response, String cookieName, String cookieValue, int seconds) {
        Cookie cookie = new Cookie(cookieName, cookieValue);
        cookie.setMaxAge(seconds);
        cookie.setPath("/");

        response.addCookie(cookie);
    }

    public static void deleteCookie(HttpServletResponse response, String cookieName) {
        createCookie(response, cookieName, null, 0);
    }

}