package com.junitdemo.repository;

import com.junitdemo.entities.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PersonRepositoryTest {


    @Autowired
    private PersonRepository personRepository;

    @Test
    void isPersonExistBy() {

//        // we have successfully save this record into the DB.
//        Person person = new Person(1,"Wasim","Kolkata");
//        personRepository.save(person);

        //
        Person person=isPersonExistBy(1);
        Boolean actualResult = personRepository.isPersonExistBy(1);
        assertThat(actualResult).isTrue();
    }
}