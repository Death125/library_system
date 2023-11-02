package com.library.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.library.dto.MemberRequest;
import com.library.exceptions.MemberNotFoundException;
import com.library.models.entities.Member;
import com.library.models.repositories.MemberRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class MemberService {
    @Autowired
    private MemberRepo memberRepo;

    public Member createMember(MemberRequest memberRequest) {
        Member member = Member.builder().memberName(memberRequest.getMemberName())
                .dateCreated(memberRequest.getDateCreated()).dateUpdated(memberRequest.getDateUpdated()).build();

        return memberRepo.save(member);
    }

    public Member updateMember(MemberRequest memberRequest) throws MemberNotFoundException {
        Optional<Member> member = memberRepo.findById(memberRequest.getId());

        if (!member.isPresent()) {
            throw new MemberNotFoundException("Member with id " + member + " not found");
        } else {
            member.get().setMemberName(memberRequest.getMemberName());
            member.get().setDateUpdated(memberRequest.getDateUpdated());

            return memberRepo.save(member.get());
        }
    }

    // Update Just Specific Field
    public Member updateSpecificField() {
        return null;
    }

    public Iterable<Member> findAllMember() throws MemberNotFoundException {
        Iterable<Member> members = memberRepo.findAll();
        if (members == null) {
            throw new MemberNotFoundException("No one member in here !! ");
        } else {
            return members;
        }
    }

    public Member findOneMember(Long id) {
        Optional<Member> member = memberRepo.findById(id);

        if (!member.isPresent()) {
            return null;
        }
        return member.get();
    }

    public void deleteMemberById(Long id) throws MemberNotFoundException {
        Optional<Member> member = memberRepo.findById(id);

        if (!member.isPresent()) {
            throw new MemberNotFoundException("Member with id " + member + " not found");
        } else {
            memberRepo.deleteById(id);
        }

    }

    public Iterable<Member> findByMemberName(String memberName, Pageable pageable) {
        return memberRepo.findByMemberNameContains(memberName, pageable);
    }

    public Iterable<Member> saveBatch(Iterable<Member> members) {
        return memberRepo.saveAll(members);
    }
}
