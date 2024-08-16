package com.domain.models.repos;

<<<<<<< HEAD
import java.util.List;

import org.springframework.data.jpa.repository.Query;
=======
>>>>>>> 15c1a6e (lastpush 16/8 - create supplier, category repo and service)
import org.springframework.data.repository.CrudRepository;

import com.domain.models.entities.Supplier;

<<<<<<< HEAD
import jakarta.websocket.server.PathParam;

public interface SupplierRepo extends CrudRepository<Supplier, Long>{
    
    Supplier findByEmail(String email);

    List<Supplier> findByNameContainsOrderByIdDesc(String name);

    List<Supplier> findByNameContains(String name);

    List<Supplier> findByNameStartsWith(String prefix);

    List<Supplier> findByNameContainsOrEmailContains(String name, String email);

    @Query("SELECT p FROM Supplier p WHERE p.email LIKE :email")
    public List<Supplier> findSupplierByEmailLike(@PathParam("email") String email);



=======
public interface SupplierRepo extends CrudRepository<Supplier, Long>{
    
>>>>>>> 15c1a6e (lastpush 16/8 - create supplier, category repo and service)
}
