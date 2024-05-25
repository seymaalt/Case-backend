package com.example.WebServer.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String tcNo;
    private String name;
    private String surname;
    private int age;
    private String gender;
}
