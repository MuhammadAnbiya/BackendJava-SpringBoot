package com.example.models.repos;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
// import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.models.entities.Book;

public interface BookRepo extends PagingAndSortingRepository<Book, Long>{

    Book save(Book book);

    Optional<Book> findById(Long id);

    void deleteById(Long id);

    void deleteAll();

    Page<Book> findByTitleContains(String title, Pageable pageable);
    
    Iterable<Book> findAll();
}
