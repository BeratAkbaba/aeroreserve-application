package com.myapp.user_service.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {
	
	private Long id;
    private String firstName;
    private String lastName;
    private String email;

    private WalletResponseDto wallet;

    private List<AddressResponseDto> addresses;
    
    private LocalDateTime memberSince; 
}
