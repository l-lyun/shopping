package com.kt.repository.order;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kt.domain.order.Order;

public interface OrderRepository extends JpaRepository<Order, Long>, OrderRepositoryCustom {
	// 1. 네이티브 쿼리로 작성
	// 2. jpql로 작성
	// 3. 쿼리 메소드로 어찌저찌 작성
	// 4. 조회할 때는 동적 쿼리를 작성하게 해줄 수 있는 querydsl 사용
}
