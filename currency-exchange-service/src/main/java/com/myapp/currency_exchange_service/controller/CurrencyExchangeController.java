package com.myapp.currency_exchange_service.controller;

import java.math.BigDecimal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myapp.currency_exchange_service.service.CurrencyExchangeService;

@RestController
@RequestMapping("/api/exchange")
public class CurrencyExchangeController {

	
	private final CurrencyExchangeService exchangeService;

    public CurrencyExchangeController(CurrencyExchangeService exchangeService) {
		this.exchangeService = exchangeService;
	}

	@GetMapping("/rate")
    public BigDecimal getRate(@RequestParam("from") String fromCurrency) {
        return exchangeService.getRateAgainstBaseCurrency(fromCurrency);
    }
}
