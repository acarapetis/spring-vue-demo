package com.example.carapetis.repository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import com.example.carapetis.model.Book;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {
    public Optional<Book> findById(Long id);
    public List<Book> findAll();
    public Page<Book> findAll(Pageable paging);
    public long count();

    public default Optional<Book> findOneRandomly() {
        long count = this.count();
        if (count <= 0) {
            return Optional.empty();
        }

        Optional<Book> book = Optional.empty();
        while (!book.isPresent()) {
            book = this.findById(ThreadLocalRandom.current().nextLong(count));
        }
        return book;
    }
}