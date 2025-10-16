package com.myapp.user_service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myapp.user_service.dto.AddressRequestDto;
import com.myapp.user_service.dto.AddressResponseDto;
import com.myapp.user_service.service.AddressService;

@RestController
@RequestMapping("/users/{userId}/addresses")
public class AddressController {

	private final AddressService addressService;

	public AddressController(AddressService addressService) {
		this.addressService = addressService;
	}
	
	@PostMapping
	public ResponseEntity<AddressResponseDto> createAddress(@PathVariable Long userId, @RequestBody AddressRequestDto requestDto){
		AddressResponseDto address = addressService.createAddress(userId, requestDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(address);
	}
	
	@GetMapping("/{addressId}")
	public ResponseEntity<AddressResponseDto> getAddress(@PathVariable Long userId,
														@PathVariable Long addressId){
		AddressResponseDto address = addressService.getAddress(userId, addressId);
		return ResponseEntity.ok(address);
	}
}
