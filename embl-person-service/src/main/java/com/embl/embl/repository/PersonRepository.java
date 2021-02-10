package com.embl.embl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.embl.embl.model.Person;

/**
 * Person JPA repository
 * @author Varun-Kulkarni
 *
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

}
