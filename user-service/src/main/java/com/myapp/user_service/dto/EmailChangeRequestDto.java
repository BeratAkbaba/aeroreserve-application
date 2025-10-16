package com.myapp.user_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EmailChangeRequestDto {

	@NotBlank(message = "E-posta alanı boş bırakılamaz.")
    @Email(message = "Lütfen geçerli bir e-posta adresi giriniz.")
    private String newEmail;
	
	@NotBlank(message = "Şifre alanı boş bırakılamaz.")
    @Size(min = 3, message = "Şifre en az 3 karakter uzunluğunda olmalıdır.")
    private String currentPassword;
}
