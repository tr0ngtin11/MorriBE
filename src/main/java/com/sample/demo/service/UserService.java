package com.sample.demo.service;

import com.sample.demo.dto.UserDTO;
import com.sample.demo.model.User;
import com.sample.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    private final Logger logger = LoggerFactory.getLogger(UserService.class);
    public List<User> getAllUser(){
        return userRepository.findAll();
    }
    @Override
    public UserDetails loadUserByUsername(String userEmail) {
        // Thực hiện tìm kiếm người dùng dựa trên userEmail
        User user = userRepository.findByEmail(userEmail);

        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + userEmail);
        }

        // Chuyển đổi thông tin người dùng thành đối tượng UserDetails
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.getAuthorities()
        );

        return userDetails;
    }

    public User findUserByEmail(String userEmail) {
        return userRepository.findByEmail(userEmail);
    }

    public User findUserById(Long id){
        return userRepository.findByUserId(id);
    }

    public User updateUser(UserDTO userDTO){
        User user = findUserById(userDTO.getUserId());

        if (user == null){
            logger.error("Can't find any user with Id: " + userDTO.getUserId());
        }

        user.setEmail(userDTO.getEmail());
        user.setUserId(userDTO.getUserId());
        user.setBirthDay(userDTO.getBirthDay());
        user.setAvatarUrl(userDTO.getAvatarUrl());
        user.setGender(userDTO.isGender());
        user.setRole(userDTO.getRole());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());


        userRepository.save(user);
        logger.trace("Updated user with Id: " + user.getUsername());
        return user;
    }
}
