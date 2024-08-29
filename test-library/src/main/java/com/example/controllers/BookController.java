package com.example.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.SearchData;
import com.example.models.entities.Book;
import com.example.services.BookService;

@RestController
@RequestMapping("/api/controller")
public class BookController {
    
    @Autowired
    private BookService bookService;

    @PostMapping
    public Book create(@RequestBody Book book){
        return bookService.save(book);
    }

    @GetMapping("/{id}")
    public Book findBook(@PathVariable("id") Long id){
        return bookService.findOne(id);
    }

    @PutMapping
    public Book update(@RequestBody Book book){
        return bookService.save(book);
    }

    @DeleteMapping("/{id}")
    public void removeOne(@PathVariable("id") Long id) {
        bookService.removeOne(id);
    }

    @DeleteMapping
    public void removeAll(){
        bookService.removeAll();
    }

    @PostMapping("/search/{size}/{page}")
    public Iterable<Book> findByName(@RequestBody SearchData searchData, @PathVariable("size") int size, 
    @PathVariable("page") int page){
        
        Pageable pageable = PageRequest.of(page, size);
        return bookService.findByTitle(searchData.getSearchKey(), pageable);
    }
    
    @GetMapping
    public Iterable<Book> findAll(){
        return bookService.findAll();
    }
}