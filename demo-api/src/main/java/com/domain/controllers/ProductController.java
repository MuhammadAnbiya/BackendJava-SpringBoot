package com.domain.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.domain.dto.ResponseData;
import com.domain.dto.SupplierData;
import com.domain.models.entities.Product;
import com.domain.models.entities.Supplier;
import com.domain.services.ProductService;

import jakarta.validation.Valid;


@RestController // layaknya service setiap class controller harus didefinisikan seperti ini dulu
@RequestMapping("/api/products") // ini untuk menjadi awalan API nya nanyi harus diawali dengan /api/products
public class ProductController {
    
    @Autowired // anotasi untuk injeksi dependensi 
    private ProductService productService; // jadi class ProductService di deklarasikan sebagai productService untuk memanggil logicnya


    // Endpoint untuk membuat product
    @PostMapping
    public ResponseEntity<ResponseData<Product>> create(@Valid @RequestBody Product product, Errors errors){
    // @ResponseEntitiy adalah sebuah kelas yang digunakan dalam Spring untuk membangun respons HTTP
    // @ResponseData  merupakan kelas custom untuk membangun respons yang lebih terstruktur (di file DTO) --> data transfer object
    // @Valid Anotasi ini digunakan untuk mengaktifkan validasi pada objek product sebelum method dieksekusi
    // Product product Parameter method ini yang berisi data produk yang dikirim oleh klien dalam body permintaan HTTP
    // Errors errors Parameter untuk menangani dan menyimpan informasi kesalahan validasi yang terjadi selama proses validasi.

        ResponseData <Product> responseData = new ResponseData<>();  // instance dari kelas ResponseData

        if (errors.hasErrors()){ // mengecek apakah ada error
            for (ObjectError error: errors.getAllErrors()) { // iterasi untuk mendapatkan semua error
                responseData.getMessages().add(error.getDefaultMessage()); // memasukan semua error ke responseData
            }
            responseData.setStatus(false); // kalau error status nya false
            responseData.setPayload(null); /// kalau error payload nya null (kosong)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData); // kalau error dimunculin error nya
        }
        responseData.setStatus(true); // kalau ngga error status nya true
        responseData.setPayload(productService.save(product)); // kalau ngga error dimunculin product nya
        return ResponseEntity.ok(responseData); // kalau ngga error munculin list kosong doang
    }


    // Endpoint untuk melihat seluruh product
    @GetMapping
    public Iterable<Product> findAll(){ //memakai Iterable karena melakukan pengecekan untuk semua product
        return productService.findAll();
    }

    // Endpoint untuk melihat product dari id nya
    @GetMapping("/{id}")
    public Product findOne(@PathVariable("id") Long id){ //@PathVariable digunakan saat melakukan pencarian yang spesifik
        return productService.findOne(id);
    }

    // Endpoint untuk melakukan pembaruan di data product nya
    @PutMapping
    public ResponseEntity<ResponseData<Product>> update(@Valid @RequestBody Product product, Errors errors){
        ResponseData <Product> responseData = new ResponseData<>();

        if (errors.hasErrors()){
            for (ObjectError error: errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        responseData.setStatus(true);
        responseData.setPayload(productService.save(product));
        return ResponseEntity.ok(responseData);   //KURANG LEBIH SAMA LOGIC NYA SAMA YANG BAGIAN CREATE TADI
    }


    // Endpoint untuk menghapus product dari Id nya
    @DeleteMapping("/{id}")
    public void removeOne(@PathVariable("id") Long id){
        productService.removeOne(id);
    }

    // Endpoint untuk menghapus semua product
    @DeleteMapping
    public void deleteAll(){
        productService.removeAll();
    }

    // Endpoint untuk mencari product dari namanya
    @GetMapping("/name/{name}")  // perlu diperhatikan agar endpoint tidak error harus unik dan berbeda
    public List<Product> findByName(@PathVariable("name") String name){
        return productService.findByName(name);
    }

    @PostMapping("/{id}")
    public void addSupplier(@RequestBody Supplier supplier, @PathVariable("id") Long productId){
        productService.addSupplier(supplier, productId);
    }
}
