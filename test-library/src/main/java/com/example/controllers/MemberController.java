package com.example.controllers;

import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.SearchData;
import com.example.models.entities.Member;
import com.example.services.MemberService;

@RestController
@RequestMapping("/api/member")
public class MemberController {
    
    @Autowired
    private MemberService memberService;

    @PostMapping
    public Member create(@RequestBody Member member){
        return memberService.save(member);
    }

    @GetMapping("/{id}")
    public Member findById(@PathVariable("id") Long id){
        return memberService.findById(id);
    }

    @PutMapping
    public Member update(@RequestBody Member member){
        return memberService.save(member);
    }

    @DeleteMapping("/{id}")
    public void removeOne(@PathVariable("id") Long id){
        memberService.removeOne(id);
    }

    @DeleteMapping
    public void removeAll(){
        memberService.removeAll();
    }

    @GetMapping
    public Iterable<Member> findALl(){
        return memberService.findAll();
    }

    @PostMapping("/search/{size}/{page}")
    public Iterable<Member> findByName(@RequestBody SearchData searchData, @PathVariable("size") int size,
    @PathVariable("page") int page){

        Pageable pageable = PageRequest.of(page, size);
        return memberService.findByName(searchData.getSearchKey(), pageable);
    }


}
