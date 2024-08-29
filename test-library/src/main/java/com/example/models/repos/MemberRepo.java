package com.example.models.repos;

import com.example.models.entities.Member;

import org.springframework.data.repository.CrudRepository;

public interface MemberRepo extends CrudRepository<Member, Long>{
    
}
