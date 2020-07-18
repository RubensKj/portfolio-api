package com.rubenskj.portfolio.configuration;

import com.rubenskj.portfolio.property.AdminProperty;
import com.rubenskj.portfolio.security.dto.UserCreationDTO;
import com.rubenskj.portfolio.security.service.SessionService;
import com.rubenskj.portfolio.security.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;

@Service
public class AdminConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminConfiguration.class);

    private final AdminProperty adminProperty;
    private final UserService userService;
    private final SessionService sessionService;

    public AdminConfiguration(AdminProperty adminProperty, UserService userService, SessionService sessionService) {
        this.adminProperty = adminProperty;
        this.userService = userService;
        this.sessionService = sessionService;
    }

    @PostConstruct
    public void createDefaultUserAdmin() {
        String email = adminProperty.getEmail();

        if (this.userService.existsByEmail(email)) {
            LOGGER.info("Deleting the old admin and their sessions.");
            this.userService.deleteUserByEmail(email);
            this.sessionService.deleteAllSessionByEmail(email);
        }

        UserCreationDTO admin = new UserCreationDTO(
                email,
                adminProperty.getPassword(),
                new ArrayList<>(Collections.singleton("RL_ADMIN"))
        );

        LOGGER.info("Saving the new admin.");
        this.userService.save(admin);
    }
}
