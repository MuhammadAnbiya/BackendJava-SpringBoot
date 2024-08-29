package com.example.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.models.entities.Book;
import com.example.models.repos.BookRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class BookService {

    @Autowired
    private BookRepo bookRepo;

    public Book save(Book book){
        return bookRepo.save(book);
    }

}