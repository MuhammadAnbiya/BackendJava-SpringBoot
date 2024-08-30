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
    public ResponseEntity<ResponseData<Member>> create(@Valid @RequestBody MemberData memberData, Errors errors) {
        ResponseData<Member> responseData = new ResponseData<>();

        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
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

    @GetMapping
    public ResponseEntity<ResponseData<Iterable<Member>>> findAll() {
        ResponseData<Iterable<Member>> responseData = new ResponseData<>();

        Iterable<Member> members = memberService.findAll();
        if (!members.iterator().hasNext()) {
            responseData.setStatus(false);
            responseData.getMessage().add("Tidak ada Member di Database");
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
        }

        responseData.setStatus(true);
        responseData.getMessage().add("Ini adalah data dari semua Member");
        responseData.setPayload(members);
        return ResponseEntity.ok(responseData);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseData<Member>> findById(@PathVariable("id") Long id) {
        ResponseData<Member> responseData = new ResponseData<>();

        Member member = memberService.findById(id);
        if (member == null) {
            responseData.setStatus(false);
            responseData.getMessage().add("Member dengan ID " + id + " tidak ditemukan.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
        }

        responseData.setStatus(true);
        responseData.setPayload(member);
        return ResponseEntity.ok(responseData);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseData<Member>> updateMember(@PathVariable("id") Long id, @Valid @RequestBody MemberData memberData, Errors errors) {

        ResponseData<Member> responseData = new ResponseData<>();

        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessage().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        Member existingMember = memberService.findById(id);
        if (existingMember == null) {
            responseData.setStatus(false);
            responseData.getMessage().add("Member dengan ID " + id + " tidak ditemukan.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
        }

        Member member = modelMapper.map(memberData, Member.class);
        member.setId(id); 
        responseData.setStatus(true);
        responseData.setPayload(memberService.save(member));
        return ResponseEntity.ok(responseData);
    }

    @DeleteMapping
    public ResponseEntity<ResponseData<Void>> removeAll() {
        ResponseData<Void> responseData = new ResponseData<>();

        Iterable<Member> members = memberService.findAll();
        if (!members.iterator().hasNext()) {
            responseData.setStatus(false);
            responseData.getMessage().add("Tidak ada member untuk dihapus.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
        }

        memberService.removeAll();
        responseData.setStatus(true);
        responseData.getMessage().add("Semua member berhasil dihapus.");
        return ResponseEntity.ok(responseData);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseData<Void>> removeById(@PathVariable("id") Long id) {
        ResponseData<Void> responseData = new ResponseData<>();

        Member member = memberService.findById(id);
        if (member == null) {
            responseData.setStatus(false);
            responseData.getMessage().add("Member dengan ID " + id + " tidak ditemukan.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
        }

        memberService.removeOne(id);
        responseData.setStatus(true);
        responseData.getMessage().add("Member dengan ID " + id + " berhasil dihapus.");
        return ResponseEntity.ok(responseData);
    }

    
    @PostMapping("/search/{size}/{page}")
    public Iterable<Member> findByName(@RequestBody SearchData searchData, @PathVariable("size") int size,
    @PathVariable("page") int page){
        
        Pageable pageable = PageRequest.of(page, size);
        return memberService.findByName(searchData.getSearchKey(), pageable);
    }
}
