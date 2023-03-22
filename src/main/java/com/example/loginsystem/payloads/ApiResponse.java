package com.example.loginsystem.payloads;

import lombok.Data;

@Data
public class ApiResponse {
    private boolean status;
    private String message;
}
