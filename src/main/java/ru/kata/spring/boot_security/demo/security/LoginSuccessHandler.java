package ru.kata.spring.boot_security.demo.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities()).toString();
        if (roles.contains("ADMIN")) {
            response.sendRedirect("/admin");
        } else {
            response.sendRedirect("/user");
        }

    }
}
