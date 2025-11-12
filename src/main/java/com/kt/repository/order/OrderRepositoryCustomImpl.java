package com.kt.repository.order;

import org.apache.logging.log4j.util.Strings;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.kt.domain.order.QOrder;
import com.kt.domain.orderproduct.QOrderProduct;
import com.kt.domain.product.QProduct;
import com.kt.dto.order.OrderResponse;
import com.kt.dto.order.QOrderResponse_Search;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryCustomImpl implements OrderRepositoryCustom {
	private final QOrder order = QOrder.order;
	private final JPAQueryFactory jpaQueryFactory;
	private final QOrderProduct orderProduct = QOrderProduct.orderProduct;
	private final QProduct product = QProduct.product;

	@Override
	public Page<OrderResponse.Search> search(
		String keyword,
		Pageable pageable
	) {
		// 페이징을 구현할 때
		// offset -> 한 페이지 몇개 보여줄 것인지 0번부터 얼마나 떨어져있는지
		// limit -> 한 페이지에 몇개 보여줄지
		// select *

		// 최초 페이지 접근했을 때 -> 전체 검색? 특정 키워드 검색?
		// name like '%null%' (동작 해야하나?)- 동작 안해야
		// keyword = null
		BooleanBuilder booleanBuilder = new BooleanBuilder();
		booleanBuilder.and(containsProductName(keyword));
		// booleanBuilder.and()
		// booleanBuilder.or()
		// booleanBuilder 안에다가 booleanExpression을 추가해주는 방식

		var content = jpaQueryFactory
			.select(new QOrderResponse_Search(
				order.id,
				order.receiver.name,
				order.receiver.address,
				product.name,
				orderProduct.quantity,
				// 더미데이터
				Expressions.asNumber(0L),
				order.status,
				order.createdAt
			)).from(order)
			// leftJoin 사용해야 하는 상황 생각
			// 현재 softdelete 시스템이라 문제가 없지만, hard delete면 leftjoin사용
			.join(orderProduct).on(orderProduct.order.id.eq(order.id))
			.join(product).on(orderProduct.product.id.eq(product.id))
			.where(booleanBuilder)
			.orderBy(order.id.desc())
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize())
			.fetch();

		// 총 갯수 필요
		var total = (long)jpaQueryFactory.select(order.id)
			// order자리에 QOrderResponse_Search
			// .select(new QOrderRes)
			.from(order)
			.join(orderProduct).on(orderProduct.order.id.eq(order.id))
			.join(product).on(orderProduct.product.id.eq(product.id))
			.where(booleanBuilder)
			.fetch().size();

		return new PageImpl<>(
			content,
			pageable,
			total
		);

	}

	// like 검색
	// 시작하는 '%keyword' statsWith
	// 끝나는 'keyword%' endsWith
	// 포함하는 '%keyword%' -> contains 쓰면 알 아서 쿼리가 나감
	// 공백이면 어떡하지?
	// Strings // 해당 타입을 도와주는 서포터 클래스 Strings, 레퍼런스 타입 끝에 s
	// Objects
	private BooleanExpression containsProductName(String keyword) {
		// if (Strings.isNotBlank(keyword)) {
		// 	return product.name.containsIgnoreCase(keyword);
		// } else {
		// 	return null;
		// }
		return Strings.isNotBlank(keyword) ? product.name.containsIgnoreCase(keyword) : null;
	}

}
