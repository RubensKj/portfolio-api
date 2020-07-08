package com.rubenskj.portfolio.repository;

import com.rubenskj.portfolio.model.Certification;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICertificationRepository extends MongoRepository<Certification, String> {
}
