package com.junitdemo.services;

import com.junitdemo.entities.Person;

import java.util.List;

public interface PersonService {

    List<Person> getAllPersons();

    Person createPerson(Person person);


    boolean isPersonExistBy();
}
