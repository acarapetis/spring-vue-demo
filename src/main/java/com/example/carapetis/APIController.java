package com.example.carapetis;

import java.util.Optional;
import java.util.Random;

import com.example.carapetis.jsonview.Simple;
import com.example.carapetis.model.Book;
import com.example.carapetis.model.Loan;
import com.example.carapetis.model.Person;
import com.example.carapetis.repository.BookRepository;
import com.example.carapetis.repository.LoanRepository;
import com.example.carapetis.repository.PersonRepository;
import com.fasterxml.jackson.annotation.JsonView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.boot.context.event.ApplicationReadyEvent;

@RestController
@RequestMapping("/api")
public class APIController {
    @Autowired BookRepository bookRepo;
    @Autowired PersonRepository personRepo;
    @Autowired LoanRepository loanRepo;

    @EventListener(ApplicationReadyEvent.class)
    private void seedDatabase() {
        Random random = new Random();
        for (int i = 0; i < 200; i++) {
            bookRepo.save(Book.fake());
        }
        for (int i = 0; i < 100; i++) {
            Person person = Person.fake();
            for (int j = 0; j < random.nextInt(10); j++) {
                Optional<Book> book = bookRepo.findOneRandomly();
                if (book.isPresent()) {
                    person.loans.add(new Loan(person, book.get()));
                }
            }
            personRepo.save(person);
        }
    }

    // I've used JsonViews to control whether collections of children are serialized.
    // The Simple view does not serialize any children.
    @JsonView(Simple.class)
    @GetMapping("/books")
    public Page<Book> listBooks(@RequestParam(defaultValue="0") Integer page, 
                                @RequestParam(defaultValue="10") Integer size,
                                @RequestParam(defaultValue="author") String sort) {
        PageRequest paging = PageRequest.of(page, size, Sort.by(sort).ascending());
        return bookRepo.findAll(paging);
    }

    @JsonView(Simple.class)
    @GetMapping("/people")
    public Page<Person> listPeople(@RequestParam(defaultValue="0") Integer page, 
                                 @RequestParam(defaultValue="10") Integer size,
                                 @RequestParam(defaultValue="name") String sort) {
        PageRequest paging = PageRequest.of(page, size, Sort.by(sort).ascending());
        return personRepo.findAll(paging);
    }

    // Person.Detailed serializes people.loans[].book.
    // See model/Person.java for the definition.
    @JsonView(Person.Detailed.class)
    @GetMapping("/people/{id}")
    public Person getPerson(@PathVariable Long id) {
        return personRepo.findById(id).orElseThrow(NotFound::new);
    }
}
