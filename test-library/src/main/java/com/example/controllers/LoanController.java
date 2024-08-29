package com.example.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.models.entities.Loan;
import com.example.services.LoanService;

@RestController
@RequestMapping("/api/loan")
public class LoanController {
    
    @Autowired
    private LoanService loanService;

    @GetMapping
    public Iterable<Loan> findAll(){
        return loanService.findAll();
    }

    @PostMapping
    public Loan create(@RequestBody Loan loan){
        return loanService.save(loan);
    }

    @GetMapping("/{id}")
    public Loan findById(@PathVariable("id") Long id){
        return loanService.findById(id);
    }

    @PutMapping
    public Loan update(@RequestBody Loan loan){
        return loanService.save(loan);
    }

    @DeleteMapping("/{id}")
    public void removeOne(@PathVariable("id") Long id){
        loanService.removeOne(id);
    }

    @DeleteMapping
    public void removeAll(){
        loanService.removeAll();
    }


}
