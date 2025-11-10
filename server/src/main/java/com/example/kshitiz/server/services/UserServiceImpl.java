package com.example.kshitiz.server.services;

import com.example.kshitiz.server.dto.LoginDTO;
import com.example.kshitiz.server.dto.UserDTO;
import com.example.kshitiz.server.entity.User;
import com.example.kshitiz.server.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDTO registerUser(UserDTO userDTO) {
        User user = userDTO.toEntity();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User saved = userRepository.save(user);
        return saved.toDTO();
    }

    @Override
    public UserDTO loginUser(LoginDTO loginDTO) {
        User user = userRepository.findByEmail(loginDTO.getEmail());
        if (user == null) {
            return null;
        }
        boolean matches = passwordEncoder.matches(loginDTO.getPassword(), user.getPassword());
        if (!matches) {
            return null;
        }
        UserDTO dto = user.toDTO();
        dto.setJwtToken(user.getEmail());
        return dto;
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        return user != null ? user.toDTO() : null;
    }
}


