package com.example.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;

public class LoanData {

    private Long id;
    
    @NotNull(message = "Loan Date is Required")
    private LocalDate loanDate;

    @NotNull(message = "Return Date is Required")
    private LocalDate returnDate;

    @NotNull(message = "Member ID is Required")
    private Long memberId;

    @NotNull(message = "Book ID is Required")
    private Long bookId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    
}