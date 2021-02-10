package com.embl.embl.controllers;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.embl.embl.exceptions.PersonNotFoundException;
import com.embl.embl.model.Person;
import com.embl.embl.service.PersonService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Person Controller to expose API
 * 
 * @author Varun-Kulkarni
 *
 */
@RestController
@Validated
@Api(tags = "API for Persons")
@RequestMapping("v1/persons")
public class PersonsController {
	
	Logger LOGGER = LoggerFactory.getLogger(PersonsController.class);

	@Autowired
	PersonService personService;

	/**
	 * To get the all the person details
	 * 
	 * @return
	 */
	@CrossOrigin(origins = "*")
	@GetMapping()
	@ApiOperation(value = "Retrive all person objects", notes = "Api to retrieve all the person objects")
	public List<Person> retrieveAllPersons() {
		LOGGER.info("Retrive all person request handled");
		return personService.findAllPersons();
	}

	/**
	 * To get the person by Id
	 * 
	 * @param id
	 * @return
	 */
	@CrossOrigin(origins = "*")
	@GetMapping("/{id}")
	@ApiOperation(value = "Retrieve a single person objects", notes = "Api to retrieve a single person object")
	public Person retrievePerson(@PathVariable Long id) {
		LOGGER.info("Retrive person by id - {} request handled", id);
		return personService.getPersonById(id).orElseThrow(() -> new PersonNotFoundException(id));
	}

	/**
	 * To delete the person by Id
	 * 
	 * @param id
	 * @return
	 */
	@CrossOrigin(origins = "*")
	@DeleteMapping("/{id}")
	@ApiOperation(value = "Delete a single person object", notes = "Api to delete a single person object")
	public Person deletePerson(@PathVariable Long id) {
		LOGGER.info("Delete person by id - {} request handled", id);
		return personService.deletePerson(id).orElse(null);
	}

	/**
	 * To create the person and save into database
	 * 
	 * @param person
	 * @return
	 */
	@CrossOrigin(origins = "*")
	@PostMapping()
	@ApiOperation(value = "Create a single person object", notes = "Api to create a single person object")
	public ResponseEntity<String> createPerson(@RequestBody Person person) {
		if (person != null) {
			LOGGER.info("Person Controller : save request for person : {}",person);
			personService.savePerson(person);
			LOGGER.info("Person Controller : Person saved sucessfully");
			return new ResponseEntity<String>(HttpStatus.CREATED);
		} else {
			LOGGER.error("Person Controller : Bad request for person : {}",person);
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}

	}

	/**
	 * To update the person details
	 * 
	 * @param person
	 * @param id
	 * @return
	 */
	@CrossOrigin(origins = "*")
	@PutMapping("/{id}")
	@ApiOperation(value = "Update a single person object", notes = "Api to update a single person object")
	public Person updatePerson(@RequestBody Person person, @PathVariable Long id) {
		LOGGER.info("Update person by id - {} request handled", id);
		return personService.updatePerson(person, id);
	}

	/**
	 * To patch the details for person
	 * 
	 * @param update
	 * @param id
	 * @return
	 */
	@CrossOrigin(origins = "*")
	@PatchMapping("/{id}")
	@ApiOperation(value = "Patch a single person object", notes = "Api to Patch a single person object")
	Person patch(@RequestBody Map<String, String> update, @PathVariable Long id) {
		LOGGER.info("Patch person by id - {} request handled", id);
		return personService.patchPerson(update, id);

	}
}
