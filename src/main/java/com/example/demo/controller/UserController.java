package com.example.demo.controller;

import com.example.demo.constants.GlobalConstants;
import com.example.demo.dto.Authentication;
import com.example.demo.dto.LoginRequestDto;
import com.example.demo.dto.UserRequestDto;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public void signupWithEmail(@RequestBody UserRequestDto userRequestDto) {
        userService.signupWithEmail(userRequestDto);
    }

    @PostMapping("/login")
    public void loginWithEmail(@RequestBody LoginRequestDto loginRequestDto, HttpServletRequest request) {
        Authentication authentication = userService.loginUser(loginRequestDto);
        HttpSession session = request.getSession();
        session.setAttribute(GlobalConstants.USER_AUTH, authentication);
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
    }

    @GetMapping("/{email}")
    public User getUser(@PathVariable String email) {
        return userService.getUserByEmail(email);
    }
}
