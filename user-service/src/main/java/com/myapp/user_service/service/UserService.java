package com.myapp.user_service.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.myapp.user_service.dto.EmailChangeRequestDto;
import com.myapp.user_service.dto.UserRequestDto;
import com.myapp.user_service.dto.UserResponseDto;
import com.myapp.user_service.dto.UserUpdateRequestDto;

public interface UserService {

	UserResponseDto createUser(UserRequestDto userRequestDto);
	
	UserResponseDto getUser(Long userId);
	
	Page<UserResponseDto> getAllUsers(Pageable pageable);
	
	UserResponseDto updateUser(Long userId, UserUpdateRequestDto requestDto);
	
	UserResponseDto changeEmail(Long userId, EmailChangeRequestDto requestDto);
	
	void deleteUser(Long userId);
}
