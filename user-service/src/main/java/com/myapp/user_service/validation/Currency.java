package com.myapp.user_service.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ElementType.FIELD}) // Bu anotasyonun sadece alanlara uygulanabileceğini belirtir
@Retention(RetentionPolicy.RUNTIME) // Çalışma zamanında da erişilebilir olmasını sağlar
@Constraint(validatedBy = CurrencyValidator.class) // Bu anotasyonun mantığını hangi sınıfın işleyeceğini belirtir
public @interface Currency {
	
	String message() default "Geçersiz para birimi. Sadece TL, USD, EUR kabul edilmektedir.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
