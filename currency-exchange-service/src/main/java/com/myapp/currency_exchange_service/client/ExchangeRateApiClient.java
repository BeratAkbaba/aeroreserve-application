package com.myapp.currency_exchange_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "exchange-rate-api", url = "https://v6.exchangerate-api.com")
public interface ExchangeRateApiClient {

	@GetMapping("/v6/{apiKey}/latest/{baseCurrency}")
    String getLatestRates(@PathVariable String apiKey, @PathVariable String baseCurrency);
}
