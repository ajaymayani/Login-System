package com.example.loginsystem.payloads;

import com.example.loginsystem.entities.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserDto {

    private String name;
    private Integer age;
    private String email;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private String about;
    private List<Role> roles=new ArrayList<>();
}
