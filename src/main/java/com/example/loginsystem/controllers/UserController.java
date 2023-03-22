package com.example.loginsystem.controllers;

import com.example.loginsystem.entities.User;
import com.example.loginsystem.payloads.UserDto;
import com.example.loginsystem.services.UserService;
import org.hibernate.engine.spi.Resolution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUser()
    {
        List<UserDto> allUser = this.userService.getAllUser();
        return ResponseEntity.ok(allUser);
    }
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Integer userId)
    {
        UserDto user = this.userService.getUserById(userId);
        return ResponseEntity.ok(user);
    }

//    @GetMapping("/{email}")
//    public ResponseEntity<UserDto> getUserByEmail(@PathVariable String email)
//    {
//        UserDto user = this.userService.getUserByEmail(email);
//        return ResponseEntity.ok(user);
//    }



    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto,@PathVariable Integer userId)
    {
        UserDto userDto1 = this.userService.updateUser(userDto, userId);
        return ResponseEntity.ok(userDto1);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer userId)
    {
        this.userService.deleteUser(userId);
        return ResponseEntity.ok("User deleted successfully..!");
    }
}
