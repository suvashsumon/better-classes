package com.suvash.betterclasses.dto;

import lombok.Data;

@Data
public class RegisterDto {
  private String name;
  private String email;
  private String username;
  private String password;
}
