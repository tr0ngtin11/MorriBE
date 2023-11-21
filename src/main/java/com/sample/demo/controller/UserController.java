package com.sample.demo.controller;

import com.sample.demo.dto.UserDTO;
import com.sample.demo.model.User;
import com.sample.demo.repository.UserRepository;
import com.sample.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;
    private final UserService userService;

        @GetMapping("/user")
        @PreAuthorize("hasAuthority('ADMIN')")
        public ResponseEntity<Object> getAllUser(){
           return ResponseEntity.ok(userService.getAllUser());
    }

    @GetMapping("/user/{userEmail}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<Object> getUserByEmail(@PathVariable String userEmail){
            User user = userService.findUserByEmail(userEmail);

            if(user == null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found");
            }
            return ResponseEntity.ok(user);
    }

    @GetMapping("/user/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<Object> getUserById(@PathVariable Long id){
        User user = userService.findUserById(id);

        if(user == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found");
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping("/user/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<Object> updateUser(@RequestBody UserDTO userDTO){
            User user = userService.updateUser(userDTO);
            return ResponseEntity.ok(user);
    }
}
