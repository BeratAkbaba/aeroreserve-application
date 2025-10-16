package com.myapp.user_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressRequestDto {

	@NotBlank(message = "Adres etiketi (örn: Ev, İş) boş olamaz.")
    @Size(max = 50)
    private String addressLabel;

    @NotBlank(message = "Sokak/Cadde bilgisi boş olamaz.")
    private String street;

    @NotBlank(message = "Şehir bilgisi boş olamaz.")
    private String city;

    @NotBlank(message = "Ülke bilgisi boş olamaz.")
    private String country;
}
