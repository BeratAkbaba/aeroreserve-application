package com.myapp.user_service.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myapp.user_service.dto.EmailChangeRequestDto;
import com.myapp.user_service.dto.UserRequestDto;
import com.myapp.user_service.dto.UserResponseDto;
import com.myapp.user_service.dto.UserUpdateRequestDto;
import com.myapp.user_service.entity.UserEntity;
import com.myapp.user_service.entity.WalletEntity;
import com.myapp.user_service.exceptions.BaseException;
import com.myapp.user_service.exceptions.ErrorMessage;
import com.myapp.user_service.exceptions.MessageType;
import com.myapp.user_service.mapper.UserMapper;
import com.myapp.user_service.repository.UserRepository;
import com.myapp.user_service.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	private final UserRepository userRepository;
    private final UserMapper userMapper;
	
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }
    
	@Override
	@Transactional
	public UserResponseDto createUser(UserRequestDto userRequestDto) {
		if(userRepository.existsByEmail(userRequestDto.getEmail())) {
			throw new BaseException(new ErrorMessage(MessageType.EXISTING_EMAIL,userRequestDto.getEmail()));
		}
		
		UserEntity user = userMapper.toEntity(userRequestDto);
		
		WalletEntity newWallet = new WalletEntity();
		newWallet.setBalance(BigDecimal.ZERO);
		newWallet.setCurrency("ARP");
		user.setWallet(newWallet);
		
		if (user.getAddresses() != null) {
            user.getAddresses().forEach(address -> address.setUser(user));
        } else {
            user.setAddresses(new ArrayList<>());
        }

        UserEntity savedUser = userRepository.save(user);
        return userMapper.toResponseDto(savedUser);
	}

	@Override
	public UserResponseDto getUser(Long userId) {
		UserEntity user = userRepository.findById(userId).
			orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.USER_NOT_FOUND, userId.toString()+" nolu user")));
		return userMapper.toResponseDto(user);
	}

	@Override
	@Transactional
	public Page<UserResponseDto> getAllUsers(Pageable pageable) {
		Page<UserEntity> userPage = userRepository.findAll(pageable);
		return userPage.map(userMapper::toResponseDto);
	}

	@Override
	public UserResponseDto updateUser(Long userId, UserUpdateRequestDto requestDto) {
		
		UserEntity userToUpdate = userRepository.findById(userId)
	            .orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.USER_NOT_FOUND_UPDATE, userId.toString()+" nolu user")));

	        if (requestDto.getFirstName() != null) {
	            userToUpdate.setFirstName(requestDto.getFirstName());
	        }
	        if (requestDto.getLastName() != null) {
	            userToUpdate.setLastName(requestDto.getLastName());
	        }
	        if (requestDto.getPhoneNumber() != null) {
	            userToUpdate.setPhoneNumber(requestDto.getPhoneNumber());
	        }
	        if (requestDto.getDateOfBirth() != null) {
	            userToUpdate.setDateOfBirth(requestDto.getDateOfBirth());
	        }
	        UserEntity updatedUser = userRepository.save(userToUpdate);
	        
	        return userMapper.toResponseDto(updatedUser);
	}

	@Override
	public UserResponseDto changeEmail(Long userId, EmailChangeRequestDto requestDto) {
		UserEntity userToUpdate = userRepository.findById(userId)
	            .orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.USER_NOT_FOUND_UPDATE, userId.toString()+" nolu user")));
		
		//şifre kontrolü
		
		if(userRepository.existsByEmail(requestDto.getNewEmail())) {
			throw new BaseException(new ErrorMessage(MessageType.EXISTING_EMAIL,requestDto.getNewEmail()));
		}
		return null;
	}

	@Override
	public void deleteUser(Long userId) {
		
		UserEntity user = userRepository.findById(userId)
	            .orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.USER_NOT_FOUND, userId.toString()+" nolu user")));
		
		userRepository.delete(user);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
