package com.kt.domain;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User {
	private String loginId;
	private String password;
	private String name;
	private LocalDate birthday;

	public static User userOf(String loginId, String password, String name, LocalDate birthday) {
		return new User(loginId, password, name, birthday);
	}
}
