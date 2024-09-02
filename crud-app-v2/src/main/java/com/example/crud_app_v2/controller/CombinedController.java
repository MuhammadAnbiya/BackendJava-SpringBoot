package com.example.crud_app_v2.controller;

import com.example.crud_app_v2.model.Laptop;
import com.example.crud_app_v2.model.Pembeli;
import com.example.crud_app_v2.repos.LaptopRepo;
import com.example.crud_app_v2.repos.PembeliRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class CombinedController {

    @Autowired
    private LaptopRepo laptopRepo;

    @Autowired
    private PembeliRepo pembeliRepo;

    @GetMapping("/getAllData")
    public ResponseEntity<Map<String, Object>> getAllData() {
        try {
            List<Laptop> laptopList = laptopRepo.findAll();
            List<Pembeli> pembeliList = pembeliRepo.findAll();

            Map<String, Object> response = new HashMap<>();
            response.put("laptops", laptopList);
            response.put("pembeli", pembeliList);

            return ResponseEntity.ok(response);

        } catch (Exception ex) {
            ex.printStackTrace(); // Cetak stack trace untuk debugging
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Endpoint untuk menambahkan laptop
    @PostMapping("/addLaptop")
    public ResponseEntity<Laptop> addLaptop(@RequestBody Laptop laptop) {
        try {
            Laptop savedLaptop = laptopRepo.save(laptop);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedLaptop);
        } catch (Exception ex) {
            ex.printStackTrace(); // Cetak stack trace untuk debugging
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Endpoint untuk menambahkan pembeli
    @PostMapping("/addPembeli")
    public ResponseEntity<Pembeli> addPembeli(@RequestBody Pembeli pembeli) {
        try {
            Pembeli savedPembeli = pembeliRepo.save(pembeli);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedPembeli);
        } catch (Exception ex) {
            ex.printStackTrace(); // Cetak stack trace untuk debugging
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
