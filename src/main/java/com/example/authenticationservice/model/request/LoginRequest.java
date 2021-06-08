package com.example.authenticationservice.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class LoginRequest {

  private String email;
  private String password;
}
