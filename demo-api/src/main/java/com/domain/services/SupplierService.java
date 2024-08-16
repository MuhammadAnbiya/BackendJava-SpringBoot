package com.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.domain.models.entities.Supplier;
import com.domain.models.repos.SupplierRepo;

import jakarta.transaction.TransactionScoped;

@Service
@TransactionScoped
public class SupplierService {

    @Autowired
    public SupplierRepo supplierRepo;

    public Supplier save(Supplier supplier){
        return supplierRepo.save(supplier);
    }

    

}
