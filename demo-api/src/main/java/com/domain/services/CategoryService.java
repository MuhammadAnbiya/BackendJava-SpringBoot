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


    // class untuk method save
    public Category save(Category category) { 
        if(category.getId()!=null){ // kalau category nya udah ada id nya, dia dibaca sebagai update
            Category currentCategory = categoryRepo.findById(category.getId()).get(); // dia mencari id yang akan di update
            currentCategory.setName(category.getName()); // dia mengganti nama dari setter getter name
            category = currentCategory; // dan merubah category tadi dari update category baru yang diterima
        } // kalau false dia dibaca sebagai create
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

    // class untuk mencari berdasarkan nama pada paging
    public Iterable<Category> findByName(String name, Pageable pageable){ 
        return categoryRepo.findByNameContains(name, pageable);
    }

    // class untuk sekaligus melakukan save banyak data
    public Iterable<Category> saveBatch(Iterable<Category> categories){ 
        return categoryRepo.saveAll(categories);
    }

    public void deleteById(Long id) {
        categoryRepo.deleteById(id);
    }
}