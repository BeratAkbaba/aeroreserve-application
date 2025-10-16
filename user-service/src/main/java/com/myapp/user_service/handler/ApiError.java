package com.myapp.user_service.handler;


import lombok.Data;

@Data
public class ApiError<T> {

	private Integer status;
	
	private ErrorResponse<T> errorRespone;

}
