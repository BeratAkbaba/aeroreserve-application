package com.myapp.user_service.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myapp.user_service.dto.AddressRequestDto;
import com.myapp.user_service.dto.AddressResponseDto;
import com.myapp.user_service.entity.AddressEntity;
import com.myapp.user_service.entity.UserEntity;
import com.myapp.user_service.exceptions.BaseException;
import com.myapp.user_service.exceptions.ErrorMessage;
import com.myapp.user_service.exceptions.MessageType;
import com.myapp.user_service.mapper.AddressMapper;
import com.myapp.user_service.repository.AddressRepository;
import com.myapp.user_service.repository.UserRepository;
import com.myapp.user_service.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService{

	private final  AddressRepository addressRepository;
	private final UserRepository userRepository;
	private final  AddressMapper addressMapper;
	
	public AddressServiceImpl(AddressRepository addressRepository,UserRepository userRepository, AddressMapper addressMapper) {
		this.addressRepository = addressRepository;
		this.addressMapper = addressMapper;
		this.userRepository = userRepository;
	}

	@Override
	@Transactional
	public AddressResponseDto createAddress(Long userId, AddressRequestDto requestDto) {
		UserEntity user = userRepository.findById(userId)
	            .orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.USER_NOT_FOUND, userId.toString())));
		
		AddressEntity entity = addressMapper.toEntity(requestDto);
		entity.setUser(user);
		AddressEntity save = addressRepository.save(entity);

		return addressMapper.toResponseDto(save);
	}

	@Override
	public AddressResponseDto getAddress(Long userId, Long addressId) {
		AddressEntity address = addressRepository.findByIdAndUser_Id(addressId, userId)
	            .orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.ADDRESS_NOT_FOUND, 
	                                                 "User Id: "+userId.toString() + ",  Adres Id: "+ addressId.toString())));
		return addressMapper.toResponseDto(address);
	}

}
