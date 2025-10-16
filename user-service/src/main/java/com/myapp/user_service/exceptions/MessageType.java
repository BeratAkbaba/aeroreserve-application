package com.myapp.user_service.exceptions;

import lombok.Getter;

@Getter
public enum MessageType {

	NO_EXIST_RECORD("1001","kayıt bulunamadı"),
	EXISTING_EMAIL("1002","Bu e-posta adresi zaten kayıtlı!"),
	EMAIL_NOT_FOUND("1003","email bulunamadı"),
	TOKEN_IS_EXPIRED("1004" , "Token süresi bitmştir"),
	USERNAME_OR_PASSWORD_INVALID("1005","Username veya password bulunamadı"),
	REFRESH_TOKEN_NOT_FOUND("1006","Refresh token bulunamadı"),
	REFRESH_TOKEN_IS_EXPIRED("1007","Refresh token süresi bitmiştir"),
	USER_NOT_FOUND("1008","kullanıcı bulunamadı"),
	FILE_UPLOAD_ERROR("1009","Dosya yüklenirken hata oluştu"),
	CITY_NOT_FOUND("1010","Şehir bulunamadı"),
	DISTANCE_ERROR("1011","Mesafe hesaplanırken hata oluştu"),
	USER_NOT_FOUND_UPDATE("1012", "Güncellenecek kullanıcı bulunamadı."),
	ADDRESS_NOT_FOUND("1013","Verilen kullanıcı id ve adres id ile birlite eşleşen bir adres bulunamadı."),
	GENERAL_EXCEPTION("9999","Genel Bir Hata Oluştu");

	private String code;
	private String message;
	
	MessageType(String code, String message) {
		this.code = code;
		this.message = message;
	}
}
