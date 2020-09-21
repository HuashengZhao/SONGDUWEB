package com.example.EAS.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginDto {
    
    private Long id;
    
    private List<String> typeIds;
    
    private String username;
    
    private String name;
    
    private String jwt;
    
}
