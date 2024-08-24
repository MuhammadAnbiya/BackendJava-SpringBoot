package com.domain.models.repos;

import com.domain.models.entities.Product;
import com.domain.models.entities.Supplier;

import java.util.List;
import jakarta.websocket.server.PathParam;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepo extends CrudRepository<Product, Long> { // menggunakan framework CRUDRepository ada juga  JPA Repository
    @Query("SELECT p FROM Product p WHERE p.name= :name") // p = * etc, biasanya FROM (table) kalau di JPQL itu ke Class nya (Product)
    public Product findProductByName(@PathParam("name")String name); // salah satu contoh custom query

    @Query("SELECT p FROM Product p WHERE p.name LIKE :name") //  untuk mencari nama yang mirip p.(entity) LIKE :entity
    public List<Product> findProductByNameLike(@PathParam("name") String name);

    @Query("SELECT p FROM Product p WHERE p.category.id = :categoryId") // 
    public List<Product> findProductByCategory(@PathParam("categoryId") Long categoryId);

    @Query("SELECT p FROM Product p WHERE :supplier MEMBER OF p.suppliers")
    public List<Product> findProductBySupplier(@PathParam("supplier") Supplier supplier);
}
