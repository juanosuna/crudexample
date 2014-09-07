package com.brownbag.crud.repository;

import com.brownbag.crud.domain.Person;
        import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the Person entity.
 */
public interface PersonRepository extends JpaRepository<Person, Long> {

}
