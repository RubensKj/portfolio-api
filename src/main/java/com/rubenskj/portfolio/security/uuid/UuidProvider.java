package com.rubenskj.portfolio.security.uuid;

import com.rubenskj.portfolio.exception.NotFoundException;
import com.rubenskj.portfolio.security.details.UserDetailsImpl;
import com.rubenskj.portfolio.security.dto.AuthDTO;
import com.rubenskj.portfolio.security.model.Session;
import com.rubenskj.portfolio.security.repository.ISessionRepository;
import com.rubenskj.portfolio.util.ParamsKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

import static com.rubenskj.portfolio.security.dto.UserDTO.ofUserDetails;

@Service
public class UuidProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(UuidProvider.class);

    private final ISessionRepository sessionRepository;

    public UuidProvider(ISessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    public String getUuidFromHeader(HttpServletRequest request) {
        String auth = request.getHeader(ParamsKey.AUTHORIZATION);

        if (auth != null) {
            return auth.trim();
        }

        return null;
    }

    public Session getSessionByUuid(String uuid) {
        LOGGER.info("Finding Session from UUID. Uuid: {}", uuid);
        return this.sessionRepository.findByUuid(uuid).orElseThrow(() -> new NotFoundException("Session cannot be found with this uuid. UUID: " + uuid));
    }

    public AuthDTO generateSessionToUserAuth(Authentication authentication) {
        UserDetailsImpl userDetails = UserDetailsImpl.getInstanceByAuthentication(authentication);

        Session session = new Session(userDetails.getUsername());

        session = this.sessionRepository.save(session);

        return new AuthDTO(session.getUuid(), ofUserDetails(userDetails), session.getCreatedAt());
    }
}
