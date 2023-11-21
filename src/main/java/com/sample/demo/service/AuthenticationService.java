package com.sample.demo.service;

import com.sample.demo.dto.AuthenticationRequestDTO;
import com.sample.demo.dto.AuthenticationResponseDTO;
import com.sample.demo.dto.RegisterRequestDTO;
import com.sample.demo.model.Role;
import com.sample.demo.model.User;
import com.sample.demo.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public AuthenticationResponseDTO register(RegisterRequestDTO request){
        var user = User.builder()
                .firstName(request.getFirstname())
                .lastName(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateJwtToken(user);
        return AuthenticationResponseDTO.builder()
                .token(jwtToken)
                .build();
    }

public AuthenticationResponseDTO authenticate(AuthenticationRequestDTO request){
      authenticationManager.authenticate(
              new UsernamePasswordAuthenticationToken(
                      request.getEmail(),
                      request.getPassword()
              )
      );
      var user = userRepository.findByEmail(request.getEmail());
      var jwtToken = jwtService.generateJwtToken(user);
    return AuthenticationResponseDTO.builder()
            .token(jwtToken)
            .build();
    }

}
