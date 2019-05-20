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
public class Book {
    public static interface Detailed extends Loan.ShowPerson {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    public Long id;

    @Column(nullable = false)
    public String title;

    @Column(nullable = false)
    public String author;

    @Column(nullable = false)
    public String ISBN;

    @JsonView(Detailed.class)
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    public Set<Loan> loans = new HashSet<>(); 

    public static Book fake() {
        Faker fake = new Faker();
        Book book = new Book();
        book.author = fake.book().author();
        book.title = fake.book().title();
        book.ISBN = fake.numerify("###-#-##-######-#");
        return book;
    }

}