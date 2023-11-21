package com.sample.demo.repository;

import com.sample.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);
    User findByUserId(Long id);
}
