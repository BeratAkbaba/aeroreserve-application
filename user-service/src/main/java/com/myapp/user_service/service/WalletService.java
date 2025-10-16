package com.myapp.user_service.service;

import com.myapp.user_service.dto.WalletResponseDto;
import com.myapp.user_service.dto.WalletTopUpRequestDto;

public interface WalletService {

	WalletResponseDto getWallet(Long userId);
	
	WalletResponseDto updateWallet(Long userId, WalletTopUpRequestDto requestDto);
}
