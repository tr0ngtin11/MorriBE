package com.sample.demo.controller;

import com.sample.demo.dto.MessageDTO;
import com.sample.demo.dto.RegisterRequestDTO;
import com.sample.demo.dto.UserDTO;
import com.sample.demo.model.User;
import com.sample.demo.repository.UserRepository;
import com.sample.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;
    private final UserService userService;
//    private final KafkaTemplate<String, Object> kafkaTemplate;


        @GetMapping("/user")
//        @PreAuthorize("hasAuthority('ADMIN')")
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

    @GetMapping("/user22/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable Long id){
        User user = userService.findUserById(id);
//        kafkaTemplate.send("notifications", String.valueOf(user.getUserId()),
//                new MessageDTO(
//                        "trantrongtin01012002@gmail.com",
//                        "Welcome to our website",
//                        "You have successfully registered an account on our website",
//                        "Thank you for registering an account on our website. We hope you have a great experience with our website",
//                        "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.pexels.com%2Fsearch%2Fhappy%2F&psig=AOvVaw0QZ4Z4Z4Z4Z4Z4Z4Z4Z4Z4&ust=1629784940008000&source=images&cd=vfe&ved=0CAsQjRxqFwoTCJjQ4ZqH9_ICFQAAAAAdAAAAABAD"
//                )
//                );
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
