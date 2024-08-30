package com.example.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

import com.example.dto.BookData;
import com.example.dto.ResponseData;
import com.example.dto.SearchData;
import com.example.models.entities.Book;
import com.example.services.BookService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/book")
public class BookController {
    
    @Autowired
    private BookService bookService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<ResponseData<Book>> create(@Valid @RequestBody BookData bookData, Errors errors){

        ResponseData<Book> responseData = new ResponseData<>();

        if(errors.hasErrors()){
            for (ObjectError error : errors.getAllErrors()){
                responseData.getMessage().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        Book book = modelMapper.map(bookData, Book.class);
        responseData.setStatus(true);
        responseData.setPayload(bookService.save(book));
        return ResponseEntity.ok(responseData);
    }

    @GetMapping("/{id}")
    public Book findBook(@PathVariable("id") Long id){
        return bookService.findOne(id);
    }

    @PutMapping
    public ResponseEntity<ResponseData<Book>> update(@Valid @RequestBody BookData bookData, Errors errors){

        ResponseData<Book> responseData = new ResponseData<>();

        if(errors.hasErrors()){
            for (ObjectError error : errors.getAllErrors()){
                responseData.getMessage().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        Book book = modelMapper.map(bookData, Book.class);
        responseData.setStatus(true);
        responseData.setPayload(bookService.save(book));
        return ResponseEntity.ok(responseData);
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
    public Iterable<Book> findBy(@RequestBody SearchData searchData, @PathVariable("size") int size, 
    @PathVariable("page") int page){
        
        Pageable pageable = PageRequest.of(page, size);
        return bookService.findByTitle(searchData.getSearchKey(), pageable);
    }
    
    @GetMapping
    public Iterable<Book> findAll(){
        return bookService.findAll();
    }
}