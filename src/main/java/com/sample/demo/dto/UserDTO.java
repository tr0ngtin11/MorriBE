package com.sample.demo.dto;

import com.sample.demo.model.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long  userId;
    private String firstName;
    private String lastName;
    private String email;
    private Date dateStart;
    private String phoneNumber;
    private Date birthDay;
    private boolean gender;
    private String avatarUrl;
    private Role role;

}
