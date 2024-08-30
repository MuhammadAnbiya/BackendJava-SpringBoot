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


    @GetMapping
    public ResponseEntity<ResponseData<Iterable<Book>>> findAll() {

        ResponseData<Iterable<Book>> responseData = new ResponseData<>();

        Iterable<Book> books = bookService.findAll();
        if (!books.iterator().hasNext()) {
            responseData.setStatus(false);
            responseData.getMessage().add("Tidak ada Buku di Database");
            responseData.setPayload(null);  // Tidak ada data yang ditampilkan jika tidak ada buku
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
        }

        responseData.setStatus(true);
        responseData.getMessage().add("Ini adalah data dari semua Buku");
        responseData.setPayload(books);  // Set payload dengan data buku
        return ResponseEntity.ok(responseData);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ResponseData<Book>> findById(@PathVariable("id") Long id){
        ResponseData<Book> responseData = new ResponseData<>();

        Book book = bookService.findOne(id);
        if (book == null) {
            responseData.setStatus(false);
            responseData.getMessage().add("Member dengan ID " + id + " tidak ditemukan.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
        }

        responseData.setStatus(true);
        responseData.setPayload(book);
        return ResponseEntity.ok(responseData);
    }

    
    @PutMapping("/{id}")
    public ResponseEntity<ResponseData<Book>> update(@PathVariable("id") Long id, @Valid @RequestBody BookData bookData, Errors errors) {

        ResponseData<Book> responseData = new ResponseData<>();

        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessage().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        Book existingBook = bookService.findOne(id);
        if (existingBook == null) {
            responseData.setStatus(false);
            responseData.getMessage().add("Buku dengan ID " + id + " tidak ditemukan.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
        }

        Book book = modelMapper.map(bookData, Book.class);
        book.setId(id); 
        responseData.setStatus(true);
        responseData.setPayload(bookService.save(book));
        return ResponseEntity.ok(responseData);
    }


    @DeleteMapping
    public ResponseEntity<ResponseData<Void>> removeAll() {

        ResponseData<Void> responseData = new ResponseData<>();

        Iterable<Book> books = bookService.findAll(); 
        if (!books.iterator().hasNext()) {
            responseData.setStatus(false);
            responseData.getMessage().add("Tidak ada buku untuk dihapus.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
        }
        bookService.removeAll(); 
        responseData.setStatus(true);
        responseData.getMessage().add("Semua buku berhasil dihapus.");
        return ResponseEntity.ok(responseData);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseData<Void>> removeById(@PathVariable("id") Long id) {

        ResponseData<Void> responseData = new ResponseData<>();

        Book book = bookService.findOne(id);
        if (book == null) {
            responseData.setStatus(false);
            responseData.getMessage().add("Buku dengan ID " + id + " tidak ditemukan.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
        }
        bookService.removeOne(id); 
        responseData.setStatus(true);
        responseData.getMessage().add("Buku dengan ID " + id + " berhasil dihapus.");
        return ResponseEntity.ok(responseData);
    }


    @PostMapping("/search/{size}/{page}")
    public Iterable<Book> findBy(@RequestBody SearchData searchData, @PathVariable("size") int size, 
    @PathVariable("page") int page){
        
        Pageable pageable = PageRequest.of(page, size);
        return bookService.findByTitle(searchData.getSearchKey(), pageable);
    }
    
}