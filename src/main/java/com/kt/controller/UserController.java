package com.kt.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.kt.dto.UserCreateRequest;
import com.kt.service.UserService;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {
	// userservice di 받아야함
	// di 받는 방식이 생성자 주입 -> 재할당 금지 == 불변

	private final UserService userService;

	// API 문서화는 크게 2가지의 방식이 존재
	// 1. Swagger -> 장점 UI가 이쁘다, 어노테이션 기반이라서 작성이 쉽다.
	//	단점: 프로덕션코드에 Swagger관련 어노테이션이 존재
	//	코드가 더러워지고 길어지고 그래서 유지보수가 힘듬
	// 2. RestDocs
	// 1번이랑 정반대
	// 장점 : 프로덕션 코드에 침범이 없다, 신뢰할 수 있음
	// 단점 : UI가 안이쁘다. 그리고 문서작성하는데 테스트코드 기반이라 시간이 걸림.
	@ApiResponses(value = {
		@ApiResponse(responseCode = "400", description = "유효성 검사 실패"),
		@ApiResponse(responseCode = "500", description = "서버 에러 - 백엔드에 바로 문의 바랍니다.")
	})
	@PostMapping("/users")
	@ResponseStatus(HttpStatus.CREATED)
	// loginId, password, name, birthday
	// json형태의 body에 담겨서 post요청으로 /users로 들어오면
	// @RequestBody를보고 jacksonObjectMapper가 동작해서 json을 읽어서 dto로 변환
	public void create(@Valid @RequestBody UserCreateRequest request) {
		userService.create(request);
	}

}
