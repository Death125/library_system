package com.library.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.library.models.entities.Member;
import com.library.models.repos.MemberRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class MemberService {
    @Autowired
    private MemberRepo memberRepo;

    public Member saveMember(Member member) {
        return memberRepo.save(member);
    }

    public Member updateMember(Member member) {
        return memberRepo.save(member);
    }

    public Iterable<Member> findAllMember() {
        return memberRepo.findAll();
    }

    public Member findOneMember(Long id) {
        Optional<Member> member = memberRepo.findById(id);

        if (!member.isPresent()) {
            return null;
        }
        return member.get();
    }

    public void deleteMemberById(Long id) {
        memberRepo.deleteById(id);
    }

    public Iterable<Member> findByMemberName(String memberName, Pageable pageable) {
        return memberRepo.findByMemberNameContains(memberName, pageable);
    }

    public Iterable<Member> saveBatch(Iterable<Member> members) {
        return memberRepo.saveAll(members);
    }
}
