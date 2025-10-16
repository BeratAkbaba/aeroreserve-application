package com.myapp.user_service.handler;

import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.myapp.user_service.exceptions.BaseException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = BaseException.class)
	public ResponseEntity<ApiError<?>> handlerBaseException(BaseException ex, WebRequest request){
		return ResponseEntity.badRequest().body(createApiError(HttpStatus.BAD_REQUEST, ex.getMessage(), request));
	}
	
	
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ResponseEntity<ApiError<Map<String, List<String>>>> handlerValidation(MethodArgumentNotValidException ex, WebRequest request){
		Map<String, List<String>> map = new HashMap<>();
		for(ObjectError objectError : ex.getBindingResult().getAllErrors()) {
			String fieldName = ((FieldError)objectError).getField();
			if(map.containsKey(fieldName)) {
				map.put(fieldName, addValue(map.get(fieldName), objectError.getDefaultMessage()));
			}else {
				map.put(fieldName, addValue(new ArrayList<>(), objectError.getDefaultMessage()));
			}
		}
		return ResponseEntity.badRequest().body(createApiError(HttpStatus.INTERNAL_SERVER_ERROR, map,  request));
	}
	
	
	@ExceptionHandler(value = NoHandlerFoundException.class)
	public ResponseEntity<ApiError<String>> handleNoHandlerFoundException(NoHandlerFoundException ex, WebRequest request){
		return ResponseEntity.badRequest().body(createApiError(HttpStatus.BAD_REQUEST, ex.getMessage(), request));
	}
	
	@ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<ApiError<String>> handleMethodNotSupported(HttpRequestMethodNotSupportedException ex, WebRequest request){
		String message = "Bu endpoint için " + ex.getMethod() + " metodu desteklenmiyor.";
		return ResponseEntity.badRequest().body(createApiError(HttpStatus.BAD_REQUEST, message, request));
	}
	
	
	
	private List<String> addValue(List<String> list, String newValue){
		list.add(newValue);
		return list;
	}
	
	public <T> ApiError<T> createApiError(HttpStatus status, T message, WebRequest request){
		ApiError<T> apiError = new ApiError<>();
		apiError.setStatus(status.value());
		ErrorResponse<T> errorRespone = new ErrorResponse<T>();
		errorRespone.setCreateTime(LocalDate.now().toString());
		errorRespone.setHostName(getHostName());
		errorRespone.setPath(request.getDescription(false).substring(4));
		errorRespone.setMessage(message);
		apiError.setErrorRespone(errorRespone);
		return apiError;
	}
	
	private String getHostName(){
		try {
			return Inet4Address.getLocalHost().getHostName();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return "";
	}
}
