package com.sk.skala.myapp.controller;

import com.sk.skala.myapp.repository.UserRepository;
import java.util.List;
// import java.util.ArrayList;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sk.skala.myapp.domain.User;
import com.sk.skala.myapp.service.UserService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api")
public class UserController {
    private final UserRepository userRepository;
    private final UserService userService;

    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
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
        return userService.getAllUsers();
    }

    // GET: @RequestParam으로 특정 사용자 가져오기
    @GetMapping("/users/{id}")
    public User getUserByEmail(@RequestParam("id") long id) {
        return userService.getUserById(id).orElse(null);
    }
    
    // POST: 사용자 추가
    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    // DELETE: 사용자 삭제
    @DeleteMapping("/users/{id}")
    public void deletUser(@RequestBody Long id) {
        userService.deleteUser(id);
    }

    // PUT: 사용자 수정
    @PutMapping("users/{id}")
    public User updateUser(
        @PathVariable("id") Long id,
        @RequestBody User updatedUser
    ) {
        User user = userService.getUserById(id).orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));
        user.setName(updatedUser.getName());
        user.setEmail(updatedUser.getEmail());
        return userRepository.save(user);
    }
}
