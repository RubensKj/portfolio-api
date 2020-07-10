package com.rubenskj.portfolio.repository;

import com.rubenskj.portfolio.model.Certification;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICertificationRepository extends MongoRepository<Certification, String> {
    List<Certification> findAllByPersonId(String personId);
}
