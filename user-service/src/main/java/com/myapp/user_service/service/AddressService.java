package com.myapp.user_service.service;

import com.myapp.user_service.dto.AddressRequestDto;
import com.myapp.user_service.dto.AddressResponseDto;

public interface AddressService {

	AddressResponseDto createAddress(Long userId, AddressRequestDto requestDto);
	
	AddressResponseDto getAddress(Long userId, Long addressId);
}
