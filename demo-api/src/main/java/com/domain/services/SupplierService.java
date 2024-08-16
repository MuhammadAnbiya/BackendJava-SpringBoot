package com.domain.services;

<<<<<<< HEAD
import java.util.List;
import java.util.Optional;

=======
>>>>>>> 15c1a6e (lastpush 16/8 - create supplier, category repo and service)
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.domain.models.entities.Supplier;
import com.domain.models.repos.SupplierRepo;

import jakarta.transaction.TransactionScoped;

@Service
@TransactionScoped
public class SupplierService {

    @Autowired
<<<<<<< HEAD
    private SupplierRepo supplierRepo;
=======
    public SupplierRepo supplierRepo;
>>>>>>> 15c1a6e (lastpush 16/8 - create supplier, category repo and service)

    public Supplier save(Supplier supplier){
        return supplierRepo.save(supplier);
    }

<<<<<<< HEAD
    public Supplier findOne(Long id){
        Optional <Supplier> supplier = supplierRepo.findById(id);
        if(!supplier.isPresent()){
            return null;
        }
        return supplier.get();
    }

    public Iterable<Supplier> findAll(){
        return supplierRepo.findAll();
    }

    public void removeOne(Long id){
        supplierRepo.deleteById(id);
    }

    public Supplier findByEmail(String email){
        return supplierRepo.findByEmail(email);
    }

    public List<Supplier> findSupplierByEmailLike(String email){
        return supplierRepo.findSupplierByEmailLike("%"+email+"%");
    }

    public List<Supplier> findByNameContais(String name){
        return supplierRepo.findByNameContains(name);
    }

    public List<Supplier> findByNameStartsWith(String prefix){
        return supplierRepo.findByNameStartsWith(prefix);
    }

    public List<Supplier> findByNameContainsOrEmailContains(String name, String email){
        return supplierRepo.findByNameContainsOrEmailContains(name, email);
    }

    public List<Supplier> findByNameContainsOrderByIdDesc(String name){
        return supplierRepo.findByNameContainsOrderByIdDesc(name);
    }
}

=======
    

}
>>>>>>> 15c1a6e (lastpush 16/8 - create supplier, category repo and service)
