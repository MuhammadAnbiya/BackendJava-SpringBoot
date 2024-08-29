package com.example.models.repos;

import java.lang.reflect.Member;

import org.springframework.data.repository.CrudRepository;

public interface MemberRepo extends CrudRepository<Member, Long>{
    
}
