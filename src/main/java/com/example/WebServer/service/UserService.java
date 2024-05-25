package com.example.WebServer.service;

import com.example.WebServer.dto.UserDTO;
import com.example.WebServer.model.User;

import java.util.List;

public interface UserService {
    String addUser(User user);
    List<UserDTO> getAllUsers();
}