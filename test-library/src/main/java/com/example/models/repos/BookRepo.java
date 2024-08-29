package com.example.models.repos;

import org.springframework.data.repository.CrudRepository;

import com.example.models.entities.Book;

public interface BookRepo extends CrudRepository<Book, Long>{

}
