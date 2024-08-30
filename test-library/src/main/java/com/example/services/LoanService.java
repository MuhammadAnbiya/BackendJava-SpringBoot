package com.example.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.models.entities.Loan;
import com.example.models.repos.LoanRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class LoanService {
    
    @Autowired
    private LoanRepo loanRepo;


    public Iterable<Loan> findAll(){
        return loanRepo.findAll();
    }

    public Loan save(Loan loan) {
        validateLoanDates(loan);
        return loanRepo.save(loan);
    }

    public Loan findById(Long id){
        Optional<Loan> loan = loanRepo.findById(id);
        if(!loan.isPresent()){
            return null;
        }
        return loan.get();
    }

    public void removeOne(Long id){
        loanRepo.deleteById(id);
    }

    public void removeAll(){
        loanRepo.deleteAll();
    }

    private void validateLoanDates(Loan loan) {
    if (loan.getReturnDate().isBefore(loan.getLoanDate())) {
        throw new IllegalArgumentException("Return Date cannot be before Loan Date.");
        }
    }
}
