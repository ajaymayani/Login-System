package com.example.loginsystem.payloads;

import lombok.Data;

@Data
public class UserDto {

    private String name;
    private Integer age;
    private String email;
    private String password;
    private String about;
}
