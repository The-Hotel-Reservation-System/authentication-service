package com.example.authenticationservice.model.dto;

import lombok.Builder;
import lombok.Getter;

import java.math.BigInteger;

@Builder
@Getter
public class GuestDto {
  private BigInteger id;
  private String name;
  private String email;
  private String phone;
}
