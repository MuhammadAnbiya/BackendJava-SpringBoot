package com.example.dto;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;

public class LoanData {

    @NotBlank(message = "Book is Required")
    private String book;

    @NotBlank(message = "Member is Required")
    private String member;

    @Temporal(TemporalType.DATE)

    private String loanDate;

    @Temporal(TemporalType.DATE)
    private String returnDate;

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
    }

    public String getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(String loanDate) {
        this.loanDate = loanDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    
}