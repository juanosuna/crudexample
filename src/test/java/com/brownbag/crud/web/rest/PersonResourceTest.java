package com.brownbag.crud.web.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.brownbag.crud.Application;
import com.brownbag.crud.domain.Person;
import com.brownbag.crud.repository.PersonRepository;

import java.util.Date;


/**
 * Test class for the PersonResource REST controller.
 *
 * @see PersonResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
    DirtiesContextTestExecutionListener.class,
    TransactionalTestExecutionListener.class })
@ActiveProfiles("dev")
public class PersonResourceTest {
    
    private static final Long DEFAULT_ID = new Long(1L);

    private static final String DEFAULT_FIRST_NAME = "Juan";

    private static final String DEFAULT_LAST_NAME = "Osuna";

    @Inject
    private PersonRepository personRepository;

    private MockMvc restPersonMockMvc;
    
    private Person person;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        PersonResource personResource = new PersonResource();
        ReflectionTestUtils.setField(personResource, "personRepository", personRepository);

        this.restPersonMockMvc = MockMvcBuilders.standaloneSetup(personResource).build();

        person = new Person();
    	person.setFirstName(DEFAULT_FIRST_NAME);
    	person.setLastName(DEFAULT_LAST_NAME);
    }

    @Test
    public void testCRUDPerson() throws Exception {

    	// Create Person
    	restPersonMockMvc.perform(post("/app/rest/persons")
    			.contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(person)))
                .andExpect(status().isOk());

    	// Read Person
    	restPersonMockMvc.perform(get("/app/rest/persons/{id}", DEFAULT_ID))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(DEFAULT_ID.intValue()))
    			.andExpect(jsonPath("$.firstName").value(DEFAULT_FIRST_NAME))
    			.andExpect(jsonPath("$.lastName").value(DEFAULT_LAST_NAME));

    	// Update Person
    	person.setFirstName(DEFAULT_FIRST_NAME);
    	person.setLastName(DEFAULT_LAST_NAME);
  
    	restPersonMockMvc.perform(post("/app/rest/persons")
    			.contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(person)))
                .andExpect(status().isOk());

    	// Read updated Person
    	restPersonMockMvc.perform(get("/app/rest/persons/{id}", DEFAULT_ID))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(DEFAULT_ID.intValue()))
    			.andExpect(jsonPath("$.firstName").value(DEFAULT_FIRST_NAME))
    			.andExpect(jsonPath("$.lastName").value(DEFAULT_LAST_NAME));

    	// Delete Person
    	restPersonMockMvc.perform(delete("/app/rest/persons/{id}", DEFAULT_ID)
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

    	// Read non-existing Person
    	restPersonMockMvc.perform(get("/app/rest/persons/{id}", DEFAULT_ID)
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isNotFound());

    }
}
