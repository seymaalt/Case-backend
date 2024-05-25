package com.example.WebServer.controller;

import com.example.WebServer.dto.UserDTO;
import com.example.WebServer.exception.CustomException;
import com.example.WebServer.model.User;
import com.example.WebServer.service.UserService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Controller
@CrossOrigin("http://localhost:5173")
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Something went wrong"),
            @ApiResponse(code = 403, message = "Access denied"), 
            @ApiResponse(code = 422, message = "Tc No is already in use")})
    @PostMapping("/signup")
    public ResponseEntity<String> addUser(@RequestBody UserDTO userDTO) {
        try {
            userService.addUser(modelMapper.map(userDTO, User.class));
            return ResponseEntity.ok("Success");
        } catch (CustomException ex) {
            return ResponseEntity.status(ex.getHttpStatus()).body(ex.getMessage());
        }
    }

    @GetMapping("/")
    public List<UserDTO> getUsers() {
        return userService.getAllUsers();
    }
}