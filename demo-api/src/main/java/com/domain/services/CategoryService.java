package com.domain.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.domain.models.entities.Category;
import com.domain.models.repos.CategoryRepo;

import jakarta.transaction.TransactionScoped;

@Service
@TransactionScoped
public class CategoryService {

    @Autowired
    public CategoryRepo categoryRepo;

    public Category save(Category category) {
        return categoryRepo.save(category);
    }

    public Category findOne(Long id) {
        Optional<Category> category = categoryRepo.findById(id);

        if (!category.isPresent()) {
            return null;
        }
        return category.get();
    }

    public Iterable<Category> findAll() {
        return categoryRepo.findAll();
    }

    public void deleteById(Long id) {
        categoryRepo.deleteById(id);
    }

}
