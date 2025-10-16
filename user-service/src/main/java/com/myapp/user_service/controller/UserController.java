package com.myapp.user_service.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myapp.user_service.dto.UserRequestDto;
import com.myapp.user_service.dto.UserResponseDto;
import com.myapp.user_service.dto.UserUpdateRequestDto;
import com.myapp.user_service.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
	
	@PostMapping
	public ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto userRequestDto){
		UserResponseDto user = userService.createUser(userRequestDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(user);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserResponseDto> getUser(@PathVariable Long id) {
		UserResponseDto user = userService.getUser(id);
		return ResponseEntity.ok(user);
	}
	
	@GetMapping
	public ResponseEntity<Page<UserResponseDto>> getAllUsers(Pageable pageable) {
         Page<UserResponseDto> responseDtoList = userService.getAllUsers(pageable);
         return ResponseEntity.ok(responseDtoList);
    }
	
	@PutMapping("/{id}")
	public ResponseEntity<UserResponseDto> updateUser(@PathVariable Long userId,
													@RequestBody UserUpdateRequestDto requestDto){
		UserResponseDto user = userService.updateUser(userId, requestDto);
		return ResponseEntity.ok(user);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable Long userId){
		userService.deleteUser(userId);
		return ResponseEntity.noContent().build();
	}
	
}
