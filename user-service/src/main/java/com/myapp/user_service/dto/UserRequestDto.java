package com.myapp.user_service.dto;

import java.time.LocalDate;
import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto {

	@NotBlank(message = "İsim alanı boş bırakılamaz.")
    @Size(min = 2, max = 50, message = "İsim 2 ile 50 karakter arasında olmalıdır.")
    private String firstName;

    @NotBlank(message = "Soyisim alanı boş bırakılamaz.")
    @Size(min = 2, max = 50, message = "Soyisim 2 ile 50 karakter arasında olmalıdır.")
    private String lastName;

    @NotBlank(message = "E-posta alanı boş bırakılamaz.")
    @Email(message = "Lütfen geçerli bir e-posta adresi giriniz.")
    private String email;

    @NotBlank(message = "Şifre alanı boş bırakılamaz.")
    @Size(min = 3, message = "Şifre en az 3 karakter uzunluğunda olmalıdır.")
    private String password;

    @NotBlank(message = "Telefon numarası boş bırakılamaz.")
    @Pattern(regexp = "^(0|\\+90)?\\s?5[0-9]{2}\\s?[0-9]{3}\\s?[0-9]{2}\\s?[0-9]{2}$", 
             message = "Geçersiz telefon numarası formatı.")
    private String phoneNumber;

    @NotNull(message = "Doğum tarihi boş bırakılamaz.")
    @Past(message = "Doğum tarihi geçmiş bir tarih olmalıdır.")
    private LocalDate dateOfBirth;
    
    @Valid
    private List<AddressRequestDto> addresses;
}
