package com.suvash.betterclasses.common;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class CommonErrorResponse<T> {
	private int status;
	private T data;

	public CommonErrorResponse() {}

	public CommonErrorResponse(int status, T data) {
		this.status = status;
		this.data = data;
	}
}
