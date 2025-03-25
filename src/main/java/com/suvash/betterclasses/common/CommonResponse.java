package com.suvash.betterclasses.common;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class CommonResponse<T> {
	private int status;
	private T data;

	public CommonResponse() {}

	public CommonResponse(int status, T data) {
		this.status = status;
		this.data = data;
	}
}
