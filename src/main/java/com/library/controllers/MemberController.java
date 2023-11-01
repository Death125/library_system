package com.library.controllers;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.dto.MemberRequest;

import com.library.dto.SearchData;
import com.library.models.entities.Member;
import com.library.services.MemberService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    // @PostMapping("/create")
    // public ResponseEntity<ResponseRequest<Member>> create(@Valid @RequestBody
    // MemberRequest memberData, Errors errors) {
    // ResponseRequest<Member> responseData = new ResponseRequest<>();

    // if (errors.hasErrors()) {
    // for (ObjectError error : errors.getAllErrors()) {
    // responseData.getMessages().add(error.getDefaultMessage());

    // responseData.setStatus(false);
    // responseData.setPayload(null);
    // return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
    // }
    // }

    // Member member = modelMapper.map(memberData, Member.class);

    // responseData.setStatus(true);
    // responseData.setPayload(memberService.saveMember(member));
    // return ResponseEntity.ok(responseData);
    // }

    // @PutMapping("/update")
    // public ResponseEntity<ResponseRequest<Member>> update(@Valid @RequestBody
    // MemberRequest memberData, Errors errors) {
    // ResponseRequest<Member> responseData = new ResponseRequest<>();

    // if (errors.hasErrors()) {
    // for (ObjectError error : errors.getAllErrors()) {
    // responseData.getMessages().add(error.getDefaultMessage());

    // responseData.setStatus(false);
    // responseData.setPayload(null);
    // return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
    // }
    // }

    // Member member = modelMapper.map(memberData, Member.class);

    // responseData.setStatus(true);
    // responseData.setPayload(memberService.updateMember(member));
    // return ResponseEntity.ok(responseData);
    // }

    @GetMapping("/findAllMember")
    public Iterable<Member> findAllMember() {
        return memberService.findAllMember();
    }

    @GetMapping("/{id}")
    public Member findOneMember(@PathVariable("id") Long id) {
        return memberService.findOneMember(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteEmployeeById(@PathVariable("id") Long id) {
        memberService.deleteMemberById(id);
    }

    @PostMapping("/search/{size}/{page}/{sort}")
    public Iterable<Member> findByMemberName(@RequestBody SearchData searchData, @PathVariable("size") int size,
            @PathVariable("page") int page, @PathVariable("sort") String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id"));

        if (sort.equalsIgnoreCase("desc")) {
            pageable = PageRequest.of(page, size, Sort.by("id").descending());
        }

        return memberService.findByMemberName(searchData.getSearchKey(), pageable);
    }

    // @PostMapping("/batch")
    // public ResponseEntity<ResponseRequest<Iterable<Member>>>
    // createBatch(@RequestBody Member[] members) {
    // ResponseRequest<Iterable<Member>> responseData = new ResponseRequest<>();

    // responseData.setPayload(memberService.saveBatch(Arrays.asList(members)));

    // responseData.setStatus(true);
    // return ResponseEntity.ok(responseData);

    // }
}
