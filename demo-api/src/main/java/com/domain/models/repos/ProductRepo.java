package com.domain.models.repos;

import com.domain.models.entities.Product;
import java.util.List;
import jakarta.websocket.server.PathParam;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepo extends CrudRepository<Product, Long> { // menggunakan framework CRUDRepository ada juga  JPA Repository
    @Query("SELECT p FROM Product p WHERE p.name= :name")
    public Product findProductByName(@PathParam("name")String name); // salah satu contoh custom query

    @Query("SELECT p FROM Product p WHERE p.name LIKE :name")
    public List<Product> findProductByNameLike(@PathParam("name") String name);
}
