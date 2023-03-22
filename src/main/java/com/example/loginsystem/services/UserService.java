package com.example.loginsystem.services;

import com.example.loginsystem.entities.User;
import com.example.loginsystem.payloads.UserDto;

import java.util.List;

public interface UserService {

    //create user
    UserDto createUser(UserDto userDto);

    //update user
    UserDto updateUser(UserDto userDto ,Integer  userId);

    //delete user
    void deleteUser(Integer userId);

    //get user by id
    UserDto getUserById(Integer userId);

    //get user by email
    UserDto getUserByEmail(String email);

    //get all user
    List<UserDto> getAllUser();
}
