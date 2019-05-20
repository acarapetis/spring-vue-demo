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

    /* 
     * Get a random book.
     * 
     * TODO: depending on the exact behaviour of generated IDs, this could
     * become very inefficient and even ineffective. The current configuration
     * assigns sequential IDs starting at 1; but in a serious system we'd want
     * to confirm this is guaranteed behaviour.
     */
    public default Optional<Book> findOneRandomly() {
        int MAX_RETRIES = 100;

        long count = this.count();
        if (count <= 0) {
            return Optional.empty();
        }

        Optional<Book> book = Optional.empty();
        for (int i = 0; i < MAX_RETRIES && !book.isPresent(); i++) {
            book = this.findById(ThreadLocalRandom.current().nextLong(count));
        }
        return book;
    }
}