package com.domain.models.repos;

import java.util.List;

import com.domain.models.entities.Product;

import jakarta.websocket.server.PathParam;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepo extends CrudRepository<Product, Long> { // menggunakan framework CRUDRepository ada juga
                                                                     // JPA Repository

    @Query("SELECT p FROM Product p WHERE p.name= :param")
    List<Product> findByNameContains(@PathParam("name") String name); // salah satu contoh custom query

}
