package com.kt.service;
import com.kt.domain.User;
import com.kt.dto.UserCreateRequest;

public interface UserService {
	void create(UserCreateRequest request);
	User findByLoginId(String loginId);
	void delete(String loginId);

	// TODO: 필드별 세분화 가능
	void update(UserCreateRequest request);
}
