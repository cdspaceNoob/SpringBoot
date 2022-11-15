package com.moon.blog.dto;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseDto<T>{
	
	public ResponseDto(int value, int data2) {
		// TODO Auto-generated constructor stub
	}
	int status;
	T data;

}

