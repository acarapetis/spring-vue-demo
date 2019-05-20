package com.example.carapetis.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonView;
import com.github.javafaker.Faker;

@Entity
public class Person {
    // Detailed view of a Person should show loans[].book, 
    // but *not* loans[].person (beware infinite recursion!)
    public static interface Detailed extends Loan.ShowBook {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    public Long id;

    @Column(nullable = false)
    public String name;

    @Column(nullable = true)
    public String address;

    @Column(nullable = true)
    public String phone;

    @JsonView(Detailed.class)
    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    public Set<Loan> loans = new HashSet<>();

    public static Person fake() {
        Faker faker = new Faker();
        Person x = new Person();
        x.name = faker.name().fullName();
        x.address = faker.address().fullAddress();
        x.phone = faker.phoneNumber().phoneNumber();
        return x;
    }
}