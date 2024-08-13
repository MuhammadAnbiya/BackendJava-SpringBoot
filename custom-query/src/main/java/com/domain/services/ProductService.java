package com.domain.services;

import com.domain.models.entities.Product;
import com.domain.repos.ProductRepo;

public class ProductService {
    
    public Product finProductByName(String name){
        return ProductRepo.finProductByName(name);
    }
}
