package com.pratham.blogapp.Security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

// This is like the first Entry Gate for the user.
// If an unauthorized user tries to access a secured REST API without a valid JWT token,
// Spring Security will call this class and return an Unauthorized (401) response.

@Component
public class JwtAuthenticationentryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Unauthorized: Access Denied");
    }
}
