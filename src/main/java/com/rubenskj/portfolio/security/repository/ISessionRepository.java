package com.rubenskj.portfolio.security.repository;

import com.rubenskj.portfolio.security.model.Session;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ISessionRepository extends MongoRepository<Session, String> {
    Optional<Session> findByUuid(String uuid);

    void deleteAllByEmail(String email);
}
