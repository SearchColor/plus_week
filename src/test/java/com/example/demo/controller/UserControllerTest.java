package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    UserService userService;

    @MockitoBean
    UserRepository userRepository;


    @Test
    @DisplayName("이메일로 사용자 조회 시 성공적으로 JSON 응답을 반환")
    void getUserByEmail_shouldReturnUser() throws Exception {
        // Given: 준비 - 특정 이메일을 가진 사용자가 존재한다고 가정
        String email = "test@example.com";
        User ownerUser = User.builder().email(email).role("user").build();
        given(userService.getUserByEmail(email)).willReturn(ownerUser);


        // When: 액션 - 해당 사용자를 조회하는 컨트롤러 호출
        mockMvc.perform(get("/users/{email}", email))
                .andExpect(status().isOk())  // Then: 결과 확인 - JSON 결과 확인
                .andExpect(jsonPath("$.email").value(equalTo(email)));
    }
}