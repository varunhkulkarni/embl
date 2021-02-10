package com.embl.embl.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.embl.embl.exceptions.PersonNotFoundException;
import com.embl.embl.model.Person;
import com.embl.embl.repository.PersonRepository;
import com.embl.embl.service.PersonService;

/**
 * Service class for person entity for handling the request and processing the
 * reponse
 * 
 * @author Varun-Kulkarni
 *
 */
@Service
@Transactional
public class PersonServiceImpl implements PersonService {
	
	Logger LOGGER = LoggerFactory.getLogger(PersonServiceImpl.class);

	@Autowired
	PersonRepository personRepository;

	/**
	 * To save the person into repository
	 */
	public Person savePerson(Person person) {
		personRepository.save(person);
		return person;
	}

	/**
	 * To get the person by Id
	 */
	public Optional<Person> getPersonById(Long id) {
		return personRepository.findById(id);
	}

	/**
	 * To get the all the persons
	 */
	public List<Person> findAllPersons() {
		return personRepository.findAll();
	}

	/**
	 * To delete the persons by Id
	 */
	public void deletePerson(Long id) {
		personRepository.deleteById(id);
	}

	/**
	 * To update the person by Id
	 */
	public Person updatePerson(Person newPerson, Long id) {
		return getPersonById(id).map(person -> {
			person.setFirstName(StringUtils.isNotBlank(newPerson.getFirstName()) ? newPerson.getFirstName()
					: person.getFirstName());
			person.setLastName(
					StringUtils.isNotBlank(newPerson.getLastName()) ? newPerson.getLastName() : person.getLastName());
			person.setAge(newPerson.getAge() != null ? newPerson.getAge() : person.getAge());
			return savePerson(person);
		}).orElseGet(() -> {
			newPerson.setId(id);
			return savePerson(newPerson);
		});
	}

	/**
	 * To patch the person by Id
	 */
	public Person patchPerson(Map<String, String> update, Long id) {
		return getPersonById(id).map(person -> {
			String firstName = update.get("lastame");
			if (StringUtils.isNoneBlank(firstName)) {
				person.setFirstName(firstName);
				// better create a custom method to update a value = :newValue where id = :id
				return savePerson(person);
			} else {
				throw new PersonNotFoundException(id);
			}
		}).orElseGet(() -> {
			throw new PersonNotFoundException(id);
		});
	}

}
