package com.typhoon0678.security.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
public class CustomFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String errorMsg = "";

        if (exception instanceof BadCredentialsException
                || exception instanceof UsernameNotFoundException) {
            errorMsg = "Bad credentials";
        } else if (exception instanceof InternalAuthenticationServiceException) {
            errorMsg = "Internal Authentication Service";
        }

        setDefaultFailureUrl("/login?error=" + errorMsg);

        super.onAuthenticationFailure(request, response, exception);
    }
}
