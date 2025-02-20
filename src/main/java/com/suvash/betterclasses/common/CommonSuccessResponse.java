package com.suvash.betterclasses.common;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CommonSuccessResponse<T> {
    private int status;
    private T data;

    public CommonSuccessResponse() {}

    public CommonSuccessResponse(int status, T data) {
        this.status = status;
        this.data = data;
    }
}
