package com.rubenskj.portfolio.repository;

import com.rubenskj.portfolio.model.Person;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPersonRepository extends MongoRepository<Person, String> {
}
