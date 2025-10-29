package com.kt.dto;

import java.time.LocalDate;

import com.kt.domain.User;

// loginId, password, name, birthday
public record UserCreateRequest(
	String loginId,
	String password,
	String name,
	LocalDate birthday
	) {
	public User toEntity() {
		return new User(loginId, password, name, birthday);
	}
}
