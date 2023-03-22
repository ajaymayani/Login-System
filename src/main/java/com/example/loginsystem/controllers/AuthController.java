package com.example.loginsystem.controllers;

import com.example.loginsystem.entities.Role;
import com.example.loginsystem.entities.User;
import com.example.loginsystem.helpers.AppConstant;
import com.example.loginsystem.payloads.ApiResponse;
import com.example.loginsystem.payloads.Credentials;
import com.example.loginsystem.payloads.JwtResponse;
import com.example.loginsystem.payloads.UserDto;
import com.example.loginsystem.repositories.RoleRepo;
import com.example.loginsystem.repositories.UserRepo;
import com.example.loginsystem.security.JwtUtils;
import com.example.loginsystem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/api/auth")
@RestController
public class AuthController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    RoleRepo roleRepo;

    @GetMapping
    public String home(){
        return "started";
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Credentials credentials)
    {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(credentials.getUsername(), credentials.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        User userDetails = (User) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getUserId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

    @PostMapping("/register")
    public ResponseEntity<?> createUser(@RequestBody UserDto userDto)
    {

        if(userRepo.existsByEmail(userDto.getEmail()))
        {
            return ResponseEntity
                    .badRequest()
                    .body(new ApiResponse(false,"Email is already in use!"));
        }

        List<Role> strRoles = userDto.getRoles();
        List<Role> roles = new ArrayList<>();

        if (strRoles.size()==0) {
            Role userRole = roleRepo.findByName(AppConstant.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role.getName()) {
                    case "admin":
                        Role adminRole = roleRepo.findByName(AppConstant.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    default:
                        Role userRole = roleRepo.findByName(AppConstant.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        userDto.setRoles(roles);

        UserDto user = this.userService.createUser(userDto);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
}
