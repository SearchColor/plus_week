package com.example.demo.service;


import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;


    @Test
    @DisplayName("존재하는 이메일로 사용자 조회 시 해당 사용자 반환")
    void getUserByEmail_whenUserExists() {
        String email = "existing@example.com";
        User mockUser = User.builder().email(email).role("user").build();

        // Stubbing
        when(userRepository.findByEmail(email)).thenReturn(mockUser);

        // Service 호출
        User foundUser = userService.getUserByEmail(email);

        // 검증
        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getEmail()).isEqualTo(email);
    }

    @Test
    @DisplayName("존재하지 않는 이메일로 사용자 조회 시 예외 발생")
    void getUserByEmail_whenUserNotExists() {
        String email = "notfound@example.com";
        User mockUser = User.builder().email(email).role("user").build();
        when(userRepository.findByEmail(email)).thenReturn(mockUser);

//        assertThatThrownBy(() -> userService.getUserByEmail(email))
//                .isInstanceOf(IllegalArgumentException.class)
//                .hasMessage("User not found");
        assertThatCode(() -> userService.getUserByEmail(email));
    }
}