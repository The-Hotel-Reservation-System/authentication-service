package com.example.authenticationservice.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Builder
@Getter
@AllArgsConstructor
public class CreateGuestRequest {
  @NotBlank(message = "name should not be blank.")
  private String name;
  @NotBlank(message = "email should not be blank.")
  private String email;
  @NotBlank(message = "phone should not be blank.")
  private String phone;
  @NotBlank(message = "password should not be blank.")
  private String password;
}
