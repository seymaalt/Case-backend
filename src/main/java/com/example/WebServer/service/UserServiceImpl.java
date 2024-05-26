package com.example.WebServer.service;

import com.example.WebServer.dto.UserDTO;
import com.example.WebServer.exception.CustomException;
import com.example.WebServer.model.User;
import com.example.WebServer.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
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
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
    }
}