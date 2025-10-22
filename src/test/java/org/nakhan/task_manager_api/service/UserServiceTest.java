package org.nakhan.task_manager_api.service;

import org.nakhan.task_manager_api.model.User;
import org.nakhan.task_manager_api.repository.UserRepository;
import org.nakhan.task_manager_api.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringJUnitConfig
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private JwtUtil jwtUtil;

    @Test
    public void testRegister() {
        User user = new User("testuser", "test@example.com", "password");
        when(userRepository.findByUsername("testuser")).thenReturn(java.util.Optional.empty());
        when(userRepository.findByEmail("test@example.com")).thenReturn(java.util.Optional.empty());
        when(passwordEncoder.encode("password")).thenReturn("encodedPassword");
        when(userRepository.save(user)).thenReturn(user);

        User result = userService.register(user);

        assertNotNull(result);
        verify(userRepository).save(user);
    }

    @Test
    public void testLogin() {
        User user = new User("testuser", "test@example.com", "encodedPassword");
        when(userRepository.findByUsername("testuser")).thenReturn(java.util.Optional.of(user));
        when(passwordEncoder.matches("password", "encodedPassword")).thenReturn(true);
        when(jwtUtil.generateToken("testuser")).thenReturn("token");

        String token = userService.login("testuser", "password");

        assertEquals("token", token);
    }
}
