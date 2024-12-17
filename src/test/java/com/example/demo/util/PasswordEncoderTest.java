package com.example.demo.util;

import com.example.demo.config.JPAConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;

@Slf4j
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(JPAConfiguration.class)
@Rollback(value = false)
class PasswordEncoderTest {

    @Test
    public void encoderTest(){
        //given
        String givenPassword = "1234abcd";
        //when
        String encodingPassword =  PasswordEncoder.encode(givenPassword);
        log.info(encodingPassword);
        //then
        log.info("{}", PasswordEncoder.matches(givenPassword, encodingPassword));
    }

}