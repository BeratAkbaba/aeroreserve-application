package com.myapp.currency_exchange_service.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myapp.currency_exchange_service.client.ExchangeRateApiClient;

@Service
public class CurrencyExchangeService {

	private final ExchangeRateApiClient apiClient;
	private final ObjectMapper objectMapper;
	
	@Value("${exchangerateapi.api-key}")
    private String apiKey;

	@Value("${app.base-currency:TRY}") 
    private String appBaseCurrency;
	
	public CurrencyExchangeService(ExchangeRateApiClient apiClient, ObjectMapper objectMapper) {
		this.apiClient = apiClient;
		this.objectMapper = objectMapper;
	}
	
	
	
    public BigDecimal getRateAgainstBaseCurrency(String fromCurrency) {
        
        String jsonResponse = apiClient.getLatestRates(apiKey, "USD");

        try {
            JsonNode rootNode = objectMapper.readTree(jsonResponse);
            JsonNode ratesNode = rootNode.path("conversion_rates");

            BigDecimal fromRateInUsd = ratesNode.path(fromCurrency.toUpperCase()).decimalValue();
            BigDecimal baseRateInUsd = ratesNode.path(appBaseCurrency.toUpperCase()).decimalValue();   

            if (fromRateInUsd == null || baseRateInUsd == null || fromRateInUsd.compareTo(BigDecimal.ZERO) == 0) {
                throw new RuntimeException("Geçersiz veya bulunamayan para birimi: " + fromCurrency);
            }

            return baseRateInUsd.divide(fromRateInUsd, 4, RoundingMode.HALF_UP);

        } catch (JsonProcessingException e) {
            throw new RuntimeException("Döviz kuru verisi işlenemedi.", e);
        }
    }
}
