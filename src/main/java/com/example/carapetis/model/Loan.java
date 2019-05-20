package com.example.carapetis.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.example.carapetis.jsonview.Simple;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
public class Loan {
    // Serialization of this loan's book and person can be controlled by using
    // one of the following views (or a view extending them).
    public static interface ShowBook extends Simple {}
    public static interface ShowPerson extends Simple {}
    public static interface Detailed extends ShowBook, ShowPerson {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    public Long id;

    @JsonView(ShowBook.class)
    @ManyToOne
    public Book book;

    @JsonView(ShowPerson.class)
    @ManyToOne
    public Person person;

    public Loan() { }

    public Loan(Person person, Book book) {
        this.person = person;
        this.book = book;
    }
}