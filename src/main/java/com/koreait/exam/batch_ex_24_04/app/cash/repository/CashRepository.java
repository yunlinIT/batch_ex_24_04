package com.koreait.exam.batch_ex_24_04.app.cash.repository;


import com.koreait.exam.batch_ex_24_04.app.cash.entity.CashLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CashRepository extends JpaRepository<CashLog,Long> {
}