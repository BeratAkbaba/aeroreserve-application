package com.myapp.user_service.client;

import java.math.BigDecimal;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "currency-exchange-service")
public interface CurrencyExchangeServiceClient {
    @GetMapping("/api/exchange/rate")
    BigDecimal getRateAgainstBaseCurrency(@RequestParam String from);
}
