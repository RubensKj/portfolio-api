package com.rubenskj.portfolio.security.uuid;

import com.rubenskj.portfolio.security.model.Session;
import com.rubenskj.portfolio.security.service.UserDetailsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UuidAuthFilter extends OncePerRequestFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(UuidAuthFilter.class);

    private final UuidProvider uuidProvider;
    private final UserDetailsServiceImpl userDetailsService;

    public UuidAuthFilter(UuidProvider uuidProvider, UserDetailsServiceImpl userDetailsService) {
        this.uuidProvider = uuidProvider;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String uuid = uuidProvider.getUuidFromHeader(request);

            this.handleSession(uuid, request);

        } catch (Exception e) {
            LOGGER.error("Error during authentication user - Message: ", e);
        }

        filterChain.doFilter(request, response);
    }

    private void handleSession(String uuid, HttpServletRequest request) {
        if (uuid == null) {
            return;
        }

        Session session = uuidProvider.getSessionByUuid(uuid);

        UsernamePasswordAuthenticationToken authenticationToken = this.createAuthorizationToken(session);

        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }

    private UsernamePasswordAuthenticationToken createAuthorizationToken(Session session) {
        UserDetails userDetails = this.findUserBySessionEmail(session.getEmail());

        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

    private UserDetails findUserBySessionEmail(String email) {
        return this.userDetailsService.loadUserByUsername(email);
    }
}
