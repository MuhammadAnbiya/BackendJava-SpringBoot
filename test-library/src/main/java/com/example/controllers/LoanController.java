package com.example.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.LoanData;
import com.example.dto.ResponseData;
import com.example.models.entities.Loan;
import com.example.services.LoanService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/loan")
public class LoanController {
    
    @Autowired
    private LoanService loanService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<ResponseData<Loan>> create(@Valid @RequestBody LoanData loanData, Errors errors){

        ResponseData<Loan> responseData = new ResponseData<>();

        if(errors.hasErrors()){
            for (ObjectError error : errors.getAllErrors()){
                responseData.getMessage().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        Loan loan = modelMapper.map(loanData, Loan.class);
        responseData.setStatus(true);
        responseData.setPayload(loanService.save(loan));
        return ResponseEntity.ok(responseData);
    }

    @GetMapping
    public Iterable<Loan> findAll(){
        return loanService.findAll();
    }

    @GetMapping("/{id}")
    public Loan findById(@PathVariable("id") Long id){
        return loanService.findById(id);
    }

    @PutMapping
    public ResponseEntity<ResponseData<Loan>> update(@Valid @RequestBody LoanData loanData, Errors errors){

        ResponseData<Loan> responseData = new ResponseData<>();

        if(errors.hasErrors()){
            for (ObjectError error : errors.getAllErrors()){
                responseData.getMessage().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        Loan loan = modelMapper.map(loanData, Loan.class);
        responseData.setStatus(true);
        responseData.setPayload(loanService.save(loan));
        return ResponseEntity.ok(responseData);
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
