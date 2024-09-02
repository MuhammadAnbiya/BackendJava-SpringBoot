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
import com.example.models.entities.Book;
import com.example.models.entities.Loan;
import com.example.models.entities.Member;
import com.example.services.BookService;
import com.example.services.LoanService;
import com.example.services.MemberService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/loan")
public class LoanController {
    
    @Autowired
    private LoanService loanService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private BookService bookService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<ResponseData<Loan>> create(@Valid @RequestBody LoanData loanData, Errors errors) {
        
        ResponseData<Loan> responseData = new ResponseData<>();

        // Cek dan kumpulkan kesalahan validasi
        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessage().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        // Cari Member dan Book
        Member member = memberService.findById(loanData.getMemberId());
        if (member == null) {
            responseData.getMessage().add("Member not found with id: " + loanData.getMemberId());
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        Book book = bookService.findById(loanData.getBookId());
        if (book == null) {
            responseData.getMessage().add("Book not found with id: " + loanData.getBookId());
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        // Buat objek Loan dan atur propertinya
        Loan loan = new Loan();
        loan.setLoanDate(loanData.getLoanDate());
        loan.setReturnDate(loanData.getReturnDate());
        loan.setMember(member);
        loan.setBook(book);
        // Simpan Loan menggunakan service
        responseData.setStatus(true);
        responseData.setPayload(loanService.save(loan));
        return ResponseEntity.ok(responseData);
    }


    @GetMapping
    public ResponseEntity<ResponseData<Iterable<Loan>>> findAll() {
        ResponseData<Iterable<Loan>> responseData = new ResponseData<>();

        Iterable<Loan> loans = loanService.findAll();
        if (!loans.iterator().hasNext()) {
            responseData.setStatus(false);
            responseData.getMessage().add("Tidak ada Loan di Database");
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
        }

        responseData.setStatus(true);
        responseData.getMessage().add("Ini adalah data dari semua Loan");
        responseData.setPayload(loans);
        return ResponseEntity.ok(responseData);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseData<Loan>> findById(@PathVariable("id") Long id) {
        ResponseData<Loan> responseData = new ResponseData<>();

        Loan loan = loanService.findById(id);
        if (loan == null) {
            responseData.setStatus(false);
            responseData.getMessage().add("Loan dengan ID " + id + " tidak ditemukan.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
        }

        responseData.setStatus(true);
        responseData.setPayload(loan);
        return ResponseEntity.ok(responseData);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseData<Loan>> updateLoan(@PathVariable("id") Long id, @Valid @RequestBody LoanData loanData, Errors errors) {

        ResponseData<Loan> responseData = new ResponseData<>();

        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessage().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        Loan existingLoan = loanService.findById(id);
        if (existingLoan == null) {
            responseData.setStatus(false);
            responseData.getMessage().add("Loan dengan ID " + id + " tidak ditemukan.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
        }

        Loan loan = modelMapper.map(loanData, Loan.class);
        loan.setId(id); 
        responseData.setStatus(true);
        responseData.setPayload(loanService.save(loan));
        return ResponseEntity.ok(responseData);
    }


    @DeleteMapping
    public ResponseEntity<ResponseData<Void>> removeAll() {
        ResponseData<Void> responseData = new ResponseData<>();

        Iterable<Loan> loans = loanService.findAll();
        if (!loans.iterator().hasNext()) {
            responseData.setStatus(false);
            responseData.getMessage().add("Tidak ada loan untuk dihapus.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
        }

        loanService.removeAll();
        responseData.setStatus(true);
        responseData.getMessage().add("Semua loan berhasil dihapus.");
        return ResponseEntity.ok(responseData);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseData<Void>> removeById(@PathVariable("id") Long id) {
        ResponseData<Void> responseData = new ResponseData<>();

        Loan loan = loanService.findById(id);
        if (loan == null) {
            responseData.setStatus(false);
            responseData.getMessage().add("Loan dengan ID " + id + " tidak ditemukan.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
        }

        loanService.removeOne(id);
        responseData.setStatus(true);
        responseData.getMessage().add("Loan dengan ID " + id + " berhasil dihapus.");
        return ResponseEntity.ok(responseData);
    }
}
