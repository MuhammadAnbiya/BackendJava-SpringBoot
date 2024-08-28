package com.domain.models.repos;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.domain.models.entities.Supplier;

import jakarta.websocket.server.PathParam;

public interface SupplierRepo extends CrudRepository<Supplier, Long>{ // Contoh Derived Query (Query yang sudah disediakan) dari CrudRepository
    
    Supplier findByEmail(String email);

    List<Supplier> findByNameContainsOrderByIdDesc(String name);

    List<Supplier> findByNameContains(String name);

    List<Supplier> findByNameStartsWith(String prefix); // Sebetulnya ini bisa String (apa saja) cuman agar semantik diganti dengan prefix

    List<Supplier> findByNameContainsOrEmailContains(String name, String email);

    @Query("SELECT p FROM Supplier p WHERE p.email LIKE :email")
    public List<Supplier> findSupplierByEmailLike(@PathParam("email") String email);



}
