package com.rubenskj.portfolio.repository;

import com.rubenskj.portfolio.model.Contact;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IContactRepository extends MongoRepository<Contact, String> {
}
