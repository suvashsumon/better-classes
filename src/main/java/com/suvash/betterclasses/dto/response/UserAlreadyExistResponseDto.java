package com.suvash.betterclasses.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAlreadyExistResponseDto {
	private String message;

	public UserAlreadyExistResponseDto(String message) {
		this.message = message;
	}
}
