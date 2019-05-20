package com.example.carapetis.repository;

import java.util.List;
import java.util.Optional;

import com.example.carapetis.model.Loan;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface LoanRepository extends CrudRepository<Loan, Long> {
    public Optional<Loan> findById(Long id);
    public List<Loan> findAll();
    public Page<Loan> findAll(Pageable paging);
    public long count();
}