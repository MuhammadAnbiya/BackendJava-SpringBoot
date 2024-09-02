package com.example.models.repos;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.models.entities.Loan;

public interface LoanRepo extends PagingAndSortingRepository<Loan, Long> {
    

    Iterable<Loan> findAll();

    Optional<Loan> findById(Long id);

    Loan save(Loan loan);

    void deleteById(Long id);

    void deleteAll();
}
