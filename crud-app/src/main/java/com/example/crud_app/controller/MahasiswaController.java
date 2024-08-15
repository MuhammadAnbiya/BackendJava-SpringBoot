package com.example.crud_app.controller;

import java.util.List;
import java.util.ArrayList;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.crud_app.model.Mahasiswa;
import com.example.crud_app.repo.MahasiswaRepo;

@RestController
public class MahasiswaController {

    @Autowired
    private MahasiswaRepo mahasiswaRepo;

    @GetMapping("/getAllMahasiswa")
    public ResponseEntity<List<Mahasiswa>> getAllMahasiswa(){
        try{
        List<Mahasiswa> mahasiswaList = new ArrayList<>();
        mahasiswaRepo.findAll().forEach(mahasiswaList::add);

        if(mahasiswaList.isEmpty()){
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(mahasiswaList, HttpStatus.OK);

        } catch (Exception ex){
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/addMahasiswa")
    public ResponseEntity<Mahasiswa> addBook(@RequestBody Mahasiswa mahasiswa) {
        Mahasiswa mahasiswaObj = mahasiswaRepo.save(mahasiswa);

        return new ResponseEntity<>(mahasiswaObj, HttpStatus.OK);    
    }
}
