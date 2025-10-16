package com.myapp.user_service.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class UserUpdateRequestDto {
	private String firstName;
    private String lastName;
    private String phoneNumber;
    private LocalDate dateOfBirth;
}
