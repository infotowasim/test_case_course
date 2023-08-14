package com.junitdemo.controller;

import com.junitdemo.entities.Person;
import com.junitdemo.services.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/persons")
public class PersonController {


    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }


    @PostMapping
    public ResponseEntity<Person> createPerson(@RequestBody Person person){
        Person person1 = personService.createPerson(person);
        return new ResponseEntity<>(person1, HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<Person>> getAllPersons(){
        List<Person> personList = personService.getAllPersons();
        return ResponseEntity.ok(personList);

    }


    @GetMapping
    public boolean isPersonExistBy(){
        boolean personExistBy = personService.isPersonExistBy();
        return personExistBy;


    }
}
