package com.kt.domain.user;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.kt.common.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;

// 1. domain과 entity를 분리해야
// 2. 굳이? 같이 쓰지 뭐
@Getter
@Entity
public class User extends BaseEntity {
	private String loginId;
	private String password;
	private String name;
	private String email;
	private String mobile;
	private LocalDate birth;
	@Enumerated(EnumType.STRING)
	private Gender gender;

	public User(String loginId, String password, String name, String email, String mobile, LocalDate birth, Gender gender,
		LocalDateTime createdAt, LocalDateTime updatedAt) {
		this.loginId = loginId;
		this.password = password;
		this.name = name;
		this.email = email;
		this.mobile = mobile;
		this.birth = birth;
		this.gender = gender;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public void changePassword(String password) {
		this.password = password;
	}

	public void update(String name, String email, String mobile) {
		this.name = name;
		this.email = email;
		this.mobile = mobile;
	}
}
