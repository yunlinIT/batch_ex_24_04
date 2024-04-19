package com.koreait.exam.batch_ex_24_04.app.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}