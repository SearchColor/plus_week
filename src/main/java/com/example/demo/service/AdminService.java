package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.util.List;

@Service
public class AdminService {
    private final UserRepository userRepository;
    private final JdbcTemplate jdbcTemplate;

    public AdminService(UserRepository userRepository, JdbcTemplate jdbcTemplate) {
        this.userRepository = userRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    // TODO: 4. find or save 예제 개선
    @Transactional
    public void reportUsers(List<Long> userIds) {

        StringBuilder list = new StringBuilder("(");
        for (Long userId : userIds) {
//            User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("해당 ID에 맞는 값이 존재하지 않습니다."));
//
//            user.updateStatusToBlocked();
//
//            userRepository.save(user);

            if (list.isEmpty()){
                list.append(userIds);
            }
            list.append(",").append(userIds);
        }
        list.append(")");
        userRepository.updateUserStatus(String.valueOf(list));
    }
}
