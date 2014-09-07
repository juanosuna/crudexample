package com.brownbag.crud.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.brownbag.crud.domain.Person;
import com.brownbag.crud.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * REST controller for managing Person.
 */
@RestController
@RequestMapping("/app")
public class PersonResource {

    private final Logger log = LoggerFactory.getLogger(PersonResource.class);

    @Inject
    private PersonRepository personRepository;

    /**
     * POST  /rest/persons -> Create a new person.
     */
    @RequestMapping(value = "/rest/persons",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void create(@RequestBody Person person) {
        log.debug("REST request to save Person : {}", person);
        personRepository.save(person);
    }

    /**
     * GET  /rest/persons -> get all the persons.
     */
    @RequestMapping(value = "/rest/persons",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Person> getAll() {
        log.debug("REST request to get all Persons");
        return personRepository.findAll();
    }

    /**
     * GET  /rest/persons/:id -> get the "id" person.
     */
    @RequestMapping(value = "/rest/persons/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Person> get(@PathVariable Long id, HttpServletResponse response) {
        log.debug("REST request to get Person : {}", id);
        Person person = personRepository.findOne(id);
        if (person == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    /**
     * DELETE  /rest/persons/:id -> delete the "id" person.
     */
    @RequestMapping(value = "/rest/persons/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete Person : {}", id);
        personRepository.delete(id);
    }
}
