package com.example.EAS.dto;

import java.util.List;

import lombok.Data;

@Data
public class UserDto{
	
	private Long id;
    
    private List<Long> areaIds;
    
    private String username;
    
    private Integer userType;
	
}
