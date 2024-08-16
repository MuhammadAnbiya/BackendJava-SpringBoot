package com.domain.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
<<<<<<< HEAD
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
=======
import org.springframework.stereotype.Service;

>>>>>>> 15c1a6e (lastpush 16/8 - create supplier, category repo and service)
import com.domain.models.entities.Category;
import com.domain.models.repos.CategoryRepo;

import jakarta.transaction.TransactionScoped;

@Service
@TransactionScoped
public class CategoryService {

    @Autowired
<<<<<<< HEAD
    private CategoryRepo categoryRepo;
=======
    public CategoryRepo categoryRepo;
>>>>>>> 15c1a6e (lastpush 16/8 - create supplier, category repo and service)

    public Category save(Category category) {
        return categoryRepo.save(category);
    }

    public Category findOne(Long id) {
        Optional<Category> category = categoryRepo.findById(id);
<<<<<<< HEAD
=======

>>>>>>> 15c1a6e (lastpush 16/8 - create supplier, category repo and service)
        if (!category.isPresent()) {
            return null;
        }
        return category.get();
    }

    public Iterable<Category> findAll() {
        return categoryRepo.findAll();
    }

<<<<<<< HEAD
    public void removeOne(Long id) {
        categoryRepo.deleteById(id);
    }

    public Iterable<Category> findByName(String name, Pageable pageable){
        return categoryRepo.findByNameContains(name, pageable);
    }

    public Iterable<Category> saveBatch(Iterable<Category> categories){
        return categoryRepo.saveAll(categories);
    }

=======
    public void deleteById(Long id) {
        categoryRepo.deleteById(id);
    }

>>>>>>> 15c1a6e (lastpush 16/8 - create supplier, category repo and service)
}
