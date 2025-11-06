package com.kt.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kt.domain.User;

// <T, ID>
// T: Entity 클래스 => User
// ID: Entity 클래스의 PK 타입 => Long
public interface UserRepository extends JpaRepository<User, Long> {
	// 쿼리 작성
	// JPA에서는 쿼리를 작성하는 3가지 방법 존재
	// 1. 네이티브 쿼리 작성 (3)
	// 2. JPQL 작성 -> 네이티브 쿼리랑 같은데 Entity 기반 (2) - 너무 길어진 메소드 이름을 그냥 쿼리 작성해서 숨김
	// 3. querymethod 작성 -> 메서드 이름을 쿼리처럼 작성 (1) - 길어지면 상당히 이상해보임
	// 찾는다: findByXX, 존재하냐: existsByXX, 삭제: deleteByXX
	Boolean existsByLoginId(String loginId);

	@Query(value = """
		SELECT exists (SELELCT u FROM User u WHERE u.loginId = ?1)
		""", nativeQuery = true)
	boolean existsByLoginIdJPQL(String loginId);

	Page<User> findAllByNameContaining(String name, Pageable pageable);

}