package com.embl.embl.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.embl.embl.model.Person;

/**
 * 
 * @author Varun-Kulkarni
 *
 */
public interface PersonService {

	Person savePerson(Person person);

	Optional<Person> getPersonById(Long id);

	List<Person> findAllPersons();

	void deletePerson(Long Id);

	Person updatePerson(Person person, Long Id);

	Person patchPerson(Map<String, String> update, Long id);
}
