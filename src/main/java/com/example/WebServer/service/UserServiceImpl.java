package com.example.WebServer.service;

import com.example.WebServer.dto.UserDTO;
import com.example.WebServer.exception.CustomException;
import com.example.WebServer.model.User;
import com.example.WebServer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public String addUser(User user) {
        if (!userRepository.existsByTcNo(user.getTcNo())) {
            userRepository.save(user);
        }else {
            throw new CustomException("Tc No is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
        }

        return null;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream().map(user -> new UserDTO(
                user.getTcNo(),
                user.getName(),
                user.getSurname(),
                user.getAge(),
                user.getGender()
        )).collect(Collectors.toList());
    }
}