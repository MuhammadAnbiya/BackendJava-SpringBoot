package com.domain.models.repos;

import com.domain.models.entities.Product;
import com.domain.models.entities.Supplier;

import java.util.List;
import jakarta.websocket.server.PathParam;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

// menggunakan framework CRUDRepository ada juga  JPA Repository
public interface ProductRepo extends CrudRepository<Product, Long> { 

    // p = * etc, biasanya FROM (table) kalau di JPQL itu ke Class nya (Product)
    @Query("SELECT p FROM Product p WHERE p.name= :name") 
    public Product findProductByName(@PathParam("name")String name);

    // untuk mencari nama yang mirip p.(entity) LIKE :entity
    @Query("SELECT p FROM Product p WHERE p.name LIKE :name") 
    public List<Product> findProductByNameLike(@PathParam("name") String name);

    // untuk mencari product dari category Id nya
    @Query("SELECT p FROM Product p WHERE p.category.id = :categoryId") 
    public List<Product> findProductByCategory(@PathParam("categoryId") Long categoryId);

    // untuk mencari supplier dari supplier Id nya
    @Query("SELECT p FROM Product p WHERE :supplier MEMBER OF p.suppliers") 
    public List<Product> findProductBySupplier(@PathParam("supplier") Supplier supplier); 
    // yang unik adalah dia menggunakan parameter object karena relasi yang many to many
    // dan mengecek dulu apakah supplier nya ada kalau ada dia akan ngecek lagi di suppliers
}   
