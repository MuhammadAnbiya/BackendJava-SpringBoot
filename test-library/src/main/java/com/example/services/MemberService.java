package com.example.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.models.entities.Member;
import com.example.models.repos.MemberRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class MemberService {
    
    @Autowired
    private MemberRepo memberRepo;

    public Member save(Member member){
        return memberRepo.save(member);
    }

    public Member findById(Long id){
        Optional<Member> member = memberRepo.findById(id); 
        if(!member.isPresent()){
            return null;
        }
        return member.get();
    }

    public void removeOne(Long id){
        memberRepo.deleteById(id);
    }

    public void removeAll(){
        memberRepo.deleteAll();
    }

    public Iterable<Member> findAll(){
        return memberRepo.findAll();
    }

    public Iterable<Member> findByName(String name, Pageable pageable){
        return memberRepo.findByNameContains(name, pageable);
    }

}
