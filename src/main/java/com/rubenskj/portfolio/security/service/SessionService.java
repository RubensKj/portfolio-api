package com.rubenskj.portfolio.security.service;

import com.rubenskj.portfolio.security.repository.ISessionRepository;
import org.springframework.stereotype.Service;

@Service
public class SessionService {

    private final ISessionRepository sessionRepository;

    public SessionService(ISessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    public void deleteAllSessionByEmail(String email) {
        this.sessionRepository.deleteAllByEmail(email);
    }
}
