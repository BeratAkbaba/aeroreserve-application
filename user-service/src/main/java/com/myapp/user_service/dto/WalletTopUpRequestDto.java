package com.myapp.user_service.dto;

import java.math.BigDecimal;

import com.myapp.user_service.validation.Currency;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WalletTopUpRequestDto {

	@NotNull(message = "Yüklenecek miktar boş olamaz.")
    @Positive(message = "Yüklenecek miktar pozitif bir değer olmalıdır.")
	@DecimalMin(value = "1.00", message = "Yüklenecek miktar en az 1.00 olmalıdır.")
    private BigDecimal amount;

    @NotBlank(message = "Para birimi belirtilmelidir.")
    @Currency
    private String currency;
}
