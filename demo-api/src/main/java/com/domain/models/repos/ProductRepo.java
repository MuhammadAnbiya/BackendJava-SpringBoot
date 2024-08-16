package com.domain.models.repos;

import java.util.List;

import com.domain.models.entities.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepo extends CrudRepository<Product, Long> { // menggunakan framework CRUDRepository ada juga JPA Repository
    
    List<Product> findByNameContains(String name); // salah satu contoh custom query 
    
}
