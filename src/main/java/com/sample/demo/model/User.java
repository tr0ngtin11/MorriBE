package com.sample.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import jakarta.persistence.Entity;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Table(name = "user")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  userId;
    private String firstName;
    private String lastName;
    private String email;
    private Date dateStart;
    private String phoneNumber;
    private Date birthDay;
    private boolean gender;
    private String avatarUrl;

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public void setRole(Role role) {
        this.role = role;
    }
    @JsonIgnore
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;


    // foreign key
    // Blog
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Blog> blogs;

    // Invoice
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Invoice> invoice;

    public User(Optional<User> user) {

    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
