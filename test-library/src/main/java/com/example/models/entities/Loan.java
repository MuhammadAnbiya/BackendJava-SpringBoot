package com.example.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_loan")
public class Loan {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 200, nullable = false)
    private String book;

    @Column(length = 150, nullable = false)
    private String member;

    @Column(length = 150, nullable = false)
    private String loanDate;

    @Column(length = 150, nullable = false)
    private String returnDate;
}
