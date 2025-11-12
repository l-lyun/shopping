package com.kt.repository.order;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.kt.dto.order.OrderResponse;

public interface OrderRepositoryCustom {
	// QClass를 import 해서 쿼리를 작성할 때 사용하면 된다
	public Page<OrderResponse.Search> search(String keyword, Pageable pageable);
}
