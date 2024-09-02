package com.domain.repos;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.domain.models.entities.Product;

public interface ProductRepo extends CrudRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE p.name = :name")
    public static Product finProductByName(@Param("name") String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'finProductByName'");
    }
}
