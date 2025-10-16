package com.myapp.user_service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myapp.user_service.dto.WalletResponseDto;
import com.myapp.user_service.dto.WalletTopUpRequestDto;
import com.myapp.user_service.service.WalletService;

@RestController
@RequestMapping("/users/{userId}/wallet")
public class WalletController {

	private final WalletService walletService;

	public WalletController(WalletService walletService) {
		this.walletService = walletService;
	}
	
	@GetMapping
	public ResponseEntity<WalletResponseDto> getWallet(@PathVariable Long userId) {
		WalletResponseDto wallet = walletService.getWallet(userId);
		return ResponseEntity.ok(wallet);
	}
	
	@PostMapping("/top-up")
	public ResponseEntity<WalletResponseDto> updateWallet(@PathVariable Long userId, @RequestBody WalletTopUpRequestDto requestDto) {
		WalletResponseDto wallet = walletService.updateWallet(userId, requestDto);
		return ResponseEntity.ok(wallet);
	}
}
