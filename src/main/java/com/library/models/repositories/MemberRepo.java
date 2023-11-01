package com.library.models.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.library.models.entities.Member;

public interface MemberRepo extends CrudRepository<Member, Long>, PagingAndSortingRepository<Member, Long> {
    Page<Member> findByMemberNameContains(String memberName, Pageable pageable);
}
