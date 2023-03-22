package com.example.loginsystem.repositories;

import com.example.loginsystem.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Integer> {

    Optional<User> findByEmail(String email);

//    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
