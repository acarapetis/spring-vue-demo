package com.example.carapetis.repository;

import java.util.List;
import java.util.Optional;

import com.example.carapetis.model.Person;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Long> {
    public Optional<Person> findById(Long id);
    public List<Person> findAll();
    public Page<Person> findAll(Pageable paging);
}