package com.example.models.repos;

import com.example.models.entities.Member;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface MemberRepo extends PagingAndSortingRepository<Member, Long>{
    
    void deleteById(Long id);
    
    void deleteAll();

    Page<Member> findByNameContains(String name, Pageable Pageable);
    
    Iterable<Member> findAll();

    Member save(Member member);

    Optional<Member> findById(Long id);
}
