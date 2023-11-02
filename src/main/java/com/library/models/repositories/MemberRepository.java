package com.library.models.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.library.models.entities.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Page<Member> findByMemberNameContains(String memberName, Pageable pageable);
}
