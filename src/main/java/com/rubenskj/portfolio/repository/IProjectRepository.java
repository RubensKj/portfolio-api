package com.rubenskj.portfolio.repository;

import com.rubenskj.portfolio.model.Project;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProjectRepository extends MongoRepository<Project, String> {
}
