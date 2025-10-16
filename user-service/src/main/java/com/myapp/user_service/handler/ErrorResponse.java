package com.myapp.user_service.handler;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse<T>{
	
	String createTime;
	
	private String hostName;
	
	private String path;
	
	private T message;
}
