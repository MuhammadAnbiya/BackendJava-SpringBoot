package com.example.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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

    public Book findById(Long id){
        Optional<Book> book = bookRepo.findById(id);
        if(!book.isPresent()){
            return null;
        }
        return book.get();
    }

    public void removeOne(Long id){
        bookRepo.deleteById(id);
    }

    public void removeAll(){
        bookRepo.deleteAll();
    }

    public Iterable<Book> findByTitle(String title, Pageable pageable){
        return bookRepo.findByTitleContains(title, pageable);
    }

    public Iterable<Book> findAll(){
        return bookRepo.findAll();
    }
}