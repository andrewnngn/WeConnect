package com.wc.weconnect;

import com.wc.weconnect.Controller.AuthController;

import com.wc.weconnect.config.JwtProvider;
import com.wc.weconnect.models.User;
import com.wc.weconnect.repository.UserRepository;
import com.wc.weconnect.response.AuthResponse;
import com.wc.weconnect.service.CustomUserDetailsService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AuthControllerTest {


    // should create a new user and return a token
    @Test
    public void test_create_user_and_return_token() throws Exception {
        // Arrange
        UserRepository userRepository = mock(UserRepository.class);
        PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);
        CustomUserDetailsService customUserDetailsService = mock(CustomUserDetailsService.class);
        JwtProvider jwtProvider = mock(JwtProvider.class);
    
        AuthController authController = new AuthController();
        authController.setUserRepository(userRepository);
        authController.setPasswordEncoder(passwordEncoder);
        authController.setCustomUserDetails(customUserDetailsService);
    
        User user = new User();
        user.setEmail("test@example.com");
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setPassword("password");
    
        User savedUser = new User();
        savedUser.setId(1);
        savedUser.setEmail("test@example.com");
        savedUser.setFirstName("John");
        savedUser.setLastName("Doe");
        savedUser.setPassword("encoded_password");
    
        Authentication authentication = new UsernamePasswordAuthenticationToken(savedUser.getEmail(), savedUser.getPassword());
        String token = "generated_token";
    
        when(userRepository.findByEmail(user.getEmail())).thenReturn(null);
        when(passwordEncoder.encode(user.getPassword())).thenReturn("encoded_password");
        when(userRepository.save(any(User.class))).thenReturn(savedUser);
        when(customUserDetailsService.loadUserByUsername(savedUser.getEmail())).thenReturn(new org.springframework.security.core.userdetails.User(savedUser.getEmail(), savedUser.getPassword(), new ArrayList<>()));
        when(jwtProvider.generateToken(authentication)).thenReturn(token);
    
        // Act
        AuthResponse response = authController.createUser(user);
    
        // Assert
        assertEquals(token, response.getToken());
        assertEquals("Register success", response.getMessage());
    }

}