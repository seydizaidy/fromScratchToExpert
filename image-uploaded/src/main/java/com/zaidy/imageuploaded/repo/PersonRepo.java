package com.zaidy.imageuploaded.repo;

import com.zaidy.imageuploaded.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepo extends JpaRepository<Person,Long>
{
}
