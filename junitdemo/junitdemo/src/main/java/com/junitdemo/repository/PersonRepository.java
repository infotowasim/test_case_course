package com.junitdemo.repository;

import com.junitdemo.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person,Integer> {

  //  @Query( "SELECT CASE WHEN COUNT(s) > 0 THEN TURE ELSE FALSE END FROM Person s WHERE s.personId=:id");
    Boolean isPersonExistBy(Integer id);
}
