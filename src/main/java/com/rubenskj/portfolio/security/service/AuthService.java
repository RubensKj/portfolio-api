package com.rubenskj.portfolio.security.service;

import com.rubenskj.portfolio.security.dto.AuthDTO;
import com.rubenskj.portfolio.security.dto.LoginDTO;
import com.rubenskj.portfolio.security.uuid.UuidProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthService.class);

    private final AuthenticationManager authenticationManager;
    private final UuidProvider uuidProvider;

    public AuthService(AuthenticationManager authenticationManager, UuidProvider uuidProvider) {
        this.authenticationManager = authenticationManager;
        this.uuidProvider = uuidProvider;
    }

    public AuthDTO authenticate(LoginDTO loginDTO) {
        LOGGER.info("Authenticating user.");

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDTO.getEmail(),
                        loginDTO.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        LOGGER.info("User authenticated");

        return this.uuidProvider.generateSessionToUserAuth(authentication);
    }
}
