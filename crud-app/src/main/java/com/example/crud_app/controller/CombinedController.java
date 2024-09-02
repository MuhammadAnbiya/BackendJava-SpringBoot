package com.example.crud_app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import com.example.crud_app.model.Book;
import com.example.crud_app.model.Mahasiswa;
import com.example.crud_app.repo.BookRepo;
import com.example.crud_app.repo.MahasiswaRepo;
import org.springframework.web.bind.annotation.*;


@RestController
public class CombinedController {

    @Autowired
    private BookRepo bookRepo;

    @Autowired
    private MahasiswaRepo mahasiswaRepo;

    @GetMapping("/getAllData")
    public ResponseEntity<Map<String, Object>> getAllData() {
        try {
            List<Book> bookList = bookRepo.findAll();
            List<Mahasiswa> mahasiswaList = mahasiswaRepo.findAll();
    
            Map<String, Object> response = new HashMap<>();
            response.put("mahasiswa", mahasiswaList);
            response.put("book", bookList);
    
            return ResponseEntity.ok(response);
    
        } catch (Exception ex) {
            ex.printStackTrace(); // Cetak stack trace untuk debugging
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}