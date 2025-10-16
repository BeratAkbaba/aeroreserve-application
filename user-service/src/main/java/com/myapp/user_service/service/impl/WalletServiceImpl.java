package com.myapp.user_service.service.impl;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myapp.user_service.client.CurrencyExchangeServiceClient;
import com.myapp.user_service.dto.WalletResponseDto;
import com.myapp.user_service.dto.WalletTopUpRequestDto;
import com.myapp.user_service.entity.UserEntity;
import com.myapp.user_service.entity.WalletEntity;
import com.myapp.user_service.exceptions.BaseException;
import com.myapp.user_service.exceptions.ErrorMessage;
import com.myapp.user_service.exceptions.MessageType;
import com.myapp.user_service.mapper.WalletMapper;
import com.myapp.user_service.repository.UserRepository;
import com.myapp.user_service.repository.WalletRepository;
import com.myapp.user_service.service.WalletService;

@Service
public class WalletServiceImpl implements WalletService{

	
	private final WalletRepository walletRepository;
	private final UserRepository userRepository;
	private final WalletMapper walletMapper;
	private final CurrencyExchangeServiceClient exchangeClient;
	
	public WalletServiceImpl(WalletRepository walletRepository, UserRepository userRepository,
			WalletMapper walletMapper, CurrencyExchangeServiceClient exchangeClient) {
		this.walletRepository = walletRepository;
		this.userRepository = userRepository;
		this.walletMapper = walletMapper;
		this.exchangeClient = exchangeClient;
	}


	@Override
	@Transactional
	public WalletResponseDto getWallet(Long userId) {
		UserEntity user = userRepository.findById(userId)
	            .orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.USER_NOT_FOUND, userId.toString())));
		WalletEntity wallet = user.getWallet();
		
		return walletMapper.toResponseDto(wallet); 
	}


	@Override
	@Transactional
	public WalletResponseDto updateWallet(Long userId, WalletTopUpRequestDto requestDto) {
		UserEntity user = userRepository.findById(userId)
	            .orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.USER_NOT_FOUND, userId.toString())));
		WalletEntity wallet = user.getWallet();
		
        BigDecimal conversionRate = exchangeClient.getRateAgainstBaseCurrency(requestDto.getCurrency());
        BigDecimal amountInBaseCurrency = requestDto.getAmount().multiply(conversionRate);

		wallet.setBalance(wallet.getBalance().add(amountInBaseCurrency));
		WalletEntity save = walletRepository.save(wallet);
		return walletMapper.toResponseDto(save);
	}

}
























