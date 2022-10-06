package com.example.backendupload.repository;

import com.example.backendupload.model.Person;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
public interface UsersRepo extends JpaRepository<Person,Long> 
{

    Optional<Person> findById(Long id);
}
