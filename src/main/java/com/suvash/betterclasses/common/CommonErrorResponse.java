package com.suvash.betterclasses.common;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CommonErrorResponse<T> {
  private int status;
  private T error;

  public CommonErrorResponse() {}

  public CommonErrorResponse(int status, T error) {
    this.status = status;
    this.error = error;
  }
}
