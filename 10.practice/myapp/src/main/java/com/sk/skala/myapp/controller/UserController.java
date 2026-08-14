package com.sk.skala.myapp.controller;

import java.util.List;
// import java.util.ArrayList;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sk.skala.myapp.aspect.Metrics;
import com.sk.skala.myapp.domain.User;
import com.sk.skala.myapp.service.UserService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

import lombok.extern.slf4j.Slf4j;


@Slf4j  // logger 설정
@RestController
@RequestMapping("/api")
public class UserController {
    
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // private List<User> users = new ArrayList<>(List.of(
    //     new User(1L, "Alice", "alice@example.com"),
    //     new User(2L, "Bob", "bob@example.com"),
    //     new User(3L, "charlie", "charlie@example.com")
    // ));
    // private long userIdCounter = 4;

    // GET: 전체 사용자 가져오기
    @GetMapping("/users")
    public List<User> getAllUsers() {
        log.info("getAllUsers called");
        log.debug("getAllUsers called");
        return userService.getAllUsers();
    }

    // GET: @RequestParam으로 특정 사용자 가져오기
    @Metrics
    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable("id") long id) {
        log.info("getUserById called");
        log.debug("getUserById called with id: {}", id);
        return userService.getUserById(id).orElse(null);
    }
    
    // POST: 사용자 추가
    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        log.info("createUser called");
        log.debug("createUser called with user: {}", user);
        return userService.createUser(user);
    }

    // DELETE: 사용자 삭제
    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        log.info("deleteUser called");
        log.debug("deleteUser called with id: {}", id);
        userService.deleteUser(id);
    }

    // PUT: 사용자 수정
    @PutMapping("users/{id}")
    public User updateUser(
        @PathVariable("id") Long id,
        @RequestBody User updatedUser
    ) {
        log.info("updateUser called");
        log.info("updateUser called with id: {}, user: {}", id, updatedUser);
        return userService.updateUser(id, updatedUser).orElse(null);
    }
}
