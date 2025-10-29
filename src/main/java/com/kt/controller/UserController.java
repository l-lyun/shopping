package com.kt.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.kt.domain.User;
import com.kt.dto.UserCreateRequest;
import com.kt.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {
	// userservice di 받아야함
	// di 받는 방식이 생성자 주입 -> 재할당 금지 == 불변

	private final UserService userService;

	@PostMapping("/users")
	@ResponseStatus(HttpStatus.CREATED)
	// loginId, password, name, birthday
	// json형태의 body에 담겨서 post 요청으로 /users로 들어오면
	// @RequestBody를 보고 jacksonObjectMapper가 동작해서 json을 읽어서 dto로 반환
	public void create(@RequestBody UserCreateRequest request) {
		// jackson object mapper -> json to dto
		userService.create(request);
		System.out.println(request.toString());
	}

	@GetMapping("/users/{loginId}")
	@ResponseStatus(HttpStatus.OK)
	public User read(@PathVariable String loginId) {
		System.out.println(loginId);
		return userService.findByLoginId(loginId);
	}

	@DeleteMapping("/users/{loginId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable String loginId) {
		userService.delete(loginId);
	}

	@PutMapping("/users")
	@ResponseStatus(HttpStatus.CREATED)
	public void update(@RequestBody UserCreateRequest request) {
		userService.update(request);
	}

}
