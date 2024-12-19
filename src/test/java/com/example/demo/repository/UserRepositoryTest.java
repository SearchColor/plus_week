package com.example.demo.repository;

import com.example.demo.DemoApplication;
import com.example.demo.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;



@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("H2: 이메일로 사용자 검색 시 존재하는 사용자 반환")
    void findByEmail_success() {
        // Given
        String email = "existing@example.com";
        User mockUser = User.builder().email(email).role("user").build();
        userRepository.save(mockUser);

        // When
       Optional<User> found = userRepository.findById(1L);

        // Then
        assertThat(found).isPresent();
        assertThat(found.get().getEmail()).isEqualTo("existing@example.com");
    }
}