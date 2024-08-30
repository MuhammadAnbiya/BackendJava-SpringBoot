package com.example.controllers;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.modelmapper.ModelMapper;
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

import com.example.dto.MemberData;
import com.example.dto.ResponseData;
import com.example.dto.SearchData;
import com.example.models.entities.Member;
import com.example.services.MemberService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/member")
public class MemberController {
    
    @Autowired
    private MemberService memberService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<ResponseData<Member>> create(@Valid @RequestBody MemberData memberData, Errors errors){

        ResponseData<Member> responseData = new ResponseData<>();

        if(errors.hasErrors()){
            for (ObjectError error : errors.getAllErrors()){
                responseData.getMessage().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        Member member = modelMapper.map(memberData, Member.class);
        responseData.setStatus(true);
        responseData.setPayload(memberService.save(member));
        return ResponseEntity.ok(responseData);
    }

    @GetMapping("/{id}")
    public Member findById(@PathVariable("id") Long id){
        return memberService.findById(id);
    }

    @PutMapping
    public ResponseEntity<ResponseData<Member>> update(@Valid @RequestBody MemberData memberData, Errors errors){

        ResponseData<Member> responseData = new ResponseData<>();

        if(errors.hasErrors()){
            for (ObjectError error : errors.getAllErrors()){
                responseData.getMessage().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        Member member = modelMapper.map(memberData, Member.class);
        responseData.setStatus(true);
        responseData.setPayload(memberService.save(member));
        return ResponseEntity.ok(responseData);
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
