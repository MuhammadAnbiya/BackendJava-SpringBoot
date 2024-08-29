package com.example.models.repos;

import org.springframework.data.repository.CrudRepository;

import com.example.models.entities.Loan;

public interface LoanRepo extends CrudRepository<Loan, Long> {
    
}
