package com.myapp.user_service.validation;

import java.util.List;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CurrencyValidator implements ConstraintValidator<Currency, String>{

	private final List<String> allowedCurrencies = List.of("TL", "USD", "EUR");
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value == null) {
            return true;
        }
        
        return allowedCurrencies.contains(value.toUpperCase());
	}

}
