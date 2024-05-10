package com.typhoon0678.jsp03.util;

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

    public static String readCookie(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies();
        String cookieValue = "";
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(cookieName)) {
                    cookieValue = cookie.getValue();
                }
            }
        }

        return cookieValue;
    }

    public static void deleteCookie(HttpServletResponse response, String cookieName) {
        createCookie(response, cookieName, null, 0);
    }

}
