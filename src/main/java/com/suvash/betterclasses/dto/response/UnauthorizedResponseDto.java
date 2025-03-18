package com.suvash.betterclasses.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UnauthorizedResponseDto {
  private String message;

  public UnauthorizedResponseDto(String message) {
    this.message = message;
  }
}
