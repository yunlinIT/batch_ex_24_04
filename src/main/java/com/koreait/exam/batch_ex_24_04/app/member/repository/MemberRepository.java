package com.koreait.exam.batch_ex_24_04.app.member.repository;

import com.koreait.exam.batch_ex_24_04.app.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Long> {
}