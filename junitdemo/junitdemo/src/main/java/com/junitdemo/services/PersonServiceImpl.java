package com.junitdemo.services;

import com.junitdemo.entities.Person;
import com.junitdemo.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService{


    private final PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public List<Person> getAllPersons() {
        List<Person> personList = personRepository.findAll();
        return personList;
    }

    @Override
    public Person createPerson(Person person) {

        Person person1 = new Person();
        person1.setName(person.getName());
        person1.setCity(person.getCity());

        Person save = personRepository.save(person1);

        return save;
    }

    @Override
    public boolean isPersonExistBy() {
        Boolean personExistBy = personRepository.isPersonExistBy(1);
        return personExistBy;
    }


}
