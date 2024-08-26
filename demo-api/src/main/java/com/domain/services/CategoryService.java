package com.domain.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.domain.models.entities.Category;
import com.domain.models.repos.CategoryRepo;

import jakarta.transaction.TransactionScoped;

@Service
@TransactionScoped
public class CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    public Category save(Category category) {
        return categoryRepo.save(category);
    }

public Category findOne(Long id) {
    Optional<Category> categoryOptional = categoryRepo.findById(id);
    if (categoryOptional.isPresent()) {
        return categoryOptional.get();
    } else {
        return null;
    }
}


    public Iterable<Category> findAll() {
        return categoryRepo.findAll();
    }

    public void removeOne(Long id) {
        categoryRepo.deleteById(id);
    }
    public Iterable<Category> findByName(String name, Pageable pageable){
        return categoryRepo.findByNameContains(name, pageable);
    }

    public Iterable<Category> saveBatch(Iterable<Category> categories){
        return categoryRepo.saveAll(categories);
    }

    public void deleteById(Long id) {
        categoryRepo.deleteById(id);
    }
}