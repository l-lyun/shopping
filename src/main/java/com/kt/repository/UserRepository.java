package com.kt.repository;

import java.sql.Date;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.kt.domain.User;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserRepository {
		private final JdbcTemplate jdbcTemplate;
	public void save(User user) {
		// 서비스에서 dto를 도메인(비즈니스 모델)으로 바꾼 다음 전달
		var sql = "INSERT INTO MEMBER (loginId, password, name, birthday) VALUES (?, ?, ?, ?)";
		jdbcTemplate.update(sql, user.getLoginId(), user.getPassword(), user.getName(), user.getBirthday());
		System.out.println("user = " + user);
	}

	public User read(String loginId) {
		var sql = "SELECT * FROM MEMBER WHERE loginId = ?";
		return jdbcTemplate.queryForObject(
			sql,
			(rs, rowNum) -> new User(
				rs.getString("loginId"),
				rs.getString("password"),
				rs.getString("name"),
				rs.getDate("birthday").toLocalDate()
			),
			loginId
		);
	}

		public void delete(String loginId) {
			jdbcTemplate.update("DELETE FROM MEMBER WHERE loginId = ?", loginId);
		}

		public void update(User user) {

			var sql = "UPDATE MEMBER SET password = ?, name = ?, birthday = ? WHERE loginId = ?";
			jdbcTemplate.update(
				sql,
				user.getPassword(),
				user.getName(),
				Date.valueOf(user.getBirthday()),
				user.getLoginId()
			);
		}
}
